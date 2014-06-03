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
<input id="myUploadFile" type="file" name="upload" data-url="/genfu/dish-image" multiple>
<div id="progress">
    <div class="bar" style="width: 0%;"></div>
</div>

<%-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> --%>
<script src="${ctx}/js/jQuery-File-Upload-master/js/vendor/jquery.ui.widget.js"></script>
<script src="${ctx}/js/jQuery-File-Upload-master/js/jquery.iframe-transport.js"></script>
<script src="${ctx}/js/jQuery-File-Upload-master/js/jquery.fileupload.js"></script>
<script>
$(function () {
	$('#myUploadFile').fileupload({
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

</body> 
</html>