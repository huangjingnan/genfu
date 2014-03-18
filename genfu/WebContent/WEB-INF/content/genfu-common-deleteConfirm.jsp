<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>actor-info ${id}</title>
</head>
<body>
	<form action="${ctx}/actor-info/${id}?_method=DELETE" method="post">
		<p>Are you sure you want to delete actor-info ${id}?</p>
		<div>
			<input type="submit" value="Delete" /> <input type="button"
				value="Cancel" onclick="self.close();" />
		</div>
	</form>
	<br />
	<a href="${ctx}/actor-info">Back to Orders</a>
</body>
</html>
