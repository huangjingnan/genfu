<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"
	scope="application" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>genfuReform</title>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/js/jquery-ui-1.10.3/development-bundle/themes/base/jquery-ui-custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/js/tonytomov-jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/js/tonytomov-jqGrid/plugins/ui.multiselect.css" />
<style>
html, body {
	margin: 0;			/* Remove body margin/padding */
	padding: 0;
	overflow: hidden;	/* Remove scroll bars on browser window */	
    font-size: 75%;
}
/*Splitter style */


#LeftPane {
	/* optional, initial splitbar position */
	overflow: auto;
}
/*
 * Right-side element of the splitter.
*/

#RightPane {
	padding: 2px;
	overflow: auto;
}
.ui-tabs-nav li {position: relative;}
.ui-tabs-selected a span {padding-right: 10px;}
.ui-tabs-close {display: none;position: absolute;top: 3px;right: 0px;z-index: 800;width: 16px;height: 14px;font-size: 10px; font-style: normal;cursor: pointer;}
.ui-tabs-selected .ui-tabs-close {display: block;}
.ui-layout-west .ui-jqgrid tr.jqgrow td { border-bottom: 0px none;}
.ui-datepicker {z-index:1200;}
.rotate
    {
        /* for Safari */
        -webkit-transform: rotate(-90deg);

        /* for Firefox */
        -moz-transform: rotate(-90deg);

        /* for Internet Explorer */
        filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=3);
    }

</style>

<script src="${ctx}/js/tonytomov-jqGrid/jqGrid_Demos_files/jquery.js" type="text/javascript"></script>
<%-- <script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script> --%>
<script src="${ctx}/js/tonytomov-jqGrid/jqGrid_Demos_files/jquery-ui-custom.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/jqGrid_Demos_files/jquery.layout.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script type="text/javascript">
	$.jgrid.no_legacy_api = true;
	$.jgrid.useJSON = true;
</script>
<script src="${ctx}/js/tonytomov-jqGrid/plugins/ui.multiselect.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/js/jquery.jqGrid.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/plugins/jquery.tablednd.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/plugins/jquery.contextmenu.js" type="text/javascript"></script>
<script type="text/javascript">

jQuery(document).ready(function(){
    //$('#switcher').themeswitcher();

	$('body').layout({
		resizerClass: 'ui-state-default',
        west__onresize: function (pane, $Pane) {
            jQuery("#west-grid").jqGrid('setGridWidth',$Pane.innerWidth()-2);
		}
	});
	$.jgrid.defaults = $.extend($.jgrid.defaults,{loadui:"enable"});
	var maintab =jQuery('#tabs','#RightPane').tabs({
        add: function(e, ui) {
            // append close thingy
            $(ui.tab).parents('li:first')
                .append('<span class="ui-tabs-close ui-icon ui-icon-close" title="Close Tab"></span>')
                .find('span.ui-tabs-close')
				.show()
                .click(function() {
                    maintab.tabs('remove', $('li', maintab).index($(this).parents('li:first')[0]));
                });
            // select just added tab
            maintab.tabs('select', '#' + ui.panel.id);
        }
    });
	
	/* treeReader = {
			   level_field: "level",
			   parent_id_field: "naviParentId", // then why does your table use "parent_id"?
			   leaf_field: "isLeaf",
			   expanded_field: "expanded"
			}; */
	
    jQuery("#west-grid").jqGrid({
        //url: "${ctx}/js/tonytomov-jqGrid/jqGrid_Demos_files/tree.xml",
      	url: "${ctx}/user-navis",
        datatype: "xml",
        height: "auto",
        pager: false,
        loadui: "disable",
        colNames: ["id","Items","url","level","parent","isLeaf","expanded"],
        colModel: [
            {name: "id",width:1,hidden:true, key:true},
            {name: "menu", width:150, resizable: false, sortable:false},
            {name: "url",width:1,hidden:true},
            {name: "level",width:1,hidden:true},
            {name: "parent",width:1,hidden:true},
            {name: "isLeaf",width:1,hidden:true},
            {name: "expanded",width:1,hidden:true}
        ],
        treeGrid: true,
        //treeReader: treeReader,
        treeGridModel: 'adjacency',
		caption: "genfuReform",
        ExpandColumn: "menu",
        autowidth: true,
        //width: 180,
        rowNum: 200,
        ExpandColClick: true,
        treeIcons: {leaf:'ui-icon-document-b'},
        onSelectRow: function(rowid) {
            var treedata = $("#west-grid").jqGrid('getRowData',rowid);
            if(treedata.isLeaf=="true") {
                //treedata.url
                var st = "#t"+treedata.id;
				if($(st).html() != null ) {
					maintab.tabs('select',st);
				} else {
					maintab.tabs('add',st, treedata.menu);
					//$(st,"#tabs").load(treedata.url);
					$.ajax({
						url: treedata.url,
						type: "GET",
						dataType: "html",
						complete : function (req, err) {
							$(st,"#tabs").append(req.responseText);
							try { var pageTracker = _gat._getTracker("UA-5463047-4"); pageTracker._trackPageview(); } catch(err) {};
						}
					});
				}
            }
        }
    });
	
// end splitter

});

var pb_strConfirmCloseMessage='确认后，即将注销！';
var pb_blnCloseWindow = false;
function ConfirmClose() {
	window.event.returnValue = pb_strConfirmCloseMessage;
	pb_blnCloseWindow = true;
}
function ShowConfirmClose(blnValue) {
	if(blnValue) {
		document.body.onbeforeunload = ConfirmClose;
	} else {
		document.body.onbeforeunload = null;
	}
}
function logout(){
	jQuery.ajax({
		type : 'POST',
		url : '${ctx}/login/0?_method=DELETE',
		success : function(data) {
			//debugger;
		},
		error : function() {
		},
		complete : function() {
			//content.hide();
		}
	});
}
</script>
</head>
<body onload='ShowConfirmClose(true);' onunload="logout();">
  	<div id="LeftPane" class="ui-layout-west ui-widget ui-widget-content">
		<table id="west-grid"></table>
	</div> <!-- #LeftPane -->
	<div id="RightPane" class="ui-layout-center ui-helper-reset ui-widget-content" ><!-- Tabs pane -->
    <div id="switcher"></div>
		<div id="tabs" class="jqgtabs">
			<ul>
				<li><a href="#tabs-1">home</a></li>
			</ul>
			<div id="tabs-1" style="font-size:12px;">
			<br/>
			<br/>
			<br/>
			<br/>

			</div>
		</div>
	</div> <!-- #RightPane -->
</body>
</html>