<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/7
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp"%>
<head>
<title></title>
<meta charset="utf-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<%@include file="head.jsp"%>
<link
	href="${staticFilePath}resource/plugins/assets/laydate/theme/default/laydate.css" />
<script
	src="${staticFilePath}resource/plugins/assets/laydate/laydate.js"></script>
<style>
* {
	font-family: "微软雅黑";
}

.szuo {
	font-size: 13px;
	text-align: left;
	height: 20px;
	padding-left: 0px;
}

.szhong {
	font-size: 13px;
	text-align: right;
	height: 20px;
	text-align: right;
}

.syou {
	font-size: 13px;
	height: 20px;
	text-align: right;
}

.xiaojie {
	font-size: 13px;
	float: right;
	text-align: right;
	margin-right: 0px;
	padding-right: 0px;
}

.tog {
	width: 100%;
	padding: 0px;
}

.xiangqing {
	margin-top: 0px;
}

.row {
	margin-top: 20px;
	background-color: white;
	height: 100%;
	background-color: white;
	width: 100%;
	float: right;
	margin-right: 1px;
	padding-left: 0px;
	padding-right: 0px;
	padding-bottom: 10px;
	border-bottom: 2px solid #ddd;
}

.bianhao {
	text-align: left;
	padding-right: 20px;
	font-size: 12px;
	height: 30px;
	margin-top: 12px;
	font-weight: 700;
	padding-left: 0px;
	line-height: 30px;
}

.shouqi {
	font-size: 10px;
	color: dodgerblue;
	float: right;
	margin-right: 13px;
	line-height: 40px;
}

.xuhao {
	background-color: lightgray;
	margin-top: 0px;
	height: 40px;
	line-height: 40px;
	padding-left: 0px;
}

.bt {
	width: 10%;
	height: 30px;
	background-color: white;
	border: 1px solid dodgerblue;
	border-radius: 5px;
	color: lightskyblue;
	margin-top: 12px;
}

.bt-primary {
	background-color: dodgerblue;
	color: white;
}

.nav {
	height: 40px;
	background-color: white;
	text-align: center;
	line-height: 40px;
}

.nav div a {
	text-decoration: none;
	out-line: none;
	font-size: 15px;
	font-weight: 700;
}

.active {
	border-bottom: 3px solid dodgerblue;
	height: 40px;
}
</style>

<style type="text/css">
.printArea {
	padding: 0;
	margin: 0;
}

.useTitle {
	font-size: 20px;
}

.useLittleTitle {
	font-size: 16px;
}

.left {
	float: left;
}

.right {
	float: right;
}

.clearfix {
	clear: both;
}

ul {
	list-style: none;
}

.print_container {
	padding: 20px;
	width: 188px;
}

.section1 {
	
}

.section2 label {
	display: block;
}

.section3 label {
	display: block;
}

.section4 {
	
}

.section4 .total label {
	display: block;
}

.section4 .other_fee {
	border-bottom: 1px solid #DADADA;
}
</style>
<style>
.tableNumberH {
	padding-left: 0;
	margin-bottom: 15px;
}

.tableNumberH>b {
	padding-top: 15px;
	font-size: 15px;
}

.pd-0 {
	padding-left: 0;
}

.tabDivFirstChild {
	padding-left: 10px;
	padding-right: 10px;
	background-color: #f3f3f4;
	padding-bottom: 15px;
}

.productDetailBody {
	padding-left: 0;
	/*padding-bottom: -10px;*/
	margin-top: 10px;
}

.productDetailBody>h5 {
	margin-bottom: 0;
}

.xiangqing.bodyContent {
	border-bottom: 1px solid gainsboro;
	border-top: 1px solid gainsboro;
	height: 45px;
	margin-top: 10px;
}

.szuo.bodyContent {
	font-weight: 700;
	font-size: 12px;
	margin-top: -7px;
}

.szhong.bodyContent {
	text-align: right;
	font-weight: 700;
	font-size: 13px;
	margin-top: -7px;
}

.syou.bodyContent {
	padding-right: 0;
	text-align: right;
	margin-right: 0;
	font-weight: 700;
	font-size: 13px;
	margin-top: -7px;
}

.xiangqing.bodyContent {
	padding-right: 0;
}
</style>

<style>
.contentCondition {
	background-color: #fff;
}

.contentCondition>label.labelName {
	padding: 15px;
	font-size: 15px;
	margin-bottom: 0;
}

.contentCondition>div.dateInputDiv {
	display: inline-block;
}

.contentCondition>div.dateInputDiv>input {
	height: 30px;
	border-radius: 2px;
	border: 1px solid #e2e2e2;
	padding-left: 10px;
	width: 220px;
}

.contentCondition>div.dateInputDiv>button {
	height: 30px;
	line-height: 30px;
	/*padding-left: 50px;*/
	margin-left: 50px;
	position: relative;
	top: -2px;
}

.dateCondition {
	padding-top: 65px;
}

