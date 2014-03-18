<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- <p><input type="search"/></p> -->
<ul id="books">
	<s:iterator value="model">
		<li class="book" id="book_${id}">
			<img src="${coverImage}" alt="Planning the ascent" width="180" height="100" />
			<!-- <img src="/genfu/files/dishImage/rac005.png" alt="Planning the ascent" width="180" height="100" /> -->
			<dl>
				<dt><a href="javascript:void(0)" onclick="viewDish(${id});">${dishName}</a></dt>
				<dd>
					<small>${price}</small>
				</dd>
				<dd>
					<strong style="font-size:18px;"><a href="javascript:void(0)" onclick="addDish('${id}');">+</a> </strong>
				</dd>
				<%-- <dd>${coverImage}</dd> --%>
			</dl>
			<script type="text/javascript">
				//<![CDATA[
						$("#book_${id}").draggable({
							revert : true,scroll: false
						});
				//]]>
			</script>
		</li>

	</s:iterator>
</ul>
<s:property value="%{#request.pager}" escapeHtml="false"/>
<script type="text/javascript">
//<![CDATA[
//next();pre();
//]]>
</script>