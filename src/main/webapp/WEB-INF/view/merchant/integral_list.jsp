<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/21
  Time: 8:32
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
        .phoneSearch > form > .condition > label {
            float: left;
            font-size: 15px;
            font-weight: 600;
            width: 80px;
            line-height: 30px;
        }

        .phoneSearch > form > .condition > div.block-input > div.inputDiv > input {
            width: 100%;
            height: 30px;
            border-radius: 2px;
            border: 1px solid #e2e2e2;
            padding-left: 10px;
            font-size: 15px;
        }

        .phoneSearch > form > .condition > div.block-input > div.buttonDiv > button {
            width: 50%;
            height: 30px;
            border-radius: 2px;
            border: 1px solid #1E9FFF;
            background-color: #1E9FFF;
            color: #fff;
            margin-left: 20px;
        }

        .integralListDiv {
            margin: 15px 0;
        }

        .integralChild {
            /*background-color: #fff;*/
        }

        .childTop {
            background-color: #fff;
            padding: 15px;
        }

        .tableContent {
            background-color: #fff;
            padding: 15px;
            margin-top: 25px;
        }

        .tableContent {
            padding-bottom: 60px;
        }

        .block-input > div {
            display: inline-block;
        }

        .block-input > div.inputDiv {
            width: 15%;
        }

        .block-input > div.buttonDiv {
            width: 10%;
        }

        a {
            color: #337ab7;
        }
    </style>

    <style>
        .contentModel {
            /*padding: 10px;*/
        }

        .childModel {
            padding: 18px;
        }

        .childModel > div.formShowItem > label {
            float: left;
            font-size: 14px;
            font-weight: 600;
            line-height: 30px;
            width: 50px;
            text-align: right;
        }

        .childModel > div.formShowItem > div.input-block > input {
            margin-left: 10px;
            border-radius: 2px;
            height: 30px;
            border: 1px solid #e2e2e2;
            padding-left: 5px;
            width: 70%;
        }

        .childModel > div.formShowItem > div.input-bloc {
            padding-left: 45px;
        }

        .enterBtn {
            margin-left: 50px;
        }

        .formShowItem {
            padding: 5px 0;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="integralListDiv">
                <div class="integralChild">
                    <div class="childTop">
                        <div class="conditionDiv">
                            <div class="phoneSearch">
                                <form action="" method="post">
                                    <div class="condition">
                                        <label>手机号:</label>
                                        <div class="block-input">
                                            <div class="inputDiv">
                                                <input placeholder="请输入手机号" name="userPhone" class="inputPhone"
                                                       value="">
                                            </div>
                                            <div class="buttonDiv">
                                                <button type="submit">查询</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="tableContent">
                        <c:choose>
                            <c:when test="${data != null}">
                                <table class="layui-table integralList">
                                    <colgroup>
                                        <col width="150">
                                        <col width="200">
                                        <col>
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>用户手机号</th>
                                        <th>加入时间</th>
                                        <th>最近消费</th>
                                        <th>最近获得</th>
                                        <th>积分值</th>
                                        <th>赠送</th>
                                        <th>抵扣</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${data}" var="each" varStatus="i">
                                        <tr>
                                            <td>#${i.count}</td>
                                            <td>${each.userPhone}</td>
                                            <td>${each.joinTime}</td>
                                            <td>${each.lastConsumeTime}</td>
                                            <td>${each.lastGetTime}</td>
                                            <td>${each.val}积分</td>
                                            <td><a data-type="z" data-user_phone="${each.userPhone}">赠送积分</a></td>
                                            <td><a data-type="d" data-user_phone="${each.userPhone}">抵扣积分</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <div class="showNoDataText" style="text-align: center;">
                                    <div class="noOrder" style="padding-top: 20px;">
                                        <div class="noOrder-content">
                                            <div class="noOrder-content-div">
                                                <div class="wane">
                                                    <i class="fa fa-file-text-o wane-icon" aria-hidden="true"
                                                       style="font-size: 100px;line-height: 170px;color: #fff"></i>
                                                </div>
                                                <p class="noOrder-p">暂无积分用户${userPhone}</p>
                                                <span class="noOrder-span">暂时没有用户${userPhone}在您的店铺下拥有积分!</span>
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
    <%@include file="footer.jsp" %>
</div>
</body>
<%@include file="js.jsp" %>
<script type="text/javascript">
    layui.use(['element', 'util', 'layer', 'laydate', 'laypage'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        var date = layui.laydate;
        var page = layui.laypage;

        //常规用法
        date.render({
            elem: '#startDate'
        });

        date.render({
            elem: '#endDate'
        })

        //不显示首页尾页
        page.render({
            elem: 'page'
            , count: 10
            , theme: '#1E9FFF'
            , first: false
            , last: false
        });

    });

    var index;
    //赠送抵扣积分弹窗
    $("table.integralList > tbody > tr > td > a").on("click", function () {
        var type = $(this).data('type');
        var userPhone = $(this).data('user_phone');
        const mes = type === 'z' ? "赠送积分" : "抵扣积分";
        var typeVal = type === 'z' ? 1 : 0;
        var params = {
            title: [mes + "{ 积分值为整数 }", "background-color:#1E9FFF;color:#fff"],
            content: '<div class="contentModel showPosition">\n' +
            '    <div class="childModel">\n' +
            '        <div class="formShowItem">\n' +
            '            <label class="labelText">积分值:</label>\n' +
            '            <div class="input-block">\n' +
            '                <input placeholder="输入积分值" class="integralValue" id="integralValue" name="integralValue">\n' +
            '            </div>\n' +
            '        </div>\n' +
            '        <div class="formShowItem">\n' +
            '            <label class="labelText">说明:</label>\n' +
            '            <div class="input-block">\n' +
            '                <input placeholder="输入积分操作说明" class="declare" id="declare" name="declare">\n' +
            '            </div>\n' +
            '        </div>\n' +
            '        <div class="formShowItem">\n' +
            '            <button type="button" data-user_phone="' + userPhone + '" data-type="' + typeVal + '" class="enterBtn layui-btn layui-btn-normal layui-btn-small">提交</button>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>',
            offset: '150px',
            fixed: true,
            area: {
                width: '280px',
                height: '200px'
            }
        }
        index = layer_pageTier(params);
    })

    //确认操作按钮
    $(document).on("click", ".enterBtn", function () {
        var type = $(this).data('type');
        var userPhone = $(this).data('user_phone');
        var value = $("#integralValue").val();
        const url = baseUrl + "/merchant/integral/presentIntegralInterface";
        var params = {
            userPhone: userPhone,
            value: value,
            operationId: type
        };
        $.post(url, params, function (result, status) {
            if (status === 'success') {
                closePageTier(index);  //关闭弹窗
                if (result && result.code === 0) {
                    layer_msg(result.message, 'success');
                    window.location.href = baseUrl + "/merchant/integral/list";
                } else {
                    layer_msg(result.message, 'error');  //显示提示信息
                }
            } else {
                layer_msg("网络连接失败", 'exception');
            }
        });
    })
</script>
</html>
