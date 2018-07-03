<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/29
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>通知中心－消息</title>
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
            <div class="mesCenter-content shadow_">
                <div class="content-list">
                    <div class="list-title clearfix">
                        <div class="item sel"><span>全部消息(${page == null ? 0 : page.total})</span></div>
                        <c:if test="${data != null}">
                            <div class="inline_block checkBoxInput">
                                <input type="checkbox" class="checkBox" ${status == 0 ? "checked='checked'" : ''}
                                       name="" data-id="${status}" title="查看未读消息？">
                                <span>只看未读消息</span>
                            </div>
                        </c:if>
                    </div>
                    <div class="list-content">
                        <c:choose>
                            <c:when test="${data == null}">
                                <div style="text-align: center;padding: 50px 0">
                                    <span style="font-size: 20px">暂时没有通知消息</span>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${data}" var="each" varStatus="i">
                                    <div class="mesItem clearfix">
                                        <div class="mesItemText">
                                            <span class="mesLogo"><span class="layui-badge">消息</span></span>
                                        </div>
                                        <div class="mesTitle" data-id="${each.id}">
                                            <h3>${each.title}</h3>
                                        </div>
                                        <c:if test="${each.msgStatus == 0}">
                                            <div class="mesStatus">
                                                <span class="layui-badge layui-bg-cyan">未读消息</span>
                                            </div>
                                        </c:if>
                                        <div class="mesDate">
                                            <span>${fn:substring(each.gmtCreate, 0, 10)}</span>
                                        </div>
                                        <div class="mesContent" style="display: block" data-id="${each.id}">
                                            <div class="mesContent-border ${!i.last ? "border-bottom_" : ""}">
                                                <span>${each.msgContent}</span>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="list-page">
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
        var page = layui.laypage;
        //不显示首页尾页
        page.render({
            elem: 'page'
            , count: ${page == null ? 0: page.pages * 10}
            , curr: ${page == null ? 1 : page.pageNum} //获取起始页
//          , hash: 'fenye' //自定义hash值
            , groups: 3
            , prev: "<"
            , next: ">"
            , theme: '#1E9FFF'
            , first: false
            , last: false
            , jump: function (obj, first) {
                if (!first) {
                    var statusVal = $(".checkBoxInput > .checkBox").attr("data-id");
                    //首页不执行
                    window.location.href = baseUrl + "/merchant/getNotificationCenterView?pageNo=" + encryptMethod(obj.curr) + "&status=" + encryptMethod(statusVal);
                }
            }
        });
    });

    var index;
    //通知消息绑定点击事件
    $(".list-content > .mesItem > .mesTitle , .mesContent").on("click", function () {
        var mesId = $(this).attr("data-id");  //消息id值
        var sel = $(this).parents(".mesItem"); //this的父级元素中筛选出 .mesItem对象
        //删除未读消息标志(和后台交互)
        var statusEle = sel.find(".mesStatus");
        var title = sel.find(".mesTitle > h3").text(); //获取消息标题
        var content = sel.find(".mesContent > div > span").text();  //获取消息内容
        var butName = statusEle.length > 0 ? "知道了" : "关闭";
        var params = {
            title: ["通知中心－外卖消息", "background-color:#1E9FFF;color:#fff"],
            content: '<div style="padding: 20px">' +
            '<div style="text-align: center"><h3 style="font-size: 18px;font-weight: bold">' + title +
            ' </h3></div>' +
            '<div style="width: 95%;margin: auto;position: relative;top: 15px;font-size: 15px;"><span>商户你好：</span></div>' +
            '<div style="padding-top: 30px;height: 150px;width: 70%;margin: auto"><p style="color:#a7a4a4">' + content + '</p></div>' +
            '<div style="border-top: 1px solid #d9d9d9;">' +
            '<div style="position: relative;top:5px;float: right">' +
            '<button class="layui-btn layui-btn-normal closePageTier">'+butName+'</button>' +
            '</div>' +
            '</div>' +
            '</div>',
            offset: '150px',
            fixed: true,
            area: {
                width: '500px',
                height: '300px'
            }
        };
        //长度大于0表示未读消息状态
        if (statusEle.length > 0) {
            const url = baseUrl + "/merchant/changeMsgStatus";
            var params_ = {
                id: mesId
            };
            $.post(url, params_, function (result, status) {
                if (status === 'success') {
                    if (result && result.code === 0) {
                        statusEle.remove(); //和后台通讯，改变消息状态为已读成功，页面中删除未读消息标识状态
                        //显示弹窗
                        index = layer_pageTier(params);
                        return false;
                    } else {
                        layer_msg('error', result.message);
                        return false;
                    }
                } else {
                    layer_msg('exception', '网络连接失败');
                    return false;
                }
            });
        } else {
            if(statusEle.length > 0){
                return false;
            }
            //显示弹窗
            index = layer_pageTier(params);
        }
    });

    //关闭消息弹窗
    $(document).on("click", ".closePageTier", function () {
        layer.close(index)
    });

    //绑定查看未读消息按钮点击（）
    $(".checkBoxInput").on("click", function () {
        var checkBoxStatus = $(this).children(".checkBox").get(0).checked;
        var url = baseUrl + "/merchant/getNotificationCenterView?status=";
        var val = encryptMethod(checkBoxStatus ? 0 : 99);
        if (checkBoxStatus) {
            window.location.href = url + val;
        } else {
            window.location.href = url + val;
        }
    })
</script>
</html>
