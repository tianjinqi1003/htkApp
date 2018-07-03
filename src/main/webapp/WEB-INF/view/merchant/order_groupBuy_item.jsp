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
    <title>团购信息</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
    <style>
        .groupBuyTab {
            margin-top: 80px;
            width: 87%;
            /*background-color: #fff;*/
            padding: 10px;
        }

        .tabContent {
            background-color: #fff;
            padding: 15px;
            margin-top: 20px;
        }

        .conditionDate {
            height: 65px;
            background-color: #fff;
            padding: 15px;
        }

        .groupBuyConditionNameLabel {
            padding: 7px;
            line-height: 12px;
            font-size: 15px;
            font-weight: 400;
        }

        .groupBuyConditionNameInput {
            display: inline-block;
            margin-left: 20px;
        }

        .groupBuyConditionNameInput > input {
            padding: 7px;
            border-radius: 2px;
            border: 1px solid #e2e2e2;
            width: 220px;
        }

        .groupBuyConditionNameInput > button {
            height: 30px;
            line-height: 30px;
            position: relative;
            top: -2px;
            margin-left: 100px;
        }

        .groupBuyMesTabTitle > span {
            font-size: 15px;
            font-weight: 600;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="childCatO clearfix tab top-tab">
                <a href="${sysPath}merchant/groupBuy/getGroupBuyItemMesPage" class="cur">团购信息</a>
                <a href="${sysPath}merchant/groupBuy/getGroupBuyOrderQueryPage">订单查询</a>
            </div>
            <div class="groupBuyTab">
                <c:choose>
                    <c:when test="${data != null}">
                        <div class="tabContent shadow_">
                            <div class="groupBuyMesTabTitle">
                                <span>团购信息列表</span>
                            </div>
                            <table class="layui-table">
                                <colgroup>
                                    <col width="300">
                                    <col width="200">
                                    <col>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>标题</th>
                                    <th>价格</th>
                                    <th>消费时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${data}" var="each">
                                    <tr>
                                        <td>${each.packageName}</td>
                                        <td>￥${each.retailPrice}</td>
                                        <td>${each.usageTime}</td>
                                        <td>
                                            <a href="javascript:void(0)" class="showDetail" data-packId="1">查看</a>
                                            <input hidden="hidden" value='${each.jsonStr}' />
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div>
                                <div id="page" style="padding-bottom: 40px"></div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div>当前还没有创建团购商品</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="popup" style="display: none">
                <div>
                    <div>
                        <div class="popupFormItem">
                            <label class="popupFormItemLabel">团购标题:</label>
                            <div class="popupFormInput-block">
                                <p></p>
                            </div>
                        </div>
                        <div class="popupFormItem">
                            <label class="popupFormItemLabel">使用人数</label>
                            <div class="popupFormInput-block">

                            </div>
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
    var index;
    layui.use(['element', 'util', 'layer', 'laydate', 'laypage'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        var date = layui.laydate;
        var page = layui.laypage;

        //日期
        date.render({
            elem: '#date'
            , theme: '#0091EA'
            , type: 'date'
            , range: '~'
            , max: 0
            , min: -365
            , format: 'yyyy-MM-dd'
        });

        //不显示首页尾页
        page.render({
            elem: 'page'
            , count: 10
            , groups: 3
            , curr: 1 //获取起始页
            , prev: "<"
            , next: ">"
            , theme: '#1E9FFF'
            , first: false
            , last: false
        });
    });
    //查详情弹窗
    $(".showDetail").on("click", function () {
        var ele = this;
        var inputVal = $(ele).siblings("input").val();
        var jsonStr = JSON.parse(inputVal);
        //通过拿到的id  获取当前团购条目的详情信息
        //调用弹出层，显示详细信息
        //通过拿到的id  获取当前团购条目的详情信息
        var contentStr = "<div style='margin-top: 20px;font-size: 14px;font-weight: 600'>\n" +
            "<div style='width:90%;margin: auto'>" +
            "<div>" +
            "<label style='width:80px;text-align:right'>标题</label>" +
            "<div style='display:inline-block;padding-left: 20px;width: 60%;'>" +
            "<span style='font-weight: 500'>"+jsonStr.packageName+"</span>"+
            "</div>" +
            "</div>" +
            "<div>" +
            "<label style='width:80px;text-align:right'>人数</label>" +
            "<div style='display:inline-block;padding-left: 20px;width: 60%;'>" +
            "<span>"+jsonStr.peopleUsedNumber+"人</span>"+
            "</div>" +
            "</div>" +
            "<div>" +
            "<label style='width:80px;text-align:right'>图片</label>" +
            "<div style='display:inline-block;padding-left: 20px;width: 60%;'>" +
            "<img src='"+jsonStr.imgUrl+"' style='height: 200px'/> "+
            "</div>" +
            "</div>" +
            "<div>" +
            "<label style='width:80px;text-align:right'>说明</label>" +
            "<div style='display:inline-block;padding-left: 20px;width: 60%;'>" +
            "<p>" +
            ""+jsonStr.useDetails+""+
            "</p>"+
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>";
        var params = {
            title: ["团购信息", "background-color:#fff;color:#000"],
            content: contentStr,
            fixed: true,
            area: {
                width: '500px',
                height: '400px'
            }
        };
        index = layer_pageTier(params);
        //调用弹出层，显示详细信息
    })
</script>
</html>
