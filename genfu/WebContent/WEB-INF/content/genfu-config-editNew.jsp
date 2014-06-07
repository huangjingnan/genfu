<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<style type="text/css">
.bar {
    height: 18px;
    background: green;
}

</style>
<meta charset="utf-8">
<title>dish-image</title>
</head>
<body>
<input id="configUpload" type="file" name="upload" data-url="/genfu/genfu-config" multiple>
<div id="progress">
    <div class="bar" style="width: 0%;"></div>
</div>

<%-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> --%>
<script src="${ctx}/js/jQuery-File-Upload-master/js/vendor/jquery.ui.widget.js"></script>
<script src="${ctx}/js/jQuery-File-Upload-master/js/jquery.iframe-transport.js"></script>
<script src="${ctx}/js/jQuery-File-Upload-master/js/jquery.fileupload.js"></script>
<script>

function confirm_oper(theId,gID,operate,p){
	p = $.extend(true, {
		top : 0,
		left: 0,
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

jQuery("#configListUpgrade").jqGrid({ 
	url:'${ctx}/genfu-config.json?style=jqGrid',
	datatype: "json", 
	height: 250, 
	colNames:['配置标识','配置键', '配置描述', '配置扩展','创建时间','配置状态','上级标识', '生效时间', '修改时间','源','值'],
	colModel:[
   		{name:'id',index:'id', width:55,editable:true,editoptions:{readonly:true,size:10}},
   		{name:'configKey',index:'configKey', width:80,editoptions:{size:10}},
   		{name:'configDescription',index:'configDescription', width:90,hidden:true,editoptions:{size:25}},
   		{name:'configOthers',index:'configOthers', width:60,hidden:true,editoptions:{size:10}},
   		{name:'configCreatedAt',index:'configCreatedAt', width: 80,hidden:true, align: 'center', sorttype: 'date',
            formatter: 'date',editrules:{date:true,required:false}, formatoptions: {newformat: 'Y-m-d H:i:sO'}, datefmt: 'Y-m-d H:i:sO'},
   		{name:'configFlag',index:'configFlag', width:40, editable: false,align:"left",edittype:"select",editoptions:{value:"OPEN:OPEN;CLOSED:CLOSED"}},
   		{name:'configParentId',index:'configParentId',hidden:true, align:"right",formatter:'number', width:80},
   		{name:'configEffDate',index:'configEffDate',hidden:true,width:100,align:"center",formatter: { srcformat:'Y-m-d', newformat: 'd/m/Y'}},
   		{name:'configUpdatedAt',index:'configUpdatedAt',width:60,align:"center"},	
   		{name:'configSrc',index:'configSrc',hidden:true, width:100,editoptions:{size:25}},
   		{name:'configValue',index:'configValue',editable:true, width:60,editoptions:{size:100}}
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#pconfigListUpgrade',
   	sortname: 'id',
    viewrecords: true, 
	autowidth: true,
	multiselect: false, caption: "configListUpgrade" }); 
	
jQuery("#configListUpgrade").jqGrid('navGrid','#pconfigListUpgrade',{edit:true,add:false,del:false},
		{url:'${ctx}/genfu-config/0?_method=put',reloadAfterSubmit:false,width: 640},
		{},
		{url:'${ctx}/genfu-config/0?_method=DELETE&className=com.genfu.reform.model.GenfuConfig',reloadAfterSubmit:true},{multipleSearch:true, multipleGroup:false, showQuery: true});
jQuery("#configListUpgrade").jqGrid('navButtonAdd','#pconfigListUpgrade',
		{caption:"执行",
			buttonicon:"ui-icon-wrench",
			onClickButton:function(){
				confirm_oper('idWrenchConfig','configListUpgrade',wrenchConfig);
			} 
		});	
		
function wrenchConfig(){
	debugger;
	var tempUrl = '${ctx}/genfu-config/0?_method=put';
	var configId = jQuery("#configListUpgrade").jqGrid('getGridParam','selrow');
	if(configId == null){
		return;
	}
	var jsonObject = jQuery("#configListUpgrade").jqGrid('getRowData',configId[0]);
	tempUrl=tempUrl+'&jmx='+jsonObject.configKey;

	$.ajax({
        type: 'POST',
        data:jsonObject,
        url: tempUrl,
        dataType: 'json',
        success: function (data) {
        	//debugger;
        	jQuery("#configListUpgrade").trigger("reloadGrid");
        	//alert(typeof(data));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
	});
}		

$(function () {
	$('#configUpload').fileupload({
		dataType: 'json',
        add: function (e, data) {
            data.context = $('<p/>').text('Uploading...').appendTo(document.body);
            data.submit();
        },
        done: function (e, data) {
            data.context.text('Upload finished.');
        },
		progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        $('#progress .bar').css(
	            'width',
	            progress + '%'
	        );
	    }

	});
});
//var overallProgress = $('#fileupload').fileupload('progress');
//var activeUploads = $('#fileupload').fileupload('active');
</script>
<table id="configListUpgrade"></table>
<div id="pconfigListUpgrade"></div>
</body> 
</html>