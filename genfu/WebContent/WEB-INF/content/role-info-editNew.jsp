
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Event</title>
<jsp:include page="/include/editNewInclude.jsp" flush="true" />

<script type="application/javascript">
	var setting = {
		
		check : {
			enable : true
		},
		data : {
			key: {
				children: "children",
				name: "naviText",
				title: "naviText",
				url: "url"
			},
			simpleData : {
				enable : true,
				pIdKey: "naviParentId",
				rootPId: null
			}
		}
	};
	
	var zNodes = <s:property value="listNaviJson" default="[]" escapeHtml="false" escapeJavaScript="false" />;
	var code;

	function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"), py = $("#py").attr(
				"checked") ? "p" : "", sy = $("#sy").attr("checked") ? "s" : "", pn = $(
				"#pn").attr("checked") ? "p" : "", sn = $("#sn")
				.attr("checked") ? "s" : "", type = {
			"Y" : py + sy,
			"N" : pn + sn
		};
		zTree.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
		showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "'
				+ type.N + '" };');
	}
	function showCode(str) {
		if (!code)
			code = $("#code");
		code.empty();
		code.append("<li>" + str + "</li>$tag");
	}
	$(document).ready(function(){
		$("#roleExpDate,#roleEffDate").datepicker({
			changeMonth : true,
			changeYear : true,
			showOtherMonths : true,
			selectOtherMonths : true
		});
		$("#roleExpDate,#roleEffDate").datepicker("option", "showAnim", "show");
		$.datepicker.setDefaults($.datepicker.regional["zh-CN"]);

		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		setCheck();
		$("#py").bind("change", setCheck);
		$("#sy").bind("change", setCheck);
		$("#pn").bind("change", setCheck);
		$("#sn").bind("change", setCheck);
	});
</script>
</head>
<body>
	<s:actionmessage />
	<div class="demo">
		<s:form method="post" action="%{#ctx}/role-info">
			<table class="datepickers">
				<tr>
					<td><s:textfield name="id" label="id" /></td>
				</tr>
				<tr>
					<td><s:textfield name="roleName" label="roleName" /></td>
				</tr>
				<tr>
					<td><s:textfield name="roleDescription"
							label="roleDescription"></s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield name="roleFlag" label="roleFlag"></s:textfield>
					</td>
				</tr>
				<tr>
					<td><s:textfield id="roleEffDate" name="roleEffDate"
							label="roleEffDate"></s:textfield></td>
				</tr>
				<tr>
					<td><s:textfield id="roleExpDate" name="roleExpDate"
							label="roleExpDate"></s:textfield></td>
				</tr>
				<tr>
					<td><s:select list="listNavi" name="navigationNodeIds"
							listKey="id" listValue="naviText" multiple="true"
							label="navigationNodes" headerKey="-1" headerValue="请选择"></s:select>
					</td>
				</tr>
				<tr>
				<td>
				navigationNodeIds
				</td>
				<td>
					<div class="zTreeDemoBackgroundGenfu">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</td>
				</tr>
				<tr class="submit_tr">
					<td colspan="2"><s:submit /></td>
				</tr>
			</table>
		</s:form>
	</div>
<SCRIPT type="text/javascript">
</SCRIPT>
</body>
</html>