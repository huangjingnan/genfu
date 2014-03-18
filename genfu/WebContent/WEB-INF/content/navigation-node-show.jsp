<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Navigation-node ${id}</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<td>${id}</td>
		</tr>
		<tr>
			<th>naviAction</th>
			<td>${naviAction}</td>
		</tr>
		<tr>
			<th>naviDescription</th>
			<td>${naviDescription}</td>
		</tr>
		<tr>
			<th>naviEffDate</th>
			<td>${naviEffDate}</td>
		</tr>
		<tr>
			<th>naviExpDate</th>
			<td>${naviExpDate}</td>
		</tr>
		<tr>
			<th>naviFlag</th>
			<td>${naviFlag}</td>
		</tr>
		<tr>
			<th>naviOrder</th>
			<td>${naviOrder}</td>
		</tr>
		<tr>
			<th>naviOthers</th>
			<td>${naviOthers}</td>
		</tr>
		<tr>
			<th>naviParentId</th>
			<td>${naviParentId}</td>
		</tr>
		<tr>
			<th>naviSrc</th>
			<td>${naviSrc}</td>
		</tr>
		<tr>
			<th>naviText</th>
			<td>${naviText}</td>
		</tr>
		<tr>
			<th>naviCreateDate</th>
			<td>${naviCreateDate}</td>
		</tr>
		<tr>
			<th>naviUpdateDate</th>
			<td>${naviUpdateDate}</td>
		</tr>
	</table>
	<a href="${ctx}/navigation-node">Back to Events</a>
</body>
</html>
