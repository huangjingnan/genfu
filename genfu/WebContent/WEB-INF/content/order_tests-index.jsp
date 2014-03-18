<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders</title>
</head>
<body>
	<s:actionmessage />
	<table>
		<tr>
			<th>ID</th>
			<th>Client</th>
			<th>Amount</th>
			<th>Actions</th>
		</tr>
		<s:iterator value="model">
			<tr>
				<td>${id}</td>
				<td>${clientName}</td>
				<td>${amount}</td>
				<td><a href="order_tests/${id}">View</a> | <a
					href="order_tests/${id}/edit">Edit</a> | <a
					href="order_tests/${id}/deleteConfirm">Delete</a></td>
			</tr>
		</s:iterator>
	</table>
	<a href="order_tests/new">Create a new order</a>
</body>
</html>
