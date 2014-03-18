package com.genfu.reform.util;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.PagedResultsControl;
import javax.naming.ldap.PagedResultsResponseControl;

/**
 * Shows how a paged search can be performed using the PagedResultsControl API
 */

class PagedSearch {

	public static void main(String[] args) {

		Hashtable<String, Object> env = new Hashtable<String, Object>(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");

		/* Specify host and port to use for directory service */
		// ldaps\://172.16.74.142\:636

		// ldap://localhost:389/ou=People,o=JNDITutorial
		env.put(Context.PROVIDER_URL, "ldaps://192.168.56.102:636");
		// set the authentication mode
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		// set user of AD
		env.put(Context.SECURITY_PRINCIPAL,
				"cn=administrator,cn=users,dc=genfuapp,dc=com");
		// set password of user
		env.put(Context.SECURITY_CREDENTIALS, "112A232212A@!");
		env.put(Context.SECURITY_PROTOCOL, "ssl");
		try {
			LdapContext ctx = new InitialLdapContext(env, null);

			// Activate paged results
			int pageSize = 500;
			byte[] cookie = null;
			ctx.setRequestControls(new Control[] { new PagedResultsControl(
					pageSize, Control.NONCRITICAL) });
			int total = 0;

			ModificationItem[] mods = new ModificationItem[1];
			byte[] newUnicodePassword = "ZTJoLzJEYVQvRnBCSkdRajhFQ2pSRnZWK0dGd3lJMFo="
					.getBytes("UTF-16LE");
			mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("unicodePwd", newUnicodePassword));

			do {
				/* perform the search */
				NamingEnumeration<SearchResult> results = ctx.search(
						"cn=users,dc=genfuapp,dc=com",
						"(&(objectclass=user) (sn=xuzhenfu))",
						new SearchControls());

				/* for each entry print out name + all attrs and values */
				while (results != null && results.hasMore()) {
					SearchResult entry = (SearchResult) results.next();
					System.out.println(entry.getName());
					System.out.println(entry.getAttributes()
							.get("sAMAccountName").get());

					ctx.modifyAttributes(
							"cn="
									+ entry.getAttributes()
											.get("sAMAccountName").get()
									+ ",dc=genfuapp,dc=com", mods);
				}

				// Examine the paged results control response
				Control[] controls = ctx.getResponseControls();
				if (controls != null) {
					for (int i = 0; i < controls.length; i++) {
						if (controls[i] instanceof PagedResultsResponseControl) {
							PagedResultsResponseControl prrc = (PagedResultsResponseControl) controls[i];
							total = prrc.getResultSize();
							if (total != 0) {
								System.out
										.println("***************** END-OF-PAGE "
												+ "(total : "
												+ total
												+ ") *****************\n");
							} else {
								System.out
										.println("***************** END-OF-PAGE "
												+ "(total: unknown) ***************\n");
							}
							cookie = prrc.getCookie();
						}
					}
				} else {
					System.out.println("No controls were sent from the server");
				}
				// Re-activate paged results
				ctx.setRequestControls(new Control[] { new PagedResultsControl(
						pageSize, cookie, Control.CRITICAL) });

			} while (cookie != null);
			// System.out.println("total=" + total);
			ctx.close();

		} catch (NamingException e) {
			System.err.println("PagedSearch failed.");
			e.printStackTrace();
		} catch (IOException ie) {
			System.err.println("PagedSearch failed.");
			ie.printStackTrace();
		}
	}
}
