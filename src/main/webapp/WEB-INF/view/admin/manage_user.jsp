<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/23
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>用户管理</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
    <style>
        .layui-body {
            background-color: #f3f3f4;
        }

        .btnGroup {
            width: 100px;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body">
        <div class="content">
            <div class="content-top">
                <div class="top-condition-phone top-condition">
                    <label class="userPhoneLabel">手机号：</label>
                    <div class="block-input">
                        <input type="text" placeholder="输入手机号查找用户" name="phone" value="">
                    </div>
                </div>
                <div class="top-condition-state top-condition">
                    <label>是否在线：</label>
                    <div class="block-input">
                        <div class="radio-item">
                            <span>是</span>
                            <input type="radio" name="lineState" checked value="1">
                        </div>
                        <div class="radio-item">
                            <span>否</span>
                            <input type="radio" name="lineState" value="0">
                        </div>
                    </div>
                </div>
            </div>
            <div class="content-down">
                <div class="layui-fluid btnGroup">
                    <div class="layui-row">
                        <div class="layui-col-md6">
                            <button class="layui-btn layui-btn-primary layui-btn-small">商户</button>
                        </div>
                        <div class="layui-col-md6">
                            <button class="layui-btn layui-btn-normal layui-btn-small">用户</button>
                        </div>
                    </div>
                </div>
                <div class="tableData">
                    <c:choose>
                        <c:when test="${data != null}">
                            <table class="layui-table">
                                <colgroup>
                                    <col width="150">
                                    <col width="200">
                                    <col>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>用户名</th>
                                    <th>注册时间</th>
                                    <th>角色</th>
                                    <th>权限</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${data}" var="each">
                                    <tr>
                                        <td>${each.userName}</td>
                                        <td>2016-11-29</td>
                                        <td>app用户</td>
                                        <td>普通用户</td>
                                        <td>在线</td>
                                        <td><a>禁用</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <div>
                                <span>没有查找到用户数据</span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="content-end layui-clear">
                <div id="page" style="float: right"></div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
<%@include file="js.jsp" %>
<script src="${staticFilePath}resources/plugins/echarts-2.2.7/build/dist/echarts.js"></script>
<script type="text/javascript">
    //JavaScript代码区域
    layui.use(['element', 'laypage', 'layer'], function () {
        var element = layui.element;
        var page = layui.laypage;
        var layer = layui.layer;


        //分页
        page.render({
            elem: "page",
            first: false,
            last: false,
            count: 30,
            curr: 2,
            theme: '#1E9FFF'
        })
    });
</script>
</body>
</html>
