<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/16
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>团购订单页面</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
    <style>


        .queryOrder {
            width: 30%;
            border-radius: 3px;
            border: 1px solid #e2e2e2;
            padding-left: 10px;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="childCatO clearfix tab top-tab">
                <a href="${sysPath}merchant/groupBuy/getGroupBuyItemMesPage">团购信息</a>
                <a href="${sysPath}merchant/groupBuy/getGroupBuyOrderQueryPage" class="cur">订单查询</a>
            </div>
            <div class="orderPage">
                <div class="orderTop">
                    <div class="filter clearfix">
                        <div class="filterName">查询订单</div>
                        <div class="clearfix">
                            <input placeholder="输入消费ID或订单号查找订单" class="queryOrder">
                            <button class="layui-btn layui-btn-normal searchOrderBtn" type="button" style="position: relative;top: -2px;height: 37px;line-height: 37px;margin-left: 100px;"> 查询 </button>
                        </div>
                    </div>

                </div>
                <div class="orderList clearfix">
                    <c:choose>
                        <c:when test="${data != null}">
                            <div class="orderLeft">
                                <div class="orderLeftCont clearfix">
                                    <c:forEach items="${data}" var="each">
                                        <c:set value="${fn:substring(each.receivingCall,0 , 3)}"
                                               var="beginPhoeNumber"/>
                                        <c:set value="${fn:substring(each.receivingCall,7, 10)}"
                                               var="endPhoneNumber"/>
                                        <div class="orderItem xDDOrd">
                                            <div class="orderHead">#<i>${each.number}</i></div>
                                            <div class="userInfo">
                                                <div class="uname">${fn:substring(each.receiptName,0,1)}（${each.sex == 0 ? "女士" : "先生"}）</div>
                                                <p>${beginPhoeNumber}*****${endPhoneNumber}</p>
                                            </div>
                                            <div class="goodsInfo">
                                                <div class="uname">商品信息</div>
                                                <div class="payed">
                                                    <c:if test="${each.orderBuyPackageContentList != null}">
                                                        <table>
                                                            <c:forEach items="${each.orderBuyPackageContentList}" var="every">
                                                                <tr>
                                                                    <td>${every.productName}</td>
                                                                    <td>￥${every.price}</td>
                                                                    <td>x1</td>
                                                                    <td>￥${every.price}</td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="plus">
                                                <div>小计<span>￥${each.orderAmount}</span></div>
                                            </div>
                                            <div class="count">
                                                <div>本单预计收入<span>￥${each.orderAmount}</span></div>
                                                <p>本单顾客实际支付：￥${each.orderAmount}<i>（已支付）</i></p>
                                            </div>
                                            <div class="buttons">
                                                <c:if test="${each.orderState == 10}">
                                                    <a href="javascript:void(0)" onclick="enterConsumption(${each.orderNumber})">确认消费</a>
                                                </c:if>
                                                <c:if test="${each.orderState == 11}">
                                                    <a href="javascript:void(0)" style="background-color: #a7a4a4;border: 1px solid #a7a4a4;cursor: context-menu">已消费</a>
                                                </c:if>
                                                <c:if test="${each.orderState == 12}">
                                                    <a href="javascript:void(0)" style="background-color: #a7a4a4;border: 1px solid #a7a4a4;cursor: context-menu">已取消</a>
                                                </c:if>
                                            </div>
                                            <p class="orderSn">${fn:substring(each.orderTime,5 , 19)} 下单 | 订单编号：${each.orderNumber}</p>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
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
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:if test="${data != null}">
                    <div id="pages" style="margin-right: 345px">
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
    layui.use(['element', 'util', 'layer', 'laydate', 'laypage'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var page = layui.laypage;

        //不显示首页尾页
        page.render({
            elem: 'pages'
            , count: ${pageInfo == null ? 0: pageInfo.pages * 10}
            , groups: 3
            , curr: ${pageInfo == null ? 1 : pageInfo.pageNum} //获取起始页
            , prev: "<"
            , next: ">"
            , theme: '#1E9FFF'
            , first: false
            , last: false
            , jump: function (obj, first) {
                if (!first) {
                    window.location.href = baseUrl + "/merchant/groupBuy/getGroupBuyOrderQueryPage?pageNum=" + obj.curr;
                }
            }
        });
    });
    
    function enterConsumption(orderNumber) {
        const url = baseUrl + "/merchant/groupBuy/enterConsumption";
        var params = {
            orderNumber: orderNumber
        }
        $.post(url, params, function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.href = baseUrl + "/merchant/groupBuy/getGroupBuyOrderQueryPage";
                }else {
                    layer_msg("error", result.message);
                }
            }else {
                layer_msg("exception", "网络连接异常");
            }
        })
    }

    //查询按钮
    $(".searchOrderBtn").on("click", function () {
        var inputVal = $(".queryOrder").val();
        var val;
        if(inputVal.length <= 0){
            val = "";
//            layer_msg("请输入数据后查找", "error");
        }
        val = inputVal;
        //根据输入值查找数据
        window.location.href = baseUrl + "/merchant/groupBuy/getGroupBuyOrderQueryPage?keyWord="+val;
    })
</script>
</html>