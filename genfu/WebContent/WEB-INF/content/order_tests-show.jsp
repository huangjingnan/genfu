<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order ${id}</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<td>${id}</td>
		</tr>
		<tr>
			<th>Client</th>
			<td>${clientName}</td>
		</tr>
		<tr>
			<th>Amount</th>
			<td>${amount}</td>
		</tr>
	</table>
	<a href="../order_tests">Back to Orders</a>
</body>
</html>
