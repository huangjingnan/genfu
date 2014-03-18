<!------------------------------------------------------------------------------
 ! Copyright (c) 2000, 2007  Corporation and others.
 ! All rights reserved. This program and the accompanying materials 
 ! are made available under the terms of the Eclipse Public License v1.0
 ! which accompanies this distribution, and is available at
 ! http://www.eclipse.org/legal/epl-v10.html
 ! 
 ! Contributors:
 !      Corporation - initial API and implementation
 ------------------------------------------------------------------------------->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Contents</title>

<script language="JavaScript">
	function onloadHandler(e) {
		resize();
	}

	function resize() {
		var titleText = window.tocToolbarFrame.document
				.getElementById("titleText");
		if (!titleText)
			return;
		var h = titleText.offsetHeight;
		if (h <= 19) {
			return;
		}
		document.getElementById("viewFrameset").setAttribute("rows",
				(11 + h) + ",*");
		window.tocToolbarFrame.document.getElementById("titleTextTableDiv").style.height = (9 + h)
				+ "px";

	}

	var resized = false;

	// Called when the view is made visible. This function is needed because 
	// with IE the resize only works after the view has been displayed for the first time.

	function onShow() {
		if (!resized) {
			resize();
			resized = true;
		}
		try {
			window.tocViewFrame.onShow();
		} catch (ex) {
		}

	}
</script>

</head>
<frameset id="viewFrameset" rows="24,*"
	frameborder="no" framespacing="0" border="0">
	<frame id="toolbar" name="tocToolbarFrame"
		title="Contents View Toolbar" src=""
		marginwidth="0" marginheight="0" frameborder="0" noresize="noresize"
		scrolling="no">
	<frame name="tocViewFrame" title="Contents View"
		src="" marginwidth="10" marginheight="0"
		frameborder="0" scrolling="auto">
</frameset>

</html>
