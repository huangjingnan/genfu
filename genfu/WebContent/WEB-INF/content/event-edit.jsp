<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Event <s:property value="id" /></title>
<style type="text/css">
@import
	url(${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/themes/base/jquery.ui.all.css)
	;
</style>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/jquery-1.7.2.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.ui.core.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.ui.widget.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-HK.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-TW.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-af.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ar.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ar-DZ.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-az.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-bg.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-bs.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ca.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-cs.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-cy-GB.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-da.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-de.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-el.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-en-AU.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-en-GB.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-en-NZ.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-eo.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-es.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-et.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-eu.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-fa.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-fi.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-fo.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-fr.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-fr-CH.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ge.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-gl.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-he.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-hi.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-hr.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-hu.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-hy.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-id.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-is.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-it.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ja.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-kk.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-km.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ko.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-lb.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-lt.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-lv.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-mk.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ml.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ms.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-nl.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-nl-BE.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-no.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-pl.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-pt.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-pt-BR.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-rm.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ro.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ru.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-sk.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-sl.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-sq.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-sr.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-sr-SR.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-sv.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-ta.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-th.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-tj.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-tr.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-uk.js"></script>
<script
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-vi.js"></script>

<script>
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			showOtherMonths : true,
			selectOtherMonths : true
		});
		$("#datepicker").datepicker("option", "showAnim", "show");
		$.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
	});
</script>
</head>
<body>
	<s:form method="post" action="%{#ctx}/event/%{id}">
		<s:hidden name="_method" value="put" />
		<table>
			<s:textfield name="id" label="ID" disabled="true" />
			<s:textfield name="title" label="title" />
			<s:textfield id="datepicker" format="yyyy-MM-dd" nice="true"
				name="stateDate" label="stateDate" />
			<tr>
				<td colspan="2"><s:submit /></td>
			</tr>
		</table>
	</s:form>
	<a href="${ctx}/event">Back to Orders</a>
</body>
</html>
