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
<title>Insert title here</title>
</head>

<frameset id="helpFrameset" cols="20%,80%" frameborder="yes"
	framespacing="3" border="1">

	<frame class="navi" name="NaviFrame"
		title="Layout frame: Navigation Pane" src='${ctx}/architecture/navi'
		marginwidth="0" marginheight="0" frameborder="0" scrolling="no">
	<frame class="content" name="ContentFrame"
		title="Layout frame: Topic View Pane"
		src='${ctx}/architecture/content' marginwidth="0" marginheight="0"
		frameborder="0" scrolling="no">
</frameset>
</html>