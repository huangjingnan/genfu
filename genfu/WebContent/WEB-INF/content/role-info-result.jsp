<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
</script>
<table id="table1">
	<thead>
		<tr>
			<th>id</th>
			<th>roleName</th>
			<th>roleDescription</th>
			<th>roleFlag</th>
			<th>roleEffDate</th>
			<th>roleExpDate</th>
			<th>NAVIGATION_NODE</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="item" value="model">
			<tr>
				<td id='id'>${item.id}</td>
				<td id='roleName'>${item.roleName}</td>
				<td id='roleDescription'>${item.roleDescription}</td>
				<td id='roleFlag'>${item.roleFlag}</td>
				<td id='roleEffDate'>${item.roleEffDate}</td>
				<td id='roleExpDate'>${item.roleExpDate}</td>
				<td id='NAVIGATION_NODE'><s:iterator var="navigation_item" value="item.NAVIGATION_NODE">
				${navigation_item.id}
				</s:iterator></td>
				<td><a href="#${ctx}/role-info/${id}" name="view_edit"
					title="">View</a> | <a href="#${ctx}/role-info/${id}/edit"
					name="view_edit" title="">Edit</a> | <a id="delete_new_new"
					href="#com.genfu.reform.model.RoleInfo" name="delete_new"
					title="">delete</a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
