<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>role-navi</title>
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/js/jquery-ui-1.10.3/development-bundle/themes/flick/jquery.ui.datepicker.css" />
<script src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/js/grid.subgrid.js" type="text/javascript"> </script>
<script type="text/javascript">

	jQuery("#navigationNodes").jqGrid({
	url:'${ctx}/navigation-nodes.json?style=jqGrid',
	datatype: "json", 
	colNames:['导航功能ID','名称','操作','描述','生效时间','失效时间','状态','其他','上级ID','源','顺序'],
	colModel:[
   		{name:'id',index:'id', width:70,editable:true,editoptions:{readonly:true,size:10}},
   		{name:'naviText',index:'naviText', width:80,editable:true,editrules:{required:true}},
   		{name:'naviAction',index:'naviAction', width:80,editable:true,editrules:{required:true}},
   		{name:'naviDescription',index:'naviDescription', width:80,editable:true,editrules:{required:true}},
   		{name:'naviEffDate',index:'naviEffDate', width:100, align: 'center',formatter:dishDatefmt},
   		{name:'naviExpDate',index:'naviExpDate', formatoptions: {newformat: 'Y-m-d H:i:s'}, width:100, align: 'center', sorttype: 'date',
   			formatter:dishDatefmt,editrules:{date:true,required:false}, datefmt: 'Y-M-d H:i:s'},
   		{name:'naviFlag',index:'naviFlag', width:80,editable:true,editrules:{required:true}},
   		{name:'naviOthers',index:'naviOthers', width:80,editable:true,editrules:{required:true}},
   		{name:'naviParentId',index:'naviParentId', width:80,editable:true,editrules:{required:true}},
   		{name:'naviSrc',index:'naviSrc', width:80,editable:true,editrules:{required:true}},
   		{name:'naviOrder',index:'naviOrder', width:80,editable:true,editrules:{required:true}}
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#navigationNodes_pager',
   	sortname: 'id',
    viewrecords: true, 
	autowidth: true,
	multiselect: true, caption: "功能列表"
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
	jQuery("#navigationNodes").jqGrid('navGrid','#navigationNodes_pager',{edit:false,add:false,del:false},
			{url:'${ctx}/navigation-nodes/0?_method=put',reloadAfterSubmit:false},
			{url:'${ctx}/navigation-nodes',clearAfterAdd:false,reloadAfterSubmit:false},
			{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.NavigationNode',reloadAfterSubmit:false},
			{multipleSearch:true, multipleGroup:false, showQuery: false});
	//jQuery("#navigationNodes_pager").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	
	
	jQuery("#roleinfos").jqGrid({ 
	url:'${ctx}/role-infos.json?style=jqGrid',
	datatype: "json",
	colNames:['角色ID','角色名称', '描述', '状态','生效时间','失效时间'],
	colModel:[
   		{name:'id',index:'id', width:55,editable:true,editoptions:{readonly:true,size:10}},
   		{name:'roleName',index:'roleName', width:150,editable:true,editoptions:{size:10}},
   		{name:'roleDescription',index:'roleDescription',width:250,sortable:false,editable:true,edittype:"textarea",editoptions:{rows:"2",cols:"20"}},
   		{name:'roleFlag',index:'roleFlag', width:100, editable: true,edittype:"select",editoptions:{value:"OPEN:OPEN;CLOSED:CLOSED"}},
   		{name:'roleEffDate',index:'roleEffDate',formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}, width:100, editable: true,
   			editoptions:{size:12,
				dataInit:function(el){
					$(el).datepicker({dateFormat:'yy-mm-dd'});
				},
				defaultValue: function(){
					var currentTime = new Date();
					var month = parseInt(currentTime.getMonth() + 1);
					month = month <= 9 ? "0"+month : month;
					var day = currentTime.getDate();
					day = day <= 9 ? "0"+day : day;
					var year = currentTime.getFullYear();
					return year+"-"+month + "-"+day;				
				}
			},
   			align: 'center',editrules:{required:true}, datefmt: 'Y-M-d H:i:s'},
       	{name:'roleExpDate',index:'roleExpDate',formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}, width:100, editable: true,
            editoptions:{size:12,
    			dataInit:function(el){
    				$(el).datepicker({dateFormat:'yy-mm-dd'});
    			},
    			defaultValue: function(){
    				var currentTime = new Date();
    				var month = parseInt(currentTime.getMonth() + 1);
    				month = month <= 9 ? "0"+month : month;
    				var day = currentTime.getDate();
    				day = day <= 9 ? "0"+day : day;
    				var year = currentTime.getFullYear();
    				return year+"-"+month + "-"+day;				
    			}
    		},
            align: 'center',editrules:{required:true}, datefmt: 'Y-M-d H:i:s'}
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#roleinfo_pager',
   	sortname: 'id',
    viewrecords: true,
	multiselect: true, 
	autowidth: true,
	subGrid: true,
	caption: "角色信息",
	subGridRowExpanded: function(subgrid_id, row_id) {
			// we pass two parameters
			// subgrid_id is a id of the div tag created whitin a table data
			// the id of this elemenet is a combination of the "sg_" + id of the row
			// the row_id is the id of the row
			// If we wan to pass additinal parameters to the url we can use
			// a method getRowData(row_id) - which returns associative array in type name-value
			// here we can easy construct the flowing
			var subgrid_table_id, pager_id;
			subgrid_table_id = subgrid_id+"_t";
			pager_id = "p_"+subgrid_table_id;
			//debugger;
			$("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
			jQuery("#"+subgrid_table_id).jqGrid({
				url:"${ctx}/navigation-nodes.json?style=jqGrid&roleId="+row_id,
				//url:'${ctx}/navigation-nodes.json?style=jqGrid',
				datatype: "json",
				colNames: ['导航功能ID','功能名称','状态','生效时间','失效时间'],
				colModel: [
					{name:"id",index:"id",width:80,key:true},
					{name:"naviText",index:"naviText",width:130},
					{name:"naviFlag",index:"naviFlag",width:70},
					{name:"naviEffDate",index:"naviEffDate",formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}, width:100},
					{name:"naviExpDate",index:"naviExpDate",formatter:dishDatefmt, formatoptions: {newformat: 'Y-m-d H:i:s'}, width:100}
				],
			   	rowNum:5,
			   	rowList:[5,30,50],
			   	pager: pager_id,
				multiselect: true, 
			    viewrecords: true, 
				autowidth: true,
			    height: '100%'
			});
			jQuery("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id,{edit:false,add:false,del:true},{},{},
					{url:'${ctx}/navigation-nodes/0?_method=DELETE&roleId='+row_id,reloadAfterSubmit:false},
					{multipleSearch:true, multipleGroup:true, showQuery: true});
		},
		subGridRowColapsed: function(subgrid_id, row_id) {
			// this function is called before removing the data
			//var subgrid_table_id;
			//subgrid_table_id = subgrid_id+"_t";
			//jQuery("#"+subgrid_table_id).remove();
		}

	}); 

	jQuery("#roleinfos").jqGrid('navGrid','#roleinfo_pager',{},
			{url:'${ctx}/role-infos/0?_method=put',reloadAfterSubmit:false},
			{url:'${ctx}/role-infos',clearAfterAdd:true,reloadAfterSubmit:false},
			{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.RoleInfo',reloadAfterSubmit:false},
			{multipleSearch:true, multipleGroup:true, showQuery: true});
			
	jQuery("#roleNaviclean").click( function() {
		var roleId = jQuery("#roleinfos").jqGrid('getGridParam','selarrrow');
		var naviId = jQuery("#navigationNodes").jqGrid('getGridParam','selarrrow');
		if ($.isArray(naviId)) {naviId = naviId.join();}
		if ($.isArray(roleId)) {roleId = roleId.join();} 
		if(roleId.length < 1 && naviId.length < 1){
			return;
		}
		var jsonObject = {};
		if(roleId !== undefined && roleId.length > 0){
			jsonObject["roleIds"]=roleId;
		} 
		if(naviId !== undefined && naviId.length > 0){
			jsonObject["naviIds"]=naviId;
		}
		$.ajax({
	        type: 'POST',
	        data:jsonObject,
	        url: '${ctx}/role-navi/0?_method=DELETE',
	        dataType: 'json',
	        success: function (data) {
	        	//debugger;
	        	jQuery("#roleinfos").trigger("reloadGrid");
	        	//alert(typeof(data));
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert(errorThrown);
	        }
		});
	});
	jQuery("#roleNavilabelling").click( function() {
		var roleId = jQuery("#roleinfos").jqGrid('getGridParam','selarrrow');
		var naviId = jQuery("#navigationNodes").jqGrid('getGridParam','selarrrow');
		if(roleId == undefined || roleId.length < 1 || naviId == undefined || naviId.length < 1){
			return;
		} 
		if ($.isArray(naviId)) {naviId = naviId.join();}
		if ($.isArray(roleId)) {roleId = roleId.join();}
		$.ajax({
	        type: 'POST',
	        data:{
	        	roleIds:roleId,
	        	naviIds:naviId
	        },
	        url: '${ctx}/role-navi',
	        dataType: 'json',
	        success: function (data) {
	        	//debugger;
	        	jQuery("#roleinfos").trigger("reloadGrid");
	        	//alert(typeof(data));
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert(errorThrown);
	        }
		});
	});
	
	$(function(){
		//$("#imageupload").hide();
	});
	
	jQuery("#userinfos").jqGrid({
		url:'${ctx}/user-info.json?style=jqGrid',
		datatype: "json",
		colNames:['用户ID','用户名称','用户账号','角色信息','密码', '描述', '状态','生效时间','失效时间'],
		colModel:[
	   		{name:'id',index:'id', width:55,editable:true,editoptions:{readonly:true,size:10}},
	   		{name:'userName',index:'userName', width:150,editable:true},
	   		{name:'userCode',index:'userCode', width:150,editable:true},
	   		{name:'roleInfos',index:'roleInfos',width:200,formatter:roleInfosFmatter},
	   		{name:'userPassword',index:'userPassword', width:150,editable:true,edittype:"password",editrules:{edithidden:true,required:true}, hidden:true},
	   		{name:'userDescription',index:'userDescription',width:250,sortable:false,editable:true,edittype:"textarea",editoptions:{rows:"2",cols:"20"}},
	   		{name:'userFlag',index:'userFlag', width:100, editable: true,edittype:"select",editoptions:{value:"OPEN:OPEN;CLOSED:CLOSED"}},
	   		{name:'userEffDate',index:'userEffDate', width: 80, editable: true,
	   			editoptions:{size:12,
					dataInit:function(el){
						$(el).datepicker({dateFormat:'yy-mm-dd'});
					},
					defaultValue: function(){
						var currentTime = new Date();
						var month = parseInt(currentTime.getMonth() + 1);
						month = month <= 9 ? "0"+month : month;
						var day = currentTime.getDate();
						day = day <= 9 ? "0"+day : day;
						var year = currentTime.getFullYear();
						return year+"-"+month + "-"+day;				
					}
				},
				align: 'center', sorttype: 'date',
				formatter:dishDatefmt,editrules:{date:true,required:false}, formatoptions: {datefmt: 'Y-M-d H:i:s'}},
	       	{name:'userExpDate',index:'userExpDate', width: 80, editable: true,
	            	editoptions:{size:12,
						dataInit:function(el){
							$(el).datepicker({dateFormat:'yy-mm-dd'});
						},
						defaultValue: function(){
							var currentTime = new Date();
							var month = parseInt(currentTime.getMonth() + 1);
							month = month <= 9 ? "0"+month : month;
							var day = currentTime.getDate();
							day = day <= 9 ? "0"+day : day;
							var year = currentTime.getFullYear();
							return year+"-"+month + "-"+day;				
						}
					},
	            	align: 'center', sorttype: 'date',
				formatter:dishDatefmt,editrules:{date:true,required:false}, formatoptions: {datefmt: 'Y-M-d H:i:s'}}
	   	],
	   	rowNum:20,
	   	rowList:[20,30,50],
	   	pager: '#userinfo_pager',
	   	sortname: 'id',
	    viewrecords: true,
		multiselect: true, 
		autowidth: true,
		caption: "用户信息"
		}); 

	function roleInfosFmatter ( cellvalue, options, rowObject ){
		var roleNames="";
		//debugger;
		if(cellvalue !== undefined && cellvalue !== null && cellvalue !== ""){
			for (var i=0;i<cellvalue.length;i++){
				roleNames=roleNames+','+cellvalue[i].roleName;
				//alert(cellvalue[i].tag.name);
			}
		}
		return roleNames.substring(1);
	}
	
		jQuery("#userinfos").jqGrid('navGrid','#userinfo_pager',{},
				{url:'${ctx}/user-info/0?_method=put',reloadAfterSubmit:false},
				{url:'${ctx}/user-info',clearAfterAdd:true,reloadAfterSubmit:false},
				{url:'${ctx}/genfu-common/0?_method=DELETE&className=com.genfu.reform.model.UserInfo',reloadAfterSubmit:false},
				{multipleSearch:true, multipleGroup:true, showQuery: true});
		jQuery("#userRoleclean").click( function() {
			var roleId = jQuery("#roleinfos").jqGrid('getGridParam','selarrrow');
			var userId = jQuery("#userinfos").jqGrid('getGridParam','selarrrow');
			if(roleId.length < 1 && userId.length < 1){
				return;
			}
			if ($.isArray(userId)) {userId = userId.join();}
			if ($.isArray(roleId)) {roleId = roleId.join();}
			var jsonObject = {};
			if(roleId !== undefined && roleId.length > 0){
				jsonObject["roleIds"]=roleId;
			} 
			if(userId !== undefined && userId.length > 0){
				jsonObject["userIds"]=userId;
			} 
			$.ajax({
		        type: 'POST',
		        data:jsonObject,
		        url: '${ctx}/user-role/0?_method=DELETE',
		        dataType: 'json',
		        success: function (data) {
		        	//debugger;
		        	jQuery("#userinfos").trigger("reloadGrid");
		        	//alert(typeof(data));
		        },
		        error: function (XMLHttpRequest, textStatus, errorThrown) {
		            alert(errorThrown);
		        }
			});
		});
		jQuery("#userRolelabelling").click( function() {
			var roleId = jQuery("#roleinfos").jqGrid('getGridParam','selarrrow');
			var userId = jQuery("#userinfos").jqGrid('getGridParam','selarrrow');
			if(roleId == undefined || roleId.length < 1 || userId == undefined || userId.length < 1){
				return;
			} 
			if ($.isArray(userId)) {userId = userId.join();}
			if ($.isArray(roleId)) {roleId = roleId.join();}
			$.ajax({
		        type: 'POST',
		        data:{
		        	roleIds:roleId,
		        	userIds:userId
		        },
		        url: '${ctx}/user-role',
		        dataType: 'json',
		        success: function (data) {
		        	//debugger;
		        	jQuery("#userinfos").trigger("reloadGrid");
		        	//alert(typeof(data));
		        },
		        error: function (XMLHttpRequest, textStatus, errorThrown) {
		            alert(errorThrown);
		        }
			});
		});		
	
</script>
<s:head />
</head>
<body>
<table id="userinfos"></table>
<div id="userinfo_pager"></div>
<input type="BUTTON" id="userRoleclean" value="解绑用户和角色" />
<input type="BUTTON" id="userRolelabelling" value="绑定用户和角色" />
<table id="roleinfos"></table>
<div id="roleinfo_pager"></div>
<input type="BUTTON" id="roleNaviclean" value="解绑角色和功能" />
<input type="BUTTON" id="roleNavilabelling" value="绑定角色和功能" />
<!-- <a href="javascript:void(0)" id="clean">clean</a>
<a href="javascript:void(0)" id="labelling">labelling</a> -->
<table id="navigationNodes"></table>
<div id="navigationNodes_pager"></div>	
</body>

</html>
