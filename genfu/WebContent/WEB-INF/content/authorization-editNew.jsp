<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<h1>Checkout</h1>

<p>
	<em>Your order is displayed in the shopping cart to the right.</em>
</p>
<div id="radio_genfu">
	<input type="radio" id="radio1" name="radio_genfu" value="add"
		onclick="render1();" /><label for="radio1">new</label> <input
		type="radio" id="radio2" name="radio_genfu" value="new"
		onclick="render2();" /><label for="radio2">add</label>
</div>
<form method="post" id="checkout" action="%{#ctx}/checkout">
	<fieldset>
		<legend>Table(s)</legend>
		<p>
			<label for="multiplied"> x(multiplied by) </label> <br /> <input
				id="multiplied" name="multiplied" size="30" type="text"
				onblur="validNumber();" />
		</p>
	</fieldset>
	<fieldset>
		<legend>repast information</legend>
		<p>
			<label for="order_id"> order id </label> <br /> <select id="order_id" name="id" size="1" disabled="disabled" style=' width: 225px;' onchange="renderSelect();">
					<option value="">new</option>
					</select>
		</p>
		<p>
			<label for="orderName"> order name </label> <br /> <input
				id="orderName" name="orderName" size="30" type="text" onblur="validNumber();" />
		</p>
		<p>
			<label for="numberPeople"> number of people </label> <br /> <input
				id="numberPeople" name="numberPeople" size="30" type="text" onblur="validNumber();" />
		</p>
		<p>
			<label for="amount"> amount </label> <br /> <input
				id="amount" name="amount" size="30" type="text" onblur="validNumber();" />
		</p>
		<p>
			<label for="staffNumber"> staff number </label> <br /> <input
				id="staffNumber" name="staffNumber" size="30" type="text" onblur="validNumber();" />
		</p>
	</fieldset>
	<p>
		<!-- <input name="commit" type="submit" value="Place Order" /> -->
		<a href="javascript:void(0)" onclick="placeOrder()">Place Order</a>
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

	var reg = /^[1-9]\d{0,2}$/;
	function validNumber() {
		validValue();
		if (!reg.test($("#multiplied").val())) {
			document.getElementById('multiplied').setAttribute('style',
					'border-color: #FF0000;border-style: solid;');
			$("#multiplied").val(1);
			valid = false;
		} else {
			document.getElementById('multiplied').removeAttribute('style');
		}
	}
	function validValue(){
		var valid = true;
		if (!reg.test($("#multiplied").val())) {
			document.getElementById('multiplied').setAttribute('style',
					'border-color: #FF0000;border-style: solid;');
			$("#multiplied").val(1);
			valid = false;
		} else {
			document.getElementById('multiplied').removeAttribute('style');
		}
		if (!reg.test($("#amount").val())) {
			document.getElementById('amount').setAttribute('style',
					'border-color: #FF0000;border-style: solid;');
			$("#amount").val(1);
			valid = false;
		} else {
			document.getElementById('amount').removeAttribute('style');
		}

		if (document.getElementById('radio1').getAttribute('checked') === 'checked'
				&& document.getElementById('radio2').getAttribute('checked') === null) {
		} else if (document.getElementById('radio2').getAttribute('checked') === 'checked'
				&& document.getElementById('radio1').getAttribute('checked') === null) {
		} else {
			valid = false;
			document.getElementById('radio_genfu').setAttribute('style',
					'border-color: #FF0000;border-style: solid;width: 335px;');
		}

		if (null === document.all.order_id.getAttribute('disabled')) {
			//var reg = /^[1-9]\d{0,10}/;
			if (!reg.test($("#order_id").val())) {
				document.getElementById('order_id').setAttribute('className',
						'input');
				$("#order_id").val('');
				valid = false;
			} else {
				document.getElementById('multiplied').removeAttribute(
						'className');
			}
			myurl = '${ctx}/checkout/' + $("#order_id").val() + '?_method=put';

		} else if ("disabled" === document.all.order_id
				.getAttribute('disabled')) {
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
		if (!reg.test($("#numberPeople").val())) {
			document.getElementById('numberPeople').setAttribute('style',
					'border-color: #FF0000;border-style: solid;');
			$("#numberPeople").val(1);
			valid = false;
		} else {
			document.getElementById('numberPeople').removeAttribute('style');
		}
		/* if (null == $("#numberPeople").val() || "" == $("#numberPeople").val() ||  $("#numberPeople").val() == undefined) {
			document.getElementById('numberPeople').setAttribute('style','border-color: #FF0000;border-style: solid;');
			valid = false;
		} else {
			document.getElementById('numberPeople').removeAttribute('style');
		} */
		
		if (null == $("#staffNumber").val() || "" == $("#staffNumber").val() ||  $("#staffNumber").val() == undefined) {
			document.getElementById('staffNumber').setAttribute('style','border-color: #FF0000;border-style: solid;');
			valid = false;
		} else {
			document.getElementById('staffNumber').removeAttribute('style');
		}

		return valid;
	}
	
	function placeOrder() {
		var valid = true;
		var myurl = '#';
		
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
		//alert($("#order_id").val());
		//return;
		//debugger;
		if (!reg.test($("#multiplied").val())) {
			$("#multiplied").attr('style','border-color: #FF0000;border-style: solid;');
			$("#multiplied").val(1);
			valid = false;
		} else {
			$("#multiplied").removeAttr("style");
		}

		if (document.getElementById('radio1').getAttribute('checked') === 'checked'
				&& document.getElementById('radio2').getAttribute('checked') === null) {
			//debugger;
			//document.all.order_id.removeAttribute('disabled');
			//document.getElementById('radio1').removeAttribute('checked');
			//document.getElementById('radio2').setAttribute('checked','checked');
			//$("#order_id").val('');
		} else if (document.getElementById('radio2').getAttribute('checked') === 'checked'
				&& document.getElementById('radio1').getAttribute('checked') === null) {
			//debugger;
			//document.all.order_id.setAttribute("disabled", "disabled");
			//document.getElementById('radio2').removeAttribute('checked');
			//document.getElementById('radio1').setAttribute('checked','checked');
			//$("#order_id").val('');
		} else {
			//debugger;
			valid = false;
			document.getElementById('radio_genfu').setAttribute('style',
					'border-color: #FF0000;border-style: solid;width: 335px;');
			//document.all.order_id.setAttribute("disabled", "disabled");
			//$("#order_id").val('');
		}

		if (null === document.all.order_id.getAttribute('disabled')) {
			var reg = /^[1-9]\d{0,10}/;
			if (!reg.test($("#order_id").val())) {
				document.getElementById('order_id').setAttribute('className',
						'input');
				$("#order_id").val('');
				valid = false;
				return;
			} else {
				document.getElementById('multiplied').removeAttribute(
						'className');
			}
			myurl = '${ctx}/checkout/' + $("#order_id").val() + '?_method=put';

		} else if ("disabled" === document.all.order_id
				.getAttribute('disabled')) {
			myurl = '${ctx}/checkout';
		} else {
			valid = false;
			return;
		}
		
		if (null == document.getElementById('orderName').value || "" == document.getElementById('orderName').value ||  document.getElementById('orderName').value == undefined) {
			document.getElementById('orderName').setAttribute('style','border-color: #FF0000;border-style: solid;');
			valid = false;
			//return;
		} else {
			document.getElementById('orderName').removeAttribute('style');
		}
		
		if (null == $("#numberPeople").val() || "" == $("#numberPeople").val() ||  $("#numberPeople").val() == undefined) {
			document.getElementById('numberPeople').setAttribute('style','border-color: #FF0000;border-style: solid;');
			$("#numberPeople").val(1);
			valid = false;
			//return;
		} else {
			document.getElementById('numberPeople').removeAttribute('style');
		}
		
		if (null == $("#staffNumber").val() || "" == $("#staffNumber").val() ||  $("#staffNumber").val() == undefined) {
			document.getElementById('staffNumber').setAttribute('style','border-color: #FF0000;border-style: solid;');
			valid = false;
			//return;
		} else {
			document.getElementById('staffNumber').removeAttribute('style');
		}

		//debugger;
		if (valid === true) {
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
		}
	}

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
				$("#staffNumber").val(orderList[i].staffNumber);
				$("#amount").val(orderList[i].amount);
				$("#multiplied").val(orderList[i].amount);
				return false;
			}
		}
		//debugger;
		//var checkIndex = jQuery("#order_id").attr("selectedIndex");
		//alert(checkIndex); 
	}
	
	function render1() {
		//debugger;
		validValue();
		$( "#order_id" ).empty();
		jQuery("#order_id").append("<option value=''>new</option>");
		document.all.order_id.setAttribute("disabled", "disabled");
		document.getElementById('radio2').removeAttribute('checked');
		document.getElementById('radio1').setAttribute('checked', 'checked');
		document.getElementById('radio_genfu').removeAttribute('style');
		$("#order_id").val('');
		$("#orderName").val('');
		$("#numberPeople").val(1);
		$("#staffNumber").val('');
	}
	function render2() {
		validValue();
		$.ajax({
			url : '${ctx}/checkout.json?status_EQ=OPEN',
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
					jQuery("#order_id").prepend("<option value='"+data[i].id+"'>#"+data[i].id+"-"+data[i].orderName+"-"+data[i].numberPeople+"位-"+data[i].staffNumber+"</option>");
				}
				if(i<1){
					jQuery("#order_id").append("<option value=''>没有订单供您添加，只能新订单</option>");
				}else{
					jQuery("#order_id").prepend("<option value=''>请选择</option>");
				}
				//debugger;
				document.all.order_id.removeAttribute('disabled');
				document.getElementById('radio1').removeAttribute('checked');
				document.getElementById('radio2').setAttribute('checked', 'checked');
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
	function previous() {

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
				$("#content").append(multiple.responseText);
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
