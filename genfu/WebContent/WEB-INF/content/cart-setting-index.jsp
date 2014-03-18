<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>

<!-- <h1>Setting</h1>

<p>
	<em>setting your prefer ...</em>
</p> -->
<form method="post" id="setting" action="%{#ctx}/cart-setting"
	enctype="multipart/form-data">
	<fieldset>
		<legend>指定参数</legend>
		<p>
			<label for="w_rows"> 每页显示多少条 </label> <br /> <input
				id="w_rows" name="w_rows" size="30" type="text" onblur="validNumber();" />
		</p>
		<p>
			<label for="cartID"> 同步购物车编号 </label> <br /> <input
				id="cartID" name="cartID" size="30" type="text" onblur="validNumberCA();" />
		</p>
		<p>
			<label for="authorization"> 授权码 </label> <br /> <input
				id="authorization" name="authorization" size="30" type="text" onblur="validNumberCA();" />
		</p>
		<!-- <p>
			<label for="staffNumber"> 授权员工号 </label> <br /> <input
				id="staffNumber" name="staffNumber" size="30" type="text" onblur="validNumber();" />
		</p> -->
	</fieldset>
	<p>
		<!-- <input name="commit" type="submit" value="Place Order" /> -->
		<a id="cart_setting" href="javascript:void(0)" onclick="setPrefer()">确认</a>
	</p>
</form>
<script type="text/javascript">
	//<![CDATA[
	
	var reg = /^[1-9]\d{0,10}$/;
	function validNumber() {
		if (!reg.test($("#w_rows").val())) {
			$("#w_rows").attr('style',
					'border-color: #FF0000;border-style: solid;');
			$("#w_rows").val(10);
		} else {
			$("#w_rows").removeAttr('style');
		}

	}
	
	function validNumberCA() {
		validNumber();
		var valid = true;
		if (!reg.test($("#cartID").val()) && $("#cartID").val() != '') {
			$("#cartID").attr('style',
					'border-color: #FF0000;border-style: solid;');
			valid = false;
		} else {
			$("#cartID").removeAttr('style');
		}

		if (!reg.test($("#authorization").val()) && $("#authorization").val() != '') {
			$("#authorization").attr('style',
					'border-color: #FF0000;border-style: solid;');
			valid = false;
		} else {
			$("#authorization").removeAttr('style');
		}
		return valid;
	}

	
	var hasSubmit = false;
	
	function setPrefer() {
		
		if(!hasSubmit){
			hasSubmit = true;

			//$("#cart_setting").show("pulsate", {}, 2000,callback);
			$( "#cart_setting:visible" ).removeAttr( "style" ).fadeOut();
			//$("#cart_setting").attr("disabled","disabled");
			if($("#cartID").val() == '' || $("#staffNumber").val() == ''){

				var w_rows = $("#w_rows").val();
				//debugger;
				if (w_rows != null && w_rows != "" && w_rows != "null") {
					w_setCookie('w_rows', w_rows, 1);
				} else {
					w_rows = 10;
					w_setCookie('w_rows', w_rows, 1);
				}
				if (jQuery("#notice").length < 1) {
					jQuery("#content").prepend("<div id='notice'>successfully. </div>");
					//clear_cart_link
				}else{
					jQuery("#notice").html("successfully. ").removeAttr( "style" ).hide().fadeIn();
				}
			}else{
				var valid = validNumberCA();
				if(valid){
					//disable submit
				jQuery.ajax({
					type : 'POST',
					dataType : 'json',
					url : '${ctx}/cart-setting',
					data : $('#setting').serialize(),
					success : function(data, result, multiple) {
						//debugger;
						//$("#content").html('');
						//$("#content").append(multiple.responseText);
						if (jQuery("#notice").length < 1) {
							jQuery("#content").prepend("<div id='notice'>successfully. </div>");
							//clear_cart_link
						}else{
							jQuery("#notice").html("successfully. ").removeAttr( "style" ).hide().fadeIn();
						}
					},
					error : function() {
						if (jQuery("#notice").length < 1) {
							jQuery("#content").prepend("<div id='notice'>Error: Could not load cart-setting. Please check the URL and try again. </div>");
							//clear_cart_link
						}else{
							jQuery("#notice").html("Error: Could not load cart-setting. Please check the URL and try again. ").removeAttr( "style" ).hide().fadeIn();
						}
						//alert("Error: Could not load cart-setting. Please check the URL and try again. ");
					},
					complete : function() {
						//content.hide();
					}
				});
				
				}
			}

			callback();
		}
		
	}
	
	function callback() {
		/*setTimeout(function() {
			$( "#cart_setting:visible" ).removeAttr( "style" ).fadeIn();
		}, 1000 );*/
		setTimeout(function() {
			$( "#cart_setting" ).removeAttr( "disabled" ).hide().fadeIn();
			hasSubmit = false;
		}, 1000 );
	};
	//]]>
</script>
