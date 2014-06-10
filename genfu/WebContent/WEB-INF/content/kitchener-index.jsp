<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>order-item</title>
<script type="text/javascript">
jQuery("#k_process").jqGrid({
	url:'${ctx}/kitchen-process.json?style=jqGrid',
	datatype: "json", 
    //height: 'auto',
   	colNames:['订单项ID','订单ID','商品ID', '商品名称', '价格', '数量', '员工号', '更新时间'],
   	colModel:[
   		{name:'id',index:'id', width:55,search:false},
   		{name:'orderId',index:'orderId', width:55},
   		{name:'dishId',index:'dishId', width:55},
   		{name:'orderItemName',index:'orderItemName', width:100},
   		{name:'price',index:'price', width:55, align:"right",formatter:'number'},
   		{name:'amount',index:'amount', width:55, align:"right"},
   		{name:'staffNumber',index:'staffNumber', width:100},
 	   	{name:'updatedAt',index:'updatedAt', width:150,align:"center",sorttype:'datetime',
   			formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}}
   	],
   	multiselect: true,
   	caption: 'process(烹饪)',
    pager: '#pk_process'
});
jQuery("#k_process").jqGrid('navGrid','#pk_process',{edit:false,add:false,del:false},
		{url:'${ctx}/kitchen-process/0?_method=put',reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/kitchen-process',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.OrderItem',reloadAfterSubmit:false},
		{multipleSearch:true, multipleGroup:false, showQuery: true});
jQuery("#k_process").jqGrid('navButtonAdd','#pk_process',
		{caption:"加工完成，准备上菜",
			buttonicon:"ui-icon-arrowthick-1-n",
			onClickButton:function(){
				confirmkitchen('idk_process','k_waiting',k_process_arrow_n);
			} 
		});			
