
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>createOver</title>
<style type="text/css">

@import url(${ctx}/js/jquery.simpledialog/jquery.simpledialog.0.1.css);
</style>
<script type="text/javascript"
	src="${ctx}/js/zTree-zTree_v3-103128e/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery.simpledialog/jquery.simpledialog.0.1.js"></script>
<script>
	$(function() {
		window.opener.searchModels();
		//self.close();
	});
</script>
</head>
<body>
You have uploaded the following file.
<hr>
File Name : ${userImageFileName} <br>
Content Type : ${userImageContentType}
	<script>
		//self.close();
	</script>
</body>
</html>