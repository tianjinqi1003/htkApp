<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/30
  Time: 16:17
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
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="breadcrumb_">
                <span class="layui-breadcrumb" lay-separator="/">
                    <a href="${sysPath}merchant/bill/billRecord" style="color: #007DDB!important;">财务管理</a>
                    <a href="${sysPath}merchant/bill/billRecord/detail">账单详情</a>
                </span>
            </div>
            <div class="Record-content">
                <div class="content-width">
                    <div class="text_title">
                        <h3>账单详情</h3>
                        <span>${fn:substring(queryDate ,0 ,10)} 日账单</span>
                    </div>
                    <div class="moneyCount">
                        <div class="money_item inline_block money_item1">
                            <div class="item-div">
                                <span>已入账金额 ( 元 )</span>
                                <h3>${todayIncome}</h3>
                                <%--<span style="display: block">--%>
                                <%--<span class="layui-badge">${recordStatus == 0 ? "未入账" : "已入账"}</span>--%>
                                <%--</span>--%>
                            </div>
                        </div>
                        <div class="money_item inline_block money_item2">
                            <div class="item-div">
                                <span>本期订单收入 &nbsp;<i class="fa fa-question-circle" title="这是什么？"></i></span>
                                <h3 class="money-text">+${orderIncome}</h3>
                            </div>
                        </div>
                        <div class="money_item inline_block money_item3">
                            <div class="item-div">
                                <span>本期订单支出 &nbsp;<i class="fa fa-question-circle" title="这是什么？"></i></span>
                                <h3 class="money-text">-${spendingOnOrder}</h3>
                            </div>
                        </div>
                    </div>
                    <div>
                        <div class="tzBtn">
                            <div class="blockDiv">
                                <div class="inline_block">
                                    <button class="layui-btn layui-btn-normal">正常单</button>
                                </div>
                                <div class="inline_block" style="float: right">
                                    <button class="layui-btn layui-btn-primary layui-btn-disabled">导出账单</button>
                                </div>
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${data != null}">
                                <div class="dataTable">
                                    <table class="layui-table">
                                        <colgroup>
                                            <col width="150">
                                            <col width="200">
                                            <col>
                                        </colgroup>
                                        <thead>
                                        <tr>
                                            <th>序号</th>
                                            <th>账单状态</th>
                                            <th>订单号</th>
                                            <th>订单创建时间</th>
                                            <th>订单完成时间</th>
                                            <th>订单收入</th>
                                            <th>订单支出</th>
                                            <th>结算金额</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${data}" var="each">
                                            <tr>
                                                <td>#${each.serialNumber}</td>
                                                <td>${each.status == 1 ? "未入账" : "已入账"}</td>
                                                <td>${each.orderNumber}</td>
                                                <td>${each.gmtCreate}</td>
                                                <td>${each.gmtModified}</td>
                                                <td>+${each.orderIncome}</td>
                                                <td>-${each.spendingOnOrder}</td>
                                                <td>+${each.amount}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
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
    });
</script>
</html>
