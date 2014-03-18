package com.genfu.arch.builder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ToggleNatureAction implements IObjectActionDelegate {

	private ISelection selection;

	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			for (Iterator it = ((IStructuredSelection) selection).iterator(); it
					.hasNext();) {
				Object element = it.next();
				IProject project = null;
				if (element instanceof IProject) {
					project = (IProject) element;
				} else if (element instanceof IAdaptable) {
					project = (IProject) ((IAdaptable) element)
							.getAdapter(IProject.class);
				}
				if (project != null) {
					toggleNature(project);
					System.out.println(project.getName());
					IJavaProject javaProject = JavaCore.create(project);

					try {
						IFolder srcFolder = javaProject.getProject().getFolder(
								"WebContent");
						if (!srcFolder.exists()) {
							srcFolder.create(true, true, null);
						}
						srcFolder = javaProject.getProject().getFolder(
								"WebContent/WEB-INF");
						if (!srcFolder.exists()) {
							srcFolder.create(true, true, null);
						}
						srcFolder = javaProject.getProject().getFolder(
								"WebContent/WEB-INF/content");
						if (!srcFolder.exists()) {
							srcFolder.create(true, true, null);
						}

					} catch (CoreException e2) {
						e2.printStackTrace();
					}

					try {

						IFile myIFile = project
								.getFile("src/META-INF/persistence.xml");
						if (myIFile != null) {
							DocumentBuilderFactory factory = DocumentBuilderFactory
									.newInstance();
							try {
								DocumentBuilder db = factory
										.newDocumentBuilder();
								System.out.println(myIFile.getFullPath()
										.toString());
								System.out.println(myIFile.getLocation());
								System.out.println(myIFile.getLocationURI()
										.toString());
								Document doc = db.parse(myIFile.getLocation()
										.toFile());
								Element elmtInfo = doc.getDocumentElement();
								NodeList nodes = elmtInfo
										.getElementsByTagName("class");
								IType tempItype;
								IType controllerType;
								String packComm = "";
								int idx = 0;

								IPackageFragmentRoot packageFragmentRoot = javaProject
										.findPackageFragmentRoot(new Path("/"
												+ javaProject.getElementName()
												+ "/src"));

								IPackageFragment packageFragment = null;
								ICompilationUnit myCompilationUnit = null;
								for (int i = 0; i < nodes.getLength(); i++) {
									Node result = nodes.item(i);
									System.out.println(result.getTextContent());

									tempItype = javaProject.findType(result
											.getTextContent());
									System.out.println(tempItype
											.getElementName());

									if ("".equals(packComm)) {
										idx = result
												.getTextContent()
												.indexOf(
														"."
																+ tempItype
																		.getElementName()) - 1;
										idx = result.getTextContent()
												.lastIndexOf(".", idx);
										packComm = result.getTextContent()
												.substring(0, idx);

										packageFragment = packageFragmentRoot
												.createPackageFragment(packComm
														+ ".controller", false,
														null);
									}

									IField[] myIFields = tempItype.getFields();
									for (int i1 = 0; i1 < myIFields.length; i1++) {
										for (int j = 0; j < myIFields[i1]
												.getAnnotations().length; j++) {
											System.out.println(myIFields[i1]
													.getAnnotations()[j]
													.getElementName());

											System.out.println(myIFields[i1]
													.getAnnotations()[j]
													.getSource());
										}
										System.out.println(myIFields[i1]
												.getElementName());
									}

									controllerType = null;
									controllerType = javaProject.findType(
											packComm + ".controller",
											tempItype.getElementName());

									if (controllerType == null) {
										StringBuffer code = new StringBuffer();

										String actionName = StringChange(tempItype
												.getElementName());
										String modelName = tempItype
												.getElementName();
										code.append("package "
												+ packComm
												+ ".controller;\nimport java.util.List;\nimport java.util.Map;import javax.interceptor.Interceptors; import javax.servlet.http.HttpServletRequest; import javax.servlet.http.HttpServletResponse; import javax.servlet.http.HttpSession;  import org.apache.struts2.convention.annotation.Result; import org.apache.struts2.convention.annotation.Results; import org.apache.struts2.interceptor.ServletRequestAware; import org.apache.struts2.interceptor.ServletResponseAware; import org.apache.struts2.interceptor.SessionAware; import org.apache.struts2.rest.DefaultHttpHeaders; import org.apache.struts2.rest.HttpHeaders;  import com.genfu.reform.model."
												+ modelName
												+ "; import com.genfu.reform.service.GenfuCommonService; import com.opensymphony.xwork2.ModelDriven; import com.opensymphony.xwork2.Preparable; import com.opensymphony.xwork2.Validateable; import com.opensymphony.xwork2.ValidationAwareSupport; import com.opensymphony.xwork2.util.logging.Logger;");
										code.append("@ParentPackage(\"genfu-rest\") @InterceptorRefs({@InterceptorRef(\"genfuAuthentication\"),");
										code.append("@InterceptorRef(params = { \"allowedTypes\",\"image/jpeg,image/gif,image/png\" }, value = \"fileUpload\"),");
										code.append("@InterceptorRef(\"restDefaultStack\") })");
										code.append("@Results({ @Result(name = \"login\", type = \"redirect\", params = { \"location\",\"/login.jsp\" }),");
										code.append("@Result(name = \"json\", type = \"json\"),");
										code.append("@Result(name = \"success\", type = \"redirectAction\", params = { 		\"actionName\", \""
												+ actionName + "\" }) })");
										code.append("public class "
												+ modelName
												+ "Controller extends ValidationAwareSupport implements 		ModelDriven<Object>, Validateable, SessionAware, ServletRequestAware, 		ServletResponseAware, ParameterAware, Preparable {");

										code.append("	/** 	 * genfu arch 	 */");
										code.append("private static final long serialVersionUID = 1L; 	private "
												+ modelName
												+ " model = new "
												+ modelName
												+ "(); 	private Long id; 	private List<"
												+ modelName
												+ "> list; 	private GenfuCommonService genfuCommonService; 	private Map<String, Object> session;private Map<String, String[]> parameters;");
										code.append("private JSONObject jsonObject; private HttpServletRequest request;private boolean verifyingOperates = false;");
										code.append("public GenfuCommonService getGenfuCommonService() {		return genfuCommonService;	}public void setGenfuCommonService(GenfuCommonService genfuCommonService) {		this.genfuCommonService = genfuCommonService;	}");
										code.append("public "
												+ modelName
												+ "Controller(GenfuCommonService theService) { 		this.genfuCommonService = theService; 	}  	");
										code.append(" 	@Override 	public void setServletResponse(HttpServletResponse arg0) {  	}");
										code.append("@Override 	public void setServletRequest(HttpServletRequest arg0) { 	}");
										code.append("	@Override 	public void setSession(Map<String, Object> arg0) {  this.session = arg0;	}  	@Override 	public void validate() {  	}  	public void setModel("
												+ modelName
												+ " model) { 		this.model = model; 	}  	public HttpHeaders show() { 		return new DefaultHttpHeaders(\"show\"); 	}");
										code.append(
												"	public HttpHeaders index() { 		if (verifyingOperates) { if (null != this.parameters.get(\"style\")) {jsonObject = genfuCommonService.searchJsonJqGrid("
														+ modelName
														+ ".class, parameters); } else {list = genfuCommonService.searchList(")
												.append(modelName)
												.append(".class,parameters);}}return new DefaultHttpHeaders(\"index\").disableCaching();}");
										code.append("public void prepareIndex() throws Exception { 		verifyingOperates = true;}");
										code.append("public String update() { 		genfuCommonService.update(model); 		addActionMessage(\"Object updated successfully\"); 		return \"success\"; 	}  	public void setId(Long id) { 		if (id != null) { 			this.model = ("
												+ modelName
												+ ") genfuCommonService.find(id, 					"
												+ modelName
												+ ".class); 		} 		this.id = id; 	}");
										code.append("	public String edit() { 		return \"edit\"; 	}  	public String editNew() { 		return \"editNew\"; 	}  	public String create() { 		genfuCommonService.save(model); 		return \"json\"; 	}  	public String deleteConfirm() { 		return \"deleteConfirm\"; 	} ");
										code.append("	public String destroy() { 		genfuCommonService.remove(model); 		addActionMessage(\"Object removed successfully\"); 		return \"deleteOver\"; 	}  	@Override 	public void prepare() throws Exception {  	}  	@Override 	public Object getModel() { 		if (jsonObject != null) {return jsonObject;}return (list != null ? list : model); 	} ");
										code.append("	@Override public void setParameters(Map<String, String[]> arg0) {parameters = arg0;}}");

										try {

											myCompilationUnit = packageFragment
													.getCompilationUnit(tempItype
															.getElementName()
															+ "Controller.java");
											if (!myCompilationUnit.exists()) {
												packageFragment
														.createCompilationUnit(
																tempItype
																		.getElementName()
																		+ "Controller.java",
																code.toString(),
																false,
																new NullProgressMonitor());

												try {
													IFile contentIndex = project
															.getFile("WebContent/WEB-INF/content/"
																	+ actionName
																			.substring(1)
																	+ "-index.jsp");
													if (!contentIndex.exists()) {
														StringBuffer indexJsp = new StringBuffer();

														indexJsp.append("<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>");
														indexJsp.append("\n<!DOCTYPE html>");
														indexJsp.append("\n<%@ taglib prefix=\"s\" uri=\"/struts-tags\"%>");
														indexJsp.append("\n<html>");
														indexJsp.append("\n<head>");
														indexJsp.append("\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
														indexJsp.append(
																"\n<title>")
																.append(modelName)
																.append("</title>");
														indexJsp.append("\n<script src=\"${ctx}/js/uploadfile/jquery.form.js\" type=\"text/javascript\"></script>");
														indexJsp.append("\n<script type=\"text/javascript\">");
														indexJsp.append(
																"\n		jQuery(\"#")
																.append(modelName)
																.append("\").jqGrid({ ");
														indexJsp.append(
																"\n		url:'${ctx}/")
																.append(actionName)
																.append(".json?style=jqGrid',");
														indexJsp.append("\n		datatype: \"json\", ");
														indexJsp.append("\n		height: 250, ");
														StringBuffer colNames = new StringBuffer();
														StringBuffer colModel = new StringBuffer();
														String fieldName = "";
														for (int i1 = 0; i1 < myIFields.length; i1++) {
															// for (int j = 0; j
															// <
															// myIFields[i1]
															// .getAnnotations().length;
															// j++) {
															// System.out.println(myIFields[i1]
															// .getAnnotations()[j]
															// .getElementName());
															//
															// System.out.println(myIFields[i1]
															// .getAnnotations()[j]
															// .getSource());
															// }
															fieldName = myIFields[i1]
																	.getElementName();
															colNames.append(
																	",'")
																	.append(fieldName)
																	.append("'");
															colModel.append(
																	"{name:'")
																	.append(fieldName)
																	.append("',index:'")
																	.append(fieldName)
																	.append("', width:80},\n");
															System.out
																	.println(myIFields[i1]
																			.getElementName());
														}
														indexJsp.append(
																"\n		colNames:[")
																.append(colNames
																		.substring(1))
																.append("],");
														indexJsp.append("\n		colModel:[");
														indexJsp.append(
																"\n		   		")
																.append(colModel
																		.substring(
																				0,
																				colModel.length() - 2));
														indexJsp.append("\n		],");
														indexJsp.append("\n		rowNum:20,");
														indexJsp.append("\n		rowList:[20,30,50],");
														indexJsp.append(
																"\n		pager: '#p")
																.append(modelName)
																.append("',");
														indexJsp.append("\n		sortname: 'id',");
														indexJsp.append("\n		viewrecords: true,");
														indexJsp.append("\n		autowidth: true,");
														indexJsp.append("\n		multiselect: true,");
														indexJsp.append(
																"\n		caption: \"")
																.append(modelName)
																.append("\" }); ");

														indexJsp.append(
																"\n		jQuery(\"#")
																.append(modelName)
																.append("\").jqGrid('navGrid','#p")
																.append(modelName)
																.append("',{edit:false,add:false,del:false},");
														indexJsp.append(
																"\n				{url:'${ctx}/")
																.append(actionName)
																.append("/0?_method=put',reloadAfterSubmit:false},");
														indexJsp.append(
																"\n				{url:'${ctx}/")
																.append(actionName)
																.append("',clearAfterAdd:true,reloadAfterSubmit:false},");
														indexJsp.append(
																"\n				{url:'${ctx}/")
																.append(actionName)
																.append("/0?_method=DELETE',reloadAfterSubmit:false},");
														indexJsp.append("\n				{multipleSearch:true, multipleGroup:true, showQuery: true});");
														indexJsp.append("\n	</script>");
														indexJsp.append("\n	<s:head />");
														indexJsp.append("\n	</head>");
														indexJsp.append("\n	<body>");
														indexJsp.append(
																"\n	<table id=\"")
																.append(modelName)
																.append("\"></table>");
														indexJsp.append(
																"\n	<div id=\"p")
																.append(modelName)
																.append("\"></div>");
														indexJsp.append("\n	</body>");
														indexJsp.append("\n	</html>");

														InputStream inputStreamJava = new ByteArrayInputStream(
																indexJsp.toString()
																		.getBytes());

														contentIndex
																.create(inputStreamJava,
																		false,
																		null);
													}
												} catch (CoreException e) {
													e.printStackTrace();
												}
											}
										} catch (JavaModelException ex) {
											ex.printStackTrace();
										}
									}

								}

							} catch (SAXException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}

							catch (ParserConfigurationException e) {
								e.printStackTrace();
							}
						}

					} catch (JavaModelException e2) {
						e2.printStackTrace();
					}

					try {
						IType myItype = javaProject.findType(
								"com.genfu.test.model", "ActorInfo");
					} catch (CoreException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	private void toggleNature(IProject project) {
		try {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();

			for (int i = 0; i < natures.length; ++i) {
				if (SampleNature.NATURE_ID.equals(natures[i])) {
					String[] newNatures = new String[natures.length - 1];
					System.arraycopy(natures, 0, newNatures, 0, i);
					System.arraycopy(natures, i + 1, newNatures, i,
							natures.length - i - 1);
					description.setNatureIds(newNatures);
					project.setDescription(description, null);
					return;
				}
			}

			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = SampleNature.NATURE_ID;
			description.setNatureIds(newNatures);
			project.setDescription(description, null);
		} catch (CoreException e) {
		}
	}

	private String StringChange(String s) {
		if (s.equals("") || s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		int len = s.length();
		char c;

		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				sb.append('-');
				c = (char) (c + 32);
			}
			sb.append(c);
		}
		return sb.toString();
	}

}
