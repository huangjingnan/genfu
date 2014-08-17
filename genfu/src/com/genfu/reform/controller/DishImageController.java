package com.genfu.reform.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.genfu.reform.model.FileInfo;
import com.genfu.reform.service.GenfuCommonService;
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
				"actionName", "dish-image" }),
		@Result(name = "create", type = "json", params = { "wrapPrefix",
				"{\"files\": ", "wrapSuffix", "}" }),
		@Result(name = "destroy", type = "json", params = { "wrapPrefix",
				"{\"files\": ", "wrapSuffix", "}" }) })
public class DishImageController extends ValidationAwareSupport implements
		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware,
		ServletResponseAware, ParameterAware, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileInfo model = new FileInfo();
	private Long id;
	private List<FileInfo> files = new ArrayList<FileInfo>();
	private Collection<File> list;
	private GenfuCommonService genfuCommonService;
	private Map<String, Object> session = null;
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
		return (list != null ? list : files);
	}

	public void setModel(FileInfo model) {
		this.model = model;
	}

	public HttpHeaders show() {
		// if (this.id != 0 && this.model.getId() == null) {
		// this.model = eventService.get(this.id);
		// }
		return new DefaultHttpHeaders("show");
	}

	// @Action(interceptorRefs = @InterceptorRef("genfuAuthentication"))
	public String index() {
		// UserInfo user = (UserInfo) session
		// .get(GenfuAuthenticationInterceptor.USER_SESSION_KEY);
		// do something
		return "index";
		// return new DefaultHttpHeaders("index").disableCaching();
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
		// genfuCommonService.save(model);
		// addActionMessage("New Object created successfully");
		// Path target = FileSystems.getDefault().getPath("userImages");

		// return new DefaultHttpHeaders("close");
		// return new
		// DefaultHttpHeaders("success").setLocationId(model.getId());
		// try {
		// Files.copy(userImage.toPath(), target);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// result =
		// "{\"files\": [{\"name\": \"picture1.jpg\",\"size\": 902604,\"url\": \"http:\\/\\/example.org\\/files\\/picture1.jpg\",\"thumbnailUrl\": \"http:\\/\\/example.org\\/files\\/thumbnail\\/picture1.jpg\",\"deleteUrl\": \"http:\\/\\/example.org\\/files\\/picture1.jpg\",\"deleteType\": \"DELETE\"}]}";
		// application/x-zip-compressed
		if (null != upload
				&& "application/x-zip-compressed".equals(uploadContentType)
				&& "WebContent.zip".equals(uploadFileName)) {

			try {
				Path file = genfuCommonService.getGenfuPath("genfuUpgrade");
				Files.copy(upload.toPath(), file.resolve(uploadFileName),
						StandardCopyOption.REPLACE_EXISTING);
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
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.setId(1);
		model.setName("picture1.jpg");
		model.setSize(902604);
		model.setUrl("http://localhost:8080/genfu/files/picture1.jpg");
		model.setThumbnailUrl("http://localhost:8080/genfu/files/thumbnail/picture1.jpg");
		model.setDeleteUrl("http://localhost:8080/genfu/dish-image/1?_method=DELETE");
		model.setDeleteType("POST");
		files.add(model);
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
