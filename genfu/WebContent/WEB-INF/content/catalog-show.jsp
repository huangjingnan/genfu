<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<p>
		<b><s:text name="catalog.IDname"/></b>
		<b>#${id},${dishName}</b>
</p>
<p>
		<b><s:text name="catalog.blurb"/></b>
		<b>${blurb}</b>
</p>
<p>
		<b><s:text name="catalog.price"/></b>
		<b>${price}</b>
</p>
<p>
		<b><strong style="font-size:18px;margin-left:30px;"><a href="javascript:void(0)" onclick="addDish('${id}');">+</a> </strong></b>
</p>
<p>
		<b><s:text name="catalog.image"/></b>
		<b><img src="${coverImage}" alt="Planning tde ascent" /></b>
</p>
<a href="javascript:void(0)" onclick="catalog();"><s:text name="catalog.back"/></a>
<ol id="tags">
	<s:iterator var="tagging" value="taggings">
		<li class="tag" id="tag_${tagging.tag.id}"><a href="javascript:void(0)"
			onclick="tagDishes(${tagging.tag.id},'${tagging.tag.name}');">${tagging.tag.name}</a></li>
		<script type="text/javascript">
		//<![CDATA[
		//]]>
		</script>
	</s:iterator>
</ol>