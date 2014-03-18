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
<title>Navigation Views</title>

<style type="text/css">

/* need this one for Mozilla */
HTML {
	width: 100%;
	height: 100%;
	margin: 0px;
	padding: 0px;
	border: 0px;
}

BODY {
	margin: 0px;
	padding: 0px;
	/* Mozilla does not like width:100%, so we set height only */
	height: 100%;
	position: relative;
	/*
	Needed
	for
	Safari*/
}

IFRAME {
	width: 100%;
	height: 100%;
	position: absolute;
	/*
	Needed
	for
	Safari*/
	top: 0px;
}

.hidden {
	visibility: hidden;
	width: 0;
	height: 0;
}

.visible {
	visibility: visible;
	width: 100%;
	height: 100%;
}
</style>
<style type="text/css">
@import url(${ctx}/js/xloadtree/xtree.css);
</style>
<style type="text/css">
body {
	background: white;
	color: black;
}
</style>

<script type="application/javascript">
	var activityFiltering = false;
	var displayShowAllConfirmation = true;

	function confirmShowAll() {

		var l = top.screenX + (top.innerWidth - w) / 2;
		var t = top.screenY + (top.innerHeight - h) / 2;

		// move the dialog just a bit higher than the middle
		if (t - 50 > 0)
			t = t - 50;

		window.location = "javascript://needModal";
		confirmShowAllDialog = window.open("confirmShowAll.jsp",
				"confirmShowAllDialog", "resizable=no,height=" + h + ",width="
						+ w + ",left=" + l + ",top=" + t);
		confirmShowAllDialog.focus();
	}

	function selectScope() {

		var l = top.screenX + (top.innerWidth - w) / 2;
		var t = top.screenY + (top.innerHeight - h) / 2;

		// move the dialog just a bit higher than the middle
		if (t - 50 > 0)
			t = t - 50;

		window.location = "javascript://needModal";
		selectScopeDialog = window.open("selectScope.jsp", "selectScopeDialog",
				"resizable=no,height=" + h + ",width=" + w + ",left=" + l
						+ ",top=" + t);
		selectScopeDialog.focus();
	}
</script>

<script type="application/javascript" src="${ctx}/js/xloadtree/xtree.js"></script>
<script type="application/javascript"
	src="${ctx}/js/xloadtree/xmlextras.js"></script>
<script type="application/javascript"
	src="${ctx}/js/xloadtree/xloadtree.js"></script>

<script type="application/javascript"
	src="${ctx}/js/frame_work/index.jsp_files/help_data/nav_data/views_data/views.js"></script>
</head>
<body dir="ltr" onunload="closeConfirmShowAllDialog()">

	<iframe style="visibility: visible;" class="visible" name="toc"
		title="Layout frame: Contents Tab View" id="toc" src=""
		frameborder="0" scrolling="no"> </iframe>

	<iframe style="visibility: hidden;" class="hidden" name="index"
		title="Layout frame: Index Tab View" id="index" src="" frameborder="0"
		scrolling="no"> </iframe>

	<iframe style="visibility: hidden;" class="hidden" name="search"
		title="Layout frame: Search Results Tab View" id="search" src=""
		frameborder="0" scrolling="no"> </iframe>

	<script type="application/javascript">
		/// XP Look
		webFXTreeConfig.rootIcon = "${ctx}/js/xloadtree/images/xp/folder.png";
		webFXTreeConfig.openRootIcon = "${ctx}/js/xloadtree/images/xp/openfolder.png";
		webFXTreeConfig.folderIcon = "${ctx}/js/xloadtree/images/xp/folder.png";
		webFXTreeConfig.openFolderIcon = "${ctx}/js/xloadtree/images/xp/openfolder.png";
		webFXTreeConfig.fileIcon = "${ctx}/js/xloadtree/images/xp/file.png";
		webFXTreeConfig.lMinusIcon = "${ctx}/js/xloadtree/images/xp/Lminus.png";
		webFXTreeConfig.lPlusIcon = "${ctx}/js/xloadtree/images/xp/Lplus.png";
		webFXTreeConfig.tMinusIcon = "${ctx}/js/xloadtree/images/xp/Tminus.png";
		webFXTreeConfig.tPlusIcon = "${ctx}/js/xloadtree/images/xp/Tplus.png";
		webFXTreeConfig.iIcon = "${ctx}/js/xloadtree/images/xp/I.png";
		webFXTreeConfig.lIcon = "${ctx}/js/xloadtree/images/xp/L.png";
		webFXTreeConfig.tIcon = "${ctx}/js/xloadtree/images/xp/T.png";

		//var tree = new WebFXLoadTree("WebFXLoadTree", "tree1.xml");
		//tree.setBehavior("classic");

		var rti;
		var tree = new WebFXTree("Root");
		tree.add(new WebFXTreeItem("Tree Item 1"));
		tree.add(new WebFXLoadTreeItem("Tree Item 2", "${ctx}/js/xloadtree/tree.xml"));
		tree.add(rti = new WebFXLoadTreeItem("Tree Item 3 (Reload)",
				"${ctx}/js/xloadtree/date.xml.pl"));
		tree.add(new WebFXTreeItem("Tree Item 4"));

		document.write(tree);
	</script>




</body>
</html>
