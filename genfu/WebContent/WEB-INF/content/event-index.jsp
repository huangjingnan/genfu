<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Events</title>
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
</style>

<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.8.20.custom/development-bundle/jquery-1.7.2.js"></script>
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
<script type="application/javascript">
	
	
	$(document).ready(function() {
		$("#large").tablesorter({
			// striping looking
			widgets : [ 'zebra' ],
			headers : {
				// assign the 4th column (we start counting zero)
				3 : {
					// disable it by setting the property sorter to false
					sorter : false
				}
			}

		}).tablesorterPager({
			container : $("#pager")
		});

		var dates = $( "#EVENT_DATE_GE, #EVENT_DATE_LE" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			numberOfMonths: 3,
			onSelect: function( selectedDate ) {
				var option = this.id == "EVENT_DATE_GE" ? "minDate" : "maxDate",
					instance = $( this ).data( "datepicker" ),
					date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
				dates.not( this ).datepicker( "option", option, date );
			}
		});

	
		$("#EVENT_DATE_GE,#EVENT_DATE_LE").datepicker("option", "showAnim", "show");
		$.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
	});
	
	
	///////////////////////new///////////////////////
	function hiddenSearchDIV(){
		//$("#search_div").slideToggle(2000);//窗帘效果的切换,点一下收,点一下开,参数可以无
		//$("#search_div").slideDown();//窗帘效果展开
		//$("#search_div").hide();//hide()函数,实现隐藏,括号里还可以带一个时间参数(毫秒)例如hide(2000)以2000毫秒的速度隐藏,还可以带slow,fast
		//$("#search_div").show();//显示,参数说明同上
		$("#search_div").toggle(1000);//显示隐藏切换,参数可以无,参数说明同上

	}
	
	function hiddenResultDIV(){
		//$("#search_div").slideToggle(2000);//窗帘效果的切换,点一下收,点一下开,参数可以无
		//$("#search_div").slideDown();//窗帘效果展开
		//$("#search_div").hide();//hide()函数,实现隐藏,括号里还可以带一个时间参数(毫秒)例如hide(2000)以2000毫秒的速度隐藏,还可以带slow,fast
		//$("#search_div").show();//显示,参数说明同上
		$("#resultSet_div").toggle(1000);//显示隐藏切换,参数可以无,参数说明同上

	}

function searchModels(){
	var paramsKeyValue="";
	if(document.getElementById('eventTitle').value !=""){
		//alert(document.getElementById('eventTitle').value);
		paramsKeyValue=paramsKeyValue+"&title_LIKE="+escape(document.getElementById('eventTitle').value).replace(/\%/g,"%");
		//alert(escape(paramsKeyValue).replace(/\%/g,"%"));
	}
	if(document.getElementById('EVENT_DATE_GE').value !=""){
		alert(document.getElementById('EVENT_DATE_GE').value);
		paramsKeyValue=paramsKeyValue+"&EVENT_DATE_GE="+document.getElementById('EVENT_DATE_GE').value;
		alert(paramsKeyValue);
	}
	if(document.getElementById('EVENT_DATE_LE').value !=""){
		alert(document.getElementById('EVENT_DATE_LE').value);
		paramsKeyValue=paramsKeyValue+"&EVENT_DATE_LE="+document.getElementById('EVENT_DATE_LE').value;
		alert(paramsKeyValue);
	}
	//document.getElementById('search_a').attributes['href'].value='${ctx}/event.xhtml?statusCode=303'+paramsKeyValue;
	paramsKeyValue='${ctx}/event.json?statusCode=303'+paramsKeyValue;
	//alert(document.getElementById('search_a').attributes['href'].value);
	$.ajax({
        type: 'get',
        url: paramsKeyValue,
        dataType: 'json',
        success: function (data) {
        	$("#large").html('');
        	var tblHead='<thead><tr><th>ID</th><th>title</th><th>stateDate</th><th>Action</th></tr></thead>';
        	var tblRow ='<tbody>';

			for(var i=0;i<data.length;i++){
				tblRow += '<tr><td>'+data[i].id+'</td><td>'+data[i].title+'</td><td>'+data[i].date+'</td>';
				tblRow +='<td><a href="#" onclick="viewModel('+data[i].id+');">View</a> | <a href="#" onclick="editModel('+data[i].id+');">Edit</a> |';
				tblRow +='<a href="#" onclick="deleteModel('+data[i].id+');">Delete</a></td></tr>';				
			}
			tblRow+="</tbody>";
			//alert(tblHead+tblRow);
			$("#large").append(tblHead+tblRow);
			$("#large").trigger("update");
			$("#large").trigger("zebra");
			$("#large").tablesorter({
				widgets: ['zebra'],
				headers : {
					// assign the 4th column (we start counting zero)
					3 : {
						// disable it by setting the property sorter to false
						sorter : false
					}
				}
			}).tablesorterPager({
				container : $("#pager")
			});;
			//$("#large").html(tblHead+tblRow);
			//$(tblHead).appendTo("#large");
			//$(tblRow).appendTo("#large");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
        }
	});
	
}


function newModel(){
	var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

	window
			.open(
					'${ctx}/event/new',
					'winNewModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no');
}


function viewModel(idVal){
	var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

	window
			.open(
					'${ctx}/event/'+idVal,
					'winNewModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no');
}

function editModel(idVal){
	var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

	window
			.open(
					'${ctx}/event/'+idVal+'/edit',
					'winNewModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no');
}

function deleteModel(idVal){
	var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

	window
			.open(
					'${ctx}/event/'+idVal+'/deleteConfirm',
					'winNewModel',
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
<body onload="searchModels();">
	<%
		System.out.println("event-index");
	%>
	<div id='search_title' style="border: 1px solid yellow;"
		ondblclick="hiddenSearchDIV();">
		<b>查询条件</b>
	</div>
	<div id='search_div'
		style="border: 1px solid yellow; background-color: #e6EEEE;">
		<table id='search_table' cellpadding="0" cellspacing="20" width="100%">
			<tr>
				<td><label>title <input type="text" id="eventTitle" /></label></td>
				<td><label>dateBegin <input type="text"
						id="EVENT_DATE_GE" />
				</label></td>
				<td><label>dateEnd <input type="text"
						id="EVENT_DATE_LE" /></label></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td colspan="3"><a href="#" target="ContentViewFrame"
					onclick="searchModels();" id="search_a" class="searchHref">Event</a></td>
			</tr>
		</table>
	</div>
	<s:actionmessage />

	<div id='resultList' style="border: 1px solid yellow;"
		ondblclick="hiddenResultDIV();">
		<b>查询结果</b><a href="#" onclick="newModel();" style="float: right;">new</a>
	</div>

	<div id="resultSet_div">
		<table id="large" class="tablesorter" cellspacing="0">
		</table>
		<div id="pager" class="pager">
			<form id="pager_form">
				<img
					src="${ctx}/js/__jquery.tablesorter/addons/pager/icons/first.png"
					class="first" /> <img
					src="${ctx}/js/__jquery.tablesorter/addons/pager/icons/prev.png"
					class="prev" /> <input type="text" class="pagedisplay" /> <img
					src="${ctx}/js/__jquery.tablesorter/addons/pager/icons/next.png"
					class="next" /> <img
					src="${ctx}/js/__jquery.tablesorter/addons/pager/icons/last.png"
					class="last" /> <select class="pagesize">
					<option selected="selected" value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
				</select>
			</form>
		</div>
	</div>
</body>
</html>
