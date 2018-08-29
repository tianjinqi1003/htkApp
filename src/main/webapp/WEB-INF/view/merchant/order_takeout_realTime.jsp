<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/18
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp"%>
<head>
<title>外卖商品实时订单页面</title>
<meta charset="utf-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<%@include file="head.jsp"%>
<!-- confirm CSS -->
<link rel="stylesheet"
	href="${staticFilePath}resource/custom/plugins/jquery-confirm/jquery-confirm.css" />
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<%@include file="top.jsp"%>
		<div class="layui-body" style="background-color: #f3f3f4">
			<div class="body-content">
				<div class="childCatO clearfix tab top-tab">
					<a href="${sysPath}merchant/takeout/order/realTimeTakeoutOrder"
						class="cur">实时订单</a> <a
						href="${sysPath}merchant/takeout/order/historyTakeoutOrder">历史订单</a>
				</div>
				<div class="orderPage">
					<div class="orderTop">
						<div class="filter clearfix">
							<div class="filterName">处理状态</div>
							<div class="filterCheck clearfix">
								<c:choose>
									<c:when test="${statusCode == 0}">
										<label><input type="radio" id="newOrd" name="filter1"
											value="1" checked /> 新订单</label>
										<label><input type="radio" id="oldOrd" name="filter1"
											value="2" /> 已处理</label>
									</c:when>
									<c:otherwise>
										<label><input type="radio" id="newOrd" name="filter1"
											value="1" /> 新订单</label>
										<label><input type="radio" id="oldOrd" name="filter1"
											value="2" checked /> 已处理</label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>

						<c:if test="${statusCode != 0}">
							<div class="filter clearfix orderState_">
								<div class="filterName">订单状态</div>
								<div class="filterCheck clearfix">
									<c:choose>
										<c:when test="${statusCode == 1}">
											<label><input type="radio" id="dPSOrd" name="filter2"
												value="1" checked /> 待配送</label>
										</c:when>
										<c:otherwise>
											<label><input type="radio" id="dPSOrd" name="filter2"
												value="1" /> 待配送</label>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${statusCode == 2}">
											<label><input type="radio" id="pSZOrd" name="filter2"
												value="2" checked /> 配送中</label>
										</c:when>
										<c:otherwise>
											<label><input type="radio" id="pSZOrd" name="filter2"
												value="2" /> 配送中</label>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${statusCode == 3}">
											<label><input type="radio" id='ySHOrd' name="filter2"
												value="3" checked /> 已收货</label>
										</c:when>
										<c:otherwise>
											<label><input type="radio" id='ySHOrd' name="filter2"
												value="3" /> 已收货</label>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${statusCode == 4}">
											<label><input type="radio" id='cDOrd' name="filter2"
												value="4" checked /> 催单</label>
										</c:when>
										<c:otherwise>
											<label><input type="radio" id='cDOrd' name="filter2"
												value="4" /> 催单</label>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${statusCode == 5}">
											<label><input type="radio" id='qXOrd' name="filter2"
												value="5" checked /> 取消单</label>
										</c:when>
										<c:otherwise>
											<label><input type="radio" id='qXOrd' name="filter2"
												value="5" /> 取消单</label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</c:if>
					</div>
					<div class="orderList clearfix">
						<div class="orderLeft">
							<div class="orderLeftCont clearfix">
								<div class="orderItem xDDOrd">
									<!-- 新订单 -->
									<c:choose>
										<c:when test="${statusCode == 0}">
											<c:if test="${data != null}">
												<c:forEach items="${data}" var="each" varStatus="i">
													<c:set value="${fn:substring(each.receivingCall,0 , 3)}"
														var="beginPhoeNumber" />
													<c:set value="${fn:substring(each.receivingCall,7, 10)}"
														var="endPhoneNumber" />
													<div class="orderHead">
														#<i>${each.number}</i>
													</div>
													<div class="userInfo">
														<div class="uname">${fn:substring(each.receiptName,0,1)}（${each.sex == 0 ? "女士" : "先生"}）</div>
														<%--<p>${beginPhoeNumber}*****${endPhoneNumber}</p>--%>
														<p>${each.receivingCall}</p>
														<p>${each.shippingAddress}</p>
														<p>备注：${each.remark}</p>
													</div>
													<div class="goodsInfo">
														<div class="uname">
															商品信息<a href="javascript:void(0);">收起</a>
														</div>
														<div class="payed">
															<c:if test="${each.productLists != null}">
																<table>
																	<c:forEach items="${each.productLists}" var="every">
																		<tr>
																			<td>${every.productName}</td>
																			<td>￥${every.price}</td>
																			<td>x${every.quantity}</td>
																			<td>￥${every.price * every.quantity}</td>
																		</tr>
																	</c:forEach>
																</table>
															</c:if>
														</div>
													</div>
													<div class="plus">
														<div>
															小计<span>￥${each.orderAmount}</span>
														</div>
														<p>
															商家活动支出 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
														<p>
															平台服务费 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
													</div>
													<div class="count">
														<div>
															本单预计收入<span>￥${each.orderAmount}</span>
														</div>
														<p>
															本单顾客实际支付：￥${each.orderAmount}<i>（已支付）</i>
														</p>
													</div>
													<div class="buttons">
														<a href="javascript:void(0)" class="promptly"
															data-num="${each.orderNumber}">立即接单</a>
													</div>
													<p class="orderSn">${fn:substring(each.orderTime,5 , 19)}
														下单 | 订单编号：${each.orderNumber}</p>
												</c:forEach>
											</c:if>
											<c:if test="${data == null}">
												<div class="noOrder">
													<div class="noOrder-content">
														<div class="noOrder-content-div">
															<div class="wane">
																<i class="fa fa-file-text-o wane-icon"
																	aria-hidden="true"></i>
															</div>
															<p class="noOrder-p">暂无指定订单</p>
															<span class="noOrder-span">暂时没有该筛选条件的订单,稍后再来看看吧!</span>
														</div>
													</div>
												</div>
											</c:if>
										</c:when>
										<c:when test="${statusCode == 1}">
											<c:if test="${data != null}">
												<c:forEach items="${data}" var="each" varStatus="i">
													<c:set value="${fn:substring(each.receivingCall,0 , 3)}"
														var="beginPhoeNumber" />
													<c:set value="${fn:substring(each.receivingCall,7, 10)}"
														var="endPhoneNumber" />
													<div class="orderHead">
														#<i>${each.number}</i>
													</div>
													<div class="userInfo">
														<div class="uname">${fn:substring(each.receiptName,0,1)}（${each.sex == 0 ? "女士" : "先生"}）</div>
														<%--<p>${beginPhoeNumber}*****${endPhoneNumber}</p>--%>
														<p>${each.receivingCall}</p>
														<p>${each.shippingAddress}</p>
														<p>备注：${each.remark}</p>
													</div>
													<div class="goodsInfo">
														<div class="uname">
															商品信息<a href="javascript:void(0);">收起</a>
														</div>
														<div class="payed">
															<c:if test="${each.productLists != null}">
																<table>
																	<c:forEach items="${each.productLists}" var="every">
																		<tr>
																			<td>${every.productName}</td>
																			<td>￥${every.price}</td>
																			<td>x${every.quantity}</td>
																			<td>￥${every.price * every.quantity}</td>
																		</tr>
																	</c:forEach>
																</table>
															</c:if>
														</div>
													</div>
													<div class="plus">
														<div>
															小计<span>￥${each.orderAmount}</span>
														</div>
														<p>
															商家活动支出 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
														<p>
															平台服务费 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
													</div>
													<div class="count">
														<div>
															本单预计收入<span>￥${each.orderAmount}</span>
														</div>
														<p>
															本单顾客实际支付：￥${each.orderAmount}<i>（已支付）</i>
														</p>
													</div>
													<div class="buttons">
														<a href="javascript:void(0)" class="delivery"
															data-num="${each.orderNumber}">自行配送</a>
															<a href="javascript:void(0)" class="recruit"
															data-num="${each.orderNumber}" data-call="${each.receivingCall}" data-address="${each.shippingAddress}">外招配送</a>
													</div>
													<p class="orderSn">${fn:substring(each.orderTime,5 , 19)}
														下单 | 订单编号：${each.orderNumber}</p>
												</c:forEach>
											</c:if>
											<c:if test="${data == null}">
												<div class="noOrder">
													<div class="noOrder-content">
														<div class="noOrder-content-div">
															<div class="wane">
																<i class="fa fa-file-text-o wane-icon"
																	aria-hidden="true"></i>
															</div>
															<p class="noOrder-p">暂无指定订单</p>
															<span class="noOrder-span">暂时没有该筛选条件的订单,稍后再来看看吧!</span>
														</div>
													</div>
												</div>
											</c:if>
										</c:when>
										<c:when test="${statusCode == 2}">
											<c:if test="${data != null}">
												<c:forEach items="${data}" var="each" varStatus="i">
													<c:set value="${fn:substring(each.receivingCall,0 , 3)}"
														var="beginPhoeNumber" />
													<c:set value="${fn:substring(each.receivingCall,7, 10)}"
														var="endPhoneNumber" />
													<div class="orderHead">
														#<i>${each.number}</i>
													</div>
													<div class="userInfo">
														<div class="uname">${fn:substring(each.receiptName,0,1)}（${each.sex == 0 ? "女士" : "先生"}）</div>
														<%--<p>${beginPhoeNumber}*****${endPhoneNumber}</p>--%>
														<p>${each.receivingCall}</p>
														<p>${each.shippingAddress}</p>
														<p>备注：${each.remark}</p>
													</div>
													<div class="goodsInfo">
														<div class="uname">
															商品信息<a href="javascript:void(0);">收起</a>
														</div>
														<div class="payed">
															<c:if test="${each.productLists != null}">
																<table>
																	<c:forEach items="${each.productLists}" var="every">
																		<tr>
																			<td>${every.productName}</td>
																			<td>￥${every.price}</td>
																			<td>x${every.quantity}</td>
																			<td>￥${every.price * every.quantity}</td>
																		</tr>
																	</c:forEach>
																</table>
															</c:if>
														</div>
													</div>
													<div class="plus">
														<div>
															小计<span>￥${each.orderAmount}</span>
														</div>
														<p>
															商家活动支出 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
														<p>
															平台服务费 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
													</div>
													<div class="count">
														<div>
															本单预计收入<span>￥${each.orderAmount}</span>
														</div>
														<p>
															本单顾客实际支付：￥${each.orderAmount}<i>（已支付）</i>
														</p>
													</div>
													<div class="buttons">
														<a href="javascript:void(0)"
															data-num="${each.orderNumber}">配送中。。</a>
													</div>
													<p class="orderSn">${fn:substring(each.orderTime,5 , 19)}
														下单 | 订单编号：${each.orderNumber}</p>
												</c:forEach>
											</c:if>
											<c:if test="${data == null}">
												<div class="noOrder">
													<div class="noOrder-content">
														<div class="noOrder-content-div">
															<div class="wane">
																<i class="fa fa-file-text-o wane-icon"
																	aria-hidden="true"></i>
															</div>
															<p class="noOrder-p">暂无指定订单</p>
															<span class="noOrder-span">暂时没有该筛选条件的订单,稍后再来看看吧!</span>
														</div>
													</div>
												</div>
											</c:if>
										</c:when>
										<c:when test="${statusCode == 3}">
											<c:if test="${data != null}">
												<c:forEach items="${data}" var="each" varStatus="i">
													<c:set value="${fn:substring(each.receivingCall,0 , 3)}"
														var="beginPhoeNumber" />
													<c:set value="${fn:substring(each.receivingCall,7, 10)}"
														var="endPhoneNumber" />
													<div class="orderHead">
														#<i>${each.number}</i>
													</div>
													<div class="userInfo">
														<div class="uname">${fn:substring(each.receiptName,0,1)}（${each.sex == 0 ? "女士" : "先生"}）</div>
														<%--<p>${beginPhoeNumber}*****${endPhoneNumber}</p>--%>
														<p>${each.receivingCall}</p>
														<p>${each.shippingAddress}</p>
														<p>备注：${each.remark}</p>
													</div>
													<div class="goodsInfo">
														<div class="uname">
															商品信息<a href="javascript:void(0);">收起</a>
														</div>
														<div class="payed">
															<c:if test="${each.productLists != null}">
																<table>
																	<c:forEach items="${each.productLists}" var="every">
																		<tr>
																			<td>${every.productName}</td>
																			<td>￥${every.price}</td>
																			<td>x${every.quantity}</td>
																			<td>￥${every.price * every.quantity}</td>
																		</tr>
																	</c:forEach>
																</table>
															</c:if>
														</div>
													</div>
													<div class="plus">
														<div>
															小计<span>￥${each.orderAmount}</span>
														</div>
														<p>
															商家活动支出 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
														<p>
															平台服务费 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
													</div>
													<div class="count">
														<div>
															本单预计收入<span>￥${each.orderAmount}</span>
														</div>
														<p>
															本单顾客实际支付：￥${each.orderAmount}<i>（已支付）</i>
														</p>
													</div>
													<div class="buttons">
														<a href="javascript:void(0)"
															data-num="${each.orderNumber}">已收货成功</a>
													</div>
													<p class="orderSn">${fn:substring(each.orderTime,5 , 19)}
														下单 | 订单编号：${each.orderNumber}</p>
												</c:forEach>
											</c:if>
											<c:if test="${data == null}">
												<div class="noOrder">
													<div class="noOrder-content">
														<div class="noOrder-content-div">
															<div class="wane">
																<i class="fa fa-file-text-o wane-icon"
																	aria-hidden="true"></i>
															</div>
															<p class="noOrder-p">暂无指定订单</p>
															<span class="noOrder-span">暂时没有该筛选条件的订单,稍后再来看看吧!</span>
														</div>
													</div>
												</div>
											</c:if>
										</c:when>
										<c:when test="${statusCode == 4}">
											<c:if test="${data != null}">
												<c:forEach items="${data}" var="each" varStatus="i">
													<c:set value="${fn:substring(each.receivingCall,0 , 3)}"
														var="beginPhoeNumber" />
													<c:set value="${fn:substring(each.receivingCall,7, 10)}"
														var="endPhoneNumber" />
													<div class="orderHead">
														#<i>${each.number}</i>
													</div>
													<div class="userInfo">
														<div class="uname">${fn:substring(each.receiptName,0,1)}（${each.sex == 0 ? "女士" : "先生"}）</div>
														<%--<p>${beginPhoeNumber}*****${endPhoneNumber}</p>--%>
														<p>${each.receivingCall}</p>
														<p>${each.shippingAddress}</p>
														<p>备注：${each.remark}</p>
													</div>
													<div class="goodsInfo">
														<div class="uname">
															商品信息<a href="javascript:void(0);">收起</a>
														</div>
														<div class="payed">
															<c:if test="${each.productLists != null}">
																<table>
																	<c:forEach items="${each.productLists}" var="every">
																		<tr>
																			<td>${every.productName}</td>
																			<td>￥${every.price}</td>
																			<td>x${every.quantity}</td>
																			<td>￥${every.price * every.quantity}</td>
																		</tr>
																	</c:forEach>
																</table>
															</c:if>
														</div>
													</div>
													<div class="plus">
														<div>
															小计<span>￥${each.orderAmount}</span>
														</div>
														<p>
															商家活动支出 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
														<p>
															平台服务费 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
													</div>
													<div class="count">
														<div>
															本单预计收入<span>￥${each.orderAmount}</span>
														</div>
														<p>
															本单顾客实际支付：￥${each.orderAmount}<i>（已支付）</i>
														</p>
													</div>
													<div class="buttons">
														<a class="replyMessage" data-num="${each.orderNumber}">确认</a>
													</div>
													<p class="orderSn">${fn:substring(each.orderTime,5 , 19)}
														下单 | 订单编号：${each.orderNumber}</p>
												</c:forEach>
											</c:if>
											<c:if test="${data == null}">
												<div class="noOrder">
													<div class="noOrder-content">
														<div class="noOrder-content-div">
															<div class="wane">
																<i class="fa fa-file-text-o wane-icon"
																	aria-hidden="true"></i>
															</div>
															<p class="noOrder-p">暂无指定订单</p>
															<span class="noOrder-span">暂时没有该筛选条件的订单,稍后再来看看吧!</span>
														</div>
													</div>
												</div>
											</c:if>
										</c:when>
										<c:when test="${statusCode == 5}">
											<c:if test="${data != null}">
												<c:forEach items="${data}" var="each" varStatus="i">
													<c:set value="${fn:substring(each.receivingCall,0 , 3)}"
														var="beginPhoeNumber" />
													<c:set value="${fn:substring(each.receivingCall,7, 10)}"
														var="endPhoneNumber" />
													<div class="orderHead">
														#<i>${each.number}</i>
													</div>
													<div class="userInfo">
														<div class="uname">${fn:substring(each.receiptName,0,1)}（${each.sex == 0 ? "女士" : "先生"}）</div>
														<%--<p>${beginPhoeNumber}*****${endPhoneNumber}</p>--%>
														<p>${each.receivingCall}</p>
														<p>${each.shippingAddress}</p>
														<p>备注：${each.remark}</p>
													</div>
													<div class="goodsInfo">
														<div class="uname">
															商品信息<a href="javascript:void(0);">收起</a>
														</div>
														<div class="payed">
															<c:if test="${each.productLists != null}">
																<table>
																	<c:forEach items="${each.productLists}" var="every">
																		<tr>
																			<td>${every.productName}</td>
																			<td>￥${every.price}</td>
																			<td>x${every.quantity}</td>
																			<td>￥${every.price * every.quantity}</td>
																		</tr>
																	</c:forEach>
																</table>
															</c:if>
														</div>
													</div>
													<div class="plus">
														<div>
															小计<span>￥${each.orderAmount}</span>
														</div>
														<p>
															商家活动支出 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
														<p>
															平台服务费 <i class="fa fa-question-circle" title="说明文字"></i><span>-￥0.00</span>
														</p>
													</div>
													<div class="count">
														<div>
															本单预计收入<span>￥${each.orderAmount}</span>
														</div>
														<p>
															本单顾客实际支付：￥${each.orderAmount}<i>（已支付）</i>
														</p>
													</div>
													<div class="buttons">
														<a class="cancelOrder" data-num="${each.orderNumber}"
															style="">取消单</a>
													</div>
													<p class="orderSn">${fn:substring(each.orderTime,5 , 19)}
														下单 | 订单编号：${each.orderNumber}</p>
												</c:forEach>
											</c:if>
											<c:if test="${data == null}">
												<div class="noOrder">
													<div class="noOrder-content">
														<div class="noOrder-content-div">
															<div class="wane">
																<i class="fa fa-file-text-o wane-icon"
																	aria-hidden="true"></i>
															</div>
															<p class="noOrder-p">暂无指定订单</p>
															<span class="noOrder-span">暂时没有该筛选条件的订单,稍后再来看看吧!</span>
														</div>
													</div>
												</div>
											</c:if>
										</c:when>
										<c:otherwise>
											<c:if test="${data == null}">
												<div class="noOrder">
													<div class="noOrder-content">
														<div class="noOrder-content-div">
															<div class="wane">
																<i class="fa fa-file-text-o wane-icon"
																	aria-hidden="true"></i>
															</div>
															<p class="noOrder-p">暂无指定订单</p>
															<span class="noOrder-span">暂时没有该筛选条件的订单,稍后再来看看吧!</span>
														</div>
													</div>
												</div>
											</c:if>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						<div class="orderRight">
							<div class="infoTit">今日订单概况</div>
							<p>已接订单：${orderQuantity} 笔</p>
							<p>今日营业总额：${income} 元</p>
							<div class="infoTit">需关注订单</div>
							<div class="orderNum">
								<span>待 发 配 送：<i>${stateCount}</i>笔
								</span> <a href="">查看订单 &gt;</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
