<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/30
  Time: 16:21
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

.modal-body span {
	text-align: center;
	margin-top: 10px;
	font-weight: 700;
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
        .PrintArea{padding:0;margin: 0;}
        .useTitle{font-size: 20px;}
        .useLittleTitle{font-size: 16px;}
        .left{
            float: left;
        }
        .right{
            float:right;
        }
        .clearfix{
            clear: both;
        }
        ul{list-style: none;}
        .print_container{
            padding: 20px;
            width: 188px;
        }
        .section1{
        }
        .section2 label{
            display: block;
        }
        .section3 label{
            display: block;
        }
        .section4{
        	font-size: 12px;
        }
        .section4 .total label{
            display: block;
        }
        .section4 .other_fee{
            border-bottom: 1px solid #DADADA;
        }
        .section5 label{
            display: block;
        }
</style>
<style>

.affirmSettleBtn {
	height: 30px;
	float: right;
	line-height: 30px;
	width: 20%;
	margin-top: 11px;
}

.print {
	height: 30px;
	float: right;
	line-height: 30px;
	width: 20%;
	margin-top: 11px;
}

.oBuffetFoodQuerySpanSelect {
	margin-top: 5px;
	margin-bottom: 0;
	padding: 5px;
}

.oBuffetFoodQuerySpanInput {
	margin-top: 5px;
	margin-bottom: 5px;
	padding: 5px;
}

.oBuffetFoodQuerySpanButton {
	margin-top: 5px;
	margin-bottom: 5px;
	padding: 5px;
}

.oBuffetFoodQuerySpanInput>span>input {
	width: 100%;
	border-radius: 2px;
	border: 1px solid #e2e2e2;
	height: 30px;
	padding-left: 10px;
}

.oBuffetFoodQuerySpanSelect>span>select {
	width: 100%;
	border-radius: 2px;
	border: 1px solid #e2e2e2;
	height: 30px;
	padding-left: 10px;
}
.orderTicketList>span>select {
	width: 100%;
	border-radius: 2px;
	border: 1px solid #e2e2e2;
	height: 30px;
	padding-left: 10px;
}

.spanFontSize {
	font-size: 13px;
}

.affirmAddItemBtn {
	height: 20px;
	border-radius: 2px;
	border: 1px solid #1E9FFF;
	color: #fff;
	padding: 8px;
	line-height: 2px;
	background-color: #1E9FFF;
}

.labelSpanText {
	font-size: 15px;
	font-weight: 500;
	line-height: 30px;
	padding-right: 0;
	text-align: left;
}

.iconSpan {
	position: relative;
	left: -150px;
	color: #1E9FFF;
	cursor: pointer;
}

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

.productDetailH {
	padding-bottom: 0;
	margin-top: 17px;
	text-align: left;
}

.numberH {
	border-radius: 6px 6px 0 0;
}

.tableTableModeH {
	margin-bottom: 15px;
}

.tableTableModeH>b {
	padding-top: 15px;
	font-size: 15px;
}

.productDetailH>h5 {
	margin-bottom: 0;
	padding-left: 0;
}

.shouqi.ModelWindow {
	cursor: pointer;
}

.xiangqing.modelWindow {
	border-bottom: 1px solid gainsboro;
	border-top: 1px solid gainsboro;
	height: 40px;
	padding-bottom: 13px;
}

.bt.modelWindow {
	width: 15%;
	border-radius: 6px;
}

.cancel.modelWindow {
	float: left;
	margin-left: 30px;
	width: 15%;
	border-radius: 6px;
}

.szhong.modelWindow {
	text-align: right;
	font-size: 14px;
	margin-top: 0;
	line-height: 0;
}

.xiaojie.modelWindow {
	text-align: right;
	font-size: 14px;
	margin-top: 0;
	line-height: 0;
}

.szuo.modelWindow {
	line-height: 0;
	margin-top: 0;
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

.tog.modelWindow {
	height: 70px;
	overflow: auto;
}
</style>
<%--<script>--%>
<%--function printMenu() {--%>
<%--var url = baseUrl+"/printMenu";--%>
<%--$.post(url,function (result) {--%>
<%--if("fail".equal(result)){--%>
<%--alert("打印失败");--%>
<%--}else if ("success".equal(result)){--%>
<%--$('#myModal').modal('dismiss');--%>
<%--}--%>
<%--});--%>
<%--}--%>
<%--</script>--%>
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
											<div class="active">
												<a href="${sysPath}merchant/buffetFood/order/query">未结算订单</a>
											</div>
										</div>
										<div class="col-md-2">
											<a href="${sysPath}merchant/buffetFood/order/done">已结算订单</a>
										</div>
									</div>
								</div>
								<div class="tabListDiv">
									<c:choose>
										<c:when test="${data != null}">
											<c:forEach items="${data}" var="each">
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
														<%--<c:set var="sum" value="${fn:length(each.productLists)}"/>--%>
														<c:forEach items="${each.productLists}" var="product">
															<c:set var="unitPrice" value="${product.price}" />
															<span class="xiangqing col-md-12"> <span
																class="szuo col-md-6">${product.productName}</span> <span
																class="szhong col-md-1">¥${unitPrice}</span> <span
																class="syou col-md-2">X${product.quantity}</span> <span
																class="xiaojie col-md-1">¥${product.price * product.quantity}</span>
																<br />
															</span>
														</c:forEach>
													</div>
													<span class="col-md-12 orderTicketList">
														<span class="col-md-4 spanFontSize labelSpanText">请选择优惠券：</span>
														<span class="col-md-8"> 
														<select >
																<c:choose>
																	<c:when test="${each.ticketList!= null}">
																		<option value="0" data-price="0">请选择优惠券</option>
																			<c:forEach items="${each.ticketList}" var="ticketList">
																				<option value="${ticketList.tMoney }" data-money="${ticketList.tUseMoney}"
																				data-Id="${ticketList.id }">${ticketList.tName}</option>
																			</c:forEach>
																	</c:when>
																	<c:otherwise>
																		<option value="0" data-price="0">没有可选择的优惠券</option>
																	</c:otherwise>
																</c:choose>
														</select>
													</span>
													</span>
													 <span class="xiangqing bodyContent col-md-12"> <br />
														<span class="szuo bodyContent col-md-6">订单时间：${each.orderTime}&nbsp;&nbsp;&nbsp;&nbsp;已提交：<b>${each.minute}</b>钟
													</span> <span class="col-md-1"></span> <span
														class="szhong bodyContent col-md-2">共<b>${each.sum}</b>件
													</span> <span class="syou bodyContent col-md-1 floatRight">合计：<b>${each.orderAmount}</b>元
													</span><br /> <br />
													</span> <span class="col-md-12"> <span
														class="col-md-6 bianhao orderNumber bodyContent"
														value="${each.orderNumber}">订单编号：${each.orderNumber}</span>
														<span class="xiangqing col-md-6"> <input
															type="button"
															class="bt bt-primary col-md-2 floatRight settleBtn curPage"
															value="结算" />
													</span>
													</span>
												</div>
											</c:forEach>
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
															<p class="noOrder-p">暂无未结算订单数据</p>
															<span class="noOrder-span">当前没有自助点餐的未结算订单记录!</span>
														</div>
													</div>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
									<c:if test="${data != null}">
										<div id="page"></div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<!-- 模态框（Modal） -->
	<div class="modal fade " id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div style="border-radius: 6px;" class="row col-md-12">
				<span class="col-md-12 oBuffetFoodQuerySpanSelect"> <span
					class="col-md-4 spanFontSize labelSpanText">请选择其他消费项目：</span> <span
					class="col-md-8"> <select class="selectDown">
							<c:choose>
								<c:when test="${productData != null}">
									<option value="0" data-price="0">请选择商品</option>
									<c:forEach items="${productData}" var="each">
										<option value="${each.id}" data-price="${each.price}">${each.productName}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<option value="0" data-price="0">请选择商品</option>
								</c:otherwise>
							</c:choose>
					</select>
				</span>
				</span> 
				<span class="col-md-12 oBuffetFoodQuerySpanInput"> <span
					class="col-md-4 spanFontSize labelSpanText">请输入数量：</span> <span
					class="col-md-8"> <input name="quantity" class="quantity"
						placeholder="请输入数量" value="">
				</span></span> 
				<span class="col-md-12 ticket modelWindow"> 
				<span class="col-md-4 spanFontSize labelSpanText">已选择的优惠券：
				</span>
				<span class="col-md-8">
					
				</span>
				</span> 
				
				<span class="col-md-12 oBuffetFoodQuerySpanButton"> <span
					class="col-md-12 spanFontSize">
						<button class="affirmAddItemBtn">确认添加</button>
				</span>
				</span>
				<h3 class="xuhao col-md-12 pd-0 numberH modelWindow">
					<b class="col-md-12"></b>
				</h3>
				<h5 class="col-md-12 pd-0 tableTableModeH modelWindow">
					<b class="col-md-12">桌号：</b>
				</h5>
				<hr />
				<span class="col-md-6 productDetailH modelWindow">
					<h5 class="col-md-12">
						<b>商品信息</b>
					</h5>
				</span> <span class="shouqi ModelWindow">收起<i
					class="arrow fa fa-angle-up"></i></span> <br />
				<div class="tog modelWindow" ></div>
				<span class="xiangqing modelWindow col-md-12"> <br /> <span
					class="szuo col-md-4 bianhao modelWindow">订单已提交：<b></b>钟
				</span> <span class="xiaojie modelWindow allXiaoJie col-med-2 ">合计：<b></b>元
				</span> <span class="szhong modelWindow allCount col-md-2 ">共<b></b>件
				</span> <br />
				</span> <span class="col-md-12"> <span
					class="col-md-6 bianhao orderNumber modelWindow">订单编号：</span> <span
					class="xiangqing col-md-12">
						<button id="myModalc" type="button"
							class="bt modelWindow bt-primary col-md-4">打印</button> <input
						data-dismiss="modal" type="button"
						class="bt cancel modelWindow bt-default col-md-4 " value="取消" /> 
						<button type="button"
							class="layui-btn layui-btn-normal col-md-4 affirmSettleBtn">确认结算</button>
				</span>
				</span>
			</div>
		</div>
	</div>
	 <div class="PrintArea area1 both" id="Retain">
       <div class="print_container">
            <h1 class="useTitle">顾客专用</h1>
            <span>**************************</span>
            <div class="section1">
                <h3 class="useLittleTitle">自助点餐结账单</h3>
            </div>
            <span>**************************</span>
            <div class="section3">
                <label id="orderNumber"></label>
                <label id="orderTime"></label>
            </div>
            <span>**************************</span>
            <div class="section4">
                <div style="border-bottom: 1px solid #DADADA;">
                    <table style="width: 100%;">
                        <thead>
                            <tr>
                                <td width="50%">菜单名称</td>
                                <td width="30%">数量</td>
                                <td width="20%">金额</td>
                            </tr>
                        </thead>
                        <tbody id="orderProduct">
                        </tbody>
                    </table>
                </div>
            	<span>**************************</span> 
                <div class="total">
                    <label class="left">总计</label>
                    <label class="right" id="orderAmount">39</label>
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
<script>

    layui.use(['element', 'util', 'layer', 'laydate', 'laypage'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var page = layui.laypage;
        page.render({
            elem: 'page'
            , curr: ${page == null ? 1 : page.pageNum}
            , groups: 3
            , count: ${page == null ? 10: page.pages * 10}
            , theme: '#1E9FFF'
            , first: false
            , last: false
        });
    });

    //当前点击对象
    var curClickEle;
    //当前点击对象的条目集合
    var curClickEleItemList;

    //商品条目构造函数
    function ProductItem(productName, productQuantity,price,total) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.price=price;
        this.total=total;
    }

    //添加的商品构造函数
    function addedEntriesConstruction(productName, productQuantity, productPrice, productId) {
        this.productName = productName;
        this.quantity = productQuantity;
        this.price = productPrice;
        this.productId = productId;
    }

    //结算时添加其它商品到订单条目中
    $(".oBuffetFoodQuerySpanButton > span > .affirmAddItemBtn").on("click", function () {
    	debugger
        //获得select选中对象
        var selectedEle = $(".oBuffetFoodQuerySpanSelect > span > select").children('option:selected');
        //获得数量输入值对象
        var inputQuantityEle = $(".oBuffetFoodQuerySpanInput > span > input.quantity");
        if (parseInt(selectedEle.val()) < 1 || parseFloat(selectedEle.attr("data-price")) < 1) {
            selectedEle.parent().addClass("animation_input");
            setTimeout(function () {
                selectedEle.parent().removeClass("animation_input");
            }, 1500);
            return false;
        }
        if (inputQuantityEle.val() == '' || inputQuantityEle == undefined) {
            inputQuantityEle.addClass("animation_input");
            inputQuantityEle.attr("placeholder", "数量值不能为空");
            setTimeout(function () {
                inputQuantityEle.removeClass("animation_input");
                inputQuantityEle.attr("placeholder", "请输入数量");
            }, 1500);
            return false;
        }
        //拿到值后动态插入页面中,页面中动态插入的元素可以动态删除
        var orderListParentEle = $(".tog.modelWindow");
        var price = selectedEle.attr("data-price");
        var productId = selectedEle.val();
        var orderId = selectedEle.attr("data-orderId");
        var insertStr =
        	'<span class="xiangqing addItem col-md-12" data-price="' + price + '" data-productId="' + productId + '">\n' +
            '                    <span class="szuo col-md-3">' + selectedEle.text() + '</span>\n' +
            '<span class="iconSpan"><i class="fa fa-minus-circle" aria-hidden="true"></i></span>' +
            '<span class="xiaojie col-md-2 folatRight">¥'+(parseInt(inputQuantityEle.val())*parseInt(price)).toFixed(1)+'</span>\n'+
            '                    <span class="syou col-md-2 floatRight">X' + inputQuantityEle.val() + '</span>\n' +
            '<span class="szhong col-md-2 folatRingth">¥'+price+'</span>\n'
            '                    <br>\n' +
        	'                </span>';
        orderListParentEle.append(insertStr);
        //动态计算弹窗总件数
        var a=0;
        sumUp(a,(parseInt(inputQuantityEle.val())*parseInt(price)),inputQuantityEle.val());
    });

    //删除条目按钮
    $(document).on("click", ".iconSpan", function () {
    	debugger
        var ele = $(this);
        var total=$(ele).siblings(".xiaojie").html().replace("¥", "");
        var quantity=$(ele).siblings(".syou").html().replace("X", "");
        var eleParent = ele.parent();
        eleParent.remove();
        var a=1;
        //动态计算弹窗总件数
        sumUp(a,total,quantity);
    })

    //删除或添加后动态计算总条数
    function sumUp(a,price,quantity) {
    	debugger
        //取总数值
        var allCountEle = $(".modelWindow.allCount > b").html();
        //计算item数量
        var itemList = $(".tog.modelWindow");
        var orderAmountEle=$(".xiaojie.modelWindow.allXiaoJie >b").html();
        if(a==0){
        	$(".xiaojie.modelWindow.allXiaoJie >b").html((parseInt(orderAmountEle)+parseInt(price)).toFixed(1))
        	$(".modelWindow.allCount > b").html(parseInt(allCountEle)+parseInt(quantity))
        }else if(a){
        	$(".xiaojie.modelWindow.allXiaoJie >b").html(parseInt(orderAmountEle)-parseInt(price).toFixed(1))
        	$(".modelWindow.allCount > b").html(parseInt(allCountEle)-parseInt(quantity))
        }
       
    }

    //结算按钮绑定点击事件
    $(".settleBtn.curPage").on("click", function () {
        //通过js方式操作模态框
        curClickEle = $(this);
        //设置模态框的内容
        $('#myModal').modal('show');
    })

    //手动调起模态框的回调函数
    $("#myModal").on('show.bs.modal', function (even) {
    	debugger
        //要显示的数据是: 桌号，商品条目信息（名字，数量），总数量,　订单号, 序号
        //初始化select选择框为未选中状态
        $(".selectDown").get(0).selectedIndex = 0;
        $(".quantity").val("");  //第次打开数量值都为空
        //获取当前点击条目的一级父元素
        var ele = $(curClickEle).parent().parent().parent();
        curClickEleItemList = new Array();
        //商品条目信息
        var tabDivListEleSpan = $(ele).find(".tabDivList.bodyContent").find("span.xiangqing");
        $(tabDivListEleSpan).each(function (index, item) {
            var productName = $(item).find(".szuo").text();
            var quantity = parseInt($(item).find(".syou").text().replace("X", ""));
            var total=$(item).find(".xiaojie").text();
            var price=$(item).find(".szhong").text();
            var productItem = new ProductItem(productName,quantity,price,total);
            curClickEleItemList.push(productItem);
        });
        //优惠券信息
        var ticket=$(ele).find(".orderTicketList > span > select").children('option:selected').text();
        //优惠券限定
        var ticket_tUseMoney=$(ele).find(".orderTicketList > span > select").children('option:selected').attr("data-money");
        //优惠券金额
        var ticket_tMoney=$(ele).find(".orderTicketList > span > select").children('option:selected').attr("value");
        //优惠券时间
        var dataId=$(ele).find(".orderTicketList > span > select").children('option:selected').attr("data-Id");
        //桌号
        var tableNumber = $(ele).find(".tableNumberH.bodyContent > b").text();
        //总数量
        var totalCount = $(ele).find(".szhong.bodyContent > b").text();
        //订单号
        var orderNumber = $(ele).find(".orderNumber.bodyContent").text();
        //序号
        var serialNumber = $(ele).find(".serialNumber.bodyContent > b").text();
        //订单提交时间
        var orderSubmitTime = $(ele).find(".szuo.bodyContent > b").text();
		var orderAmount=$(ele).find(".syou.bodyContent > b").text();
        //获取模态框的tabDom对象
        //模态框优惠券
        var ticket_m=$(this).find(".ticket.modelWindow > span:last");
        if(ticket_tMoney!=0){
        	if(parseInt(ticket_tUseMoney)<=parseInt(orderAmount)){
                ticket_m.text(ticket);
            }else{
            	ticket_m.text("优惠券使用条件不满足");
           }
        }else{
        	ticket_m.text("不使用优惠券");
        }
       
        //模态框序号
        var serialNumber_m = $(this).find(".numberH.modelWindow > b");
        serialNumber_m.text(serialNumber);
        //模态框桌号
        var tableNumber_m = $(this).find(".tableTableModeH.modelWindow > b");
        tableNumber_m.text(tableNumber);
        //总数对象
        var totalCountEle_m = $(this).find(".xiangqing.modelWindow").find(".szhong.modelWindow > b");
        totalCountEle_m.text(totalCount);
        //模态框订单号
        var orderNumberEle = $(this).find(".orderNumber.modelWindow");
        orderNumberEle.text(orderNumber);
        //模态框订单已提交时间
        var orderSubmitTimeEle = $(this).find(".bianhao.modelWindow > b");
        orderSubmitTimeEle.text(orderSubmitTime);
        //模态框总价
        var orderAmountEle=$(this).find(".xiaojie.modelWindow > b");
        if(parseInt(ticket_tUseMoney)<parseInt(orderAmount)){
        	orderAmountEle.text((parseInt(orderAmount)-parseInt(ticket_tMoney)).toFixed(1));
        }else{
        	 orderAmountEle.text(parseInt(orderAmount).toFixed(1));
        }
        $(".print").attr("orderNumber",$(ele).find(".orderNumber.bodyContent").attr("value"));
        //模态框商品条目内容
        var productItemParent = $(this).find(".tog.modelWindow");
        productItemParent.empty();
        $(".affirmSettleBtn").attr("data-orderNumber", orderNumber);
        $(".affirmSettleBtn").attr("data-Id",dataId);
        $(curClickEleItemList).each(function (index, item) {
            var insertStr = '<span class="xiangqing col-md-12">\n' +
                '                    <span class="szuo col-md-3">' + item.productName + '</span>\n' +
                '<span class="xiaojie col-md-2 folatRight">'+item.total+'</span>\n'+
                '                    <span class="syou col-md-2 floatRight">X' + item.productQuantity + '</span>\n' +
                '<span class="szhong col-md-2 folatRingth">'+item.price+'</span>\n'
                '                    <br>\n' +
                '                </span>';
            productItemParent.append(insertStr);
        })
    });


    $(".affirmSettleBtn").on("click", function () {
    	debugger
        var orderNumber = $(this).attr("data-orderNumber").replace("订单编号：", "");
        var dataId = $(this).attr("data-Id");
        const url = baseUrl + "/merchant/buffetFood/affirmSettleMethod";
        //获取新增的商品名，价格，数量，商品id
        var addedEntries = $(".xiangqing.addItem");
        var paramList = new Array();
        $(addedEntries).each(function (index, item) {
            //商品名,价格，数量，商品id
            var price = $(item).attr("data-price");
            var productId = $(item).attr("data-productId");
            var productName = $(item).find(".szuo").text();
            var quantity = $(item).find(".syou").text().replace("X", "");
            paramList.push(new addedEntriesConstruction(productName, quantity, price, productId));
        });
        $.ajax({
            url: url,
            type: 'post',
            data: {productList: JSON.stringify(paramList), orderNumber: orderNumber,dataId:(dataId!=null?dataId:null)},
            dataType: 'json',
            success: function (data) {
                if (data && data.code === 0) {
                	alert(data.message)
                    window.location.href = window.location.href;
                } else {
                    layer_msg(data.message, 'error');
                    return false;
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrow) {
                layer_msg("网络连接失败", 'exception');
                return false;
            }
        });
    })
    
    $(function(){
	 $("#myModalc").on("click",function(){
		 updataPrintArea()
	    })
})

function updataPrintArea(){
       curClickEleItemList = new Array();
       //商品条目信息
       var tabDivListEleSpan = $(".modal-dialog").find(".tog.modelWindow").find("span.xiangqing");
       $(tabDivListEleSpan).each(function (index, item) {
           var productName = $(item).find(".szuo").text();
           var quantity = parseInt($(item).find(".syou").text().replace("X", ""));
           var total=$(item).find(".xiaojie").text();
           var price=$(item).find(".szhong").text();
           var productItem = new ProductItem(productName,quantity,price,total);
           curClickEleItemList.push(productItem);
       });
       var orderSubmitTimeEle=$("#myModal").find(".bianhao.modelWindow > b").html();
       $("#orderTime").html("下单时间:<br />"+orderSubmitTimeEle+"钟")
       var orderNumberEle=$("#myModal").find(".orderNumber.modelWindow").html().replace("订单编号：", "");
       $("#orderNumber").html("订单编号:<br />"+orderNumberEle)
       var orderAmountEle=$("#myModal").find(".xiaojie.modelWindow > b").html();
       $("#orderAmount").html("¥"+orderAmountEle)
       var productItems=$("#orderProduct")
       productItems.empty();
       var myDate = new Date();    
       $("#nowTime").html("时间:<br />"+myDate.toLocaleDateString()+myDate.toLocaleTimeString())
       $(curClickEleItemList).each(function (index, item) {
           var insertStr = "<tr>"+
           "<td>"+item.productName+"</td>"+
           "<td>"+item.productQuantity+"</td>"+
           "<td>"+item.price+"</td>"+
           "</tr>"
           productItems.append(insertStr);
       })
	 printArea()
}
    function printArea(){
    	var printArea=$("div.PrintArea").html();
    	$("#Retain").printArea()
    }
 
    	
 </script>
<script>
    $(function () {
        $('.shouqi').click(function () {
            if ($(this).html() == '收起<i class="arrow fa fa-angle-up"></i>') {
                $(this).html('展开<i class="arrow fa fa-angle-down"></i>');
            } else {
                $(this).html('收起<i class="arrow fa fa-angle-up"></i>');
            }
            $(this).nextAll('.tog').toggle(200);
        });
    });
</script>
</html>
