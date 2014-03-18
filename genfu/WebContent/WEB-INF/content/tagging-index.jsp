<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tagging</title>
<script src="${ctx}/js/uploadfile/jquery.form.js" type="text/javascript"></script>
<script type="text/javascript">

	jQuery("#tagging_tags").jqGrid({ 
	url:'/genfu/tag.json?style=jqGrid',
	datatype: "json", 
	colNames:['标签ID','标签名称','展示顺序'],
	colModel:[
   		{name:'id',index:'id', width:120,editable:true,editoptions:{readonly:true,size:10}},
   		{name:'name',index:'name', width:120,editable:true,editrules:{required:true}},
   		{name:'tagSn',index:'tagSn', width:120,editable:true}
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#tagging_tag_pager',
   	sortname: 'id',
    viewrecords: true, 
	autowidth: true,
	multiselect: true, caption: "标签"
}); 

	jQuery("#tagging_tags").jqGrid('navGrid','#tagging_tag_pager',{},
			{url:'${ctx}/tag/0?_method=put',reloadAfterSubmit:false},
			{url:'${ctx}/tag',clearAfterAdd:false,reloadAfterSubmit:false},
			{url:'${ctx}/tag/0?_method=DELETE&className=com.genfu.reform.model.Tag',reloadAfterSubmit:false},
			{multipleSearch:true, multipleGroup:false, showQuery: false});
	//jQuery("#tagging_tag_pager").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	
	var tags = { "groupOp": "AND", "rules": [ { "field": "taggings.name", "op": "bw", "data": "a" } ] } ;
	
	jQuery("#tagging_dishes").jqGrid({ 
	url:'/genfu/dish.json?style=jqGrid',
	datatype: "json",
	colNames:['商品ID','商品名称', 'isbn', 'masterId','publishedAt','状态','价格', '创建时间', '更新时间','图片信息','图片文件','标签','描述'],
	colModel:[
   		{name:'id',index:'id', width:55,editable:true,editoptions:{readonly:true,size:10}},
   		{name:'dishName',index:'dishName', width:150,editable:true,editoptions:{size:10}},
   		{name:'isbn',index:'isbn', width:90,editable:true,hidden:true,editoptions:{size:25}},
   		{name:'publishedId',index:'publishedId', width:60,editable:false, hidden:true,editoptions:{size:10}},
   		{name:'publishedAt',index:'publishedAt', width: 80, hidden:true, align: 'center', sorttype: 'date',
            formatter: 'date',editrules:{date:true,required:false}, formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},
   		{name:'dishFlag',index:'dishFlag', width:80, editable: true,align:"left",edittype:"select",editoptions:{value:"OPEN:OPEN;CLOSED:CLOSED"}},
   		{name:'price',index:'price',editable:true, align:"right",formatter:'number', width:80,editrules:{required:true,number:true,minValue:0.01}},
   		{name:'createdAt',index:'createdAt', width:100, align:"center", hidden:true, sorttype: 'date',
            formatter: 'date', formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},
   		{name:'updatedAt',index:'updatedAt',width:100,align:"center", hidden:true,sorttype:'date',formatter:'date', formatoptions: {newformat: 'Y-M-d'}, datefmt: 'Y-M-d H:i:s'},	
   		{name:'coverImage',index:'coverImage',width:170},	
   		{name:'fileImage',index:'fileImage',width:70,editable:true,editrules:{edithidden:true,required:false}, hidden:true,edittype:"file"},
   		{name:'taggings',index:'taggings',width:200,formatter:tagsFmatter},
   		{name:'blurb',index:'blurb',width:250,sortable:false,editable:true,edittype:"textarea",editoptions:{rows:"2",cols:"20"}}	
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#tagging_dish_pager',
   	sortname: 'id',
    viewrecords: true, 
	autowidth: true,
	multiselect: true, caption: "商品" }); 

	jQuery("#tagging_dishes").jqGrid('navGrid','#tagging_dish_pager',{},
			{url:'${ctx}/dish/0?_method=put',reloadAfterSubmit:false,recreateForm:true,beforeSubmit:editfileUpload},
			{url:'${ctx}/dish',clearAfterAdd:true,reloadAfterSubmit:false,recreateForm:true,beforeSubmit:fileUpload},
			{url:'${ctx}/dish/0?_method=DELETE',reloadAfterSubmit:false},
			{multipleSearch:true, multipleGroup:true, showQuery: true,"tmplNames" : ["tags"],"tmplFilters": [tags]});
			
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
	function pickdates(id){
		//jQuery("#"+id+"_sdate","#tagging_dishes").datepicker({dateFormat:"yy-mm-dd"});
	}
	function fileUpload(postdata, formid){
		var ret = [true,""];
		//formid[0].coverImage.formEnctype="multipart/form-data";
		//formid[0].coverImage.formMethod="post";
		//jQuery("#FrmGrid_tagging_dishes").attr("enctype","multipart/form-data");
		if(formid[0].fileImage.files.length>0){
			ret[0] = false;
			formid[0].id.value = 0;
			formid[0].dishName.value = encodeURIComponent(formid[0].dishName.value);
			formid[0].isbn.value = encodeURIComponent(formid[0].isbn.value);
			formid[0].price.value = encodeURIComponent(formid[0].price.value);
			formid[0].blurb.value = encodeURIComponent(formid[0].blurb.value);
			//postdata["coverImage"]=formid[0].coverImage.files[0].name;
			//$("#tempCoverImage")[0].files = formid[0].coverImage.files;
			//var myfile = $("#coverImage");
			
			//var form = '<form action="${ctx}/dish-image" method="post" enctype="multipart/form-data" />';
			//form = $("#coverImage").wrapAll(form);
			//form.submit();
			//form.submit();
			//alert($file);
			//formid[0].coverImage.files;
			//$("#tempCoverImage").attr("value","‪C:\\Users\\xuzf\\Desktop\\2.jpg");
			//$("#tempCoverImage").attr("files",formid[0].coverImage.files);
			//var imageForm = $("#imageupload");
			//http://www.irt.org/script/1154.htm;http://www.cs.tut.fi/~jkorpela/forms/file.html
			//alert(imageForm);
			//jQuery("#FrmGrid_tagging_dishes").attr("method","post");
			//jQuery("#FrmGrid_tagging_dishes").attr("action","${ctx}/dish");
			//jQuery("#FrmGrid_tagging_dishes").attr("enctype","multipart/form-data");
			//jQuery("#FrmGrid_tagging_dishes").removeAttr("onSubmit");
			//formid[0].submit();
			//$("#tagging_dishes").jqGrid("addRowData",ret[2],postdata,"first");
			//$("#imageupload").append($file);
			//$("#imageupload").submit();
			//debugger;
			jQuery("#FrmGrid_tagging_dishes").ajaxSubmit({
				forceSync:false,
				dataType:'json',
				type:'POST',
				url:'${ctx}/dish',
	        	success:function(json){
	            	console.info(json);
	            	formid[0].fileImage.value = "";
	            	formid[0].dishName.value = "";
	            	formid[0].price.value = "";
	            	formid[0].blurb.value = "";
	            	formid[0].isbn.value = "";
	            	$("#tagging_dishes").jqGrid("addRowData",json.id,json,"first");
				},
				error:function(){alert('请求异常');},
				complete:function(){
					//alert(data);
	           		//buttons.attr('disabled', false);
				},
				beforeSubmit:function(){
	           		//buttons.attr('disabled', true);
				},
				beforeSerialize:function(form, options){
	           		// form[0].someinput.value = 'changed value';
				}
			});

			//jQuery("#FrmGrid_tagging_dishes").removeAttr("enctype");
			//jQuery("#FrmGrid_tagging_dishes").attr("onSubmit","return false;");
		}
		//alert(formid[0].coverImage.files[0].name);
		//alert(formid[0].coverImage.files[0].size);
		//alert(formid[0].coverImage.files[0].type);
		//alert(formid[0].coverImage.value);
		//formid[0].enctype="multipart/form-data";
		//alert(formid[0].enctype);
		//debugger;
		return ret;
	}
	
	function editfileUpload(postdata, formid){
		var ret = [true,""];
		//formid[0].coverImage.formEnctype="multipart/form-data";
		//formid[0].coverImage.formMethod="post";
		//jQuery("#FrmGrid_tagging_dishes").attr("enctype","multipart/form-data");
		if(formid[0].fileImage.files.length>0){
			ret[0] = false;
			var urls = '${ctx}/dish/'+formid[0].id.value+'?_method=put';
			var dishName = formid[0].dishName.value;
			formid[0].dishName.value = encodeURIComponent(formid[0].dishName.value);
			var isbn = formid[0].isbn.value;
			formid[0].isbn.value = encodeURIComponent(formid[0].isbn.value);
			var price = formid[0].price.value;
			formid[0].price.value = encodeURIComponent(formid[0].price.value);
			var blurb = formid[0].blurb.value;
			formid[0].blurb.value = encodeURIComponent(formid[0].blurb.value);
			//formid[0].id.value = 0;
			//postdata["coverImage"]=formid[0].coverImage.files[0].name;
			//$("#tempCoverImage")[0].files = formid[0].coverImage.files;
			//var myfile = $("#coverImage");
			
			//var form = '<form action="${ctx}/dish-image" method="post" enctype="multipart/form-data" />';
			//form = $("#coverImage").wrapAll(form);
			//form.submit();
			//form.submit();
			//alert($file);
			//formid[0].coverImage.files;
			//$("#tempCoverImage").attr("value","‪C:\\Users\\xuzf\\Desktop\\2.jpg");
			//$("#tempCoverImage").attr("files",formid[0].coverImage.files);
			//var imageForm = $("#imageupload");
			//http://www.irt.org/script/1154.htm;http://www.cs.tut.fi/~jkorpela/forms/file.html
			//alert(imageForm);
			//jQuery("#FrmGrid_tagging_dishes").attr("method","post");
			//jQuery("#FrmGrid_tagging_dishes").attr("action","${ctx}/dish");
			//jQuery("#FrmGrid_tagging_dishes").attr("enctype","multipart/form-data");
			//jQuery("#FrmGrid_tagging_dishes").removeAttr("onSubmit");
			//formid[0].submit();
			//$("#tagging_dishes").jqGrid("addRowData",ret[2],postdata,"first");
			//$("#imageupload").append($file);
			//$("#imageupload").submit();
			jQuery("#FrmGrid_tagging_dishes").ajaxSubmit({
				forceSync:false,
				dataType:'json',
				type:'POST',
				url:urls,
	        	success:function(json){
	            	//console.info(json);
	            	formid[0].fileImage.value = "";
	    			formid[0].dishName.value = dishName;
	    			formid[0].isbn.value = isbn;
	    			formid[0].price.value = price;
	    			formid[0].blurb.value = blurb;
	            	$("#tagging_dishes").jqGrid("setRowData", json.id,json);
				},
				error:function(){alert('请求异常');},
				complete:function(){
					//alert(data);
	           		//buttons.attr('disabled', false);
				},
				beforeSubmit:function(){
	           		//buttons.attr('disabled', true);
				},
				beforeSerialize:function(form, options){
	           		// form[0].someinput.value = 'changed value';
				}
			});

			//jQuery("#FrmGrid_tagging_dishes").removeAttr("enctype");
			//jQuery("#FrmGrid_tagging_dishes").attr("onSubmit","return false;");
		}
		//alert(formid[0].coverImage.files[0].name);
		//alert(formid[0].coverImage.files[0].size);
		//alert(formid[0].coverImage.files[0].type);
		//alert(formid[0].coverImage.value);
		//formid[0].enctype="multipart/form-data";
		//alert(formid[0].enctype);
		//debugger;
		return ret;
	}
	
	jQuery("#m1").click( function() {
		var s;
		s = jQuery("#tagging_dishes").jqGrid('getGridParam','selarrrow');
		alert(s);
	});
	jQuery("#m1s").click( function() {
		var s;
		s = jQuery("#tagging_tags").jqGrid('getGridParam','selarrrow');
		alert(s);
	});
	
	function cleanDishTag(){
		var dishId = jQuery("#tagging_dishes").jqGrid('getGridParam','selarrrow');
		var tagId = jQuery("#tagging_tags").jqGrid('getGridParam','selarrrow');
		if(dishId.length < 1 && tagId.length < 1){
			return;
		}
		var jsonObject = {};
		if(dishId !== undefined && dishId.length > 0){
			jsonObject["dishIds"]=dishId;
		} 
		if(tagId !== undefined && tagId.length > 0){
			jsonObject["tagIds"]=tagId;
		} 
		$.ajax({
	        type: 'POST',
	        data:jsonObject,
	        url: '${ctx}/tagging/0?_method=DELETE',
	        dataType: 'json',
	        success: function (data) {
	        	//debugger;
	        	jQuery("#tagging_dishes").trigger("reloadGrid");
	        	//alert(typeof(data));
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert(errorThrown);
	        }
		});
	}
	
	jQuery("#clean").click( function() {
		//debugger;
		confirm_oper('tagging_dishes_clean','tagging_tags',cleanDishTag);
		/* var dishId = jQuery("#tagging_dishes").jqGrid('getGridParam','selarrrow');
		var tagId = jQuery("#tagging_tags").jqGrid('getGridParam','selarrrow');
		var jsonObject = {};
		if(dishId !== undefined && dishId.length > 0){
			jsonObject["dishIds"]=dishId;
		} 
		if(tagId !== undefined && tagId.length > 0){
			jsonObject["tagIds"]=tagId;
		} 
		$.ajax({
	        type: 'POST',
	        data:jsonObject,
	        url: '${ctx}/tagging/0?_method=DELETE',
	        dataType: 'json',
	        success: function (data) {
	        	//debugger;
	        	jQuery("#tagging_dishes").trigger("reloadGrid");
	        	//alert(typeof(data));
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert(errorThrown);
	        }
		}); */
	});
	
	function labelDishTag(){
		var dishId = jQuery("#tagging_dishes").jqGrid('getGridParam','selarrrow');
		var tagId = jQuery("#tagging_tags").jqGrid('getGridParam','selarrrow');
		//if ($.isArray(dishId)) {dishId = dishId.join();}
		//if ($.isArray(tagId)) {tagId = tagId.join();} 
		if(dishId.length < 1 && tagId.length < 1){
			return;
		}
		$.ajax({
	        type: 'POST',
	        data:{
	        	dishIds:dishId,
	        	tagIds:tagId
	        },
	        url: '${ctx}/tagging',
	        dataType: 'json',
	        success: function (data) {
	        	//debugger;
	        	jQuery("#tagging_dishes").trigger("reloadGrid");
	        	//alert(typeof(data));
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert(errorThrown);
	        }
		});
	}
	
	jQuery("#labelling").click( function() {
		confirm_oper('tagging_dishes_labelling','tagging_dishes',labelDishTag);
	});
	
	$(function(){
		//$("#imageupload").hide();
	});
	
	
	// 确认，避免误操作
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
	
	
</script>
<s:head />
</head>
<body>
<table id="tagging_dishes"></table>
<div id="tagging_dish_pager"></div>
<input type="BUTTON" id="clean" value="撕掉标签" style="margin-right:30px;margin-left:30px;" />
<input type="BUTTON" id="labelling" value="贴上标签" />
<!-- <a href="javascript:void(0)" id="clean">clean</a>
<a href="javascript:void(0)" id="labelling">labelling</a> -->
<table id="tagging_tags"></table>
<div id="tagging_tag_pager"></div>	
</body>

</html>