<%@include file="js.jsp"%>
<!-- confirm JS -->
<script
	src="${staticFilePath}resource/custom/plugins/jquery-confirm/jquery-confirm.js"></script>
<script type="text/javascript">
    layui.use(['element', 'util', 'layer'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        var date = layui.laydate;
    });

    //新订单接单
    $(".promptly").on("click", function () {
        var orderNumber = $(this).attr("data-num");
        $.confirm({
            title: '确认接单吗？',
            content: '点击确定马上接单!',
            confirmButton: '确定',
            cancelButton: '取消',
            confirm: function () {
                var url = baseUrl + "/merchant/takeout/order/confirmTheOrder";
                var params = {
                    orderNumber: orderNumber
                };
                $.post(url, params, function (result, status) {
                    if (status === 'success') {
                        if (result && result.code === 0) {
                            layer_msg(result.message, "success");
                            setTimeout(function () {
                                window.location.href = window.location.href;
                            }, 1500)
                        } else {
                            layer_msg(result.message, "error");
                            setTimeout(function () {
                                window.location.href = window.location.href;
                            }, 3000)
                        }
                    } else {
                        layer_msg("网络连接失败", 'exception');
                    }
                }, "json");
            },
            cancel: function () {
            }
        });
    });
	$(".recruit").on("click",function(){
		var orderNumber = $(this).attr("data-num");
		var receivingCall = $(this).attr("data-call");
		var shippingAddress = $(this).attr("data-address");
		$.confirm({
            title: '确认外招配送吗？',
            content: '点击确定马上开始寻找空闲配送员!',
            confirmButton: '确定',
            cancelButton: '取消',
            confirm: function () {
                var url = baseUrl + "/merchant/takeout/order/needHelp";
                var params = {orderNumber: orderNumber,receiverPhone:receivingCall,toAddress:shippingAddress,pubUserMobile:'${merchantUser.userName}'};
                $.post(url, params, function (result, status) {
                    if (status === 'success') {
                        if (result && result.code === 0) {
                            layer_msg(result.message, "success");
                            setTimeout(function () {
                                window.location.href = window.location.href;
                            }, 1500)
                        } else {
                            layer_msg(result.message, "error");
                            setTimeout(function () {
                                window.location.href = window.location.href;
                            }, 3000)
                        }
                    } else {
                        layer_msg("网络连接失败", 'exception');
                    }
                }, "json");
            },
            cancel: function () {
            }
        })
	})
    //待配送
    $(".delivery").on("click", function () {
        var orderNumber = $(this).attr("data-num");
        $.confirm({
            title: '确认开始配送吗？',
            content: '点击确定马上开始配送!',
            confirmButton: '确定',
            cancelButton: '取消',
            confirm: function () {
                var url = baseUrl + "/merchant/takeout/order/itemsToShip";
                var params = {orderNumber: orderNumber};
                $.post(url, params, function (result, status) {
                    if (status === 'success') {
                        if (result && result.code === 0) {
                            layer_msg(result.message, "success");
                            setTimeout(function () {
                                window.location.href = window.location.href;
                            }, 1500)
                        } else {
                            layer_msg(result.message, "error");
                            setTimeout(function () {
                                window.location.href = window.location.href;
                            }, 3000)
                        }
                    } else {
                        layer_msg("网络连接失败", 'exception');
                    }
                }, "json");
            },
            cancel: function () {
            }
        })
    })
    //已处理-checkbox
    $("#oldOrd").on("click", function () {
        //默认取第一个子筛选项  ＝待配送
        window.location.href = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=1"
    })

    //新订单
    $("#newOrd").on("click", function () {
        //新订单条件
        window.location.href = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=0"
    })

    //配送中
    $("#pSZOrd").on("click", function () {
        window.location.href = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=2"
    })

    //待配送
    $("#dPSOrd").on("click", function () {
        window.location.href = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=1"
    })

    //已收货
    $("#ySHOrd").on("click", function () {
        window.location.href = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=3"
    })

    //催单
    $("#cDOrd").on("click", function () {
        window.location.href = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=4"
    })

    //取消单
    $("#qXOrd").on("click", function () {
        window.location.href = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=5"
    })

    //催单－回复消息
    $(".replyMessage").on("click", function () {
        var orderNum = $(this).attr("data-num");
        const url = baseUrl + "/merchant/takeout/order/replyMessage";
        var params = {
            orderNumber: orderNum
        };
        $.post(url, params, function (result, status) {
            if (status === 'success') {
                if (result && result.code === 0) {
                    window.location.href = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=4";
                } else {
                    layer_msg(result.message, 'error');
                }
            } else {
                layer_msg("网络连接失败,请稍后再试", "exception");
            }
        })
    })
</script>
</html>