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
<%-- <%@ include file="/include/jq-ingridInclude.jsp"%> --%>
<jsp:include page="/include/jq-ingridInclude.jsp" flush="true" />
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
			url: '${ctx}/department?statusCode=303&FIRST_RESULT=0',
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
				url: '${ctx}/department?statusCode=303&FIRST_RESULT=0',
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
			
			var params = {
					extraParams: {},
					totalRecords: 98
				};
			mygrid1.g.load(params);
			//alert(mygrid1.g);
			//jQuery.extend(cfg, o);
			//alert(mygrid1.g.p.getPage());
		});

	});
	
	
	///////////////////////new///////////////////////
	function hiddenSearchDIV(){
		//$("#search_div").slideToggle(2000);//窗帘效果的切换,点一下收,点一下开,参数可以无
		//$("#search_div").slideDown();//窗帘效果展开
		//$("#search_div").hide();//hide()函数,实现隐藏,括号里还可以带一个时间参数(毫秒)例如hide(2000)以2000毫秒的速度隐藏,还可以带slow,fast
		//$("#search_div").show();//显示,参数说明同上
		$("#search_div").toggle(1000);//显示隐藏切换,参数可以无,参数说明同上

	}
	
///////////////////////new///////////////////////
	function hiddenResultDIV(){
		//$("#search_div").slideToggle(2000);//窗帘效果的切换,点一下收,点一下开,参数可以无
		//$("#search_div").slideDown();//窗帘效果展开
		//$("#search_div").hide();//hide()函数,实现隐藏,括号里还可以带一个时间参数(毫秒)例如hide(2000)以2000毫秒的速度隐藏,还可以带slow,fast
		//$("#search_div").show();//显示,参数说明同上
		$("#resultSet_div").toggle(1000);//显示隐藏切换,参数可以无,参数说明同上

	}



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

function newModel(){
	var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

	window
			.open(
					'${ctx}/department/new',
					'winNewModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no');
}

function viewModel(idVal){
	var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

	window
			.open(
					'${ctx}/department/'+idVal,
					'winViewModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no');
}

function editModel(idVal){
	var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

	window
			.open(
					'${ctx}/department/'+idVal+'/edit',
					'winEditModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no');
}

function deleteModel(idVal){
	var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

	window
			.open(
					'${ctx}/department/'+idVal+'/deleteConfirm',
					'winDeleteModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no');
}


















</script>

<jsp:include page="architecture/commonInclude.jsp" />
<s:head />
</head>
<body>

	<%
		System.out.println("department-index"
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
		<b>查询结果</b><a href="#" onclick="testCheckBox();" style="float: right;">测试复选框</a><a
			href="#" onclick="setWinOpener();" style="float: right;">测试弹出框</a><a
			href="#" onclick="newModel();" style="float: right;">new</a>
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
	<script type="text/javascript">
		/*alert('${totalRecords }');
		alert(mygrid1.cfg.totalRecords);
		mygrid1.cfg.totalRecords = '${totalRecords }';
		alert(mygrid1.cfg.totalRecords);*/
	</script>
</body>
</html>
