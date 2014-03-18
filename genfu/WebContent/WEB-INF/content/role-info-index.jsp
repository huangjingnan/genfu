<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserInfos</title>
<jsp:include page="/include/jq-ingridInclude1_10_3.jsp" flush="true" />
<script type="application/javascript" src="${ctx}/js/genfu/genfu.js"></script>
<script type="application/javascript">
function viewModelGenfu(urlVal) {
	var iTop = (window.screen.availHeight - 500) / 2; // 获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; // 获得窗口的水平位置;

	window
			.open(
					urlVal,
					'winViewModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no');
}
	
var mygrid1;
	
	$(document).ready(function() {
		
		// add ie checkbox widget
		// $.tablesorter.addWidget({
		//	id: "iecheckboxes",
		//	format: function(table) {
		//		if($.browser.msie) {
		//			if(!this.init) {
		//				$(":checkbox",table).change(function() { this.checkedState = this.checked});				
		//				this.init = true;
		//			}
		//			$(":checkbox",table).each(function() {
		//				$(this).attr("checked",this.checkedState);
		//			});
		//		}
		//	}
		//});
		
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
			url: '${ctx}/role-info?statusCode=303&FIRST_RESULT=0',
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
			totalRecords: 
<%=session.getAttribute("totalRecords")%>
	,
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
				url: '${ctx}/role-info?statusCode=303&FIRST_RESULT=0',
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
	var rows = mygrid1.g.getSelectedRows();
	//alert('Checked ' + c.join(', ') + '.');
	//alert($("input[name='checkbox_id']:checked").length);
	//window.opener.document.getElementById("userUpperId").value=c.join(', ');
	//window.opener.document.getElementById("checkboxStatus").innerHTML=userNames.join(', ');
	//alert(rows.length);
	var jsonData = '';
	for(i=0;i<rows.length;i++){
		jsonData = jsonData + ',{id:'+jQuery(rows[i]).find("#id").text()+',roleName:"'+jQuery(rows[i]).find("#roleName").text()+'"}'
	}

	jsonData = jsonData.substring(1);
	jsonData = '[' + jsonData +']';
	//window.opener.myRoleIds=jsonData;
	window.opener.setRoles(jsonData);
	self.close();
}

function searchModels(){
	
}







</script>
<s:head />
</head>
<body>

	<%
		System.out.println("role-info-index"
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
			style="float: right;">确认选择</a><a href="#"
			onclick="newModelGenfu('${ctx}/role-info/new');"
			style="float: right;">new</a>
	</div>

	<div id='resultSet_div'>
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
				<tr>
					<td>id</td>
					<td>roleName</td>
					<td>roleDescription</td>
					<td>roleFlag</td>
					<td>roleEffDate</td>
					<td>roleExpDate</td>
					<td>NAVIGATION_NODE</td>
					<td>操作</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
