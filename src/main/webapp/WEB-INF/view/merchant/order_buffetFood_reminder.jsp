<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/7
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title></title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
    <link href="${staticFilePath}resource/plugins/assets/laydate/theme/default/laydate.css"/>
    <script src="${staticFilePath}resource/plugins/assets/laydate/laydate.js"></script>
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
            margin-top: 5px;
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
            color: # *****;
            font-size: 15px;
            font-weight: 700;
        }

        .active {
            border-bottom: 3px solid dodgerblue;
            height: 40px;
        }


    </style>
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

	<style>
		.affirmSettleBtn {
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

		.oBuffetFoodQuerySpanInput > span > input {
			width: 100%;
			border-radius: 2px;
			border: 1px solid #e2e2e2;
			height: 30px;
			padding-left: 10px;
		}

		.oBuffetFoodQuerySpanSelect > span > select {
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

		.tableNumberH > b {
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

		.tableTableModeH > b {
			padding-top: 15px;
			font-size: 15px;
		}

		.productDetailH > h5 {
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
			float: right;
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

		.productDetailBody > h5 {
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
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="main-container" id="main-container">
                <div class="main-content">
                    <div class="tabbable">
                        <div class="col-md-12"
                             style="padding-left: 10px;padding-right: 10px;background-color: #f3f3f4;padding-bottom:15px;">
                            <div>
                                <div class="col-md-12 nav">
                                    <div class="col-md-2">
                                        <div><a href="${sysPath}merchant/buffetFood/order/new">新订单</a></div>
                                    </div>
                                    <div class="col-md-2">
                                        <a href="${sysPath}merchant/buffetFood/order/edit">订单调整</a></div>
                                    <div class="col-md-2">
                                        <div class="active"><a
                                                href="${sysPath}merchant/buffetFood/order/reminder">催单</a></div>
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
												<hr/>
												<span class="col-md-6 productDetailBody bodyContent">
                                                    <h5 class="col-md-12"><b>商品信息</b></h5>
                                                </span>
												<span class="shouqi">收起<i class="arrow fa fa-angle-up"></i></span><br/>
												<div class="tog tabDivList bodyContent">
													<c:forEach items="${each.productLists}" var="product">
														<span class="xiangqing col-md-12">
															<span class="szuo col-md-6">${product.productName}</span>
                                                        <span class="szhong col-md-1">¥${product.price}</span>
                                                        <span class="syou col-md-2">X${product.quantity}</span>
                                                        <span class="xiaojie col-md-1">¥${product.price * product.quantity}</span>
                                                        <br/>
                                                    </span>
													</c:forEach>
												</div>
												<span class="xiangqing bodyContent col-md-12">
                                                    <br/>
                                                    <span class="szuo bodyContent col-md-6">订单时间：${each.orderTime}&nbsp;&nbsp;&nbsp;&nbsp;已提交：<b>${each.minute}</b>钟</span>
                                                    <span class="col-md-1"></span>
                                                    <span class="szhong bodyContent col-md-2">共<b>${each.sum}</b>件</span>
                                                    <span class="syou bodyContent col-md-1 floatRight">合计：${each.orderAmount}<b>
                                                    </b>元</span><br/>
                                                    <br/>
                                                </span>
												<span class="col-md-12">
                                                    <span class="col-md-6 bianhao orderNumber bodyContent">订单编号：${each.orderNumber}</span>
                                                    <span class="xiangqing col-md-6">
                                                        <input type="button"
															   class="bt bt-primary col-md-2 floatRight settleBtn curPage"
															   value="回复" data-orderNumber="${each.orderNumber}"/>
                                                    </span>
                                                </span>
											</div>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<!-- 没有数据显示 -->
										<div class="showNoDataText" style="text-align: center;margin-top: 50px;">
											<div class="noOrder" style="padding-top: 20px;">
												<div class="noOrder-content">
													<div class="noOrder-content-div">
														<div class="wane">
															<i class="fa fa-file-text-o wane-icon" aria-hidden="true"
															   style="font-size: 100px;line-height: 170px;color: #fff"></i>
														</div>
														<p class="noOrder-p">暂无催单订单数据</p>
														<span class="noOrder-span">当前没有自助点餐的催单订单记录!</span>
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
                            <!--订单单位结束-->
                        </div>
                    </div>
                </div>
            </div><!-- /.main-content -->
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
<!-- 模态框（Modal） -->

</body>
<%@include file="js.jsp" %>
<script type="text/javascript">
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

        $(function () {
            $("#myModalc").click(function () {
                $("#myModal *:not('.modal-footer')").jqprint();
            });

        });

        $(function () {
            laydate.render({
                elem: '.timepicker'
            });
            laydate.render({
                elem: '.timepicker2'
            });
        });
    });

    $(".settleBtn.curPage").on("click", function () {
        var orderNumber = $(this).attr("data-orderNumber");
        const url = baseUrl + "/merchant/buffetFood/replyReminder";
        $.post(url,{orderNumber:orderNumber},function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.href = baseUrl + "/merchant/buffetFood/order/reminder";
                }else {
                    alert("回复失败");
                }
            }else {
                alert("网络连接失败");
            }
        })
    })
</script>
</html>
