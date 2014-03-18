<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Account</title>
</head>
<body>
	<s:iterator var="tempOrder" value="model">
		<div class="group">
		<h3>${tempOrder.id}#-${tempOrder.orderName}-${tempOrder.getTotal()}</h3>
		<%-- <div>
			<p>
			</p>
			<ol id="order_${id}">
			<s:set name="countAmount" value="0"/>
			<s:iterator var="item" value="orderItems">
				<s:set name="countAmount" value="#countAmount+#item.amount"/>
				<li>${item.dishId}#&nbsp;|&nbsp;${item.orderItemName}&nbsp;|&nbsp;${item.status}&nbsp;|&nbsp;￥${item.price}&nbsp;|&nbsp;${item.amount}&nbsp;|&nbsp;￥<em>${item.price*item.amount}</em>&nbsp;|&nbsp;${item.createdAt}</li>
			</s:iterator>
			</ol>
			<em>Total: <s:property value="#countAmount"/>份,￥</em><em id="em_order_${id}">0.00</em>
			<script type="text/javascript">
				//<![CDATA[
				jQuery(function() {
					var tempSum = 0.00;
					jQuery("#order_${id}").find('li em').each(function(i){
						tempSum = tempSum + (jQuery(this).text() - 0.00);
					});
					
					jQuery("#em_order_${id}").text(parseFloat(tempSum).toFixed(2));
				});
				//]]>
			</script>
		</div> --%>
		</div>
	</s:iterator>
	<s:form method="post" action="%{#ctx}/account">
		<s:submit />
	</s:form>
	<a href="<%=request.getContextPath()%>/order_tests">Back to Orders</a>
</body>
</html>
