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
   		{name:'configParentId',index:'configParentId',hidden:true,editable:true, align:"right",formatter:'number', width:80,editrules:{required:true,number:true,minValue:0.01}},
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
	multiselect: true, caption: "configListUpgrade" }); 
	
jQuery("#configListUpgrade").jqGrid('navGrid','#pconfigListUpgrade',{edit:true,add:false,del:false},
		{url:'${ctx}/genfu-config/0?_method=put',reloadAfterSubmit:false},
		{},
		{url:'${ctx}/genfu-config/0?_method=DELETE&className=com.genfu.reform.model.GenfuConfig',reloadAfterSubmit:true},{multipleSearch:true, multipleGroup:false, showQuery: true});
	

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