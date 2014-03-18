<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<!-- <h1>Checkout</h1>

<p>
	<em>Your order is displayed in the shopping cart to the right.</em>
</p> -->
<div id="radio_genfu">
	<input type="radio" id="radioNew" name="radio_genfu" value="new"
		onclick="render1();" /><label for="radioNew"><s:text name="checkout.new"/></label> <input
		type="radio" id="radioAdd" name="radio_genfu" value="add"
		onclick="render2();" /><label for="radioAdd"><s:text name="checkout.add"/></label>
</div>
<form method="post" id="checkout" action="%{#ctx}/checkout">
	<fieldset>
		<legend><s:text name="checkout.times"/></legend>
		<p>
			<label for="multiplied"> <s:text name="checkout.multiplier"/> </label> <br /> <input
				id="multiplied" name="multiplied" size="30" type="text"
				onblur="validValue();" />
		</p>
	</fieldset>
	<fieldset>
		<legend><s:text name="checkout.repast"/></legend>
		<p>
			<label for="order_id"> <s:text name="checkout.order.id"/> </label> <br /> <select id="order_id" name="id" size="1" disabled="disabled" style=' width: 225px;' onchange="renderSelect();">
					<option value="0">new</option>
					</select>
		</p>
		<p>
			<label for="orderName"> <s:text name="checkout.order.name"/> </label> <br /> <input
				id="orderName" name="orderName" size="30" type="text" onblur="validValue();" />
		</p>
		<p>
			<label for="numberPeople"> <s:text name="checkout.order.pepleNum"/> </label> <br /> <input
				id="numberPeople" name="numberPeople" size="30" type="text" onblur="validValue();" />
		</p>
		<p>
			<label for="amount"> <s:text name="checkout.order.amount"/> </label> <br /> <input
				id="amount" name="amount" size="30" type="text" onblur="validValue();" />
		</p>
		<%-- <p>
			<label for="staffNumber"> <s:text name="checkout.order.authorization"/> </label> <br /> <input
				id="staffNumber" name="staffNumber" size="30" type="text" onblur="validValue();" disabled="disabled"/>
		</p> --%>
		<p>
			<label for="authCode"> <s:text name="checkout.order.authorization"/> </label> <br /> <input
				id="authCode" name="authCode" size="30" type="password" onblur="validValue();"/>
		</p>
	</fieldset>
	<p>
		<!-- <input name="commit" type="submit" value="Place Order" /> -->
		<a href="javascript:void(0)" onclick="placeOrder()"><s:text name="checkout.submit"/></a>
	</p>
