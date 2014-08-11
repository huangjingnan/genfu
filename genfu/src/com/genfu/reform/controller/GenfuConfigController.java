package com.genfu.reform.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
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

import com.genfu.reform.jmx.mxbeans.ConfigNetworkMXBean;
import com.genfu.reform.model.FileInfo;
import com.genfu.reform.model.GenfuConfig;
import com.genfu.reform.service.GenfuCommonService;
import com.genfu.reform.util.DES;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;
//@Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
//@InterceptorRefs({ @InterceptorRef("timer"), @InterceptorRef("genfuAuthentication"),
//		@InterceptorRef("logger"), @InterceptorRef("defaultStack") })

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("genfuAuthentication"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "genfu-config" }),
		@Result(name = "create", type = "json", params = { "wrapPrefix",
				"{\"files\": ", "wrapSuffix", "}" }),
		@Result(name = "destroy", type = "json", params = { "wrapPrefix",
				"{\"files\": ", "wrapSuffix", "}" }) })
public class GenfuConfigController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenfuConfig model = new GenfuConfig();
	private Long id;
	private List<FileInfo> files = new ArrayList<FileInfo>();
	private List<GenfuConfig> list;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session = null;
	private HttpServletRequest request;
	private boolean verifyingOperates = false;
	private JSONObject jsonObject;
	private Map<String, String[]> parameters;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

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
		this.request = arg0;
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
		return (list != null ? list : files);
	}

	public void setModel(GenfuConfig model) {
		this.model = model;
	}

	public HttpHeaders show() {
		// if (this.id != 0 && this.model.getId() == null) {
		// this.model = eventService.get(this.id);
		// }
		return new DefaultHttpHeaders("show");
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {
		jsonObject = genfuCommonService.validateAndRecord("genfu-config",
				"index", request, GenfuConfig.class, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {

			if (this.parameters.containsKey("style")) {
				if (null != this.parameters.get("style")
						&& "jqGrid".equalsIgnoreCase(this.parameters
								.get("style")[0])) {
					jsonObject = null;
					// jsonObject =
					// genfuCommonService.searchJsonJqGridFilter(
					// Dish.class, parameters);
					// Map<String, Object> para = new HashMap<String,
					// Object>();
					// para.put("taggings",
					// Long.parseLong(parameters.get("taggings")[0]));
					jsonObject = genfuCommonService
							.searchJsonNativeQuery(
									"SELECT * FROM GENFU_CONFIGS X WHERE CONFIG_ID IN (2,4,5) ",
									null, GenfuConfig.class, parameters);
				}
			} else {
				list = genfuCommonService.searchList(GenfuConfig.class,
						parameters);
			}

		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {

		// 修改，执行
		// 执行修改IP地址

		if (parameters.containsKey("jmx")) {
			synchronized (this) {
				Map<String, Object> paras = new HashMap<String, Object>();
				paras.put("configKeyP", parameters.get("jmx")[0]);
				this.model = genfuCommonService.searchList(
						"from GenfuConfig WHERE configKey = :configKeyP)",
						paras, GenfuConfig.class).get(0);

				if ("CONFIG_INTERFACES".equals(parameters.get("jmx")[0])) {
					// 正则表达式验证IP

					try {
						System.out.println("\nInitialize the environment map");
						HashMap env = new HashMap();

						// Provide the credentials required by the server to
						// successfully
						// perform user authentication
						//
						String[] credentials = new String[] { "username",
								"password" };
						env.put("jmx.remote.credentials", credentials);

						//
						// System.setProperty("javax.net.ssl.trustStore",
						// "C:\\Users\\xuzhenfu\\Downloads\\jmx_examples\\jmx_examples\\Security\\fine_grained\\config\\truststore");
						// System.setProperty("javax.net.ssl.trustStorePassword",
						// "trustword");

						// Create an RMI connector client and
						// connect it to the RMI connector server
						//
						// System.out
						// .println("\nCreate an RMI connector client and "
						// + "connect it to the RMI connector server");
						JMXServiceURL url = new JMXServiceURL(
								"service:jmx:rmi:///jndi/rmi://localhost:9999/server");
						JMXConnector jmxc = JMXConnectorFactory.connect(url,
								env);

						// Get an MBeanServerConnection
						//
						// System.out.println("\nGet an MBeanServerConnection");
						MBeanServerConnection mbsc = jmxc
								.getMBeanServerConnection();

						// Get domains from MBeanServer
						//
						// System.out.println("\nDomains:");
						String[] domains = mbsc.getDomains();
						for (int i = 0; i < domains.length; i++) {
							System.out.println("\tDomain[" + i + "] = "
									+ domains[i]);
						}

						// Create ConfigNetwork MBean
						//
						ObjectName mbeanName = new ObjectName(
								"com.genfu.agent.mxbeans:type=ConfigNetwork");
						// System.out.println("\nCreate ConfigNetwork MBean...");
						mbsc.createMBean(
								"com.genfu.agent.mxbeans.ConfigNetwork",
								mbeanName, null, null);

						// Get MBean count
						//
						// System.out.println("\nMBean count = "
						// + mbsc.getMBeanCount());

						// Get State attribute
						//
						// System.out.println("\nState = " +
						// mbsc.getAttribute(mbeanName, "State"));

						// Set State attribute
						//
						// mbsc.setAttribute(mbeanName,
						// new Attribute("State", "changed state"));

						// Get State attribute
						//
						// Another way of interacting with a given MBean is
						// through
						// a
						// dedicated proxy instead of going directly through the
						// MBean
						// server connection
						//
						ConfigNetworkMXBean proxy = JMX.newMBeanProxy(mbsc,
								mbeanName, ConfigNetworkMXBean.class);
						// System.out.println("\nState = " + proxy.getState());

						// Add notification listener on ConfigNetwork MBean
						//
						// ClientListener listener = new ClientListener();
						// System.out.println("\nAdd notification listener...");
						// mbsc.addNotificationListener(mbeanName, listener,
						// null,
						// null);

						// Invoke "reset" in ConfigNetwork MBean
						//
						// Calling "reset" makes the ConfigNetwork MBean emit a
						// notification that will be received by the registered
						// ClientListener.
						//

						// /^(([01]?\d?\d|2[0-4]\d|25[0-5])\.){3}([01]?\d?\d|2[0-4]\d|25[0-5])\/(\d{1}|[0-2]{1}\d{1}|3[0-2])$/

						// Pattern ipPat = Pattern
						// .compile("^(([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5])$");
						String[] strParams = model.getConfigValue().split("#");

						Object[] params = new Object[] { strParams[0],
								strParams[1], strParams[2] };
						// System.out
						// .println("\nInvoke cfgDebian() in ConfigNetwork MBean...");
						mbsc.invoke(
								mbeanName,
								"cfgDebian",
								params,
								new String[] { String.class.getName(),
										String.class.getName(),
										String.class.getName() });

						// Sleep for 2 seconds in order to have time to receive
						// the
						// notification before removing the notification
						// listener.
						//
						// System.out.println("\nWaiting for notification...");
						Thread.sleep(2000);

						// Remove notification listener on ConfigNetwork MBean
						//
						// System.out.println("\nRemove notification listener...");
						// mbsc.removeNotificationListener(mbeanName, listener);

						// Unregister ConfigNetwork MBean
						//
						// System.out
						// .println("\nUnregister ConfigNetwork MBean...");
						mbsc.unregisterMBean(mbeanName);

						// Close MBeanServer connection
						//
						// System.out
						// .println("\nClose the connection to the server");
						jmxc.close();
						System.out.println("\nBye! Bye!");
					} catch (Exception e) {
						e.printStackTrace();

					}
				} else if ("PREPARE_FOR_UPGRADE"
						.equals(parameters.get("jmx")[0])) {

					// 修改前，变更数据库状态，禁止再上传升级包
					if ("UPLOADED".equals(this.model.getConfigOthers())) {

						// model.setConfigOthers("PROCESS");
						// model.setConfigUpdatedAt(new Date());
						// genfuCommonService.update(model);

						try {
							System.out
									.println("\nInitialize the environment map");
							HashMap env = new HashMap();

							// Provide the credentials required by the server to
							// successfully
							// perform user authentication
							//
							String[] credentials = new String[] { "username",
									"password" };
							env.put("jmx.remote.credentials", credentials);

							//
							// System.setProperty("javax.net.ssl.trustStore",
							// "C:\\Users\\xuzhenfu\\Downloads\\jmx_examples\\jmx_examples\\Security\\fine_grained\\config\\truststore");
							// System.setProperty("javax.net.ssl.trustStorePassword",
							// "trustword");

							// Create an RMI connector client and
							// connect it to the RMI connector server
							//
							// System.out
							// .println("\nCreate an RMI connector client and "
							// + "connect it to the RMI connector server");
							JMXServiceURL url = new JMXServiceURL(
									"service:jmx:rmi:///jndi/rmi://localhost:9999/server");
							JMXConnector jmxc = JMXConnectorFactory.connect(
									url, env);

							// Get an MBeanServerConnection
							//
							// System.out
							// .println("\nGet an MBeanServerConnection");
							MBeanServerConnection mbsc = jmxc
									.getMBeanServerConnection();

							// Get domains from MBeanServer
							//
							// System.out.println("\nDomains:");
							String[] domains = mbsc.getDomains();
							for (int i = 0; i < domains.length; i++) {
								System.out.println("\tDomain[" + i + "] = "
										+ domains[i]);
							}

							// Create ConfigNetwork MBean
							//
							ObjectName mbeanName = new ObjectName(
									"com.genfu.agent.mxbeans:type=ConfigNetwork");
							// System.out
							// .println("\nCreate ConfigNetwork MBean...");
							try {
								mbsc.unregisterMBean(mbeanName);
							} catch (Exception e) {
								// e.printStackTrace();
							}
							mbsc.createMBean(
									"com.genfu.agent.mxbeans.ConfigNetwork",
									mbeanName, null, null);

							// Get MBean count
							//
							// System.out.println("\nMBean count = "
							// + mbsc.getMBeanCount());

							// Get State attribute
							//
							// System.out.println("\nState = " +
							// mbsc.getAttribute(mbeanName, "State"));

							// Set State attribute
							//
							// mbsc.setAttribute(mbeanName,
							// new Attribute("State", "changed state"));

							// Get State attribute
							//
							// Another way of interacting with a given MBean is
							// through
							// a
							// dedicated proxy instead of going directly through
							// the
							// MBean
							// server connection
							//
							ConfigNetworkMXBean proxy = JMX.newMBeanProxy(mbsc,
									mbeanName, ConfigNetworkMXBean.class);
							// System.out.println("\nState = " +
							// proxy.getState());

							// Add notification listener on ConfigNetwork MBean
							//
							// ClientListener listener = new ClientListener();
							// System.out.println("\nAdd notification listener...");
							// mbsc.addNotificationListener(mbeanName, listener,
							// null,
							// null);

							// Invoke "reset" in ConfigNetwork MBean
							//
							// Calling "reset" makes the ConfigNetwork MBean
							// emit a
							// notification that will be received by the
							// registered
							// ClientListener.
							//

							// /^(([01]?\d?\d|2[0-4]\d|25[0-5])\.){3}([01]?\d?\d|2[0-4]\d|25[0-5])\/(\d{1}|[0-2]{1}\d{1}|3[0-2])$/

							// Pattern ipPat = Pattern
							// .compile("^(([01]?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}(\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5])$");
							String[] strParams = model.getConfigValue().split(
									"#");
							DES desE = new DES(strParams[2]);

							Object[] params = new Object[] {
									desE.getDesString(strParams[0]),
									strParams[1], strParams[2] };
							// System.out
							// .println("\nInvoke cfgUpgrade() in ConfigNetwork MBean...");
							// System.out.println("************" +
							// strParams[0]);
							mbsc.invoke(mbeanName, "cfgUpgrade", params,
									new String[] { String.class.getName(),
											String.class.getName(),
											String.class.getName() });

							// Sleep for 2 seconds in order to have time to
							// receive
							// the
							// notification before removing the notification
							// listener.
							//
							// System.out.println("\nWaiting for notification...");
							Thread.sleep(2000);

							// Remove notification listener on ConfigNetwork
							// MBean
							//
							// System.out.println("\nRemove notification listener...");
							// mbsc.removeNotificationListener(mbeanName,
							// listener);

							// Unregister ConfigNetwork MBean
							//
							// System.out
							// .println("\nUnregister ConfigNetwork MBean...");
							mbsc.unregisterMBean(mbeanName);

							// Close MBeanServer connection
							//
							// System.out
							// .println("\nClose the connection to the server");
							jmxc.close();
							// System.out.println("\nBye! Bye!");
						} catch (Exception e) {
							e.printStackTrace();

						}
						model.setConfigOthers("NONE");
						model.setConfigUpdatedAt(new Date());
						genfuCommonService.update(model);
					}
				}

			}

		} else {
			model.setConfigUpdatedAt(new Date());
			model.setConfigValue(parameters.get("configValue")[0]);
			genfuCommonService.update(model);
			addActionMessage("Object updated successfully");
		}
		return "json";
	}

	public void setId(Long id) {
		if (id != null && id > 0) {
			this.model = (GenfuConfig) genfuCommonService.find(id,
					GenfuConfig.class);
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

		synchronized (this) {
			this.model = (GenfuConfig) genfuCommonService.find(
					Long.parseLong("4"), GenfuConfig.class);
			if (null != upload) {

				// NONE=等待上传；UPLOADED=已经上传；PROCESS=正在升级；

				try {

					// 验证文件名，longth，解密后
					DES desE = new DES(model.getConfigValue().split("#")[1]);
					String strFileName = desE.getDesString(uploadFileName);

					// System.out.println(strFileName.substring(11,
					// strFileName.indexOf(".")));// size
					// System.out.println(upload.lastModified());
					// System.out.println(upload.length());
					if (Math.abs(upload.length()
							- Long.parseLong((strFileName.substring(11,
									strFileName.indexOf("."))))) < 10) {
						Path file = genfuCommonService
								.getGenfuPath("genfuUpgrade");
						Files.copy(upload.toPath(),
								file.resolve("WebContent.zip"),
								StandardCopyOption.REPLACE_EXISTING);
						model.setConfigOthers("UPLOADED");
						model.setConfigValue("升级包准备就绪，等待执行升级。");
					} else {
						model.setConfigOthers("NONE");
						model.setConfigValue("升级包不正常，请核实其来源");
					}

					// file =
					// genfuCommonService.getGenfuPath("Dish.coverImage");
					// file.resolve(fileImageFileName);
					// model.setCoverImage("/genfu/files/dishImage/"
					// + fileImageFileName);
					// model.setDishName(URLDecoder.decode(model.getDishName(),
					// "UTF-8"));
					// model.setIsbn(URLDecoder.decode(model.getIsbn(),
					// "UTF-8"));
					// model.setBlurb(URLDecoder.decode(model.getBlurb(),
					// "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					model.setConfigOthers("NONE");
					model.setConfigValue("升级包上传出错，请重新上传。");
					e.printStackTrace();
				} catch (IOException e) {
					model.setConfigOthers("NONE");
					model.setConfigValue("升级包上传出错，请重新上传。");
					e.printStackTrace();
				} catch (Exception e) {
					model.setConfigOthers("NONE");
					model.setConfigValue("升级包上传出错，请重新上传。");
					e.printStackTrace();
				}
			} else {
				model.setConfigOthers("NONE");
				model.setConfigValue("没有上传的文件！");
			}
			model.setConfigUpdatedAt(new Date());
			genfuCommonService.update(model);

			FileInfo fInfo = new FileInfo();
			fInfo.setId(0);
			fInfo.setName("picture1.jpg");
			fInfo.setSize(902604);
			fInfo.setUrl("http://localhost:8080/genfu/files/picture1.jpg");
			fInfo.setThumbnailUrl("http://localhost:8080/genfu/files/thumbnail/picture1.jpg");
			fInfo.setDeleteUrl("http://localhost:8080/genfu/file/1?_method=DELETE");
			fInfo.setDeleteType("POST");
			files.add(fInfo);
			return "create";
		}
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		// genfuCommonService.remove(model);
		addActionMessage("Object removed successfully");
		files.clear();
		return "destroy";
	}

	@Override
	public void prepare() throws Exception {
	}

	public List<FileInfo> getFiles() {
		return files;
	}

	public void setFiles(List<FileInfo> files) {
		this.files = files;
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		parameters = arg0;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

}
