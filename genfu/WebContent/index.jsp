
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"
	scope="application" />



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>


</head>

<frameset id="indexFrameset" rows="81,24,*"
	framespacing="0" frameborder="no" border="0">

	<frame name="BannerFrame" title="Banner"
		src='/genfu/architecture/banner' marginwidth="0" marginheight="0"
		frameborder="0" scrolling="no">
	<frame name="HelpToolbarFrame"
		title="Layout frame: Main Help View Toolbar"
		src='/genfu/architecture/help-toolbar' frameborder="0"
		marginheight="0" marginwidth="0" scrolling="no">
	<frame name="HelpFrame" title="Layout frame: Main Help View"
		src='/genfu/architecture/main-content' frameborder="0"
		marginheight="0" marginwidth="0">
</frameset>

</html>


<%
	//response.sendRedirect("event");
%>
