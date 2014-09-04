<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>orders</title>
<script type="text/javascript">
var order_detail_url = "${ctx}/order-item.json?style=jqGrid";
//alert(order_detail_url);
var headerRowData;
jQuery("#order_header").jqGrid({
   	url:'${ctx}/order.json?style=jqGrid&status_EQ=OPEN',
	datatype: "json",
   	colNames:['订单ID','订单名称', '人数', '员工号', '数量','订单状态', '支付状态', '创建时间', '更新时间','联系电话','email','备注信息'],
   	colModel:[
   		{name:'id',index:'id', width:55, sorttype:'integer',editable:true,editoptions:{readonly:true,size:10}},
   		{name:'orderName',index:'orderName', width:90,editable:true,editrules:{required:true}},
   		{name:'numberPeople',index:'numberPeople',width:90, sorttype:'integer', align:"right",editable:true,editrules:{required:true,integer:true,minValue:1,maxValue:1000}},
   		{name:'staffNumber',index:'staffNumber', width:90},
   		{name:'amount',index:'amount', width:80, sorttype:'integer', align:"right"},
   		{name:'status',index:'status', width:90,defval:"PROCESS", editable: true,edittype:"select",editoptions:{value:"PROCESS:PROCESS;CLOSED:CLOSED"}},
   		{name:'payFlag',index:'payFlag', width:90},
   		{name:'createdAt',index:'createdAt',sorttype:'date', formatter:dishDatefmt, width:100},
   		{name:'updatedAt',index:'updatedAt', formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}, width:100},
   		{name:'phoneNumber',index:'phoneNumber',editable:true, width:80},		
   		{name:'email',index:'email', width:80,editable:true,editrules:{required:false,email:true},align:"right"},		
   		{name:'errorMessage',index:'errorMessage',sortable:false,editable:true,edittype:"textarea",editrules:{required:true},editoptions:{rows:"2",cols:"20"}}		
   	],
   	rowNum:10,
   	rowList:[10,20,30],
   	pager: '#order_header_pager',
   	sortname: 'id',
    viewrecords: true,
    sortorder: "desc",
	multiselect: true,
	multiboxonly:true,
	autowidth: true,
	caption: "订单",
	onSelectRow: function(ids) {
		if(ids == null) {
			ids=0;
			if(jQuery("#order_detail").jqGrid('getGridParam','records') >0 )
			{
				var _detail_url = order_detail_url + "&orderId=" + ids;
				jQuery("#order_detail").jqGrid('setGridParam',{url:_detail_url,page:1});
				jQuery("#order_detail").jqGrid('setCaption',"Order Detail: "+ids)
				.trigger('reloadGrid');
			}
			disableAddToOrder(true);
		} else {
			headerRowData = jQuery("#order_header").jqGrid('getRowData',ids);
			var _detail_url = order_detail_url + "&orderId=" + ids;
			jQuery("#order_detail").jqGrid('setGridParam',{url:_detail_url,page:1});
			jQuery("#order_detail").jqGrid('setCaption',headerRowData.orderName)
			.trigger('reloadGrid');
			jQuery("#orderAmount").val(headerRowData.amount);
			if("CLOSED" !== headerRowData.status && "001" !== headerRowData.payFlag && "011" !== headerRowData.payFlag){
				disableAddToOrder(false);
			}else{
				disableAddToOrder(true);
			}
		}
		clearResp();
	}
});

function dishDatefmt(cellvalue, options, rowObject)
{
	//debugger;
	if(cellvalue !== undefined && cellvalue !== null && cellvalue !== ""){
		return new Date(cellvalue.time).toLocaleString("ca");
	}else{
		return "";
	}
	//
	//return cellvalue.toLocaleString("ca");
	//return 0;
}
jQuery("#order_header").jqGrid('navGrid','#order_header_pager',
		{edit:true,add:false,del:true,beforeRefresh:clearDetailForRefresh},
		{url:'${ctx}/order/0?_method=put',reloadAfterSubmit:true,beforeInitData:ifEditAble},
		{},
		{url:'${ctx}/order/0?_method=DELETE&className=com.genfu.reform.model.Order',reloadAfterSubmit:true,beforeInitData:ifHeaderDelAble,afterSubmit:clearDetail},
		{multipleSearch:true, multipleGroup:false, showQuery: true});

