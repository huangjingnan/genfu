

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC
"-//W3C//DTD XHTML 1.1 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Navigation Views</title>

<style type="text/css">
@import url(${ctx}/js/xloadtree/xtree.css);
</style>


<script type="application/javascript" src="${ctx}/js/xloadtree/xtree.js"></script>
<script type="application/javascript"
	src="${ctx}/js/xloadtree/xmlextras.js"></script>
<script type="application/javascript"
	src="${ctx}/js/xloadtree/xloadtree.js"></script>
<script type="application/javascript">
function testA(){

	window.open('/genfu/navigation-node','ContentViewFrame');
	
	//alert(this);
	//alert(this.action);
	//alert(window.parent.frames.name);
}

function openInFrame(src){

	window.open(src,'ContentViewFrame');
	
	//alert(this);
	//alert(this.action);
	//alert(window.parent.frames.name);
}
</script>

</head>
<body dir="ltr">

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

		/* var rti;
		var tree = new WebFXTree("Root");
		tree.add(new WebFXTreeItem("Tree Item 1","javascript:testA()"));
		tree.add(new WebFXLoadTreeItem("Tree Item 2", "${ctx}/js/xloadtree/tree.xml"));
		tree.add(rti = new WebFXLoadTreeItem("Tree Item 3 (Reload)",
				"${ctx}/js/xloadtree/date.xml.pl"));
		tree.add(new WebFXTreeItem("Tree Item 4"));
		
		//tree.indent();
		document.write(tree);
		document.close(); */
		<s:property value="treeString" default="" escapeHtml="false" escapeJavaScript="false" escapeXml="false" />
	</script>



</body>
</html>
