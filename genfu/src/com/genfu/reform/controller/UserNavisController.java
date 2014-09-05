package com.genfu.reform.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.genfu.reform.model.NavigationNode;
import com.genfu.reform.model.RoleInfo;
import com.genfu.reform.model.UserInfo;
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("genfuAuthentication"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "listNavi", type = "stream", params = { "contentType",
				"text/plain", "inputName", "inputStream" }),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "user-navis" }) })
public class UserNavisController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserInfo model = new UserInfo();//
	private Long id;
	private Collection<UserInfo> list;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private InputStream inputStream;

	// public UserNavisController(GenfuCommonService theService) {
	// this.genfuCommonService = theService;
	// }

	public GenfuCommonService getGenfuCommonService() {
		return genfuCommonService;
	}

	public void setGenfuCommonService(GenfuCommonService genfuCommonService) {
		this.genfuCommonService = genfuCommonService;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	@Override
	public void validate() {

	}

	@Override
	public Object getModel() {
		if (jsonObject != null) {
			return jsonObject;
		}
		return (list != null ? list : model);
	}

	public void setModel(UserInfo model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	public String index() {
		jsonObject = (JSONObject) session
				.get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
		this.model = (UserInfo) genfuCommonService.find(
				jsonObject.getLong("userId"), UserInfo.class);
		Set<NavigationNode> navis = new HashSet<NavigationNode>();

		StringBuffer naviStr = new StringBuffer();
		if (this.model.getRoleInfos().size() > 0) {
			Date now = new Date();
			Iterator<RoleInfo> itRoleInfo = this.model.getRoleInfos()
					.iterator();
			while (itRoleInfo.hasNext()) {
				RoleInfo tempRI = itRoleInfo.next();
				if (now.after(tempRI.getRoleEffDate())
						&& now.before(tempRI.getRoleExpDate())) {
					navis.addAll(tempRI.getNavigationNodes());
				}
			}

			Map<String, Object> tempPara = new Hashtable<String, Object>();
			List<Long> naviIds = new ArrayList<Long>();
			for (NavigationNode tempNn : navis) {

				if (now.before(tempNn.getNaviExpDate())
						&& now.after(tempNn.getNaviEffDate())) {
					if ("001".equalsIgnoreCase(tempNn.getNaviFlag())) {
						naviIds.add(tempNn.getId());
					}
				}
			}
			if (naviIds.size() > 0) {
				// tempPara.put("_naviFlag", "001");x.naviFlag = :_naviFlag
				// AND
				tempPara.put("naviIds", naviIds);

				List<NavigationNode> tempNavis = genfuCommonService
						.searchList(
								"SELECT x FROM NavigationNode x WHERE x.id IN(:naviIds) ORDER BY x.naviOrder ASC",
								tempPara, NavigationNode.class);

				long level = tempNavis.get(0).getLevel();
				naviStr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><rows><page>1</page><total>1</total><records>1</records>");

				for (NavigationNode tempNn : tempNavis) {

					if (now.before(tempNn.getNaviExpDate())
							&& now.after(tempNn.getNaviEffDate())) {

						naviStr.append("<row><cell>");
						naviStr.append(tempNn.getId());
						naviStr.append("</cell><cell>");
						naviStr.append(tempNn.getNaviText());
						naviStr.append("</cell><cell>");
						naviStr.append(tempNn.getNaviSrc());
						naviStr.append("</cell><cell>");
						naviStr.append(tempNn.getLevel() - level);
						naviStr.append("</cell><cell>");
						naviStr.append(tempNn.getNaviParentId());
						naviStr.append("</cell><cell>");
						// 计算机运算加法比减法快
						if (tempNn.getRgt() > tempNn.getLft() + 1) {
							naviStr.append("false");
						} else {
							naviStr.append("true");
						}
						naviStr.append("</cell><cell>false</cell></row>");
					}
				}
				naviStr.append("</rows>");
			}
		}
		try {
			inputStream = new ByteArrayInputStream(naviStr.toString().getBytes(
					"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// return new DefaultHttpHeaders("index").disableCaching();
		return "listNavi";
	}

	public String update() {
		genfuCommonService.update(model);
		addActionMessage("Object updated successfully");
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
			this.model = (UserInfo) genfuCommonService.find(id, UserInfo.class);
		}
		this.id = id;
	}

	public String edit() {
		return "edit";
	}

	public String editNew() {
		return "editNew";
	}

	public String create() {
		genfuCommonService.save(model);
		addActionMessage("New Object created successfully");
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		genfuCommonService.remove(model);
		addActionMessage("Object removed successfully");
		return "json";
	}

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		this.parameters = arg0;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

}
