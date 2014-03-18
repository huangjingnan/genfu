package com.genfu.reform.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
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
import com.genfu.reform.service.GenfuCommonService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.Validateable;
import com.opensymphony.xwork2.ValidationAwareSupport;

@ParentPackage("genfu-rest")
@InterceptorRefs({
		@InterceptorRef("genfuAuthentication"),
		@InterceptorRef(params = { "allowedTypes",
				"image/jpeg,image/gif,image/png" }, value = "fileUpload"),
		@InterceptorRef("restDefaultStack") })
@Results({
		@Result(name = "login", type = "redirect", params = { "location",
				"/login.jsp" }),
		@Result(name = "json", type = "json"),
		@Result(name = "index1", type = "httpheader", params = { "headers",
				"index", "status", "303" }),
		@Result(name = "json-index", type = "json", params = {
				"excludeNullProperties", "true", "contentType",
				"application/json" }),
		@Result(name = "success", type = "redirectAction", params = {
				"actionName", "dish" }) })
public class DishController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dish model = new Dish();
	private Long id;
	private List<Dish> list;
	private JSONObject jsonObject;
	private GenfuCommonService genfuCommonService;
	private HttpServletRequest request;
	private Map<String, Object> session;
	private Map<String, String[]> parameters;
	private boolean verifyingOperates = false;
	private File fileImage;
	private String fileImageContentType;
	private String fileImageFileName;

	@Autowired
	public DishController(
			@Qualifier("dishService") GenfuCommonService theService) {
		genfuCommonService = theService;
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
		session = arg0;
	}

	@Override
	public void validate() {

	}

	public void setModel(Dish model) {
		this.model = model;
	}

	public HttpHeaders show() {
		return new DefaultHttpHeaders("show");
	}

	// public void prepareIndex() throws Exception {
	// jsonObject = genfuCommonService.validateAndRecord("dish", "index",
	// request, Dish.class, session);
	//
	// verifyingOperates = jsonObject.getBoolean("validResult");
	// }

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public HttpHeaders index() {
		jsonObject = genfuCommonService.validateAndRecord("dish", "index",
				request, Dish.class, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {

			if (this.parameters.containsKey("style")) {
				if (null != this.parameters.get("style")
						&& "jqGrid".equalsIgnoreCase(this.parameters
								.get("style")[0])) {

					jsonObject = null;
					if (null != this.parameters.get("cd_mask")) {
						try {
							String mask = URLDecoder.decode(
									parameters.get("cd_mask")[0], "UTF-8");
							Map<String, Object> paraMap = new HashMap<String, Object>();
							paraMap.put("dishNameLike", mask + "%");

							jsonObject = genfuCommonService
									.searchJsonJqGridFilter(
											"SELECT x FROM Dish x WHERE x.dishName LIKE :dishNameLike",
											paraMap, Dish.class, parameters);

						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
							jsonObject = genfuCommonService
									.searchJsonJqGridFilter(Dish.class,
											parameters);
						}
					} else {
						// jsonObject =
						// genfuCommonService.searchJsonJqGridFilter(
						// Dish.class, parameters);
						// Map<String, Object> para = new HashMap<String,
						// Object>();
						// para.put("taggings",
						// Long.parseLong(parameters.get("taggings")[0]));
						jsonObject = genfuCommonService.searchJsonNativeQuery(
								"SELECT * FROM DISHES X WHERE 1=1", null,
								Dish.class, parameters);
					}
				}
			} else {
				list = genfuCommonService.searchList(Dish.class, parameters);
			}

		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	// public void prepareUpdate() throws Exception {
	//
	// }

	public String update() {
		jsonObject = genfuCommonService.validateAndRecord("dish", "update",
				request, Dish.class, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {
			if (null != fileImage) {

				try {
					Path file = genfuCommonService
							.getGenfuPath("Dish.coverImage");
					Files.copy(fileImage.toPath(),
							file.resolve(fileImageFileName),
							StandardCopyOption.REPLACE_EXISTING);
					// file =
					// genfuCommonService.getGenfuPath("Dish.coverImage");
					// file.resolve(fileImageFileName);
					model.setCoverImage("/genfu/files/dishImage/"
							+ fileImageFileName);
					model.setDishName(URLDecoder.decode(model.getDishName(),
							"UTF-8"));
					model.setIsbn(URLDecoder.decode(model.getIsbn(), "UTF-8"));
					model.setBlurb(URLDecoder.decode(model.getBlurb(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			model.setUpdatedAt(new Date());
			genfuCommonService.update(model);
			addActionMessage("Object updated successfully");
		}
		return "json";
	}

	public void setId(Long id) {
		if (id != null) {
			model = (Dish) genfuCommonService.find(id, Dish.class);
		}
		this.id = id;
	}

	public String edit() {
		return "edit";
	}

	public String editNew() {

		return "editNew";
	}

	// public void prepareCreate() throws Exception {
	// }

	public String create() {
		jsonObject = genfuCommonService.validateAndRecord("dish", "create",
				request, Dish.class, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {
			if (null != fileImage) {

				try {

					Path file = genfuCommonService
							.getGenfuPath("Dish.coverImage");
					Files.copy(fileImage.toPath(),
							file.resolve(fileImageFileName),
							StandardCopyOption.REPLACE_EXISTING);
					// file =
					// genfuCommonService.getGenfuPath("Dish.coverImage");
					// file.resolve(fileImageFileName);
					model.setCoverImage("/genfu/files/dishImage/"
							+ fileImageFileName);
					model.setDishName(URLDecoder.decode(model.getDishName(),
							"UTF-8"));
					model.setIsbn(URLDecoder.decode(model.getIsbn(), "UTF-8"));
					model.setBlurb(URLDecoder.decode(model.getBlurb(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			genfuCommonService.save(model);
		}
		jsonObject = null;
		return "json";
	}

	public String deleteConfirm() {
		return "deleteConfirm";
	}

	// public void prepareDestroy() throws Exception {
	// }

	public String destroy() {
		jsonObject = genfuCommonService.validateAndRecord("dish", "destroy",
				request, Dish.class, session);

		verifyingOperates = jsonObject.getBoolean("validResult");
		if (verifyingOperates) {
			if (null != parameters.get("id")) {

				// Map<String, Object> tempPara = new HashMap<String, Object>();
				String ids = parameters.get("id")[0];
				// tempPara.put("orderIds", ids);

				StringBuffer execSQL = new StringBuffer();
				execSQL.append(
						"DELETE FROM TAGGINGS WHERE TAGGABLE_TYPE='Dish' AND TAGGABLE_ID IN (")
						.append(ids).append(")");
				execSQL.append("#DELETE FROM DISHES WHERE DISH_ID IN (")
						.append(ids).append(")");

				genfuCommonService.batchExcuseNativeQuery(execSQL.toString());
			}
		}
		jsonObject = new JSONObject();
		return "json";
	}

	public void prepareValidate() throws Exception {
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

	public File getFileImage() {
		return fileImage;
	}

	public void setFileImage(File fileImage) {
		this.fileImage = fileImage;
	}

	public String getFileImageContentType() {
		return fileImageContentType;
	}

	public void setFileImageContentType(String fileImageContentType) {
		this.fileImageContentType = fileImageContentType;
	}

	public String getFileImageFileName() {
		return fileImageFileName;
	}

	public void setFileImageFileName(String fileImageFileName) {
		this.fileImageFileName = fileImageFileName;
	}

}