function ifEditAble(){
	//alert('beforeInitData');
	var headerIds;
	headerIds = jQuery("#order_header").jqGrid('getGridParam','selarrrow');
	for(var i = 0;i<headerIds.length;i++){
		headerRowData = jQuery("#order_header").jqGrid('getRowData',headerIds[i]);
		if("CLOSED" == headerRowData.status || "000" !== headerRowData.payFlag){
			$("#resp_header").html(headerRowData.id + ",status == \"CLOSED\" || payFlag != \"000\"").css("color","red");
			return false;
			//disableAddToOrder(true);
		}
	}
	return true;
	//if("CLOSED" == headerRowData.status){
		//$("#resp_header").html("status == \"CLOSED\"").css("color","red");
		//return false;
		//disableAddToOrder(true);
	//}else{
		//return true;
		//disableAddToOrder(false);
	//}
}

function ifHeaderDelAble(){
	//alert('beforeInitData');
	var headerIds;
	headerIds = jQuery("#order_header").jqGrid('getGridParam','selarrrow');
	//alert(s);
	//debugger;
	for(var i = 0;i<headerIds.length;i++){
		headerRowData = jQuery("#order_header").jqGrid('getRowData',headerIds[i]);
		if("OPEN" !== headerRowData.status || "000" !== headerRowData.payFlag){
			$("#resp_header").html(headerRowData.id + ",status != \"OPEN\" || payFlag != \"000\"").css("color","red");
			return false;
			//disableAddToOrder(true);
		}
	}
	return true;
	//$("#resp_header").html("I'm row number: and setted dynamically").css("color","red");
	//return false;
}

function clearDetail(){
	clearDetailForRefresh();
	return [true,""];
}

function clearDetailForRefresh(){
	clearResp();
	disableAddToOrder(true);
	jQuery("#order_detail").jqGrid('setGridParam',{url:order_detail_url+"&orderId=0",page:1}).trigger('reloadGrid');
}

function mysum(val, name, record)
{
	//debugger;
	//val = val - 0.00;
	return parseFloat(val||0) + parseFloat(record['price'] * record['amount']);
	//return 0;
}
jQuery("#order_detail").jqGrid({
   	url:'${ctx}/order-item.json?style=jqGrid&orderId=0',
	datatype: "json", 
	//height: 290, 
   	colNames:['订单项ID','订单ID','商品ID', '商品名称', '状态', '工号', '价格', '数量', '合计', '创建时间', '更新时间'],
   	colModel:[
   		{name:'id',index:'id', width:55, sorttype:'integer',search:false},
   		{name:'orderId',index:'orderId', sorttype:'integer', width:55},
   		{name:'dishId',index:'dishId', sorttype:'integer', width:55},
   		{name:'orderItemName',index:'orderItemName', width:100,editable:true,editoptions:{readonly:true}},
   		{name:'status',index:'status', width:100,editable:true,editoptions:{readonly:true}},
   		{name:'staffNumber',index:'staffNumber', width:100,editable:true,editoptions:{readonly:true}},
   		{name:'price',index:'price', width:55, align:"right", width:100,sorttype:'currency',formatter:'currency',editable:true,editoptions:{readonly:true}},
   		{name:'amount',index:'amount', width:55, sorttype:'integer', align:"right",editable:true,
   			editrules:{required:true,integer:true,minValue:1,maxValue:1000}, summaryType:'sum'},
   		{name:'total',index:'total', width:55, align:"right", width:100,search:false,
   			cellattr: function(rowId, value, rowObject, colModel, arrData) {
				return ' colspan=1';
			},
			formatter : function(value, options, rData){
				//parseFloat(tempSum).toFixed(2)
				//debugger;
				if(value !== undefined && value !== 'undefined'){
					return parseFloat(value).toFixed(2);
				}
				return parseFloat(rData['price'] * rData['amount']).toFixed(2);
			}, summaryType:mysum
   		},
   		{name:'createdAt',index:'createdAt', width:110,align:"center",sorttype:'datetime',
   			formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}},
 	   	{name:'updatedAt',index:'updatedAt', width:110,align:"center",sorttype:'datetime',
   			formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}}
   	],
   	rowNum:2000,
   	rowList:[50,100,200],
   	pager: '#order_detail_pager',
   	sortname: 'id',
    viewrecords: true,
    sortorder: "asc",
	multiselect: true, 
	autowidth: true,
	grouping:true,
	groupingView : {
   		groupField : ['orderId'],
   		groupSummary : [true],
   		groupColumnShow : [false],
   		groupText : ['<b>{0}</b>'],
   		groupCollapse : false,
		groupOrder: ['asc']
   	},
	//footerrow : true,
	//userDataOnFooter: false,
	caption:"订单项"
});
function myDatefmt(cellvalue, options, rowObject)
{
	return cellvalue.toLocaleString();
	//return 0;
}
jQuery("#order_detail").jqGrid('navGrid','#order_detail_pager',{edit:true,add:false,del:true},
		{url:'${ctx}/order-item/0?_method=put',reloadAfterSubmit:false,beforeInitData:ifDetailEditAble},
		{},
		{url:'${ctx}/order-item/0?_method=DELETE&className=com.genfu.reform.model.OrderItem',reloadAfterSubmit:true,beforeInitData:ifDetailDelAble},{multipleSearch:true, multipleGroup:false, showQuery: true});