.page {
	font-size: 15px;
	text-align: center;
	font-weight: 2px;
}
</style>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<%@include file="top.jsp"%>
		<div class="layui-body" style="background-color: #f3f3f4">
			<div class="body-content">
				<div class="main-container" id="main-container">
					<div class="main-content">
						<div class="tabbable">
							<div class="col-md-12 tabDivFirstChild">
								<div class="tabTop">
									<div class="col-md-12 nav">
										<div class="col-md-2">
											<a href="${sysPath}merchant/buffetFood/order/query">未结算订单</a>
										</div>
										<div class="col-md-2">
											<div class="active">
												<a href="${sysPath}merchant/buffetFood/order/done">已结算订单</a>
											</div>
										</div>
									</div>
								</div>
								<div class="dateCondition">
									<div class="contentCondition">
										<label class="labelName">选择日期:</label>
										<div class="dateInputDiv">
											<input id="date" readonly placeholder="点击选择筛选日期" name="date"
												value="">
											<button class="layui-btn-normal layui-btn nowQueryBtn">查询</button>
										</div>
									</div>
								</div>
								<div class="tabListDiv">
									<c:choose>
										<c:when test="${page.list != null}">
											<c:forEach items="${page.list}" var="each">
												<!-- 有数据显示 -->
												<div class="row item col-md-12">
													<h3 class="xuhao serialNumber bodyContent col-md-12 pd-0">
														<b class="col-md-12">#${each.allSerialNumber}</b>
													</h3>
													<h5 class="col-md-12 tableNumberH bodyContent">
														<b class="col-md-12">桌号：${each.seatName}</b>
													</h5>
													<hr />
													<span class="col-md-6 productDetailBody bodyContent">
														<h5 class="col-md-12">
															<b>商品信息</b>
														</h5>
													</span> <span class="shouqi">收起<i
														class="arrow fa fa-angle-up"></i></span><br />
													<div class="tog tabDivList bodyContent">
														<c:forEach items="${each.productLists}" var="product">
															<span class="xiangqing col-md-12"> <span
																class="szuo col-md-6">${product.productName}</span> <span
																class="szhong col-md-1">¥${product.price}</span> <span
																class="syou col-md-2">X${product.quantity}</span> <span
																class="xiaojie col-md-1">¥${product.price * product.quantity}</span>
																<br />
															</span>
														</c:forEach>
													</div>
													<span class="xiangqing bodyContent col-md-12"> <br />
														<span class="szuo bodyContent col-md-6">订单时间：${each.orderTime}&nbsp;&nbsp;&nbsp;&nbsp;已提交：<b>${each.minute}</b>钟
													</span> <span class="col-md-1"></span> <span
														class="szhong bodyContent col-md-2">共<b>${each.sum}</b>件
													</span> <span class="syou bodyContent col-md-1 floatRight">合计：${each.orderAmount}<b>
														</b>元
													</span><br /> <br />
													</span> <span class="col-md-12"> <span
														class="col-md-6 bianhao orderNumber bodyContent">订单编号：${each.orderNumber}</span>
														<span class="xiangqing col-md-6"> <input
															type="button"
															class="bt bt-primary col-md-2 floatRight println curPage"
															value="打印" data-orderNumber="${each.orderNumber}"
															data-orderAmount="${each.orderAmount} " />
													</span>
													</span>
												</div>
											</c:forEach>
											<c:choose>
												<c:when test="${startTime!=null&&endTime!=null }">
													<div class="row item col-md-12 page">
														<p>当前${page.pageNum}页，一共${page.pages}页</p>
														<a href="done?pageNum=${page.firstPage}&startTime=${startTime }&endTime=${endTime}">第一页</a> <a
															href="done?pageNum=${page.nextPage}&startTime=${startTime }&endTime=${endTime}">下一页</a> <a
															href="done?pageNum=${page.prePage}&startTime=${startTime }&endTime=${endTime}">上一页</a> <a
															href="done?pageNum=${page.lastPage}&startTime=${startTime }&endTime=${endTime}">最后页</a>
													</div>
												</c:when>
												<c:otherwise>
													<div class="row item col-md-12 page">
														<p>当前${page.pageNum}页，一共${page.pages}页</p>
														<a href="done?pageNum=${page.firstPage}">第一页</a> <a
															href="done?pageNum=${page.nextPage}">下一页</a> <a
															href="done?pageNum=${page.prePage}">上一页</a> <a
															href="done?pageNum=${page.lastPage}">最后页</a>
													</div>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<!-- 没有数据显示 -->
											<div class="showNoDataText"
												style="text-align: center; margin-top: 50px;">
												<div class="noOrder" style="padding-top: 20px;">
													<div class="noOrder-content">
														<div class="noOrder-content-div">
															<div class="wane">
																<i class="fa fa-file-text-o wane-icon"
																	aria-hidden="true"
																	style="font-size: 100px; line-height: 170px; color: #fff"></i>
															</div>
															<p class="noOrder-p">暂无订单数据</p>
															<span class="noOrder-span">当前没有自助点餐的未结算订单记录!</span>
														</div>
													</div>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<div class="modal printArea" style="background-color: #fff;">
		<div class="print_container">
			<h1 class="useTitle">顾客专用</h1>
			<span>**************************</span>
			<div class="section1">
				<h3 class="useLittleTitle">自助点餐已结账单</h3>
			</div>
			<span>**************************</span>
			<div class="section3">
				<label id="orderNumber"></label> <label id="orderTime"></label>
			</div>
			<span>**************************</span>
			<div class="section4">
				<div style="border-bottom: 1px solid #DADADA;">
					<!--<ul>
                        <div>菜单名称     数量    金额</div>
                        <li>米饭米饭 米饭 米饭 米饭 米饭 米饭       2    28元</li>
                        <li>米饭      2    28元</li>
                    </ul>-->
					<table style="width: 100%;">
						<thead>
							<tr>
								<td width="60%">菜单名称</td>
								<td width="20%">数量</td>
								<td width="20%">金额</td>
							</tr>
						</thead>
						<tbody id="orderProduct">
						</tbody>
					</table>
				</div>
				<span>**************************</span>
				<div class="total">
					<label class="left">总计</label> <label class="right"
						id="orderAmount">39</label>
					<div class="clearfix"></div>
				</div>
				<div class="section3">
					<label id="nowTime"></label>
				</div>
			</div>
			<span>**************************</span>
		</div>
	</div>