</form>
<script type="text/javascript">
	//<![CDATA[
	//$(function() 
	var orderList = [];
	/*var names = [ "Jörn Zaefferer", "Scott González", "John Resig" ];

	var accentMap = {
		"á" : "a",
		"ö" : "o"
	};
	var normalize = function(term) {
		var ret = "";
		for ( var i = 0; i < term.length; i++) {
			ret += accentMap[term.charAt(i)] || term.charAt(i);
		}
		return ret;
	};

 	$("#orderName").autocomplete(
			{
				source : function(request, response) {
					var matcher = new RegExp($.ui.autocomplete
							.escapeRegex(request.term), "i");
					response($.grep(names, function(value) {
						value = value.label || value.value || value;
						return matcher.test(value)
								|| matcher.test(normalize(value));
					}));
				}
			}); */
	//});

	var myurl = '#';
	
	function validValue(){
		var valid = true;
		var reg_multiplied = new RegExp("^[1-9]\d{0,10}$");
		if (!reg_multiplied.test($("#multiplied").val())) {
			document.getElementById('multiplied').setAttribute('style',
					'border-color: #FF0000;border-style: solid;');
			$("#multiplied").val(1);
			valid = false;
		} else {
			document.getElementById('multiplied').removeAttribute('style');
		}
		var reg_amount = /^[1-9]\d{0,10}/;
		if (!reg_amount.test($("#amount").val())) {
			document.getElementById('amount').setAttribute('style',
					'border-color: #FF0000;border-style: solid;');
			$("#amount").val(1);
			valid = false;
		} else {
			document.getElementById('amount').removeAttribute('style');
		}

		if (document.getElementById('radioNew').getAttribute('checked') === 'checked'
				&& document.getElementById('radioAdd').getAttribute('checked') === null) {
			//new 下订单
		} else if (document.getElementById('radioAdd').getAttribute('checked') === 'checked'
				&& document.getElementById('radioNew').getAttribute('checked') === null) {
			//add 加商品
		} else {
			valid = false;
			document.getElementById('radio_genfu').setAttribute('style',
					'border-color: #FF0000;border-style: solid;width: 335px;');
		}

		if (null === document.all.order_id.getAttribute('disabled')) {
			var reg_order_id = /^[1-9]\d{0,10}/;
			if (!reg_order_id.test($("#order_id").val()-0)) {
				document.getElementById('order_id').setAttribute('style',
				'border-color: #FF0000;border-style: solid;');
				//document.getElementById('order_id').setAttribute('className','input');
				//alert($("#order_id").val());
				valid = false;
			} else {
				document.getElementById('order_id').removeAttribute(
						'style');
			}
			myurl = '${ctx}/checkout/' + $("#order_id").val() + '?_method=put';

		} else if ("disabled" === document.all.order_id
				.getAttribute('disabled')) {
			document.getElementById('order_id').removeAttribute(
			'style');
			myurl = '${ctx}/checkout';
		} else {
			valid = false;
		}
		
		if (null == document.getElementById('orderName').value || "" == document.getElementById('orderName').value ||  document.getElementById('orderName').value == undefined) {
			document.getElementById('orderName').setAttribute('style','border-color: #FF0000;border-style: solid;');
			valid = false;
		} else {
			document.getElementById('orderName').removeAttribute('style');
		}
		var reg_numberPeople = /^[1-9]\d{0,10}/;
		if (!reg_numberPeople.test($("#numberPeople").val())) {
			document.getElementById('numberPeople').setAttribute('style',
					'border-color: #FF0000;border-style: solid;');
			$("#numberPeople").val(2);
			valid = false;
		} else {
			document.getElementById('numberPeople').removeAttribute('style');
		}
		/* if (null == $("#numberPeople").val() || "" == $("#numberPeople").val() ||  $("#numberPeople").val() == undefined) {
			document.getElementById('numberPeople').setAttribute('style','border-color: #FF0000;border-style: solid;');
			valid = false;
		} else {
			document.getElementById('numberPeople').removeAttribute('style');
		}
		
		if (null == $("#staffNumber").val() || "" == $("#staffNumber").val() ||  $("#staffNumber").val() == undefined) {
			document.getElementById('staffNumber').setAttribute('style','border-color: #FF0000;border-style: solid;');
			valid = false;
		} else {
			document.getElementById('staffNumber').removeAttribute('style');
		} */

		return valid;
	}
	var hasSubmit = false;
	function placeOrder() {
		var valid = true;
		
		var total = 0.00;
		jQuery("#shopping_cart").find('ul li')
				.each(
						function(i) {
							total = total
									+ (jQuery(this).find("em")
											.text() - 0);
							//$( "<li></li>" ).text( ui.draggable.text() ).appendTo( $(this).find( "ul" ) );
						});
		if(total<0.01){
			valid = false;
			if (jQuery("#notice").length < 1) {
				jQuery("#content").prepend("<div id='notice'>购物车为空！不能提交订单</div>");
				//clear_cart_link
			}else{
				jQuery("#notice").html("购物车为空！不能提交订单").removeAttr( "style" ).hide().fadeIn();
			}
			return;
		}
		//debugger;
		valid = validValue();
		if (valid === true) {
			if(!hasSubmit){
				hasSubmit = true;
				jQuery.ajax({
					type : 'POST',
					url : myurl,
					data : $('#checkout').serialize(),
					success : function(data, result, multiple) {
						//debugger;
						$("#content").html(multiple.responseText);
						//$("#content").append(multiple.responseText);
					},
					error : function() {
						alert('Error: Could not load "' + myurl
								+ '". Please check the URL and try again. ');
					},
					complete : function() {
						//content.hide();
					}
				});
				callback();
			}
			
		}
	}
	
	function callback() {
		/*setTimeout(function() {
			$( "#cart_setting:visible" ).removeAttr( "style" ).fadeIn();
		}, 1000 );*/
		setTimeout(function() {
			//$( "#orderPlace" ).removeAttr( "disabled" ).hide().fadeIn();
			hasSubmit = false;
		}, 1000 );
	};

	function renderSelect(){
		var checkValue = jQuery("#order_id").val();
		//alert(checkValue);
		//var checkText = jQuery("#order_id :selected").text(); 
		//alert(checkText.indexOf('-'));
		for(var i=0;i<orderList.length;i++){
			//debugger;
			if(checkValue == orderList[i].id){
				$("#orderName").val(orderList[i].orderName);
				$("#numberPeople").val(orderList[i].numberPeople);
				//$("#staffNumber").val(orderList[i].staffNumber);
				$("#amount").val(orderList[i].amount);
				$("#multiplied").val(orderList[i].amount);

				validValue();
				return false;
			}
		}
	}
	
	function render1() {
		//debugger;
		validValue();
		$( "#order_id" ).empty();
		jQuery("#order_id").append("<option value='0'>new</option>");
		document.all.order_id.setAttribute("disabled", "disabled");
		document.getElementById('radioAdd').removeAttribute('checked');
		document.getElementById('radioNew').setAttribute('checked', 'checked');
		document.getElementById('radio_genfu').removeAttribute('style');
		$("#order_id").val('');
		$("#orderName").val('');
		$("#numberPeople").val(1);
		//$("#staffNumber").val('');
	}
	function render2() {
		validValue();
		$.ajax({
			url : '${ctx}/checkout.json?status_EQ=OPEN&NO_PAGE',
			async : false,
			dataType : 'json',
			data : {

			},
			type : "GET",
			beforeSend : function() {
				// $("#large").html('');
			},
			success : function(data, result, multiple) {
				orderList = data;
				$( "#order_id" ).empty();
				//jQuery("#order_id").append("<option value=''>请选择</option>");
				var i = 0;
				for(i=0;i<data.length;i++){
					jQuery("#order_id").prepend("<option value='"+data[i].id+"'>"+data[i].id+"#-"+data[i].orderName+"-"+data[i].numberPeople+"位-"+data[i].staffNumber+"</option>");
				}
				if(i<1){
					jQuery("#order_id").append("<option value='0'>没有订单供您添加，只能新订单</option>");
				}else{
					jQuery("#order_id").prepend("<option value='0'>请选择</option>");
				}
				//debugger;
				document.all.order_id.removeAttribute('disabled');
				document.getElementById('radioNew').removeAttribute('checked');
				document.getElementById('radioAdd').setAttribute('checked', 'checked');
				document.getElementById('radio_genfu').removeAttribute('style');
				//$("#order_id").val('');
			},
			complete : function() {
				//
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
	//]]>
</script>
