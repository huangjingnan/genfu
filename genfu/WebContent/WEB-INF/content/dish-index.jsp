<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>dishes</title>
<script src="${ctx}/js/uploadfile/jquery.form.js" type="text/javascript"></script>
<%-- <script src="${ctx}/js/jq-ingrid/lib/jquery.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/jqGrid_Demos_files/jquery-ui-custom.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/jqGrid_Demos_files/jquery.layout.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/plugins/ui.multiselect.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/js/jquery.jqGrid.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/plugins/jquery.tablednd.js" type="text/javascript"></script>
<script src="${ctx}/js/tonytomov-jqGrid/plugins/jquery.contextmenu.js" type="text/javascript"></script> --%>
<%-- <link rel="stylesheet" type="text/css" media="screen" href="${ctx}/js/jquery-ui-1.10.3/development-bundle/themes/flick/jquery.ui.datepicker.css" />
<script src="${ctx}/js/jquery-ui-1.10.3/development-bundle/ui/jquery.ui.datepicker.js" type="text/javascript"></script>
 --%>
<script type="text/javascript">
/* jQuery("#list2").jqGrid({
   	url:'${ctx}/dish.json?style=jqGrid',
	datatype: "json",
   	colNames:['Inv No','dishName', 'isbn', 'publishedId','publishedAt','blurb','price', 'createdAt', 'updatedAt','coverImage','dishFlag'],
   	colModel:[
	   		{name:'id',index:'id', width:55},
	   		{name:'dishName',index:'dishName', width:90},
	   		{name:'isbn',index:'isbn asc, invdate', width:100},
	   		{name:'publishedId',index:'publishedId', width:80, align:"right"},
	   		{name:'publishedAt',index:'publishedAt', width:100},
	   		{name:'blurb',index:'blurb', width:100},
	   		{name:'price',index:'price', width:100},
	   		{name:'createdAt',index:'createdAt', width:100},
	   		{name:'updatedAt',index:'updatedAt', width:80, align:"right"},		
	   		{name:'coverImage',index:'coverImage', width:80,align:"right"},		
	   		{name:'dishFlag',index:'dishFlag', width:150, sortable:false}			
   	],
   	rowNum:10,
   	rowList:[10,20,30],
   	pager: '#pager2',
   	sortname: 'id',
    viewrecords: true,
    sortorder: "desc",
    caption:"JSON Example"
});
jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false}); */

	jQuery("#dishlist4").jqGrid({ 
	url:'${ctx}/dish.json?style=jqGrid',
	datatype: "json", 
	height: 250, 
	colNames:['dishId','dishName', 'isbn', 'masterId','publishedAt','dishFlag','price', 'createdAt', 'updatedAt','coverImage','fileImage','blurb'],
	colModel:[
   		{name:'id',index:'id', width:55,editable:true,editoptions:{readonly:true,size:10}},
   		{name:'dishName',index:'dishName', width:80,editable:true,editoptions:{size:10}},
   		{name:'isbn',index:'isbn', width:90,hidden:true,editoptions:{size:25}},
   		{name:'publishedId',index:'publishedId', width:60,hidden:true,editoptions:{size:10}},
   		{name:'publishedAt',index:'publishedAt', width: 80,hidden:true, align: 'center', sorttype: 'date',
            formatter: 'date',editrules:{date:true,required:false}, formatoptions: {newformat: 'Y-m-d H:i:sO'}, datefmt: 'Y-m-d H:i:sO'},
   		{name:'dishFlag',index:'dishFlag', width:80, editable: true,align:"left",edittype:"select",editoptions:{value:"OPEN:OPEN;CLOSED:CLOSED"}},
   		{name:'price',index:'price',editable:true, align:"right",formatter:'number', width:80,editrules:{required:true,number:true,minValue:0.01}},
   		{name:'createdAt',index:'createdAt',width:100,align:"center",formatter: { srcformat:'Y-m-d', newformat: 'd/m/Y'}},
   		{name:'updatedAt',index:'updatedAt',width:100,align:"center"},	
   		{name:'coverImage',index:'coverImage',width:170},	
   		{name:'fileImage',index:'fileImage',width:70,editable:true,editrules:{edithidden:true,required:false}, hidden:true,edittype:"file"},
   		{name:'blurb',index:'blurb',width:100,sortable:false,editable:true,edittype:"textarea",editoptions:{rows:"2",cols:"20"}}	
   	],
   	rowNum:20,
   	rowList:[20,30,50],
   	pager: '#pdishlist4',
   	sortname: 'id',
    viewrecords: true, 
	autowidth: true,
	multiselect: true, caption: "dishes" }); 
	
