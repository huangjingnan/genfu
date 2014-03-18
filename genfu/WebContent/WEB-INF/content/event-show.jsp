<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Event ${id}</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<td>${id}</td>
		</tr>
		<tr>
			<th>Title</th>
			<td>${title}</td>
		</tr>
		<tr>
			<th>Date</th>
			<td>${date}</td>
		</tr>
	</table>
	<a href="${ctx}/event">Back to Events</a>
</body>
</html>
