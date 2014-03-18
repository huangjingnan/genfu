<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<ol id="tags">
	<s:iterator value="model">
		<li class="tag" id="tag_${id}"><a href="javascript:void(0)"
			onclick="tagDishes(${id},'${name}');">${name}</a></li>
		<script type="text/javascript">
		//<![CDATA[
		//]]>
		</script>
	</s:iterator>
</ol>