<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/9
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>外卖商品订单页面</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="childCatO clearfix tab top-tab">
                <a href="${sysPath}merchant/takeout/order/realTimeTakeoutOrder">实时订单</a>
                <a href="${sysPath}merchant/takeout/order/historyTakeoutOrder" class="cur">历史订单</a>
            </div>
            <div class="orderPage">
                <div class="orderTop">
                    <%--<div class="filter clearfix">--%>
                        <%--<div class="filterName">订单排序</div>--%>
                        <%--<div class="filterCheck clearfix">--%>
                            <%--<label><input type="radio" name="filter3" value="1" checked/> 订单序号</label>--%>
                            <%--<label><input type="radio" name="filter3" id="OrderTheNextTime" value="2"/> 下单时间</label>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="filter clearfix">
                        <div class="filterName">选择日期</div>
                        <div class="filterCheck clearfix">
                            <input type="text" class="dateStart" value="${dateStart}"/>
                            <span class="spacer">至</span>
                            <input type="text" class="dateEnd" value="${dateEnd}"/>
                        </div>
                    </div>
                </div>
                <div class="orderList clearfix">
                    <div class="orderLeft">
                        <div class="orderLeftCont clearfix">
                            <c:choose>
                                <c:when test="${data != null}">
                                    <c:forEach items="${data}" var="each" varStatus="i">
                                        <c:set value="${fn:substring(each.receivingCall,0 , 3)}" var="beginPhoeNumber"/>
                                        <c:set value="${fn:substring(each.receivingCall,6, 10)}" var="endPhoneNumber"/>
                                        <div class="orderItem">
                                            <div class="orderHead">
                                                    <%-->${pageInfo.pageNum == 1 ? i.index+1 : ((pageInfo.pageNum - 1) * 10) + i.index +1}--%>
                                                #<i>${each.allNumber}</i><span>用户已确认收餐</span>
                                            </div>
                                            <div class="remark">
                                                <i>  备注：${each.remark}</i>
                                            </div>
                                            <div class="userInfo">
                                                <div class="uname">${fn:substring(each.receiptName,0,1)}（${each.sex == 0 ? "女士" : "先生"}）<a
                                                        href="javascript:void(0)" class="lookHistoryOrder">查看用户历史订单
                                                    &gt;</a></div>
                                                <p>${beginPhoeNumber}****${endPhoneNumber}</p>
                                            </div>
                                            <p class="status">配送：餐已送出</p>
                                            <div class="goodsInfo">
                                                <div class="uname">商品信息<a href="javascript:void(0);"
                                                                          class="close_">收起</a>
                                                </div>
                                                <div class="payed">
                                                    <table>
                                                        <c:if test="${each.productLists != null}">
                                                            <c:forEach items="${each.productLists}" var="pro">
                                                                <tr>
                                                                    <td>${pro.productName}</td>
                                                                    <td>￥${pro.price}</td>
                                                                    <td>x${pro.quantity}</td>
                                                                    <td>￥${pro.price * pro.quantity}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </c:if>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="plus">
                                                <div>小计<span>￥${each.orderAmount}</span></div>
                                                <p>商家活动支出 <i class="fa fa-question-circle"
                                                             title="商家活动支出"></i><span>-￥0.00</span></p>
                                                <p>平台服务费 <i class="fa fa-question-circle"
                                                            title="平台服务费"></i><span>-￥0.00</span></p>
                                            </div>
                                            <div class="count">
                                                <div>本单预计收入<span>￥${each.orderAmount}</span></div>
                                                <p>本单顾客实际支付：￥${each.orderAmount}<i>（已支付）</i></p>
                                            </div>
                                            <div class="buttons">
                                                <%--<a href="javascript:void(0)" class="refund">部分退款</a>--%>
                                                <a href="javascript:void(0)" class="printOrder">打印订单</a>
                                                <%--<a href="javascript:void(0)" class="copyOrder">复制订单</a>--%>
                                            </div>
                                            <p class="orderSn">${fn:substring(each.orderTime,5 , 19)} 下单 |
                                                订单编号：${each.orderNumber}</p>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div class="noOrder">
                                        <div class="noOrder-content">
                                            <div class="noOrder-content-div">
                                                <div class="wane">
                                                    <i class="fa fa-file-text-o wane-icon" aria-hidden="true"></i>
                                                </div>
                                                <p class="noOrder-p">暂无指定订单</p>
                                                <span class="noOrder-span">暂时没有该筛选条件的订单,稍后再来看看吧!</span>
                                            </div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="orderRight">
                        <div class="infoTit">今日订单概况</div>
                        <p>已接订单：${orderCount} 笔</p>
                        <p>今日营业总额：${turnoverToday} 元</p>
                        <div class="infoTit">需关注订单</div>
                        <div class="orderNum">
                            <span>待 发 配 送：<i>${orderCount}</i>笔</span>
                            <a href="">查看订单 &gt;</a>
                        </div>
                    </div>
                </div>
                <c:if test="${data != null}">
                    <div class="pages">
                        <c:if test="${pageInfo != null}">
                            <a href="javascript:void(0);">${pageInfo.total}条数据</a>
                            <c:if test="${pageInfo.hasPreviousPage}">
                                <a href="${sysPath}merchant/takeout/order/historyTakeoutOrder?pageNo=${pageInfo.pageNum - 1}&dateStart=${dateStart}&dateEnd=${dateEnd}">上一页</a>
                            </c:if>
                            <c:if test="${!pageInfo.hasPreviousPage}">
                                <a href="javascript:void(0)">上一页</a>
                            </c:if>
                            <c:forEach var="index" begin="1" end="${pageInfo.pages}" varStatus="i">
                                <c:choose>
                                    <c:when test="${pageInfo.pageNum == index}">
                                        <a class="cur"
                                           href="${sysPath}merchant/takeout/order/historyTakeoutOrder?pageNo=${pageInfo.pageNum}&dateStart=${dateStart}&dateEnd=${dateEnd}">${index}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${sysPath}merchant/takeout/order/historyTakeoutOrder?pageNo=${index}&dateStart=${dateStart}&dateEnd=${dateEnd}">${index}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${pageInfo.hasNextPage}">
                                <a href="${sysPath}merchant/takeout/order/historyTakeoutOrder?pageNo=${pageInfo.pageNum + 1}&dateStart=${dateStart}&dateEnd=${dateEnd}">下一页</a>
                            </c:if>
                            <c:if test="${!pageInfo.hasNextPage}">
                                <a href="javascript:void(0)">下一页</a>
                            </c:if>
                        </c:if>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