function dishDatefmt(cellvalue, options, rowObject)
{
	//debugger;
	//return new Date(cellvalue.time).toLocaleString("ca");
	//return cellvalue.toLocaleString("ca");
	//return 0;
}
//var mydata = [ {id:"1",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"}, {id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"}, {id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}, {id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"}, {id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"}, {id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}, {id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"}, {id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"}, {id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"} ]; 

/* 	for(var i=0;i<=mydata.length;i++) 
		jQuery("#dishlist4").jqGrid('addRowData',i+1,mydata[i]); */
	//alert(typeof myJqGrid[0].p.records);
/* 		for (i in myJqGrid[0]){
			alert(i);
			alert(myJqGrid.i);
		} */
	//alert(myJqGrid[0].p.records);
//alert(myJqGrid);	
/* $(document).ready(function() {
	$.ajax({
        type: 'get',
        url: '${ctx}/dish.json?statusCode=303',
        dataType: 'json',
        success: function (data) {
        	//alert(typeof(data));
        	for(var i=0;i<=data.length;i++) 
        		jQuery("#dishlist4").jqGrid('addRowData',i+1,data[i]);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
        }
	});
}); */

	jQuery("#dishlist4").jqGrid('navGrid','#pdishlist4',{},
			{url:'${ctx}/dish/0?_method=put',reloadAfterSubmit:false,beforeSubmit:editfileUpload},
			{url:'${ctx}/dish',clearAfterAdd:true,reloadAfterSubmit:false,beforeSubmit:fileUpload},
			{url:'${ctx}/dish/0?_method=DELETE',reloadAfterSubmit:false},
			{multipleSearch:true, multipleGroup:true, showQuery: true});
	//jQuery("#pdishlist4").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false});
	
	function pickdates(id){
		//jQuery("#"+id+"_sdate","#dishlist4").datepicker({dateFormat:"yy-mm-dd"});
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

</script>
<s:head />
</head>
<body>


<!-- <table id="list2"></table>
<div id="pager2"></div> -->
<table id="dishlist4"></table>
<div id="pdishlist4"></div>
	<%-- <div id="resultSet_div">
	
		<table id="example-advanced">
        <caption>
        </caption>
        <thead>
				<tr>
					<th>ID</th>
					<th>dishName</th>
					<th>isbn</th>
					<th>publishedId</th>
					<th>publishedAt</th>
					<th>blurb</th>
					<th>price</th>
					<th>createdAt</th>
					<th>updatedAt</th>
					<th>coverImage</th>
					<th>dishFlag</th>
					<th>Action</th>
				</tr>
		</thead>
		<tbody>
				<s:iterator value="model">
					<tr>
						<td>${id}</td>
						<td>${dishName}</td>
						<td>${isbn}</td>
						<td>${publishedId}</td>
						<td>${publishedAt}</td>
						<td>${blurb}</td>
						<td>${price}</td>
						<td>${createdAt}</td>
						<td>${updatedAt}</td>
						<td>${coverImage}</td>
						<td>${dishFlag}</td>
						<td><a href="#"
							onclick="viewModelGenfu('${ctx}/navigation-node/${id}');">View</a>
							| <a href="#"
							onclick="viewModelGenfu('${ctx}/navigation-node/${id}/edit');">Edit</a>
							| <a href="#"
							onclick="deleteModelGenfu('${ctx}/navigation-node/${id}/deleteConfirm');">Delete</a></td>
					</tr>
				</s:iterator>
			</tbody>
      </table>
	</div> --%>
	
</body>

</html>
