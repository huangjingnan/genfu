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
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.draggable.js"></script>
<script type="application/javascript"
	src="${ctx}/js/jquery-ui-1.10.3/ui/jquery.ui.droppable.js"></script>
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

<script type="text/javascript">
	function w_getCookie(c_name) {
		if (document.cookie.length > 0) {
			c_start = document.cookie.indexOf(c_name + "=");
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1;
				c_end = document.cookie.indexOf(";", c_start);
				if (c_end == -1)
					c_end = document.cookie.length;
				return unescape(document.cookie.substring(c_start, c_end));
			}
		}
		return "";
	}

	function w_setCookie(c_name, value, expiredays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + expiredays);
		document.cookie = c_name
				+ "="
				+ escape(value)
				+ ((expiredays == null) ? "" : ";expires="
						+ exdate.toGMTString());
	}

	function checkCookie() {
		username = w_getCookie('username');
		if (username != null && username != "") {//alert('Welcome again '+username+'!');
		} else {
			username = prompt('Please enter your name:', "");
			if (username != null && username != "") {
				w_setCookie('username', username, 1);
			}
		}
	}
	
	function pageCatalog(page){
		w_setCookie('w_page', page, 1);
		catalog();
	}
	function searchCatalog(){
		w_setCookie('w_page', page, 1);
		catalog();
	}

	//var myurl = '${ctx}/catalog';
	function catalog() {
		//refreshCart(0);
		var dataJson = {};
		w_tag = w_getCookie('w_tag_id');
		if (w_tag != null && w_tag != "" && w_tag != "null") {//alert('Welcome again '+w_tag+'!');
			dataJson["tagId"] = w_tag;
		}

		//debugger;
		w_page = w_getCookie('w_page');
		if (w_page != null && w_page != "" && w_page != "null") {
			dataJson["page"] = w_page;
		} else {
			w_page = 1;
			w_setCookie('w_page', w_page, 1);
			dataJson["page"] = w_page;
		}

		w_rows = w_getCookie('w_rows');
		if (w_rows != null && w_rows != "" && w_rows != "null") {
			dataJson["rows"] = w_rows;
			w_setCookie('w_rows', w_rows, 1);
		} else {
			w_rows = 10;
			w_setCookie('w_rows', w_rows, 1);
			dataJson["rows"] = w_rows;
		}

		w_filter = w_getCookie('w_filter');
		if (w_filter != null && w_filter != "" && w_filter != "null") {
			dataJson["filter"] = w_filter;
		}
		
		//debugger;
		
		jQuery.ajax({
			type : 'GET',
			url : '${ctx}/catalog',
			data : dataJson,
			success : function(data, result, multiple) {
				//debugger;
				$("#content").html('');
				$("#content").append(multiple.responseText);
			},
			error : function() {
				alert('Error: Could not load "' + '/catalog'
						+ '". Please check the URL and try again. ');
			},
			complete : function() {
				//content.hide();
			}
		});
	}

	$(document)
			.ready(
					function() {
						/* var content = $("#content");
						$("#menu")
								.find("a")
								.click(
										function() {
											//content.html("Next Step...");
											//alert(jQuery(this).attr('href').substring(1));
											w_setCookie('w_tag_id', null, 1);
											w_setCookie('w_tag_name', null, 1);
											w_setCookie('w_filter', null, 1);
											w_setCookie('w_page', 1, 1);
											var w_rows = w_getCookie('w_rows');
											if (w_rows != null && w_rows != "" && w_rows != "null") {
												//w_setCookie('w_rows', w_rows, 1);
											} else {
												w_rows = 10;
											}
											//debugger;
											var myurl = jQuery(this).attr(
													'href').substring(1);
											jQuery
													.ajax({
														type : 'GET',
														url : myurl,
														data : {
															rows : w_rows
														},
														success : function(
																data, result,
																multiple) {
															content.html(multiple.responseText);
														},
														error : function() {
															alert('Error: Could not load "'
																	+ myurl
																	+ '". Please check the URL and try again. ');
														},
														complete : function() {
															//content.hide();
															//refreshCart(0);
														}
													});
										});*/
						
						refreshCartNoID();

					}); 
	function refreshCartNoID(){
		jQuery.ajax({
				type : 'GET',
				url : '${ctx}/cart',
				data : {},
				success : function(data, result, multiple) {
					$("#shopping_cart").html(multiple.responseText);
					//shopping_cart.append(multiple.responseText);
				},
				error : function() {
					alert('Error: Could not load %{#ctx}/cart. Please check the URL and try again. ');
				},
				complete : function() {
					//content.hide();
				}
			});
	}
	
	function refreshCart(id){
		jQuery
				.ajax({
					type : 'GET',
					url : '${ctx}/cart',
					data : {},
					success : function(data, result, multiple) {
						$("#shopping_cart").html(multiple.responseText);
						//shopping_cart.append(multiple.responseText);
					},
					error : function() {
						alert('Error: Could not load %{#ctx}/cart. Please check the URL and try again. ');
					},
					complete : function() {
						//content.hide();
						jQuery("#cart_item_"+id).effect( "highlight", {}, 2000 );
					}
				});
	}

	function addDish(id) {
		//var name = $("#book_"+id).find("dt").text();
		$.ajax({
					url : '${ctx}/cart/0.json?_method=put',
					data : {
						dish_id : id
					},
					type : "POST",
					beforeSend : function() {
						// $("#large").html('');
					},
					success : function(data, result, multiple) {
						//debugger;
						// $("#weather-temp").html("<strong>" + data + "</strong> degrees");
						refreshCart(id);
					},
					complete : function() {
						//debugger;
						/* var total = 0.00;
						jQuery("#shopping_cart").find('ul li')
								.each(
										function(i) {
											if (id == jQuery(this).attr('id')
													.substring(10)) {
												//alert(jQuery(this).attr('id').substring(10));
												//debugger;
												var amount = jQuery(this).find(
														"b").text() - 0;
												amount = amount + 1;
												//var d_price = jQuery(this).find("em").text();
												//alert(d_price-0);
												//alert(amount);
												jQuery(this).find("b").text(
														amount);
												jQuery(this).find("em").text(
														parseFloat(
																amount * price)
																.toFixed(2));
												id = 0;
												//return false;
											}
											total = total
													+ (jQuery(this).find("em")
															.text() - 0);
											//$( "<li></li>" ).text( ui.draggable.text() ).appendTo( $(this).find( "ul" ) );
										});
						 */
						 //parseFloat(amount[i].value*price[i].value).toFixed(2);
						/* if (id > 0) {
							total = parseFloat(total + (price - 0)).toFixed(2);
							$("<li></li>")
									.attr({
										"id" : "cart_item_" + id
									})
									.append(
											'<a href="#" onclick="viewDish('
													+ id
													+ ');">'
													+ name
													+ '</a> <b>1</b> ￥<em>'
													+ parseFloat(price)
															.toFixed(2)
													+ '</em>(<a href="#" onclick="removeDish('
													+ id
													+ ','
													+ price
													+ ');" title="Remove dish">—</a>)')
									.appendTo($("#shopping_cart").find("ul"));
						}
						jQuery("#cart_total").find('strong').text(
								'Total: ' + parseFloat(total).toFixed(2));

						if (jQuery("#clear_cart_link").length < 1) {
							jQuery("#cart_total")
									.after(
											"<p id=\"clear_cart_link\"><small><a href=\"#\" onclick=\"clearCart();\" title=\"Clear cart\">Clear Cart</a></small></p>");
							//clear_cart_link
						}
 */						
						

					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown);
					}
				});
	}

	function removeDish(id, price) {
		//debugger;
		$.ajax({
			url : '${ctx}/cart/0.json?_method=DELETE',
			data : {
				dish_id : id
			},
			type : "POST",
			beforeSend : function() {
				// $("#large").html('');
			},
			success : function(data, result, multiple) {
				//debugger;
				// $("#weather-temp").html("<strong>" + data + "</strong> degrees");
				refreshCart(id);
			},
			complete : function() {
				/* jQuery("#cart_item_"+id).effect( "highlight", { percent: 0 }, 2000 );
				var total = 0.00;
				jQuery("#shopping_cart").find('ul li').each(
						function(i) {
							//alert(jQuery(this).attr('id').substring(10));
							//debugger;
							total = total
									+ (jQuery(this).find("em").text() - 0);
							if (id == jQuery(this).attr('id').substring(10)) {
								//debugger;
								total = total - (price - 0);
								var amount = jQuery(this).find("b").text() - 0;
								amount = amount - 1;
								if (amount > 0) {
									jQuery(this).find("b").text(amount);
									jQuery(this).find("em").text(
											parseFloat(amount * price).toFixed(
													2));
								} else {
									jQuery(this).remove();
								}
								id = 0;
								//return false;
							}
							//$( "<li></li>" ).text( ui.draggable.text() ).appendTo( $(this).find( "ul" ) );
						});
				jQuery("#cart_total").find('strong').text(
						'Total: ' + parseFloat(total).toFixed(2));
				if (!(total > 0)) {
					jQuery("#clear_cart_link").remove();
					//clear_cart_link
				} */
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
	function clearCart() {

		$.ajax({
			url : '${ctx}/cart/0.json?_method=DELETE',
			data : {
				clear_cart : 0
			},
			type : "POST",
			beforeSend : function() {
				// $("#large").html('');
			},
			success : function(data, result, multiple) {
				//debugger;
				// $("#weather-temp").html("<strong>" + data + "</strong> degrees");
				refreshCartNoID();
			},
			complete : function() {
				//jQuery("#shopping_cart").find('ul').html('');
				//jQuery("#cart_total").find('strong').text('Total: 0.00');
				//jQuery("#clear_cart_link").remove();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
	function viewDish(id) {

		$.ajax({
			url : '${ctx}/catalog/' + id,
			data : {
			//dish_id : id
			},
			type : "GET",
			beforeSend : function() {
				// $("#large").html('');
			},
			success : function(data, result, multiple) {
				//debugger;
				$("#content").html(multiple.responseText);
				//$("#content").append(multiple.responseText);
				// $("#weather-temp").html("<strong>" + data + "</strong> degrees");
			},
			complete : function() {
				//
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}

	function tagDishes(tag_id, tag_name) {
		w_setCookie('w_tag_id', tag_id, 1);
		w_setCookie('w_tag_name', tag_name, 1);
		w_setCookie('w_filter', null, 1);
		w_setCookie('w_page', 1, 1);
		catalog();
		/* $.ajax({
			url : '${ctx}/catalog/',
			data : {
				tagId : tag_id
			},
			type : "GET",
			beforeSend : function() {
				// $("#large").html('');
			},
			success : function(data, result, multiple) {
				debugger;
				$("#content").html('');
				$("#content").append(multiple.responseText);
				// $("#weather-temp").html("<strong>" + data + "</strong> degrees");
			},
			complete : function() {
				//
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		}); */
	}

	function checkout() {
		refreshCartNoID();
		$.ajax({
			url : '${ctx}/checkout/new',
			data : {

			},
			type : "GET",
			beforeSend : function() {
				// $("#large").html('');
			},
			success : function(data, result, multiple) {
				//debugger;
				$("#content").html(multiple.responseText);
			},
			complete : function() {
				//
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
	
	function m_tags(){
		jQuery.ajax({
			type : 'GET',
			url : '${ctx}/tags?ORDER_BY=tagSn',
			success : function(
					data, result,
					multiple) {
				$("#content").html(multiple.responseText);
			},
			error : function() {
				alert('Error: Could not load "'
						+ '${ctx}/tags'
						+ '". Please check the URL and try again. ');
			},
			complete : function() {
				//content.hide();
				//refreshCart(0);
			}
		});
	}
	function m_catalog(){
		var w_rows = w_getCookie('w_rows');
		if (w_rows != null && w_rows != "" && w_rows != "null") {
			//w_setCookie('w_rows', w_rows, 1);
		} else {
			w_rows = 10;
		}
		
		//
		w_setCookie('w_tag_id', null, 1);
		w_setCookie('w_tag_name', null, 1);
		w_setCookie('w_filter', null, 1);
		
		jQuery.ajax({
			type : 'GET',
			url : '${ctx}/catalog',
			data : {
				rows : w_rows
			},
			success : function(
					data, result,
					multiple) {
				$("#content").html(multiple.responseText);
			},
			error : function() {
				alert('Error: Could not load "'
						+ '${ctx}/catalog'
						+ '". Please check the URL and try again. ');
			},
			complete : function() {
				//content.hide();
				//refreshCart(0);
			}
		});
	}
	function m_checkoutList(){
		jQuery.ajax({
			type : 'GET',
			url : '${ctx}/checkout?status_EQ=OPEN&NO_PAGE',
			success : function(
					data, result,
					multiple) {
				$("#content").html(multiple.responseText);
			},
			error : function() {
				alert('Error: Could not load "'
						+ '${ctx}/checkout?status_EQ=OPEN&NO_PAGE'
						+ '". Please check the URL and try again. ');
			},
			complete : function() {
				//content.hide();
				//refreshCart(0);
			}
		});
	}
	function m_setting(){
		jQuery.ajax({
			type : 'GET',
			url : '${ctx}/cart-setting',
			success : function(
					data, result,
					multiple) {
				$("#content").html(multiple.responseText);
			},
			error : function() {
				alert('Error: Could not load "'
						+ '${ctx}/cart-setting'
						+ '". Please check the URL and try again. ');
			},
			complete : function() {
				//content.hide();
				//refreshCart(0);
			}
		});
	}
	function m_about(){
		jQuery.ajax({
			type : 'GET',
			url : '${ctx}/about',
			success : function(
					data, result,
					multiple) {
				$("#content").html(multiple.responseText);
			},
			error : function() {
				alert('Error: Could not load "'
						+ '${ctx}/about'
						+ '". Please check the URL and try again. ');
			},
			complete : function() {
				//content.hide();
				//refreshCart(0);
			}
		});
	}
	
	function logout(){
		jQuery.ajax({
			type : 'POST',
			url : '${ctx}/login/0?_method=DELETE',
			success : function(data) {
				//debugger;
			},
			error : function() {
			},
			complete : function() {
				//content.hide();
			}
		});
	}
</script>
<style type="text/css">
@import url(${ctx}/css/genfu/style.css);
</style>

<title>Dish</title>
</head>
<body>
	<div id="header">
		<h1 id="logo">有谱&trade;</h1>
		<h2 id="slogan">genfu</h2>
		<p id="loginlogout">harry</p>
	</div>
	<!-- <p>
		<span class="translation_missing">en, select_locale</span> <a
			href="/?locale=en">en</a>&middot;<a href="/?locale=zh">zh</a>
	</p> -->
	<div id="menu">
		<ul>
			<li><a href="javascript:void(0)" onclick="m_tags();"><s:text name="menu.tags"/></a>&nbsp;|&nbsp;</li>
			<li><a href="javascript:void(0)" onclick="m_catalog();"><s:text name="menu.catalog"/></a>&nbsp;|&nbsp;</li>
			<li><a href="javascript:void(0)" onclick="m_checkoutList();"><s:text name="menu.checkoutList"/></a>&nbsp;|&nbsp;</li>
			<li><a href="javascript:void(0)" onclick="m_setting();"><s:text name="menu.setting"/></a>&nbsp;|&nbsp;</li>
			<li><a href="javascript:void(0)" onclick="m_about();"><s:text name="menu.about"/></a>&nbsp;|&nbsp;</li>
			<li></li>
			<li></li>
		</ul>
	</div>
	<div id="content">欢迎您！</div>
	<div id="shopping_cart"></div>
	<div id="footer">&copy; 2009-2014 genfu</div>
</body>
</html>