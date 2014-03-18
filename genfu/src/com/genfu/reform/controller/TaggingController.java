package com.genfu.reform.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.genfu.reform.model.Dish;
import com.genfu.reform.model.Tag;
import com.genfu.reform.model.Tagging;
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({ @InterceptorRef("genfuAuthentication"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login_old", type = "redirectAction", params = {
				"actionName", "re-login?nextAction=tagging" }),
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "create", type = "stream", params = { "contentType",
				"text/html", "inputName", "inputStream" }),
		@Result(name = "create2json", type = "json"),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "tagging" }) })
public class TaggingController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tagging model = new Tagging();
	private Long id;
	private List<Dish> dishList;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private boolean verifyingOperates = false;

	// private boolean verifyingOperates;
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	@Autowired
	public TaggingController(
			@Qualifier("genfuCommonService") GenfuCommonService theService) {
		genfuCommonService = theService;
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

	public void setModel(Tagging model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {

		// if (this.parameters.containsKey("style")) {
		// if (null != this.parameters.get("style")
		// && "jqGrid".equalsIgnoreCase(this.parameters
		// .get("style")[0])) {
		// jsonObject = genfuCommonService.searchJsonJqGrid(Tagging.class,
		// parameters);
		// }
		// } else if (this.parameters.containsKey("statusCode")) {
		// list = genfuCommonService.searchList(Tagging.class, parameters);
		// }
		return new DefaultHttpHeaders("index").disableCaching();
		// return null;
	}

	public String update() {
		// genfuCommonService.update(model);
		jsonObject = new JSONObject();
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
			// model = (Tagging) genfuCommonService.find(id, Tagging.class);
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
		jsonObject = genfuCommonService.validateOperates("", "", "tagging",
				"create", null, Dish.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		// genfuCommonService.save(model);
		if (parameters.containsKey("dishIds[]")
				&& parameters.containsKey("tagIds[]")) {

			String[] dishIds = parameters.get("dishIds[]");
			String[] tagIds = parameters.get("tagIds[]");

			if (dishIds.length > 0 && tagIds.length > 0) {
				StringBuffer strBuffJPQL = new StringBuffer();
				// String taggable = "";
				int i = 0, j = 0;
				for (i = 0; i < dishIds.length; i++) {
					strBuffJPQL.append("," + dishIds[i]);
				}

				strBuffJPQL.delete(0, 1);
				// taggable = strBuffJPQL.toString();
				strBuffJPQL.insert(0, "from Tagging WHERE TAGGABLE_ID IN (");
				strBuffJPQL.append(") ORDER BY TAGGABLE_ID,TAG_ID");
				List<Tagging> tagginglist = genfuCommonService.searchList(
						strBuffJPQL.toString(), null, Tagging.class);
				Date date = new Date();
				List<Tagging> addList = new ArrayList<Tagging>();
				if (tagginglist.size() > 0) {
					long taggableId = 0, tagId = 0;
					boolean notFound = false;
					for (i = 0; i < dishIds.length; i++) {
						for (j = 0; j < tagIds.length; j++) {
							notFound = true;
							taggableId = Long.parseLong(dishIds[i]);
							tagId = Long.parseLong(tagIds[j]);
							for (Tagging tempTagging : tagginglist) {
								if (taggableId == tempTagging.getTaggableId()
										&& tagId == tempTagging.getTag()
												.getId()) {
									notFound = false;
									tagginglist.remove(tempTagging);
									break;
								}
							}
							if (notFound) {

								addList.add(new Tagging(0, taggableId, "Dish",
										date, new Tag(tagId, "0")));
							}
						}
					}
					// for (Tagging tempTagging : list) {
					// for (j = 0; j < tagIds.length; j++) {
					// }
					// tempTagging.getTaggableId();
					// }
				} else {
					for (i = 0; i < dishIds.length; i++) {
						for (j = 0; j < tagIds.length; j++) {
							addList.add(new Tagging(0, Long
									.parseLong(dishIds[i]), "Dish", date,
									new Tag(Long.parseLong(tagIds[j]), "0")));
						}
					}
				}
				genfuCommonService.save(addList);

				// strBuffJPQL = new StringBuffer();
				// strBuffJPQL.append("from Dish WHERE DISH_ID IN (");
				// strBuffJPQL.append(taggable);
				// strBuffJPQL.append(")");
				// dishList = genfuCommonService.searchList(
				// strBuffJPQL.toString(), null, Dish.class);
			}

		}
		// try {
		// inputStream = new ByteArrayInputStream(
		// "Hello World! This is a text string response from a Struts 2 Action."
		// .getBytes("UTF-8"));
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		// return new DefaultHttpHeaders("show").setLocationId(model.getId());
		jsonObject = new JSONObject();
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	public String destroy() {
		jsonObject = genfuCommonService.validateOperates("", "", "tagging",
				"destroy", null, Dish.class, parameters, session);

		verifyingOperates = jsonObject.getBoolean("validResult");

		if (verifyingOperates) {
			StringBuffer strBuffJPQL;
			// String taggable = "";
			if (parameters.containsKey("dishIds[]")
					&& parameters.containsKey("tagIds[]")) {

				String dishIds[] = parameters.get("dishIds[]");
				String tagIds[] = parameters.get("tagIds[]");

				if (dishIds.length > 0 && tagIds.length > 0) {
					strBuffJPQL = new StringBuffer();

					for (int i = 0; i < dishIds.length; i++) {
						strBuffJPQL.append("," + dishIds[i]);
					}

					strBuffJPQL.delete(0, 1);
					// taggable = strBuffJPQL.toString();
					strBuffJPQL
							.insert(0,
									"DELETE FROM TAGGINGS WHERE TAGGABLE_TYPE = 'Dish' AND TAGGABLE_ID IN (");
					strBuffJPQL.append(") AND TAG_ID IN (");

					for (int i = 0; i < tagIds.length; i++) {
						if (i > 0) {
							strBuffJPQL.append(",");
						}
						strBuffJPQL.append(tagIds[i]);
					}

					strBuffJPQL.append(")");

					genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
							.toString());

				}
			} else if (parameters.containsKey("dishIds[]")
					&& !parameters.containsKey("tagIds[]")) {

				String dishIds[] = parameters.get("dishIds[]");

				if (dishIds.length > 0) {
					strBuffJPQL = new StringBuffer();

					for (int i = 0; i < dishIds.length; i++) {
						strBuffJPQL.append("," + dishIds[i]);
					}

					strBuffJPQL.delete(0, 1);
					// taggable = strBuffJPQL.toString();
					strBuffJPQL
							.insert(0,
									"DELETE FROM TAGGINGS WHERE TAGGABLE_TYPE = 'Dish' AND TAGGABLE_ID IN (");
					strBuffJPQL.append(")");
					genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
							.toString());
				}

			} else if (!parameters.containsKey("dishIds[]")
					&& parameters.containsKey("tagIds[]")) {

				String tagIds[] = parameters.get("tagIds[]");

				if (tagIds.length > 0) {
					strBuffJPQL = new StringBuffer();

					for (int i = 0; i < tagIds.length; i++) {
						strBuffJPQL.append("," + tagIds[i]);
					}

					strBuffJPQL.delete(0, 1);
					strBuffJPQL
							.insert(0,
									"DELETE FROM TAGGINGS WHERE TAGGABLE_TYPE = 'Dish' AND TAG_ID IN (");
					strBuffJPQL.append(")");
					genfuCommonService.batchDeleteByNativeQuery(strBuffJPQL
							.toString());
				}

			}
			// strBuffJPQL = new StringBuffer();
			// strBuffJPQL.append("from Dish WHERE DISH_ID IN (");
			// strBuffJPQL.append(taggable);
			// strBuffJPQL.append(")");
			// dishList = genfuCommonService.searchList(
			// strBuffJPQL.toString(), null, Dish.class);

			// genfuCommonService.remove(model);
			addActionMessage("Object removed successfully");
		}
		jsonObject = new JSONObject();
		return "json";
	}

	@Override
	public Object getModel() {
		if (jsonObject != null) {
			return jsonObject;
		}
		return (dishList != null ? dishList : model);
	}

	@Override
	public void prepare() throws Exception {

	}

	@Override
	public void setParameters(Map<String, String[]> arg0) {
		parameters = arg0;
	}

}
