<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="<%=request.getContextPath()%>"
	scope="application" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>点评</title>
<style type="text/css">
@import url(${ctx}/css/genfu/consumerStyle.css);
</style>
<%-- <script type="application/javascript"
	src="${ctx}/js/jquery/jquery-2.0.3.js"></script> --%>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/jquery-1.9.1.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery-ui.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.core.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.widget.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.mouse.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.button.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.draggable.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.droppable.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.position.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.sortable.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.accordion.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.menu.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.autocomplete.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.resizable.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.dialog.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.accordion.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.effect.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.effect-highlight.js"></script>
<style>
		body { font-size: 62.5%; }
		label, input { display:block; }
		input.text { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips { border: 1px solid transparent; padding: 0.3em; }
		.no-close .ui-dialog-titlebar-close {
			display: none;
		}
	</style>
	<script>
	$(function() {
		/* $( "#slider-range-min" ).slider({
			range: "min",
			value: 77,
			min: 1,
			max: 100,
			slide: function( event, ui ) {
				$( "#score" ).val( "$" + ui.value );
			}
	    });
	    $( "#score" ).val( "$" + $( "#slider-range-min" ).slider( "value" ) ); */


		
		var score = $( "#score" ),
			comment = $( "#comment" ),
			commentator = $( "#commentator" ),
			contact = $( "#contact" ),
			allFields = $( [] ).add( score ).add( comment ).add( commentator ).add( contact ),
			tips = $( ".validateTips" );

		function updateTips( t ) {
			tips
				.text( t )
				.addClass( "ui-state-highlight" );
			setTimeout(function() {
				tips.removeClass( "ui-state-highlight", 1500 );
			}, 500 );
		}

		function checkLength( o, n, min, max ) {
			if ( o.val().length > max || o.val().length < min ) {
				o.addClass( "ui-state-error" );
				updateTips( "Length of " + n + " must be between " +
					min + " and " + max + "." );
				return false;
			} else {
				return true;
			}
		}

		function checkRegexp( o, regexp, n ) {
			if ( !( regexp.test( o.val() ) ) ) {
				o.addClass( "ui-state-error" );
				updateTips( n );
				return false;
			} else {
				return true;
			}
		}

		$( "#dialog-form" ).dialog({
			autoOpen: false,
			height: 430,
			width: 350,
			modal: true,
			buttons: {
				"吐槽一下": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );

					bValid = bValid && checkLength( score, "score", 1, 3 );
					bValid = bValid && checkLength( comment, "comment", 3, 600 );
					bValid = bValid && checkLength( commentator, "commentator", 0, 80 );
					bValid = bValid && checkLength( contact, "contact", 0, 16 );

					bValid = bValid && checkRegexp( score, /^[0-9]\d{0,1}$/, "score 0-99" );
					//bValid = bValid && checkRegexp( comment, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
					// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/commentator_address_validation/
					//bValid = bValid && checkRegexp( commentator, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
					//bValid = bValid && checkRegexp( contact, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );

					if ( bValid ) {
						//验证通过-提交
						jQuery.ajax({
							type : 'POST',
							url : '${ctx}/dish-comment',
							data : $('#consumerComment').serialize(),
							success : function(data) {
								//debugger;
								$( "#users tbody" ).append( "<tr>" +
									"<td>" + score.val() + "</td>" +
									"<td>" + comment.val() + "</td>" +
									"<td>" + commentator.val() + "</td>" +
									"<td>" + contact.val() + "</td>" +
								"</tr>" );
							},
							error : function() {
								alert('Error: Could not load dish-comment. Please check the URL and try again. ');
							},
							complete : function() {
								//content.hide();
								allFields.val( "" ).removeClass( "ui-state-error" );
							}
						});
						
						$( this ).dialog( "close" );
					}
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				//allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});

		$( "#create-user" )
			.button()
			.click(function() {
				$( "#dialog-form" ).dialog( "open" );
			});
	});
	</script>
</head>
<body>

<div id="dialog-form" title="Create new comment">
	<p class="validateTips">感谢您的支持！</p>

	<form id="consumerComment">
	<input type="hidden" name="dishId" value="${dishId}" />
	<input type="hidden" name="orderItemId" value="${orderItemId}" />
	<input type="hidden" name="orderId" value="${orderId}" />
	<input type="hidden" name="dishName" value="${dishName}" />
	<fieldset>
		<%-- <label for="dishName">dishName</label>
		<input type="text" name="dishName" id="dishName" value="${dishName}" class="text ui-widget-content ui-corner-all" readonly="readonly"/> --%>
		<label for="score">得分</label>
		<input type="text" name="score" id="score" class="text ui-widget-content ui-corner-all" />
		<label for="comment">意见或建议</label>
		<input type="text" name="comment" id="comment" class="text ui-widget-content ui-corner-all" />
		<label for="commentator">签个到吧</label>
		<input type="text" name="commentator" id="commentator" value="" class="text ui-widget-content ui-corner-all" />
		<label for="contact">联系方式</label>
		<input type="text" name="contact" id="contact" value="" class="text ui-widget-content ui-corner-all" />
	</fieldset>
			<!-- <div id="slider-range-min"></div> -->
	</form>
</div>


<div id="users-contain" class="ui-widget">
	<h1>${dishName} Existing Comments:</h1>
	<table id="users" class="ui-widget ui-widget-content">
		<thead>
			<tr class="ui-widget-header ">
				<th>得分</th>
				<th>意见或建议</th>
				<th>签个到</th>
				<th>联系我</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="comment" value="model">
			<tr>
				<td>${score}</td>
				<td>${comment}</td>
				<td>${commentator}</td>
				<td>${contact}</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
</div>
<button id="create-user">新增点评</button>
<div id="footer">&copy; 2009-2013 genfu</div>
</body>
</html>
