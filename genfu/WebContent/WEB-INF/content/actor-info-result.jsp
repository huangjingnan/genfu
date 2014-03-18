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
			<th>Col 1a</th>
			<th>Col 1a</th>
			<th>Col 1a</th>
			<th>Col 1a</th>
			<th>Col 1a</th>
			<th>Col 1a</th>
			<th>Col 1a</th>
			<th>Col 1a</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="model">
			<tr>
				<td id='id'>${id}</td>
				<td id='actor_name'>${id}</td>
				<td>${id}</td>
				<td>${id}</td>
				<td>${id}</td>
				<td>${id}</td>
				<td>${id}</td>
				<td><a href="#${ctx}/actor-info/${id}" name="view_edit"
					title="">View</a> | <a href="#${ctx}/actor-info/${id}/edit"
					name="view_edit" title="">Edit</a> | <a id="delete_new_new"
					href="#com.genfu.reform.model.ActorInfo" name="delete_new"
					title="">delete</a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
