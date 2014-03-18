<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	int rows = 25;
	int i = 0;
%>
<table>
	<tbody>
		<s:iterator value="model">
			<%
				for (; i < rows; i++) {
						System.out.println(i);
			%>
			<tr>
				<td>${id}</td>
				<td>${title}</td>
				<td>${stateDate}</td>
				<td><a href="${ctx}/event/${id}">View</a> | <a
					href="${ctx}/event/${id}/edit">Edit</a> | <a
					href="${ctx}/event/${id}/deleteConfirm">Delete</a></td>
			</tr>
			<%
				}
					System.out.println(i);
			%>
		</s:iterator>
	</tbody>
</table>

