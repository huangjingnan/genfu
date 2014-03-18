<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>dish ${id}</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<td>${id}</td>
		</tr>
		<tr>
			<th>dishName</th>
			<td>${dishName}</td>
		</tr>
		<tr>
			<th>updateDate</th>
			<td>${updateDate}</td>
		</tr>
		<tr>
			<th>price</th>
			<td>${price}</td>
		</tr>
	</table>
	<a href="#">Back to dishes</a>
</body>
</html>
