<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:actionmessage />

<p>对不起，访问参数有误！</p>
	<%-- <table id="checkout_table">
		<tr>
			<td>亲，请留意，加餐，结账——订单编号：#${id}</td>
		</tr>
		<tr>
			<td>订单名称：${orderName}</td>
		</tr>
		<tr>
			<td>用餐人数：${numberPeople}</td>
		</tr>
		<tr>
			<td>服务员编号：${staffNumber}</td>
		</tr>
		<tr>
		<td rowspan="17" align="center"><img src="${ctx}/checkout/${id}?QR_CODE"  alt="QR_CODE" /></td><td>#商品编号</td><td>商品名称</td><td align="right">商品价格</td><td align="right">数量</td><td align="right">合计</td><td>创建时间</td>
		</tr>
			<s:set name="itemSize" value="0"/>
		<s:iterator var="orderItem" value="orderItems">
			<s:set name="itemSize" value="#itemSize+1"/>
			<tr>
			<s:if test="#itemSize<17">
				<td>#${orderItem.dishId}</td><td>${orderItem.orderItemName}</td><td align="right">￥${orderItem.price}</td><td align="right">${orderItem.amount}</td><td align="right">￥<em>${orderItem.amount*orderItem.price}</em></td><td>${orderItem.createdAt}</td><td>${orderItem.createdAt}</td><td>${orderItem.updatedAt}</td>
			</s:if>
			<s:else>
				<td></td><td>#${orderItem.dishId}</td><td>${orderItem.orderItemName}</td><td align="right">￥${orderItem.price}</td><td align="right">${orderItem.amount}</td><td align="right">￥<em>${orderItem.amount*orderItem.price}</em></td><td>${orderItem.createdAt}</td><td>${orderItem.createdAt}</td><td>${orderItem.updatedAt}</td>
			</s:else>
			</tr>
		</s:iterator>
		<tr>
			<s:if test="#itemSize<17">
				<td></td><td></td><td align="right"></td><td align="right"></td><td align="right"><b>Total: <s:property value="#itemSize"/>道,￥</b><b id="b_checkout">0.00</b></td><td></td>
			</s:if>
			<s:else>
				<td></td><td></td><td></td><td align="right"></td><td align="right"></td><td align="right"><b>Total: <s:property value="#itemSize"/>道,￥</b><b id="b_checkout">0.00</b></td><td></td>
			</s:else>
		</tr>
	</table>
	<script type="text/javascript">
				//<![CDATA[
				jQuery(function() {
					var tempSum = 0.00;
					jQuery("#checkout_table").find('td em').each(function(i){
						tempSum = tempSum + (jQuery(this).text() - 0.00);
					});
					
					jQuery("#b_checkout").text(parseFloat(tempSum).toFixed(2));
				});
				//]]>
	</script> --%>
	<%-- <a href="${ctx}/event">Back to Events</a> --%>
