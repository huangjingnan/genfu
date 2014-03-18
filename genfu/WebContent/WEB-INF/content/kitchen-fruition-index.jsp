<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"
	scope="application" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%-- <script type="application/javascript"
	src="${ctx}/js/jquery/jquery-2.0.3.js"></script> --%>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.core.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.widget.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.mouse.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.draggable.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.droppable.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.sortable.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.accordion.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.menu.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.autocomplete.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.resizable.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.dialog.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.accordion.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.effect.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.effect-highlight.js"></script>

<style type="text/css">
@import url(${ctx}/css/genfu/style.css);
</style>

<title>Dish</title>
</head>
<body>
<div id="kitchen">
	<table>
		<tr>
			<th>id#</th><th>订单号#</th><th>服务员编号</th><th>商品号#</th><th>商品名</th><th>数量</th><th>更新时间</th>
		</tr>
		<s:iterator value="model">
			<tr><td>${id}#</td><td>订单号：${orderId}#</td><td>服务员编号：${staffNumber}#</td><td>商品号：${dishId}#</td><td>商品名：${orderItemName}</td><td>数量：${amount}</td><td>更新时间：${updatedAt}</td>
			</tr>
		</s:iterator>
	</table>
</div>

<script type="text/javascript">
	//<![CDATA[

	//]]>
</script>

</body>
</html>