function k_process_arrow_n(){
	var k_processId = jQuery("#k_process").jqGrid('getGridParam','selarrrow');
	if ($.isArray(k_processId)) {k_processId = k_processId.join();}
	if(k_processId.length < 1){
		return;
	}

	$.ajax({
        type: 'POST',
        data:{orderItemId:k_processId},
        url: '${ctx}/kitchen-waiting/0?_method=put',
        dataType: 'json',
        success: function (data) {
        	jQuery("#k_process").trigger("reloadGrid");
        	jQuery("#k_waiting").trigger("reloadGrid");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}				
jQuery("#k_fruition").jqGrid({
	url:'${ctx}/kitchen-fruition.json?style=jqGrid',
	datatype: "json", 
    //height: 'auto',
   	colNames:['订单项ID','订单ID','商品ID', '商品名称', '价格', '数量', '员工号', '更新时间'],
   	colModel:[
   		{name:'id',index:'id', width:55,search:false},
   		{name:'orderId',index:'orderId', width:55},
   		{name:'dishId',index:'dishId', width:55},
   		{name:'orderItemName',index:'orderItemName', width:100},
   		{name:'price',index:'price', width:55, align:"right",formatter:'number'},
   		{name:'amount',index:'amount', width:55, align:"right"},
   		{name:'staffNumber',index:'staffNumber', width:100},
 	   	{name:'updatedAt',index:'updatedAt', width:150,align:"center",sorttype:'datetime',
   			formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}}
   	],
    caption: 'fruition(享用)',
    pager: '#pk_fruition'
});
jQuery("#k_fruition").jqGrid('navGrid','#pk_fruition',{edit:false,add:false,del:false},
		{url:'${ctx}/kitchen-fruition/0?_method=put',reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/kitchen-fruition',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.OrderItem',reloadAfterSubmit:false},
		{multipleSearch:true, multipleGroup:false, showQuery: true});		
jQuery("#k_open").jqGrid({
	url:'${ctx}/kitchen-open.json?style=jqGrid',
	datatype: "json", 
    //height: 'auto',
   	colNames:['订单项ID','订单ID','商品ID', '商品名称', '价格', '数量', '员工号', '更新时间'],
   	colModel:[
   		{name:'id',index:'id', width:55,search:false},
   		{name:'orderId',index:'orderId', width:55},
   		{name:'dishId',index:'dishId', width:55},
   		{name:'orderItemName',index:'orderItemName', width:100},
   		{name:'price',index:'price', width:55, align:"right",formatter:'number'},
   		{name:'amount',index:'amount', width:55, align:"right"},
   		{name:'staffNumber',index:'staffNumber', width:100},
 	   	{name:'updatedAt',index:'updatedAt', width:150,align:"center",sorttype:'datetime',
   			formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}}
   	],
   	multiselect: true,
    caption: 'open(未开始)',
    pager: '#pk_open'
});
jQuery("#k_open").jqGrid('navGrid','#pk_open',{edit:false,add:false,del:false},
		{url:'${ctx}/kitchen-open/0?_method=put',reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/kitchen-open',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/kitchen-open/0?_method=DELETE&className=com.genfu.reform.model.OrderItem',reloadAfterSubmit:false},
		{multipleSearch:true, multipleGroup:false, showQuery: true});
jQuery("#k_open").jqGrid('navButtonAdd','#pk_open',
		{caption:"开始加工",
			buttonicon:"ui-icon-arrowthick-1-n",
			onClickButton:function(){
				confirmkitchen('idarrow_n','k_process',k_open_arrow_n);
			} 
		});	

function confirmkitchen(theId,gID,operate,p){
	p = $.extend(true, {
		top : 320,
		left: 370,
		width: 240,
		height: 'auto',
		dataheight : 'auto',
		modal: false,
		overlay: 30,
		drag: true,
		resize: true,
		url : '',
		mtype : "POST",
		reloadAfterSubmit: true,
		beforeShowForm: null,
		beforeInitData : null,
		afterShowForm: null,
		beforeSubmit: null,
		onclickSubmit: null,
		afterSubmit: null,
		jqModal : true,
		closeOnEscape : false,
		delData: {},
		delicon : [],
		cancelicon : [],
		onClose : null,
		ajaxDelOptions : {},
		processing : false,
		serializeDelData : null,
		useDataProxy : false
	}, $.jgrid.col, p ||{});
	var $t = jQuery("#"+theId),onCS = {},
	dtbl = "gConTbl_"+$.jgrid.jqID(gID),postd, idname, opers, oper,
	dtbl_id = "gConTbl_" + gID,
	IDs = {themodal:'gConmod'+gID,modalhead:'gConhd'+gID,modalcontent:'gConcnt'+gID, scrollelm: dtbl};
	//debugger;
	var theOperate = $.isFunction( operate );
	//onOperate.call($t, $("#"+frmgr), frmoper); 
	if ( $("#"+$.jgrid.jqID(IDs.themodal))[0] !== undefined ) {
		$("#DelData>td","#"+dtbl).text('rowids');
		$("#DelError","#"+dtbl).hide();
		$.jgrid.viewModal("#"+$.jgrid.jqID(IDs.themodal),{gbox:"#gbox_"+$.jgrid.jqID(gID),jqm:true,jqM: false, overlay: 30, modal:false});
	} else {
		var dh = "80px",
		dw = "250px",
		tbl = "<div id='"+dtbl_id+"' class='formdata' style='width:"+dw+";overflow:auto;position:relative;height:"+dh+";'>";
		tbl += "<table class='DelTable'><tbody>";
		// error data
		tbl += "<tr id='DelError' style='display:none'><td class='ui-state-error'></td></tr>";
		tbl += "<tr id='DelData' style='display:none'><td >"+'rowids'+"</td></tr>";
		tbl += "<tr><td class=\"delmsg\" style=\"white-space:pre;\">"+"确定执行此操作吗？"+"</td></tr><tr><td >&#160;</td></tr>";
		// buttons at footer
		tbl += "</tbody></table></div>";
		var bS  = "<a id='dData' class='fm-button ui-state-default ui-corner-all'>"+p.bSubmit+"</a>",
		bC  = "<a id='eData' class='fm-button ui-state-default ui-corner-all'>"+p.bCancel+"</a>";
		tbl += "<table cellspacing='0' cellpadding='0' border='0' class='EditTable' id='"+dtbl+"_2'><tbody><tr><td><hr class='ui-widget-content' style='margin:1px'/></td></tr><tr><td class='DelButton EditButton'>"+bS+"&#160;"+bC+"</td></tr></tbody></table>";
		p.gbox = "#gbox_"+$.jgrid.jqID(gID);
		$.jgrid.createModal(IDs,tbl,p,"#gview_"+gID,$("#gview_"+gID)[0]);

		/*$(".fm-button","#"+dtbl+"_2").hover(
			function(){$(this).addClass('ui-state-hover');},
			function(){$(this).removeClass('ui-state-hover');}
		);*/
		p.delicon = [true,"left","ui-icon-check"];
		p.cancelicon = [true,"left","ui-icon-cancel"];
		if(p.delicon[0]===true) {
			$("#dData","#"+dtbl+"_2").addClass(p.delicon[1] === "right" ? 'fm-button-icon-right' : 'fm-button-icon-left')
			.append("<span class='ui-icon "+p.delicon[2]+"'></span>");
		}
		if(p.cancelicon[0]===true) {
			$("#eData","#"+dtbl+"_2").addClass(p.cancelicon[1] === "right" ? 'fm-button-icon-right' : 'fm-button-icon-left')
			.append("<span class='ui-icon "+p.cancelicon[2]+"'></span>");
		}
		$("#dData","#"+dtbl+"_2").click(function(){

			if(theOperate){
				operate.call();
			}

			//$("#dData", "#"+dtbl+"_2").removeClass('ui-state-active');
			$.jgrid.hideModal("#"+$.jgrid.jqID(IDs.themodal),{gb:"#gbox_"+$.jgrid.jqID(gID),jqm:true, onClose: null});
		
			return false;
		});
		$("#eData", "#"+dtbl+"_2").click(function(){
			$.jgrid.hideModal("#"+$.jgrid.jqID(IDs.themodal),{gb:"#gbox_"+$.jgrid.jqID(gID),jqm:true, onClose: null});
			return false;
		});
		$.jgrid.viewModal("#"+$.jgrid.jqID(IDs.themodal),{gbox:"#gbox_"+$.jgrid.jqID(gID),jqm:true, overlay:30, modal:false});
	}
}
function k_open_arrow_n(){
	var k_openId = jQuery("#k_open").jqGrid('getGridParam','selarrrow');
	if ($.isArray(k_openId)) {k_openId = k_openId.join();}
	if(k_openId.length < 1){
		return;
	}

	$.ajax({
        type: 'POST',
        data:{orderItemId:k_openId},
        url: '${ctx}/kitchen-process/0?_method=put',
        dataType: 'json',
        success: function (data) {
        	jQuery("#k_process").trigger("reloadGrid");
        	jQuery("#k_open").trigger("reloadGrid");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}			
		
jQuery("#k_waiting").jqGrid({
	url:'${ctx}/kitchen-waiting.json?style=jqGrid',
	datatype: "json", 
    //height: 'auto',
   	colNames:['订单项ID','订单ID','商品ID', '商品名称', '价格', '数量', '员工号', '更新时间'],
   	colModel:[
   		{name:'id',index:'id', width:55,search:false},
   		{name:'orderId',index:'orderId', width:55},
   		{name:'dishId',index:'dishId', width:55},
   		{name:'orderItemName',index:'orderItemName', width:100},
   		{name:'price',index:'price', width:55, align:"right",formatter:'number'},
   		{name:'amount',index:'amount', width:55, align:"right"},
   		{name:'staffNumber',index:'staffNumber', width:100},
 	   	{name:'updatedAt',index:'updatedAt', width:150,align:"center",sorttype:'datetime',
   	   		formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}}
   	],
    caption: 'waiting(完成，待上蔡)',
    pager: '#pk_waiting'
});
jQuery("#k_waiting").jqGrid('navGrid','#pk_waiting',{edit:false,add:false,del:false},
		{url:'${ctx}/kitchen-process/0?_method=put',reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/kitchen-process',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true},
		{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.OrderItem',reloadAfterSubmit:false},
		{multipleSearch:true, multipleGroup:false, showQuery: true});
/* jQuery("#k_waiting").jqGrid('navButtonAdd','#pk_waiting',
		{caption:"开始上菜",
			buttonicon:"ui-icon-arrowthick-1-n",
			onClickButton:function(){
				confirmkitchen('idk_waiting','k_waiting',k_waiting_arrow_n);
			} 
		}); */			
function k_waiting_arrow_n(){
	var k_waitingId = jQuery("#k_waiting").jqGrid('getGridParam','selarrrow');
	if ($.isArray(k_waitingId)) {k_waitingId = k_waitingId.join();}
	if(k_waitingId.length < 1){
		return;
	}

	$.ajax({
        type: 'POST',
        data:{orderItemId:k_waitingId},
        url: '${ctx}/kitchen-fruition/0?_method=put',
        dataType: 'json',
        success: function (data) {
        	jQuery("#k_fruition").trigger("reloadGrid");
        	jQuery("#k_waiting").trigger("reloadGrid");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}		
		
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
/* var mydata1 = [
    {id1:"1",name1:"test1",values1:'One'},
    {id1:"2",name1:"test2",values1:'Two'},
    {id1:"3",name1:"test3",values1:'Three'}
];
var mydata2 = [
    {id2:"11",name2:"test11",values2:'One1'},
    {id2:"21",name2:"test21",values2:'Two1'},
    {id2:"31",name2:"test31",values2:'Three1'}
];
var mydata3 = [
    {id3:"12",name3:"test12",values3:'One2'},
    {id3:"22",name3:"test22",values3:'Two2'},
    {id3:"32",name3:"test32",values3:'Three2'}
]; */
/*for (var i = 0; i <= mydata1.length; i++) {
    jQuery("#k_process").jqGrid('addRowData',i + 1, mydata1[i]);
    jQuery("#k_open").jqGrid('addRowData',i + 1, mydata2[i]);
    jQuery("#k_waiting").jqGrid('addRowData',i + 1, mydata3[i]);
}*/
jQuery("#k_process").jqGrid('gridDnD',{connectWith:'#k_open,#k_waiting',ondrop:testDrop});
jQuery("#k_open").jqGrid('gridDnD',{connectWith:'#k_process',ondrop:testDrop});
jQuery("#k_waiting").jqGrid('gridDnD',{connectWith:'#k_fruition',ondrop:testDrop});

function testDrop(jG,opts){
	//debugger;
	if(jG.target.id == 'k_waiting'){
		kWaiting(opts.draggable[0].id,'');
		//jQuery("#k_waiting").trigger("reloadGrid");
	}else if(jG.target.id == 'k_open'){
		kOpen(opts.draggable[0].id,'');
		//jQuery("#k_open").trigger("reloadGrid");
	}else if(jG.target.id == 'k_process'){
		kProcess(opts.draggable[0].id,'');
		//jQuery("#k_process").trigger("reloadGrid");
	}else if(jG.target.id == 'k_fruition'){
		kFruition(opts.draggable[0].id,'');
		//jQuery("#k_process").trigger("reloadGrid");
	}
	//alert(jG.target.id);
	//alert(opts.draggable[0].id);
}
var ctxk = '${ctx}';
function kWaiting(oItemId,oStatus){
	$.ajax({
        type: 'POST',
        //data:jsonObject,
        url: ctxk + '/kitchen-waiting/'+oItemId+'?_method=put',
        dataType: 'json',
        success: function (data) {
        	//debugger;
			jQuery("#k_process").trigger("reloadGrid");
        	jQuery("#k_waiting").trigger("reloadGrid");
        	//$("#resp_addDishToOrder").html("add dish to order successfully").css("background-color","#FFCC80");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}

function kOpen(oItemId,oStatus){
	$.ajax({
        type: 'POST',
        //data:jsonObject,
        url: ctxk + '/kitchen-open/'+oItemId+'?_method=put',
        dataType: 'json',
        success: function (data) {
        	//debugger;
			jQuery("#k_process").trigger("reloadGrid");
        	jQuery("#k_open").trigger("reloadGrid");
        	//$("#resp_addDishToOrder").html("add dish to order successfully").css("background-color","#FFCC80");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}

function kProcess(oItemId,oStatus){
	$.ajax({
        type: 'POST',
        //data:jsonObject,
        url: ctxk + '/kitchen-process/'+oItemId+'?_method=put',
        dataType: 'json',
        success: function (data) {
        	//debugger;
			jQuery("#k_open").trigger("reloadGrid");
        	jQuery("#k_process").trigger("reloadGrid");
        	//$("#resp_addDishToOrder").html("add dish to order successfully").css("background-color","#FFCC80");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}

function kFruition(oItemId,oStatus){
	$.ajax({
        type: 'POST',
        //data:jsonObject,
        url: ctxk + '/kitchen-fruition/'+oItemId+'?_method=put',
        dataType: 'json',
        success: function (data) {
        	//debugger;
			jQuery("#k_waiting").trigger("reloadGrid");
        	jQuery("#k_fruition").trigger("reloadGrid");
        	//$("#resp_addDishToOrder").html("add dish to order successfully").css("background-color","#FFCC80");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}
</script>
<s:head />
</head>
<body>
<table>
    <tbody>
        <tr>
            <td><table id="k_fruition"></table><div id="pk_fruition"></div></td>
        </tr>
        <tr>
            <td align= "center"> /\ </td>
        </tr>
        <tr>
            <td align= "center"> || </td>
        </tr>
        <tr>
            <td><table id="k_waiting"></table><div id="pk_waiting"></div></td>
        </tr>
        <tr>
            <td align= "center"> /\ </td>
        </tr>
        <tr>
            <td align= "center"> || </td>
        </tr>
        <tr>
            <td><table id="k_process"></table><div id="pk_process"></div></td>
        </tr>
        <tr>
            <td align= "center"> /\ </td>
        </tr>
        <tr>
            <td align= "center"> || </td>
        </tr>
        <tr>
            <td align= "center"> \/ </td>
        </tr>
        <tr>
            <td>
                <table id="k_open"></table>
                <div id="pk_open"></div>
            </td>
        </tr>
    </tbody>
</table>
</body>

</html>