</body>
<%@include file="js.jsp"%>
<script type="text/javascript">
	layui.use([ 'element', 'util', 'layer', 'laydate', 'laypage' ], function() {
		var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
		var date = layui.laydate;
		var page = layui.laypage;

        date.render({
			elem : '#date',
			theme : '#20A0FF',
			trigger : 'click',
			max : 0,
			min : -365,
			range : '~',
			format : 'yyyy-MM-dd'
		})
	});
	//当前点击对象
	var curClickEle;
	//当前点击对象的条目集合
	var curClickEleItemList;

	//商品条目构造函数
	function ProductItem(productName, productQuantity, price, total) {
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.price = price;
		this.total = total;
	}

	//添加的商品构造函数
	function addedEntriesConstruction(productName, productQuantity,
			productPrice, productId) {
		this.productName = productName;
		this.quantity = productQuantity;
		this.price = productPrice;
		this.productId = productId;
	}

	$('.shouqi').click(function() {
		if ($(this).html() == '收起<i class="arrow fa fa-angle-up"></i>') {
			$(this).html('展开<i class="arrow fa fa-angle-down"></i>');
		} else {
			$(this).html('收起<i class="arrow fa fa-angle-up"></i>');
		}
		$(this).nextAll('.tog').toggle(200);
	});

	$(function() {
		$(".floatRight.println.curPage").on("click", function() {
			curClickEle = $(this);
			updataPrintArea()
		})
	})
	function updataPrintArea() {
		var ele = $(curClickEle).parent().parent().parent();
		curClickEleItemList = new Array();
		//商品条目信息
		var tabDivListEleSpan = $(ele).find(".tog.tabDivList.bodyContent")
				.find("span.xiangqing");
		$(tabDivListEleSpan).each(
				function(index, item) {
					var productName = $(item).find(".szuo").text();
					var quantity = parseInt($(item).find(".syou").text()
							.replace("X", ""));
					var total = $(item).find(".xiaojie").text();
					var price = $(item).find(".szhong").text();
					var productItem = new ProductItem(productName, quantity,
							price, total);
					curClickEleItemList.push(productItem);
				});
		var orderSubmitTimeEle = $(".xiangqing.bodyContent").find(
				".szuo.bodyContent > b").html();
		$("#orderTime").html("下单时间:<br />" + orderSubmitTimeEle + "钟")
		var orderNumberEle = $(curClickEle).attr("data-orderNumber");
		$("#orderNumber").html("订单编号:<br />" + orderNumberEle)
		var orderAmountEle = $(curClickEle).attr("data-orderAmount");
		$("#orderAmount").html("¥" + orderAmountEle)
		var productItems = $("#orderProduct")
		productItems.empty();
		var myDate = new Date();
		$("#nowTime").html(
				"时间:<br />" + myDate.toLocaleDateString()
						+ myDate.toLocaleTimeString())
		$(curClickEleItemList).each(
				function(index, item) {
					var insertStr = "<tr>" + "<td>" + item.productName
							+ "</td>" + "<td>" + item.productQuantity + "</td>"
							+ "<td>" + item.price + "</td>" + "</tr>"
					productItems.append(insertStr);
				})
		printArea()
	}
	function printArea() {
		var body = $("body").html();
		var printArea = $(".printArea").html();
		$("body").html(printArea);
		window.print();
		$("body").html(body);
	}

	//查询按钮绑定点击事件
	$("button.nowQueryBtn").on(
			"click",
			function() {
				var dateVal = $("#date").val();
				if (dateVal === '' || dateVal === undefined) {
					return false;
				}
				var dateValList = dateVal.split("~");
				window.location.href = baseUrl
						+ "/merchant/buffetFood/order/done?startTime="
						+ dateValList[0] + "&endTime=" + dateValList[1];
			})
</script>
</html>
