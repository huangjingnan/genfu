<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
<!--
	function viewModel(idVal) {
		var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

		window
				.open(
						'${ctx}/department/' + idVal,
						'winViewModel',
						'height=500,width=900,top='
								+ iTop
								+ ',left='
								+ iLeft
								+ ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no');
	}
//-->
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
				<td>${id}</td>
				<td>${id}</td>
				<td>${id}</td>
				<td>${id}</td>
				<td>${id}</td>
				<td>${id}</td>
				<td>${id}</td>
				<td><a href="#${ctx}/department/${id}" name="view_edit"
					title="">View</a> | <a href="#${ctx}/department/${id}/edit"
					name="view_edit" title="">Edit</a> | <a
					href="#${ctx}/department/${id}?_method=DELETE" name="delete"
					title="">delete</a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