<%@include file="js.jsp" %>
<script type="text/javascript">
    layui.use(['element', 'util', 'layer', 'laydate'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        var date = layui.laydate;

        //前后若干天可选，这里以7天为例
        date.render({
            elem: '.dateStart'
//            , min: -30
            , max: 0
//            ,trigger: 'click'
        });

        //前后若干天可选，这里以7天为例
        date.render({
            elem: '.dateEnd'
//            , min: 0
            , max: 0
            , done: function (value, date) {
                var dateStart = $(".dateStart").val();
                if (dateStart === '' || dateStart === undefined) {
                    layer_msg("请选择起始日期", "error");
                    return false
                }
                window.location.href = baseUrl + "/merchant/takeout/order/historyTakeoutOrder?dateStart=" + dateStart + "&dateEnd=" + value;
//                alert('你选择的日期是：' + value + '\n获得的对象是' + JSON.stringify(date));
            }
        });
    });

    //查看历史订单按钮点击
    $(".lookHistoryOrder").on("click", function () {
        layer_msg('暂没有历史订单', 'lock');
    });

    //点击了部分退款按钮
    $(".refund").on("click", function () {
        layer_msg("点击了部分退款按钮", "lock");
    });

    //点击了打印订单按钮
    $(".printOrder").on("click", function () {
        layer_msg("点击了打印订单按钮", "lock");
    });

    //点击了复制订单按钮
    $(".copyOrder").on("click", function () {
        layer_msg("点击了复制订单按钮", "lock");
    })

    //下单时间排序
    $(".OrderTheNextTime").on("click", function () {
        layer_msg("现在默认是按时间排序", "info")
    })

    $(function () {
        if("${messg}" !== ""){
            alert("${messg}")
        };
    })
</script>
</html>
