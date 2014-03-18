<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>navigation-node</title>
    <link rel="stylesheet" href="${ctx}/js/ludo-jquery-treetable-afc45de/stylesheets/screen.css" media="screen" />
    <link rel="stylesheet" href="${ctx}/js/ludo-jquery-treetable-afc45de/stylesheets/jquery.treetable.css" type="text/css" />
    <link rel="stylesheet" href="${ctx}/js/ludo-jquery-treetable-afc45de/stylesheets/jquery.treetable.theme.default.css" />
<%-- <jsp:include page="/include/tablesorterInclude.jsp" flush="true" /> --%>
<script type="application/javascript" src="${ctx}/js/genfu/genfu.js"></script>
<script type="application/javascript">
	

</script>

<s:head />
</head>
<body>

	<div id='search_title' ondblclick="hiddenSearchDIV();">
		<b>查询条件</b>
	</div>
	<div id='search_div'>
		<table id='search_table' width="100%">
			<tr class=".search_tr">
				<td class=".search_td"><label>naviText <input
						type="text" id="NAVI_TEXT" /></label></td>
				<td class=".search_td"><label>naviEffDateBegin <input
						type="text" id="NAVI_EFF_DATE_GE" />
				</label></td>
				<td class=".search_td"><label>naviEffDateEnd <input
						type="text" id="NAVI_EFF_DATE_LE" /></label></td>
				<td class=".search_td"></td>
			</tr>
			<tr class=".search_tr">
				<td class=".search_td"></td>
				<td class=".search_td"></td>
				<td class=".search_td"></td>
				<td class=".search_td"><a href="#" onclick="searchModels();"
					id="search_a" class="searchHref">search</a></td>
			</tr>
		</table>
	</div>

	<div id='resultList' ondblclick="hiddenResultDIV();">
		<b>查询结果</b><a href="#" onclick="testCheckBox();" style="float: right;">测试复选框</a><a
			href="#" onclick="setWinOpener();" style="float: right;">测试弹出框</a><a
			href="#" onclick="newModelGenfu('${ctx}/navigation-node/new');"
			style="float: right;">new</a>
	</div>
	<div id="resultSet_div">
	
		<table id="example-advanced">
        <caption>
          <a href="#" onclick="jQuery('#example-advanced').treetable('expandAll'); return false;">Expand all</a>
          <a href="#" onclick="jQuery('#example-advanced').treetable('collapseAll'); return false;">Collapse all</a>
        </caption>
        <thead>
				<tr>
					<th>ID</th>
					<th>naviAction</th>
					<th>naviDescription</th>
					<th>naviEffDate</th>
					<th>naviExpDate</th>
					<th>naviFlag</th>
					<th>naviOrder</th>
					<th>naviOthers</th>
					<th>naviParentId</th>
					<th>naviSrc</th>
					<th>naviText</th>
					<th>Action</th>
				</tr>
		</thead>
		<tbody>
				<s:set name="itemSize" value="0"/>
				<s:iterator value="model">
				<s:set name="itemSize" value="#itemSize+1"/>
				
				<s:if test="#itemSize==1">
						<tr data-tt-id='${id}'>
						<td><span class='folder'>${id}</span></td>
						<td>${naviAction}</td>
						<td>${naviDescription}</td>
						<td>${naviEffDate}</td>
						<td>${naviExpDate}</td>
						<td>${naviFlag}</td>
						<td>${naviOrder}</td>
						<td>${naviOthers}</td>
						<td>${naviParentId}</td>
						<td>${naviSrc}</td>
						<td>${naviText}</td>
						<td><a href="#"
							onclick="viewModelGenfu('${ctx}/navigation-node/${id}');">View</a>
							| <a href="#"
							onclick="viewModelGenfu('${ctx}/navigation-node/${id}/edit');">Edit</a>
							| <a href="#"
							onclick="deleteModelGenfu('${ctx}/navigation-node/${id}/deleteConfirm');">Delete</a></td>
				</tr>
				</s:if>
				<s:else>
					<tr data-tt-id='${id}' data-tt-parent-id='${naviParentId}'>
						<td><span class='folder'>${id}</span></td>
						<td>${naviAction}</td>
						<td>${naviDescription}</td>
						<td>${naviEffDate}</td>
						<td>${naviExpDate}</td>
						<td>${naviFlag}</td>
						<td>${naviOrder}</td>
						<td>${naviOthers}</td>
						<td>${naviParentId}</td>
						<td>${naviSrc}</td>
						<td>${naviText}</td>
						<td><a href="#"
							onclick="viewModelGenfu('${ctx}/navigation-node/${id}');">View</a>
							| <a href="#"
							onclick="viewModelGenfu('${ctx}/navigation-node/${id}/edit');">Edit</a>
							| <a href="#"
							onclick="deleteModelGenfu('${ctx}/navigation-node/${id}/deleteConfirm');">Delete</a></td>
					</tr>
				</s:else>
				</s:iterator>
			</tbody>
      </table>
	</div>
	
    <script src="${ctx}/js/ludo-jquery-treetable-afc45de/vendor/jquery.js"></script>
    <script src="${ctx}/js/ludo-jquery-treetable-afc45de/vendor/jquery-ui.js"></script>
    <script src="${ctx}/js/ludo-jquery-treetable-afc45de/javascripts/src/jquery.treetable.js"></script>	
<script type="application/javascript">
$("#example-advanced").treetable({ expandable: true });

// Highlight selected row
$("#example-advanced tbody").on("mousedown", "tr", function() {
  $(".selected").not(this).removeClass("selected");
  $(this).toggleClass("selected");
});

// Drag & Drop Example Code
$("#example-advanced .file, #example-advanced .folder").draggable({
  helper: "clone",
  opacity: .75,
  refreshPositions: true,
  revert: "invalid",
  revertDuration: 300,
  scroll: true
});

$("#example-advanced .folder").each(function() {
  $(this).parents("tr").droppable({
    accept: ".file, .folder",
    drop: function(e, ui) {
      var droppedEl = ui.draggable.parents("tr");
      $("#example-advanced").treetable("move", droppedEl.data("ttId"), $(this).data("ttId"));
    },
    hoverClass: "accept",
    over: function(e, ui) {
      var droppedEl = ui.draggable.parents("tr");
      if(this != droppedEl[0] && !$(this).is(".expanded")) {
        $("#example-advanced").treetable("expandNode", $(this).data("ttId"));
      }
    }
  });
});
</script>
</body>
</html>
