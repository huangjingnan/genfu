<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<div id="accordion">
	<s:iterator value="model">
		<div class="group">
		<h3>${id}#-${orderName}-${status}-${numberPeople}位-${staffNumber}</h3>
		<div>
			<p>
			</p>
			<ol id="order_${id}">
			<s:set name="countAmount" value="0"/>
			<s:iterator var="item" value="orderItems">
				<s:set name="countAmount" value="#countAmount+#item.amount"/>
				<li>${item.dishId}#&nbsp;|&nbsp;${item.orderItemName}&nbsp;|&nbsp;${item.status}&nbsp;|&nbsp;￥${item.price}&nbsp;|&nbsp;${item.amount}&nbsp;|&nbsp;￥<em>${item.price*item.amount}</em>&nbsp;|&nbsp;${item.createdAt}</li>
			</s:iterator>
			</ol>
			<em><s:text name="carts.total"/>: <s:property value="#countAmount"/>份,￥</em><em id="em_order_${id}">0.00</em>
			<script type="text/javascript">
				//<![CDATA[
				jQuery(function() {
					var tempSum = 0.00;
					jQuery("#order_${id}").find('li em').each(function(i){
						tempSum = tempSum + (jQuery(this).text() - 0.00);
					});
					
					jQuery("#em_order_${id}").text(parseFloat(tempSum).toFixed(2));
				});
				//]]>
			</script>
		</div>
		</div>
	</s:iterator>
</div>
<style>
  /* IE has layout issues when sorting (see #5413) */
  .group { zoom: 1 }
</style>
<script type="text/javascript">
	//<![CDATA[
	$(function() {
		$( "#accordion" ).accordion({
			header: "> div > h3",
			heightStyle: "content",
			event: "click"
		}).sortable({
	        axis: "y",
	        handle: "h3",
	        stop: function( event, ui ) {
	          	ui.item.children( "h3" ).triggerHandler( "focusout" );
	        }
		});
	});

	$.event.special.hoverintent = {
		setup: function() {
			$( this ).bind( "mouseover", jQuery.event.special.hoverintent.handler );
		},
		teardown: function() {
			$( this ).unbind( "mouseover", jQuery.event.special.hoverintent.handler );
		},
		handler: function( event ) {
			var currentX, currentY, timeout,
				args = arguments,
				target = $( event.target ),
				previousX = event.pageX,
				previousY = event.pageY;

			function track( event ) {
				currentX = event.pageX;
				currentY = event.pageY;
			};

			function clear() {
				target
					.unbind( "mousemove", track )
					.unbind( "mouseout", clear );
				clearTimeout( timeout );
			}

			function handler() {
				var prop,
					orig = event;

				if ( ( Math.abs( previousX - currentX ) +
						Math.abs( previousY - currentY ) ) < 7 ) {
					clear();

					event = $.Event( "hoverintent" );
					for ( prop in orig ) {
						if ( !( prop in event ) ) {
							event[ prop ] = orig[ prop ];
						}
					}
					// Prevent accessing the original event since the new event
					// is fired asynchronously and the old event is no longer
					// usable (#6028)
					delete event.originalEvent;

					target.trigger( event );
				} else {
					previousX = currentX;
					previousY = currentY;
					timeout = setTimeout( handler, 100 );
				}
			}

			timeout = setTimeout( handler, 100 );
			target.bind({
				mousemove: track,
				mouseout: clear
			});
		}
	};

	//]]>
</script>
