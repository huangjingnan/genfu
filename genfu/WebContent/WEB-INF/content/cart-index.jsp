<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>


<s:if test="%{parameters.notice}">
	<p id="cart_notice">notice</p>
</s:if>
<h3><s:text name="carts.cart"/> ${model.id}</h3>
<p>
	<strong><a href="javascript:void(0)" onclick="checkout();" title="checkout"><s:text name="carts.checkout"/> </a></strong>
</p>
<ul>
	<s:set name="countAmount" value="0"/>
	<s:iterator var="item" value="model.cartItems">
		<s:set name="countAmount" value="#countAmount+#item.amount"/>
		<li id="cart_item_${item.dishId}"><a href="javascript:void(0)" onclick="viewDish(${item.dishId});">${item.cartItemName}</a> <b>${item.amount}</b> ￥<em>${item.price*item.amount}</em>
			<a href="javascript:void(0)" onclick="removeDish(${item.dishId},${item.price});" title="Remove dish">—</a></li>
	</s:iterator>
</ul>
<p id="cart_total">
	<strong>Total:${model.cartItems.size()} 道,<s:property value="#countAmount"/>份,￥${model.total()}</strong>
</p>
<s:if test="model.total()>0">
	<p id="clear_cart_link">
		<small> <a href="javascript:void(0)" onclick="clearCart();" title="Clear cart">Clear Cart</a>
		</small>
	</p>
</s:if>

<script type="text/javascript">
	//<![CDATA[
		$( "#shopping_cart" ).droppable({
			accept: "#books > li",
			drop: function( event, ui ) {
				//debugger;
				//$( this ).find( ".placeholder" ).remove();
				//$(this).find( "ul" ).append($( "<li></li>" ).text( ui.draggable.text() ));
				
				//alert(ui.draggable[0].id);
				//var d_name = $(ui.draggable[0].outerHTML).find("small");
				//alert(d_name.text());
				//addDish(ui.draggable[0].id.substring(5),$(ui.draggable[0].outerHTML).find("dt").text(),$(ui.draggable[0].outerHTML).find("small").text());
				//alert(d_name.html());
				addDish(ui.draggable[0].id.substring(5));
			}
		}).sortable({
			items: "li:not(.placeholder)",
			sort: function() {
				// gets added unintentionally by droppable interacting with sortable
				// using connectWithSortable fixes this, but doesn't allow you to customize active/hoverClass options
				//$( this ).removeClass( "ui-state-default" );
			}
		});
	//]]>
</script>