<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!--// START SNIPPET: common-include-->
<sx:head cache="true" />
<!--// END SNIPPET: common-include-->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserInfos</title>
<style type="text/css">
@import url(${ctx}/js/__jquery.tablesorter/themes/blue/style.css);

@import
	url(${ctx}/js/__jquery.tablesorter/addons/pager/jquery.tablesorter.pager.css)
	;

@import
	url(${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/themes/base/jquery.ui.all.css)
	;

@import
	url(${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/demos/demos.css)
	;
/*@import url(${ctx}/js/jquery-ui-1.8.20.custom/css/screen.css);*/
@import url(${ctx}/js/jq-ingrid/css/ingrid.css);

@import url(${ctx}/css/objectIndex.css);

/* @import url(${ctx}/js/jq-ingrid/css/site.css); */
</style>

<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/jquery-1.7.2.js"></script>
<%-- <script type="application/javascript"
	src="${ctx}/js/jq-ingrid/lib/jquery.js"></script> --%>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/js/custom.js"></script>
<script type="application/javascript"
	src="${ctx}/js/__jquery.tablesorter/jquery.tablesorter.js"></script>
<script type="application/javascript"
	src="${ctx}/js/__jquery.tablesorter/addons/pager/jquery.tablesorter.pager.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.ui.core.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.effects.core.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.effects.bounce.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/jquery.ui.datepicker.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-HK.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-zh-TW.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/ui/i18n/jquery.ui.datepicker-en-AU.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jq-ingrid/js/jquery.ingrid.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jq-ingrid/lib/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/genfu/genfu.js"></script>
<script type="application/javascript">
	
	
	
	
	
	
var mygrid1;
	
	$(document).ready(function() {
		
		// add ie checkbox widget
		$.tablesorter.addWidget({
			id: "iecheckboxes",
			format: function(table) {
				if($.browser.msie) {
					if(!this.init) {
						$(":checkbox",table).change(function() { this.checkedState = this.checked});				
						this.init = true;
					}
					$(":checkbox",table).each(function() {
						$(this).attr("checked",this.checkedState);
					});
				}
			}
		});
		
		var dates = $( "#USER_EFF_DATE_GE, #USER_EFF_DATE_LE" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 3,
			onSelect: function( selectedDate ) {
				var option = this.id == "USER_EFF_DATE_GE" ? "minDate" : "maxDate",
					instance = $( this ).data( "datepicker" ),
					date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
				dates.not( this ).datepicker( "option", option, date );
			}
		});

	
		$("#USER_EFF_DATE_GE,#USER_EFF_DATE_LE").datepicker("option", "showAnim", "show");
		$.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
		
		mygrid1 = $("#table1").ingrid({ 
			url: '${ctx}/actor-info?statusCode=303&FIRST_RESULT=0',
			height: 250,
			savedStateLoad: false,
			initialLoad: true,
			colWidths: ['10%','10%','10%','10%','10%','10%','10%','30%'],
			minColWidth : 60,
			headerHeight: 30,
			resizableCols: true,
			rowClasses: ['grid-row-style1','grid-row-style1','grid-row-style2','grid-row-style1','grid-row-style1','grid-row-style3'],
			sorting: false,
			paging: true,
			rowSelection: true,
			recordsPerPage: 10,
			totalRecords: <%=session.getAttribute("totalRecords")%>,
			extraParams: {test : 'Hello,tang',name:'test',MAX_RESULTS: '10'}        
		});
		
		$('#search_a').click(function(){
			// the 'g' object is ingrid - call methods like so:
			/* var rows = mygrid1.g.getSelectedRows();			
			for (i=0; i<rows.length; i++) {				
				var str = 'SELECTED ROW ' + i + " - InnerHTML is : \n";
				alert( str + $(rows[i]).html() );
			} */	
			//mygrid1.ingrid({totalRecords: 99});
			//alert(mygrid1.ingrid({totalRecords: 99}));
			/*var my_totalRecords = 99;
			mygrid1.ingrid({ 
				url: '${ctx}/actor-info?statusCode=303&FIRST_RESULT=0',
				height: 350,
				savedStateLoad: false,
				initialLoad: true,
				colWidths: [60,60,60,60,60,60,60,220],
				minColWidth : 60,
				headerHeight: 30,
				resizableCols: true,
				rowClasses: ['grid-row-style1','grid-row-style1','grid-row-style2','grid-row-style1','grid-row-style1','grid-row-style3'],
				sorting: false,
				paging: true,
				rowSelection: true,
				recordsPerPage: 10,
				totalRecords: my_totalRecords,
				extraParams: {test : 'Hello,tang',name:'test',MAX_RESULTS: '10'}        
			});*/
			//var params = {totalRecords:50};
			
			mygrid1.g.load();
			//alert(mygrid1.g);
			//jQuery.extend(cfg, o);
			//alert(mygrid1.g.p.getPage());
		});
		$('#delete_new_new').click(function(){
			// the 'g' object is ingrid - call methods like so:
			var rows = mygrid1.g.getSelectedRows();			
			var ids = '';
			for (i=0; i<rows.length; i++) {				
				var str = 'SELECTED ROW ' + i + " - InnerHTML is : \n";
				//alert( str + $(rows[i]).html() );
				//alert(jQuery(rows[i]).find("#id").text());
				ids = ids + ',' + jQuery(rows[i]).find("#id").text();
			}
			//alert('${ctx}/genfu-common/'+ids+'?_method=DELETE');
			ids = ids.substring(1);
			$.ajax({
				url : '${ctx}/genfu-common/'+ids+'?_method=DELETE',
				data: {
					className: 'com.genfu.reform.model.ActorInfo'
				},
				type : "POST",
				beforeSend : function() {
					//$("#large").html('');
				},
				success : function(data) {
					//$("#weather-temp").html("<strong>" + data + "</strong> degrees");
					//alert(date);
					//mygrid1.g.load();
				},
				complete : function() {
					mygrid1.g.load();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		});

	});
	


function setWinOpener(){
	var c = [];
	var userNames = [];
	var userObj;
	$("input[name='checkbox_id']:checked").each(function() {
		//alert($(this).val());
		userObj = eval("(" + $(this).val() + ")");
		c.push(userObj.userId);
		userNames.push(userObj.userName);
	});
	//alert('Checked ' + c.join(', ') + '.');
	//alert($("input[name='checkbox_id']:checked").length);
	window.opener.document.getElementById("userUpperId").value=c.join(', ');
	window.opener.document.getElementById("checkboxStatus").innerHTML=userNames.join(', ');
	self.close();
}

function searchModels(){
	
}




</script>

<jsp:include page="architecture/commonInclude.jsp" />
<s:head />
</head>
<body>

	<%
		System.out.println("actor-info-index"
				+ session.getAttribute("totalRecords"));
	%>
	<div id='search_title' ondblclick="hiddenSearchDIV();">
		<b>查询条件</b>
	</div>
	<div id='search_div'>
		<table id='search_table'>
			<tr class=".search_tr">
				<td class=".search_td"><label>userCode <input
						type="text" id="USER_CODE_EQ" /></label></td>
				<td class=".search_td"><label>userName <input
						type="text" id="USER_NAME_LIKE" /></label></td>
				<td class=".search_td"><label>dateBegin <input
						type="text" id="USER_EFF_DATE_GE" />
				</label></td>
				<td class=".search_td"><label>dateEnd <input
						type="text" id="USER_EFF_DATE_LE" /></label></td>
				<td class=".search_td"></td>
			</tr>
			<tr>
				<td class=".search_tr"></td>
				<td class=".search_tr"></td>
				<td class=".search_tr"></td>
				<td class=".search_tr"></td>
				<td class=".search_td"><a href="#" onclick="" id="search_a"
					class="searchHref">search</a></td>
			</tr>
		</table>
	</div>
	<s:actionmessage />

	<div id='resultList' ondblclick="hiddenResultDIV();">
		<b>查询结果</b><a id="delete_new_new" href="#" onclick=""
			style="float: right;">测试复选框</a><a href="#" onclick="setWinOpener();"
			style="float: right;">测试弹出框</a><a href="#"
			onclick="viewModelGenfu('${ctx}/actor-info/new');"
			style="float: right;">new</a>
	</div>

	<div id='resultSet_div'>
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
				<tr>
					<td>static col 1</td>
					<td>static col 1</td>
					<td>static col 1</td>
					<td>static col 1</td>
					<td>static col 1</td>
					<td>static col 1</td>
					<td>static col 1</td>
					<td>static col 1</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
