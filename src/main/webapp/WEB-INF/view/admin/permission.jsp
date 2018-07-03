<%--
  Created by IntelliJ IDEA.
  User: terabithia
  Date: 1/3/18
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>权限管理</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
    <style>
        .layui-body {
            background-color: #f3f3f4;
        }

        .body-content {
            padding: 15px;
            background-color: #fff;
            margin: 15px;
        }

        .body-content {
            height: auto;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body">
        <div class="body-content">
            <div class="content">
                <div>
                    <c:choose>
                        <c:when test="${data != null}">
                            <div class="table-content">
                                <table class="layui-table">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>商户帐号</th>
                                        <th>商铺列表</th>
                                        <th>权限</th>
                                        <th>过期时间</th>
                                        <th style="text-align: center">权限修改</th>
                                        <th style="text-align: center">时间修改</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${data}" var="each" varStatus="i">
                                        <tr>
                                            <td>#${i.count}</td>
                                            <td>${each.userName}</td>
                                            <td>${each.shopListStr}</td>
                                            <td>${each.roleStr == null ? "空权限" : each.roleStr}</td>
                                            <td>${each.useEndTime}</td>
                                            <th style="text-align: center"><a href="javascript:void(0)" data-id="${each.id}" class="p_up">修改</a></th>
                                            <th style="text-align: center"><a href="javascript:void(0)" data-id="${each.id}" class="t_up">修改</a></th>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div>
                                <div id="page" style="text-align: right"></div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div>
                                <div style="text-align: center">当前没有商户列表或查询失败,请稍后重试</div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
<%@include file="js.jsp" %>
<script>
    layui.use(['element', 'util', 'layer', 'laypage', 'form', 'laydate'], function () {
        var page = layui.laypage;
        var form = layui.form;
        var date = layui.laydate;

        form.on('submit(dateEnter)', function (data) {
            const url = baseUrl + "/admin/permission/updateUseTimeByMerchantId";
            $.post(url, data.field, function (result) {
                if(result && result.code == 0){
                    window.location.href = baseUrl + "/admin/permissionPage";
                }else {
                    layer_msg(result.message, 'error');
                    setTimeout(function () {
                        window.location.href = baseUrl + "/admin/permissionPage";
                    }, 2000)
                }
            })
        });

        form.on('submit(perEnter)', function (data) {
            const url = baseUrl + "/admin/permission/updatePermissionByMerchantId";
            $.post(url, data.field, function (result) {
                if(result && result.code == 0){
                    window.location.href = baseUrl + "/admin/permissionPage";
                }else {
                    layer_msg(result.message, 'error');
                    setTimeout(function () {
                        window.location.href = baseUrl + "/admin/permissionPage";
                    }, 2000)
                }
            })
        });

        page.render({
            elem: 'page'
            , count: ${page == null ? 0: page.pages * 10}
            , curr: ${page == null ? 1 : page.pageNum}
            , groups: 3
            , prev: "<"
            , next: ">"
            , theme: '#1E9FFF'
            , first: false
            , last: false
            , jump: function (obj, first) {
                if (!first) {
                    //不是第一页
                    var url = baseUrl + "/admin/permissionPage?page="+obj.curr+"&pageNum="+obj.limit;
                    window.location.href = url;
//                    var param = {
//                        "page":obj.curr,
//                        "pageNum":obj.limit
//                    };
//                    $.post(url, param, function (result) {
//                        if(result && result.code == 0){
//                        }else {
//                            layer_msg(result.message, 'error');
//                        }
//                    });
                }
            }
        });

        //权限弹窗
        $(".p_up").on("click", function () {
            //调接口获取权限列表渲染
            const url = baseUrl + "/admin/permission/getPermissionsList";
            const id = $(this).attr("data-id");
            $.post(url,{},function (result) {
                if(result && result.code == 0) {
                    var a = "";
                    $(result.data).each(function (index, ele) {
                        a += "<option value=\""+ele.id+"\">"+ele.roleDes+"</option>\n";
                    });
                    var contentStr = "<div>" +
                        "<form class=\"layui-form\" style='padding: 20px 30px 10px 0'>\n" +
                        "  <div class=\"layui-form-item\">\n" +
                        "    <label class=\"layui-form-label\">权限选择</label>\n" +
                        "    <div class=\"layui-input-block\">\n" +
                        "      <select name=\"roleId\" lay-filter=\"aihao\">\n" +
                            a+
                        "      </select>\n" +
                        "<input hidden='hidden' name=\"id\" value='"+id+"' />"+
                        "    </div>\n" +
                        "  </div>\n" +
                        "  <div class=\"layui-form-item\">\n" +
                        "    <div class=\"layui-input-block\">\n" +
                        "      <button class=\"layui-btn\" type='button' lay-submit lay-filter=\"perEnter\">确定</button>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "</form>" +
                        "</div>";
                    var params = {
                        title: ["权限修改", "background-color:#1E9FFF;color:#fff"],
                        content: contentStr,
                        fixed: true,
                        area: {width: '380px', height: '200px'}
                    };
                    layer_pageTier(params);
                    form.render();
                }else {
                    layer_msg(result.message, '获取权限列表失败');
                    return;
                }
            });
            return;
        });

        //时间修改弹窗
        $(".t_up").on("click", function () {
            const id = $(this).attr("data-id");
            var contentStr = "<div>" +
                "<form class=\"layui-form\" style='padding: 20px 30px 10px 0'>\n" +
                "  <div class=\"layui-form-item\">\n" +
                "    <label class=\"layui-form-label\">时间选择</label>\n" +
                "    <div class=\"layui-input-block\">\n" +
                "      <input type=\"text\" id='date' name=\"time\" required  lay-verify=\"required\" readonly placeholder=\"请输入选择日期\" autocomplete=\"off\" class=\"layui-input\">\n" +
                    "<input hidden='hidden' name=\"id\" value='"+id+"' />"+
                "    </div>\n" +
                "  </div>\n" +
                "  <div class=\"layui-form-item\">\n" +
                "    <div class=\"layui-input-block\">\n" +
                "      <button class=\"layui-btn\" type='button' lay-submit lay-filter=\"dateEnter\">确定</button>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</form>" +
                "</div>";
            var params = {
                title: ["时间修改", "background-color:#1E9FFF;color:#fff"],
                content: contentStr,
                fixed: true,
                area: {width: '380px', height: '200px'}
            };
            layer_pageTier(params);
            form.render();
            date.render({
                elem: "#date",
                theme: '#20A0FF',
                type: 'datetime',
                min: 0,
                trigger: 'click',
                ready: function () {
                },
                done: function (value) {

                }
            });
        });
    })
</script>
</body>
</html>
