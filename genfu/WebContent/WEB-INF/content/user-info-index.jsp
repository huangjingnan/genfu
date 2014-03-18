<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserInfos</title>
<jsp:include page="/include/tablesorterInclude.jsp" flush="true" />
<style type="text/css">
</style>

<script type="application/javascript">
	
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
		
		$("#large").tablesorter({
			// striping looking
			widgets : [ 'zebra' ],
			headers : {
				// assign the 4th column (we start counting zero)
				9 : {
					// disable it by setting the property sorter to false
					sorter : false
				},
				0 : {
					// disable it by setting the property sorter to false
					sorter : false
				}
			}

		}).tablesorterPager({
			container : $("#pager")
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

function searchModels(){
	var paramsKeyValue="";
	if(document.getElementById('USER_CODE_EQ').value !=""){
		paramsKeyValue=paramsKeyValue+"&USER_CODE_EQ="+escape(document.getElementById('USER_CODE_EQ').value).replace(/\%/g,"%");
		//alert(escape(paramsKeyValue).replace(/\%/g,"%"));
	}
	if(document.getElementById('USER_NAME_LIKE').value !=""){
		paramsKeyValue=paramsKeyValue+"&USER_NAME_LIKE="+escape(document.getElementById('USER_NAME_LIKE').value).replace(/\%/g,"%");
		//alert(escape(paramsKeyValue).replace(/\%/g,"%"));
	}
	if(document.getElementById('USER_EFF_DATE_GE').value !=""){
		//alert(document.getElementById('USER_EFF_DATE_GE').value);
		paramsKeyValue=paramsKeyValue+"&USER_EFF_DATE_GE="+document.getElementById('USER_EFF_DATE_GE').value;
		//alert(paramsKeyValue);
	}
	if(document.getElementById('USER_EFF_DATE_LE').value !=""){
		//alert(document.getElementById('USER_EFF_DATE_LE').value);
		paramsKeyValue=paramsKeyValue+"&USER_EFF_DATE_LE="+document.getElementById('USER_EFF_DATE_LE').value;
		//alert(paramsKeyValue);
	}
	paramsKeyValue = '${ctx}/user-info.json?statusCode=303'+paramsKeyValue;
	//document.getElementById('search_a').attributes['href'].value='${ctx}/user-info.xhtml?statusCode=303'+paramsKeyValue;
	//paramsKeyValue='${ctx}/user-info.xhtml?statusCode=303'+paramsKeyValue;
	//alert(document.getElementById('search_a').attributes['href'].value);
	//alert(document.getElementById('resultSet_tbody').innerHTML);
	//document.getElementById('resultSet_tbody').innerHTML=""; 
	//document.getElementById('resultSet_tbody').innerHTML="<s:iterator value='model' var='resultSet_iterator'><tr><td>${id}</td><td>${title}</td><td>${stateDate}</td><td><a href='${ctx}/user-info/${id}'>View</a> | <a href='${ctx}/user-info/${id}/edit'>Edit</a> | <a href='${ctx}/user-info/${id}/deleteConfirm'>Delete</a></td></tr></s:iterator>";
	//alert(document.getElementById('resultSet_tbody').innerHTML);  
	//$("#resultSet_div").load(paramsKeyValue);
	//$("#resultSet_div").load(paramsKeyValue);
	
	$.ajax({
        type: 'get',
        url: paramsKeyValue,
        dataType: 'json',
		beforeSend: function(){
        	$("#large").html('');
		},
        success: function (data) {
        	var tblHead='<thead><tr><th></th><th>userName</th><th>userCode</th><th>districtId</th>'+
        		'<th>departmentId</th><th>userUpperId</th><th>userEffDate</th><th>userExpDate</th>'+
        		'<th>userFlag</th><th>Action</th></tr></thead>';
        	var tblRow ='<tbody>';

			for(var i=0;i<data.length;i++){
				tblRow += '<tr><td><input type="checkbox" value="{userId:'+data[i].id+',userName:\''+data[i].userName+'\'}" name="checkbox_id" /></td><td>'+data[i].userName+'</td><td>'+data[i].userCode+'</td>';
				tblRow += '<td>'+data[i].districtId+'</td><td>'+data[i].departmentId+'</td><td>'+data[i].userUpperId+'</td>';
				
				tblRow += '<td>'+new Date(data[i].userEffDate == null?new Date():data[i].userEffDate.time)+'</td><td>'+new Date(data[i].userExpDate == null?new Date():data[i].userExpDate.time)+'</td><td>'+data[i].userFlag+'</td>';
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
					9 : {
						// disable it by setting the property sorter to false
						sorter : false
					},
					0 : {
						// disable it by setting the property sorter to false
						sorter : false
					}
				}
			}).tablesorterPager({
				//container : $("#pager")
			});;
			//$("#large").html(tblHead+tblRow);
			//$(tblHead).appendTo("#large");
			//$(tblRow).appendTo("#large");
        },
        complete: function(){
			//
	    },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
        }
	});

}

function testCheckBox(){
	var c = [];
	var tr;
	$("input[name='checkbox_id']:checked").each(function() {
		c.push($(this).val());
		/* tr = $(this).parentNode.parentNode;
		alert(tr.cells[1].innerHTML); */
	});
	alert('Checked ' + c.join(', ') + '.');
	alert($("input[name='checkbox_id']:checked").length);

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
	if(c.length<1){
		return;
	}
	window.opener.document.getElementById("userUpperId").value=c.join(', ');
	window.opener.document.getElementById("checkboxStatus").innerHTML=userNames.join(', ');
	self.close();
}

function newModel(){
	var iTop = (window.screen.availHeight - 500) / 2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 900) / 2; //获得窗口的水平位置;

	window
			.open(
					'${ctx}/user-info/new',
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
					'${ctx}/user-info/'+idVal,
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
					'${ctx}/user-info/'+idVal+'/edit',
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
					'${ctx}/user-info/'+idVal+'/deleteConfirm',
					'winDeleteModel',
					'height=500,width=900,top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',toolbar=no,menubar=no,scrollbars=no,resizable=yes,location=no,status=no');
}


</script>

<jsp:include page="architecture/commonInclude.jsp" />
</head>
<body onload="searchModels();">
	<%
		System.out.println("user-info-index");
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
				<td class=".search_td"></td>
				<td class=".search_td"></td>
				<td class=".search_td"></td>
				<td class=".search_td"></td>
				<td class=".search_td"><a href="#" onclick="searchModels();"
					id="search_a" class="searchHref">search</a></td>
			</tr>
		</table>
	</div>
	<s:actionmessage />

	<div id='resultList' ondblclick="hiddenResultDIV();">
		<b>查询结果</b><a href="#" onclick="testCheckBox();" style="float: right;">测试复选框</a><a
			href="#" onclick="setWinOpener();" style="float: right;">选为上级用户</a><a
			href="#" onclick="newModel();" style="float: right;">new</a>
	</div>
	<div id="resultSet_div">
		<table id="large" class="tablesorter">

		</table>
		<div id="pager" class="pager" style="margin-top: 0px; float: left;">
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
