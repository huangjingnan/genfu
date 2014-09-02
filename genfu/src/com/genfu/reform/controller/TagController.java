package com.genfu.reform.controller;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

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

import com.genfu.reform.model.Tag;
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("genfuAuthentication"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login", type = "redirectAction", params = {
				"actionName", "re-login?nextAction=tagging" }),
		@Result(name = "json", type = "json"),
		@Result(name = "create", type = "stream", params = { "contentType",
				"text/html", "inputName", "inputStream" }),
		@Result(name = "create2json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "tag" }) })
public class TagController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tag model = new Tag();
	private Long id;
	private List<Tag> list;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;

	// private boolean verifyingOperates;
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	// @Autowired
	// public TagController(
	// @Qualifier("genfuCommonService") GenfuCommonService theService) {
	// genfuCommonService = theService;
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
		session = arg0;
	}

	@Override
	public void validate() {

	}

	public void setModel(Tag model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {

		if (this.parameters.containsKey("style")) {
			jsonObject = genfuCommonService.searchJsonJqGridFilter(Tag.class,
					parameters);
		} else if (this.parameters.containsKey("statusCode")) {
			list = genfuCommonService.searchList(Tag.class, parameters);
		}
		return new DefaultHttpHeaders("index").disableCaching();
		// return null;
	}

	public String update() {
		genfuCommonService.update(model);
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
			// model = (Tag) genfuCommonService.find(id, Tag.class);
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
		// try {
		// inputStream = new ByteArrayInputStream(
		// "Hello World! This is a text string response from a Struts 2 Action."
		// .getBytes("UTF-8"));
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// return new DefaultHttpHeaders("show").setLocationId(model.getId());
		jsonObject = null;
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {

		jsonObject = new JSONObject();
		if (null != parameters.get("id")) {
			try {
				// Map<String, Object> tempPara = new HashMap<String,
				// Object>();
				String ids = parameters.get("id")[0];
				// tempPara.put("orderIds", ids);

				StringBuffer execSQL = new StringBuffer();
				execSQL.append("DELETE FROM TAGS WHERE TAG_ID IN (")
						.append(ids).append(")");

				genfuCommonService.batchExcuseNativeQuery(execSQL.toString());
			} catch (Exception ex) {
				jsonObject.put("errorMsg", ex.toString());
			}
			addActionMessage("Object removed successfully");
		}
		return "json";
	}

	@Override
	public Object getModel() {
		if (jsonObject != null) {
			return jsonObject;
		}
		return (list != null ? list : model);
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		parameters = arg0;
	}

}
