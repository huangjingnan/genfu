<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta charset="utf-8">
<title>dish-image</title>
</head>
<body>
<input id="fileupload" type="file" name="upload" data-url="%{#ctx}/file" multiple>
<%-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> --%>
<script src="${ctx}/js/jQuery-File-Upload-master/js/vendor/jquery.ui.widget.js"></script>
<script src="${ctx}/js/jQuery-File-Upload-master/js/jquery.iframe-transport.js"></script>
<script src="${ctx}/js/jQuery-File-Upload-master/js/jquery.fileupload.js"></script>
<script>
$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            $.each(data.result.files, function (index, file) {
                $('<p/>').text(file.name).appendTo(document.body);
            });
        }
    });
});
</script>
</body> 
</html>