function ifDetailEditAble(){
	//alert('beforeInitData');
	if("CLOSED" == headerRowData.status || "001" == headerRowData.payFlag || "011" == headerRowData.payFlag){
		$("#resp_detail").html(headerRowData.id + ",status == \"CLOSED\" || \"000\" != payFlag").css("color","red");
		return false;
		//disableAddToOrder(true);
	}else{
		return true;
		//disableAddToOrder(false);
	}
	//$("#resp_header").html("I'm row number: and setted dynamically").css("color","red");
	//return false;
}


function ifDetailDelAble(){
	//alert('beforeInitData');
	if("CLOSED" == headerRowData.status || "001" == headerRowData.payFlag || "011" == headerRowData.payFlag){
		$("#resp_detail").html("status == \"CLOSED\" || \"000\" != payFlag").css("color","red");
		return false;
		//disableAddToOrder(true);
	}else{
		return true;
		//disableAddToOrder(false);
	}
	//$("#resp_header").html("I'm row number: and setted dynamically").css("color","red");
	//return false;
}

function clearResp(){
	$("#resp_header").html("");
	$("#resp_detail").html("");
	$("#resp_addDishToOrder").html("");
}


jQuery("#order_dishes").jqGrid({ 
	url:'${ctx}/dish.json?style=jqGrid',
	datatype: "json",
	colNames:['商品ID','商品名称', 'isbn', 'masterId','publishedAt','状态','价格', '创建时间', '更新时间','图片信息','图片文件','标签','描述'],
	colModel:[
   		{name:'id',index:'id', width:55,editable:true, sorttype:'integer',editoptions:{readonly:true,size:10}},
   		{name:'dishName',index:'dishName', width:150,editable:true,editoptions:{size:10}},
   		{name:'isbn',index:'isbn', width:90,editable:true,hidden:true,editoptions:{size:25}},
   		{name:'publishedId',index:'publishedId', width:60,editable:false, hidden:true,editoptions:{size:10}},
   		{name:'publishedAt',index:'publishedAt', width: 80, hidden:true, align: 'center', sorttype: 'date',
            formatter: 'date',editrules:{date:true,required:false}, formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},
   		{name:'dishFlag',index:'dishFlag', width:100, editable: true,edittype:"select",editoptions:{value:"OPEN:OPEN;CLOSED:CLOSED"}},
   		{name:'price',index:'price',editable:true, align:"right",sorttype:'currency',formatter:'currency', width:100,editrules:{required:true,number:true,minValue:0.01}},
   		{name:'createdAt',index:'createdAt', width:90, align:"center", hidden:true, sorttype: 'date',
            formatter: 'date', formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},
   		{name:'updatedAt',index:'updatedAt',width:90,align:"center", hidden:true,sorttype:'date',formatter:'date', formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},	
   		{name:'coverImage',index:'coverImage',hidden:true,width:170},	
   		{name:'fileImage',index:'fileImage',width:70,editable:true,editrules:{edithidden:true,required:false}, hidden:true,edittype:"file"},
   		{name:'taggings',index:'taggings',width:200,formatter:tagsFmatter},
   		{name:'blurb',index:'blurb',width:250,sortable:false,editable:true,edittype:"textarea",editoptions:{rows:"2",cols:"20"}}	
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#order_dish_pager',
   	sortname: 'id',
    viewrecords: true, 
	autowidth: true,
	multiselect: true, caption: "商品" }); 
jQuery("#order_dishes").jqGrid('navGrid','#order_dish_pager',{edit:false,add:false,del:false},
		{url:'${ctx}/dish/0?_method=put',reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/dish',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.Dish',reloadAfterSubmit:false},
		{multipleSearch:true, multipleGroup:false, showQuery: true});
		
function tagsFmatter ( cellvalue, options, rowObject ){
	var tagNames="";
	//debugger;
	if(cellvalue !== undefined && cellvalue !== null && cellvalue !== ""){
		for (var i=0;i<cellvalue.length;i++){
			tagNames=tagNames+','+cellvalue[i].tag.name;
			//alert(cellvalue[i].tag.name);
		}
	}
	return tagNames.substring(1);
}

var timeoutHnd;
var flAuto = false;

function doSearch(ev){
	if(!flAuto)
		return;
//	var elem = ev.target||ev.srcElement;
	if(timeoutHnd)
		clearTimeout(timeoutHnd);
	timeoutHnd = setTimeout(gridReload,1000);
}
var order_dishes_url = "${ctx}/dish.json?style=jqGrid";
var preCD_mask = "";
function gridReload(){
	//var nm_mask = jQuery("#item_nm").val();
	var cd_mask = jQuery("#search_cd").val();

	var paramsKeyValue = "";
	if(escape(cd_mask) !== '%20' && preCD_mask !== cd_mask){
		preCD_mask = cd_mask;
		//cd_mask = encodeURIComponent(cd_mask);
		//alert(cd_mask);
		//encodeURI(":");
		if(cd_mask !== ""){
			//paramsKeyValue = encodeURI(order_dishes_url +"&cd_mask="+cd_mask);
			paramsKeyValue = order_dishes_url +"&cd_mask="+encodeURIComponent(encodeURIComponent(cd_mask));
			//alert(paramsKeyValue);
			jQuery("#order_dishes").jqGrid('setGridParam',{url:paramsKeyValue,page:1}).trigger("reloadGrid");
		}else{
			jQuery("#order_dishes").jqGrid('setGridParam',{url:order_dishes_url,page:1}).trigger("reloadGrid");
		}
	}
	//alert(nm_mask);
	//alert(cd_mask);
	//jQuery("#order_dishes").jqGrid('setGridParam',{url:"${ctx}/dish.json?style=jqGrid&nm_mask="+escape(nm_mask)+"&cd_mask="+escape(cd_mask),page:1}).trigger("reloadGrid");
}
function enableAutosubmit(state){
	flAuto = state;
	jQuery("#submitButton").attr("disabled",state);
}

function disableAddToOrder(state){
	jQuery("#addDishToOrder").attr("disabled",state);
	jQuery("#orderAmount").attr("disabled",state);
}
disableAddToOrder(true);

function addDishToOrder(){
	//debugger;
		var reg = /^[1-9]\d{0,2}/;
		if (!reg.test($("#orderAmount").val())) {
			jQuery("#orderAmount").attr('style','border-color: #FF0000;border-style: solid;');
			$("#orderAmount").val(1);
			return;
		} else {
			jQuery("#orderAmount").removeAttr("style");
		}
	var sr = $("#order_dishes")[0].p.selrow;
	var urls = '${ctx}/order-dish/'+headerRowData.id+'?_method=put';
	if (sr) {
		//alert(headerRowData.id);
		var dishesId = jQuery("#order_dishes").jqGrid('getGridParam','selarrrow');
		//if ($.isArray(dishesId)) {dishesId = dishesId.join();}
		
		var jsonObject = {};
		if(dishesId !== undefined && dishesId.length > 0){
			jsonObject["dishIds"]=dishesId;
			jsonObject["orderAmount"]=$("#orderAmount").val();
		} 
		$.ajax({
	        type: 'POST',
	        data:jsonObject,
	        url: urls,
	        dataType: 'json',
	        success: function (data) {
	        	//debugger;
	        	jQuery("#order_detail").trigger("reloadGrid");
	        	$("#resp_addDishToOrder").text("add dish to order successfully").addClass( "ui-state-highlight" );
	    		setTimeout(function() {
	    			$( "#resp_addDishToOrder" ).removeClass( "ui-state-highlight", 1500 );
	    		}, 500 );
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert(errorThrown);
	        }
		});
	} else {
		if ($("#alertmod_order_dish")[0] === undefined) {
			$.jgrid.createModal({themodal: 'alertmod_order_dish', modalhead: 'alerthd_order_dish',modalcontent: 'alertcnt_order_dish'},
				"<div>Please, select dishes</div><span tabindex='0'><span tabindex='-1' id='jqg_alrt_order_dish'></span></span>",
				{ 
					gbox:"#gbox_order_dish",
					jqModal:true,
					drag:true,
					resize:true,
					caption:'Warning',
					top:359,
					left:520,
					width:200,
					height:'auto',
					closeOnEscape:true, 
					zIndex: null
				},
				"#gview_order_dish",
				$("#gbox_order_dish")[0],
				true
			);
		}
		$.jgrid.viewModal("#alertmod_order_dish",{gbox:"#gbox_"+$.jgrid.jqID('order_dish'),jqm:true});
		$("#jqg_alrt_order_dish").focus();
	}
}
var waitLoad = false;
function callback() {
	/*setTimeout(function() {
		$( "#cart_setting:visible" ).removeAttr( "style" ).fadeIn();
	}, 1000 );*/
	setTimeout(function() {
		//$( "#orderPlace" ).removeAttr( "disabled" ).hide().fadeIn();
		waitLoad = false;
	}, 3000 );
};
jQuery("#order_header").jqGrid('navButtonAdd','#order_header_pager',{caption:"付款结算",
	buttonicon:"ui-icon-cart",
	onClickButton:function(){
		var headerIds;
		headerIds = jQuery("#order_header").jqGrid('getGridParam','selarrrow');
		if(headerIds.length>0){
			for(var i = 0;i<headerIds.length;i++){
				headerRowData = jQuery("#order_header").jqGrid('getRowData',headerIds[i]);
				if("000" !== headerRowData.payFlag){
					$("#resp_header").html(headerRowData.id + ",payFlag != \"000\"").css("color","red");
					return false;
					//disableAddToOrder(true);
				}
			}
			if(!waitLoad){
				waitLoad = true;
				if ($.isArray(headerIds)) {headerIds = headerIds.join();}
				jQuery.ajax({
					type : 'GET',
					url : '${ctx}/account/new',
					data : {orderId:headerIds},
					success : function(data, result) {
						//debugger;
						$( "#accOrderList" ).html("");
						var totalSum = 0.00;
						var tempAccOrderId = "";
						for(var i=0;i<data["orderList"].length;i++){
							tempAccOrderId = tempAccOrderId + "," + data["orderList"][i].id;
							var tempSum = 0.00;
							for(var j=0;j<data["orderList"][i].orderItems.length;j++){
								//计算总额
								tempSum = tempSum + data["orderList"][i].orderItems[j].price*data["orderList"][i].orderItems[j].amount;
							}
							totalSum = totalSum + tempSum;
							$( "#accOrderList" ).append("<li>"+data["orderList"][i].id+"#，小计："+parseFloat(tempSum).toFixed(2)+"</li>");
						}
						$( "#accOrderId" ).val(tempAccOrderId.substring(1));
						//debugger;
						//("总计："+parseFloat(totalSum).toFixed(2)).insertAfter($( "#accOrderList" ));
						$( ".accountTotal" ).text("总计："+parseFloat(totalSum).toFixed(2));
						$( "#receivable" ).val(parseFloat(totalSum).toFixed(2));
						//$( "#accOrderList" ).html("<li>xuzhenfu</li><li>xuzhenfu2</li><li>xuzhenfu2</li>");
						$( "#accOrder-form" ).dialog( "open" );
					},
					error : function() {
						alert('Error: Could not load account new. Please check the URL and try again. ');
					},
					complete : function() {
						//content.hide();
					}
				});
				callback();
			}
			
		}else{
			return false;
		}
		/*if ($("#account_order")[0] === undefined) {
			$.jgrid.createModal({themodal: 'account_order', modalhead: 'alerthd_order_dish',modalcontent: 'alertcnt_order_dish'},
				"<div>Please, select dishes</div><span tabindex='0'><span tabindex='-1' id='jqg_account_order'></span></span>",
				{ 
					gbox:"#gbox_order_dish",
					jqModal:true,
					drag:true,
					resize:true,
					caption:'Warning',
					top:359,
					left:520,
					width:200,
					height:'auto',
					closeOnEscape:true, 
					zIndex: null
				},
				"#gview_order_dish",
				$("#gbox_order_dish")[0],
				true
			);
		}
		$.jgrid.viewModal("#account_order",{gbox:"#gbox_"+$.jgrid.jqID('order_dish'),jqm:true});
		$("#jqg_account_order").focus();
		window
		.open('${ctx}/account/new?orderId=35,36,37',
				'winNewAccount',
				'height=500,width=900,top=300,left=100,toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no');*/
	
	} 
});
function figureChange(){
	var reg = new RegExp("^[0-9]+(.[0-9]{2})?$");
	if(!reg.test($("#received").val())){
		$("#received").addClass( "ui-state-error" );
		$( ".accValidTips" ).text( "error num" ).addClass( "ui-state-highlight" );
		setTimeout(function() {
			$( ".accValidTips" ).removeClass( "ui-state-highlight", 1500 );
		}, 500 );
	}else{
		$("#received").removeClass( "ui-state-error" );
		$( ".accValidTips" ).text( "需要填写实收。" );
		$("#received").val(parseFloat($("#received").val()).toFixed(2));
		$("#change").val(parseFloat($("#received").val()-$("#receivable").val()).toFixed(2));
	}
	//alert(0);
}
$(function() {
	/* $( "#slider-range-min" ).slider({
		range: "min",
		value: 77,
		min: 1,
		max: 100,
		slide: function( event, ui ) {
			$( "#receivable" ).val( "$" + ui.value );
		}
    });
    $( "#receivable" ).val( "$" + $( "#slider-range-min" ).slider( "value" ) ); */


	
	var receivable = $( "#receivable" ),
		received = $( "#received" ),
		change = $( "#change" ),
		accountRemarks = $( "#accountRemarks" ),
		allFields = $( [] ).add( receivable ).add( received ).add( change ).add( accountRemarks ),
		tips = $( ".accValidTips" );

	function updateTips( t ) {
		tips
			.text( t )
			.addClass( "ui-state-highlight" );
		setTimeout(function() {
			tips.removeClass( "ui-state-highlight", 1500 );
		}, 500 );
	}

	function checkLength( o, n, min, max ) {
		if ( o.val().length > max || o.val().length < min ) {
			o.addClass( "ui-state-error" );
			updateTips( "Length of " + n + " must be between " +
				min + " and " + max + "." );
			return false;
		} else {
			return true;
		}
	}

	function checkRegexp( o, regexp, n ) {
		if ( !( regexp.test( o.val() ) ) ) {
			o.addClass( "ui-state-error" );
			updateTips( n );
			return false;
		} else {
			return true;
		}
	}
	
	function checkEqCR( o1, o2, n ) {
		//debugger;
		if ( o1.val() != receivable.val()-o2.val() ) {
			o1.addClass( "ui-state-error" );
			updateTips( n );
			return false;
		} else {
			return true;
		}
	}	

	$( "#accOrder-form" ).dialog({
		autoOpen: false,
		height: 430,
		width: 350,
		modal: true,
		buttons: {
			"确定支付": function() {
				//debugger;
				var bValid = true;
				allFields.removeClass( "ui-state-error" );

				bValid = bValid && checkLength( receivable, "应收", 1, 10 );
				bValid = bValid && checkLength( received, "实收", 1, 10 );
				bValid = bValid && checkLength( change, "找零", 1, 10 );
				bValid = bValid && checkLength( accountRemarks, "备注", 1, 160 );

				bValid = bValid && checkRegexp( received, /^[0-9]+(.[0-9]{2})?$/, "请正确填写实收，精确到小数点后2位" );
				bValid = bValid && checkEqCR( change, received, "请重新填写实收！" );
				//bValid = bValid && checkRegexp( received, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
				// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/change_address_validation/
				//bValid = bValid && checkRegexp( change, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
				//bValid = bValid && checkRegexp( accountRemarks, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );

				if ( bValid ) {
					//debugger;
					//验证通过-提交
					jQuery.ajax({
						type : 'POST',
						url : '${ctx}/account',
						data : $('#accountOrder').serialize(),
						success : function(data) {
							//debugger;
				        	jQuery("#order_header").trigger("reloadGrid");
				        	$("#resp_header").text("pay successfully").addClass( "ui-state-highlight" );
				    		setTimeout(function() {
				    			$( "#resp_header" ).removeClass( "ui-state-highlight", 1500 );
				    		}, 500 );
						},
						error : function() {
							alert('Error: Could not load account. Please check the URL and try again. ');
						},
						complete : function() {
							//content.hide();
							allFields.val( "" ).removeClass( "ui-state-error" );
						}
					});
					
					$( this ).dialog( "close" );
				}
			},
			"取消": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			//allFields.val( "" ).removeClass( "ui-state-error" );
		}
	});

	/*$( "#create-user" )
		.button()
		.click(function() {
			$( "#accOrder-form" ).dialog( "open" );
		});*/
});

</script>

<style>
		.ui-dialog .ui-state-error { padding: .3em; }
		.accValidTips { border: 1px solid transparent; padding: 0.3em; }
</style>
<s:head />
</head>
<body>
<table id="order_header"></table>
<div id="order_header_pager"></div>
<b>提示:</b> <span id="resp_header"></span>
<br />
<table id="order_detail"></table>
<div id="order_detail_pager"></div>	
<b>提示:</b> <span id="resp_detail"></span>
<br />
<div class="h">查    找:</div>
<div>
	<input type="checkbox" id="autosearch" onclick="enableAutosubmit(this.checked)"> 开启自动查询 <br/>
	商品名
	<input type="text" id="search_cd" onkeydown="doSearch(arguments[0]||event)" onchange=""/>
	<!-- blurb
	<input type="text" id="item_nm" onkeydown="doSearch(arguments[0]||event)" onchange="" /> -->
	<button onclick="gridReload()" id="submitButton" style="margin-right:30px;margin-left:30px;">查询</button>
	数量
	<input type="text" id="orderAmount" />
	<input type="BUTTON" onclick="addDishToOrder()" id="addDishToOrder" value="添加到订单" style="margin-left:30px;margin-right:30px;" /><span id="resp_addDishToOrder"></span>
</div>

<table id="order_dishes"></table>
<div id="order_dish_pager"></div>	

<div id="accOrder-form" title="结算">
	<p class="accValidTips">需要填写实收。</p>
	<ol id="accOrderList"></ol>
	<p class="accountTotal"></p>
	<form id="accountOrder">
	<input type="hidden" id="accOrderId" name="accOrderId" value="" />
	<table>
		<tr><td><label for="receivable">应收</label></td><td>
		<input type="text" name="receivable" id="receivable" value="" class="text ui-widget-content ui-corner-all" readonly="readonly"/></td></tr>
		<tr><td><label for="received">实收</label></td><td>
		<input type="text" name="received" id="received" value="" class="text ui-widget-content ui-corner-all" onblur="figureChange();"/></td></tr>
		<tr><td><label for="change">找零</label></td><td>
		<input type="text" name="change" id="change" value="" class="text ui-widget-content ui-corner-all" readonly="readonly"/></td></tr>
		<tr><td><label for="accountRemarks">备注</label></td><td>
		<textarea name="accountRemarks" id="accountRemarks" class="text ui-widget-content ui-corner-all"></textarea></td></tr>
	</table>
	</form>
</div>

</body>

</html>