
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<style type="text/css">
@import url(${ctx}/js/__jquery.tablesorter/themes/blue/style.css);

@import
	url(${ctx}/js/__jquery.tablesorter/addons/pager/jquery.tablesorter.pager.css)
	;

@import url(${ctx}/css/blue/commonSearch.css);
/*@import url(${ctx}/js/jquery-ui-1.8.20.custom/css/screen.css);*/
</style>
</head>
<body>
	<%-- ${ targetSearch} --%>
	<div class="outerSearchDIV">
		<div id="eventSearch" class="searchDIV">
			<c:if test="${ targetSearch eq 'Event'}">
				<table>
					<tr>
						<td><label>title <input type="text" /></label></td>
						<td><label>dateBegin <input type="text" />
						</label></td>
						<td><label>dateEnd <input type="text" /></label></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="3"><a href="${ctx}/event"
							target="ContentViewFrame"
							onclick="document.getElementById('search_id').attributes['href'].value='${ctx}/event';alert(document.getElementById('search_id').attributes['href'].value);"
							id="search_id" class="searchHref">Event</a></td>
					</tr>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>