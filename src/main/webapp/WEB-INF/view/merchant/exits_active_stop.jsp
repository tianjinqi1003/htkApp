<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/15
  Time: 16:43
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
    <style>
        .topTab {
            top: 50px;
        }

        .topOneItem {
            position: relative;
            top: 60px;
        }

        .businessSet > .busSetItem > .itemContent > div {
            display: inline-block;
        }

        .businessSet > .busSetItem > .itemContent {
            display: inline-block;
            width: 87%;
        }

        .businessSet > .busSetItem > .itemContent > div.rightContent {
            width: auto;
            float: right;
        }

        .businessSet > .busSetItem > .itemContent > div.rightContent > a > span {
            line-height: 50px;
            font-size: 15px;
            font-weight: 500;
        }

        .businessSet > .busSetItem label {
            width: 12%;
            color: #a7a4a4;
            font-size: 15px;
            font-weight: 500;
            float: left;
            line-height: 50px;
        }

        .exit-active-content {
            width: 65%;
            margin: auto;
        }

        .content-child {
            width: 80%;
            padding: 15px;
        }

        .active-status {
            background-color: #fff;
            margin-bottom: 15px;
        }

        .active-item {
            background-color: #fff;
            margin-bottom: 15px;
        }

        .active-com {
            padding: 15px;
        }

        .btnGroup > a.aItem {
            margin: 0 30px;
            color: #a7a4a4;
        }

        .btnGroup > a.cur {
            background-color: #1E9FFF;
            color: #fff;
            padding: 3px 8px;
        }

        .item-com {
            padding: 5px 25px;
        }

        .active-content {
            padding: 0;
        }

        .imgSpan {
            width: 40px;
            height: 40px;
            border:1px solid #c4c4d2;
            border-radius: 3px;
        }

        .title_name {
            font-size: 15px;
            font-weight: 600;
            position: relative;
            top: 12px;
            padding-left: 15px;
        }

        .name {
            display: block;
        }

        .title_time {
            display: block;
            color: #a7a4a4;
            font-size: 14px;
            font-weight: 500;
        }

        .btn-item > button{
            height: 35px;
            line-height: 35px;
        }

        .textItem {
            display: block;
            padding: 5px 0;
        }

        .textItem > :first-child {
            color: #a7a4a4;
        }

        .item-content {
            padding-bottom: 15px;
        }

        .singleNewActive {
            border: 1px solid #f3f3f4;
            border-radius: 2px;
            padding: 5px;
        }

        .active_icon {
            text-align: center;
            padding: 15px;
        }

        .i_size {
            font-size: 50px;
        }

        .single {
            padding: 10px;
        }

        .active-contentP {
            width: 65%;
            margin: auto;
        }

        .active_content {
            text-align: center;
            padding: 20px 0;
        }

        .createDiv {
            text-align: center;
            padding-bottom: 30px;
        }

        .content-title {
            font-size: 16px;
            font-weight: 600;
        }

        .content-des {
            padding: 10px 0;
            color: #a7a4a4;
        }

        .createBtn {
            width: 50%;
        }

        .text-tips {
            padding: 15px 0;
            color: #a7a4a4;
        }

        .numText {
            color: #1E9FFF;
            padding: 0 2px;
        }

        .icon_span {
            width: 65px;
            height: 65px;
        }

        .active-Div {
            padding-bottom: 50px;
        }

        .labelText {
            color: #a7a4a4;
            font-weight: 500;
            width: 80px;
            text-align: right;
        }

        .rightContent {
            display: inline-block;
            padding-left: 20px;
        }

        .form-item {
            padding: 10px 0;
        }

        .inputEle {
            border-radius: 2px;
            border: 1px solid #eee;
            height: 30px;
            padding-left: 5px;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="childCat topTab clearfix tab shadow_">
                <span class="layui-breadcrumb" lay-separator="-">
                    <a href="${sysPath}merchant/integral/getNewActivePage">新建</a>
                    <a href="${sysPath}merchant/integral/getExitsActiveProcessPage" class="cur">已创建</a>
                </span>
            </div>
            <div class="exit-active-content s_info topOneItem">
                <div class="content-child">
                    <div class="active-status active-com shadow_">
                        <label style="font-size: 14px;font-weight: 500">活动状态：</label>
                        <div class="btnGroup" style="display: inline-block">
                            <a class="aItem" href="${sysPath}merchant/integral/getExitsActiveNoStartPage">待开始</a>
                            <a class="aItem" href="${sysPath}merchant/integral/getExitsActiveProcessPage">进行中</a>
                            <a class="aItem cur" href="${sysPath}merchant/integral/getExitsActiveStopPage">已结束</a>
                            <a class="aItem" href="${sysPath}merchant/integral/getExitsActiveMessagePage">资讯</a>
                        </div>
                    </div>
                    <div class="layui-fluid active-content">
                        <div class="active-item shadow_">
                            <c:choose>
                                <c:when test="${data != null}">
                                    <c:forEach items="${data}" var="each">
                                        <div class="exits-item">
                                            <div class="layui-row item-com">
                                                <div class="layui-col-md6">
                                    <span class="spanTitle">
                                        <span class="imgSpan">
                                            <img src="${staticFilePath}resource/images/active_icon2.png"/>
                                        </span>
                                        <span class="title_name">
                                            <span class="name">${each.tName}</span>
                                            <span class="title_time">活动ID：${each.id}
                                                </span>
                                        </span>
                                    </span>
                                                </div>
                                                <div class="layui-col-md6 btnGroup" style="text-align: right">
                                                    <div style="padding-top: 12px;">
                                                        <div class="btn-item" style="display: inline-block; margin-right: 5px;">
                                                            <button class="layui-btn-normal showDetail layui-btn">查看详情</button>
                                                            <input hidden="hidden" value='${each.jsonStr}' />
                                                        </div>
                                                        <div class="btn-item" style="display: inline-block;margin-left: 5px;">
                                                            <button class="layui-btn layui-btn-disabled" onclick="">已结束</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr/>
                                            <div class="item-content item-com">
                                    <span class="textItem">
                                        <span>类型：</span>
                                        <span>兑换</span>
                                    </span>
                                                <span class="textItem">
                                        <span>规则：</span>
                                        <span>满 ${each.tUseMoney} 可用 ${each.tMoney}元 优惠券</span>
                                    </span>
                                                <span class="textItem">
                                        <span>日期：</span>
                                        <span>${each.tStartTime} 至 ${each.tExpiration}</span>
                                    </span>
                                                <span class="textItem">
                                        <span>时间：</span>
                                        <span>全天</span>
                                    </span>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div style="text-align: center">没有数据</div>
                                </c:otherwise>
                            </c:choose>
                        </div>
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
    $(function () {
        $('input, textarea').placeholder();
    });
    layui.use(['element', 'util', 'layer'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
    });

    //查看详情
    $(".showDetail").on("click", function () {
        var ele = this;
        var inputVal = $(ele).siblings("input").val();
        var jsonStr = JSON.parse(inputVal);
        const contentStr = "" +
            "<div style='height: 200px;padding: 15px;'>" +
            "<div>" +
            "<div class='form-item'><label class='labelText'>活动名称</label>" +
            "<div class='rightContent'>" +
            "<span>"+jsonStr.tName+"</span>"+
            "</div>" +
            "</div>" +
            "<div class='form-item'><label class='labelText'>活动日期</label>" +
            "<div class='rightContent'>" +
            "<span>"+jsonStr.tStartTime+"</span>" +
            "<span style='padding: 0 8px;color: black;font-weight: 600'>至</span> " +
            "<span>"+jsonStr.tExpiration+"</span>" +
            "</div>" +
            "</div>" +
            "<div class='form-item'><label class='labelText'>活动内容</label>" +
            "<div class='rightContent'>" +
            "<span>"+jsonStr.integralValue+"积分</span>" +
            "<span style='padding: 0 8px; color: black;font-weight: 600'>换</span> " +
            "<span>"+jsonStr.tMoney+"元</span>" +
            "</div>" +
            "</div>" +
            "<div class='form-item'>" +
            "<label class='labelText'>使用金额限制</label>" +
            "<div class='rightContent'>" +
            "<span>满足"+jsonStr.tUseMoney+"元</span>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>";
        var params = {
            title: ["活动详情", "background-color:#fff;color:#000"],
            content: contentStr,
            fixed: true,
            area: {width: '500px', height: '265px'}
        };
        layer_pageTier(params)
    })
</script>
</html>
