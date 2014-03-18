<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>New Order</title>
</head>
<body>
    <s:form method="post" action="%{#request.contextPath}/order_tests">
    <table>
        <s:textfield name="clientName" label="Client"/>
        <s:textfield name="amount" label="Amount" />
        <tr>
            <td colspan="2">
                <s:submit />
            </td>
    </table>
    </s:form>    	
    <a href="<%=request.getContextPath() %>/order_tests">Back to Orders</a>
</body>
</html>
	