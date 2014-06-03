package com.genfu.reform.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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

import com.genfu.reform.model.Dish;
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
					jsonObject = genfuCommonService.searchJsonNativeQuery(
							"SELECT * FROM GENFU_CONFIGS X WHERE CONFIG_ID IN (2,4) ", null,
							GenfuConfig.class, parameters);
				}
			} else {
				list = genfuCommonService.searchList(GenfuConfig.class,
						parameters);
			}

		}
		return new DefaultHttpHeaders("index").disableCaching();
	}

	public String update() {
		genfuCommonService.update(model);
		addActionMessage("Object updated successfully");
		return "success";
	}

	public void setId(Long id) {
		if (id != null) {
			// this.model = (com.genfu.reform.model.File)
			// genfuCommonService.find(
			// id, com.genfu.reform.model.File.class);
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

		this.model = (GenfuConfig) genfuCommonService.find(Long.parseLong("4"),
				GenfuConfig.class);
		if (null != upload) {

			try {

				// 验证文件名，longth，解密后
				DES desE = new DES("huge-stream");
				String strFileName = desE.getDesString(uploadFileName);

				// System.out.println(strFileName.substring(11,
				// strFileName.indexOf(".")));// size
				// System.out.println(upload.lastModified());
				// System.out.println(upload.length());
				if (upload.length() == Long.parseLong((strFileName.substring(
						11, strFileName.indexOf("."))))) {
					Path file = genfuCommonService.getGenfuPath("genfuUpgrade");
					Files.copy(upload.toPath(), file.resolve("WebContent.zip"),
							StandardCopyOption.REPLACE_EXISTING);
					model.setConfigValue("升级包准备就绪，等待执行升级。");
				} else {
					model.setConfigValue("升级包不正常，请核实其来源");
				}

				// file =
				// genfuCommonService.getGenfuPath("Dish.coverImage");
				// file.resolve(fileImageFileName);
				// model.setCoverImage("/genfu/files/dishImage/"
				// + fileImageFileName);
				// model.setDishName(URLDecoder.decode(model.getDishName(),
				// "UTF-8"));
				// model.setIsbn(URLDecoder.decode(model.getIsbn(), "UTF-8"));
				// model.setBlurb(URLDecoder.decode(model.getBlurb(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				model.setConfigValue("升级包上传出错，请重新上传。");
				e.printStackTrace();
			} catch (IOException e) {
				model.setConfigValue("升级包上传出错，请重新上传。");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			model.setConfigValue("升级包不正常，请核实其来源");
		}
		model.setConfigUpdatedAt(new Date());
		genfuCommonService.save(model);
		return "create";
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
