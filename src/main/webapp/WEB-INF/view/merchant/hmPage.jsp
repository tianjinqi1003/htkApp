<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/9
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE HTML>
<!-- IE浏览器编码设置 -->
<%@include file="/WEB-INF/view/merchant/IE_lang.jsp"%>
<head>
    <title>回头客商家首页</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <!--　导入共用head内文件 -->
    <%@include file="/WEB-INF/view/merchant/head.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp"%>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <%--<div class="alert alert-warning" style="margin-bottom: 0;margin-top: 20px">--%>
                <%--<a href="#" class="close" data-dismiss="alert">--%>
                    <%--&times;--%>
                <%--</a>--%>
                <%--<strong>【中绿粗粮王豆奶赠饮活动】</strong>火热报名中!--%>
            <%--</div>--%>
            <div class="panel_content layui-fluid">
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-md7">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="layui-row">
                                    <div class="layui-col-md2">
                                        <h3 class="panel-title">待处理订单</h3>
                                    </div>
                                    <div class="layui-col-md2 layui-col-md-offset8">
                                        <h3 class="panel-title" style="float: right">
                                            <a class="a_" href="${sysPath}merchant/takeout/order/realTimeTakeoutOrder">处理订单<i class="layui-icon">&#xe602;</i></a>
                                        </h3>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="layui-row layui-col-space15">
                                    <div class="layui-col-md4">
                                        <div class="panel-body-div">
                                            <div class="panel-body-div">
                                                <div class="panel-body-div-fg">
                                                    <span style="font-size: 50px;font-weight: 500">${data.newOrderNumber}</span>
                                                </div>
                                                <div>
                                                    <span>新订单</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%--<div class="layui-col-md3">--%>
                                        <%--<div class="panel-body-div">--%>
                                            <%--<div class="panel-body-div-fg">--%>
                                                <%--<span style="font-size: 50px;font-weight: 500">0</span>--%>
                                            <%--</div>--%>
                                            <%--<div>--%>
                                                <%--<span>异常订单</span>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <div class="layui-col-md4">
                                        <div class="panel-body-div">
                                            <div class="panel-body-div-fg">
                                                <span style="font-size: 50px;font-weight: 500">0</span>
                                            </div>
                                            <div>
                                                <span>催单</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-md4">
                                        <div class="panel-body-div">
                                            <div>
                                                <span style="font-size: 50px;font-weight: 500">${data.returnOrderNumber}</span>
                                            </div>
                                            <div>
                                                <span>退单</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md5">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="layui-row">
                                    <div class="layui-col-md3">
                                        <h3 class="panel-title">待处理反馈</h3>
                                    </div>
                                    <div class="layui-col-md3 layui-col-md-offset6">
                                        <h3 class="panel-title" style="float: right">
                                            <a class="a_" href="${sysPath}merchant/takeout/comment/index">服务评价<i class="layui-icon">&#xe602;</i></a>
                                        </h3>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="layui-row layui-col-space15">
                                    <div class="layui-col-md6">
                                        <div class="panel-body-div">
                                            <div class="panel-body-div">
                                                <div class="panel-body-div-fg">
                                                    <span style="font-size: 50px;font-weight: 500;color: orange">${data.badCommentNumber}</span>
                                                </div>
                                                <div>
                                                    <span>未回复差评</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-md6">
                                        <div class="panel-body-div">
                                            <div>
                                                <span style="font-size: 50px;font-weight: 500;color: orange">${data.noCommentNumber}</span>
                                            </div>
                                            <div>
                                                <span>未回复评价</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-row layui-col-space10">
                    <div class="layui-col-md7">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="layui-row">
                                    <div class="layui-col-md2">
                                        <h3 class="panel-title">今日总览</h3>
                                    </div>
                                    <div class="layui-col-md2 layui-col-md-offset8">
                                        <h3 class="panel-title" style="float: right">
                                            <a class="a_" href="${sysPath}merchant/bill/billMoney">数据中心
                                                <i class="layui-icon">&#xe602;</i>
                                            </a>
                                        </h3>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="layui-row layui-col-space15">
                                    <div class="layui-col-md6">
                                        <div class="panel-body-div">
                                            <div class="panel-body-div">
                                                <div>
                                                    <span>订单</span>
                                                </div>
                                                <div class="panel-body-div-fg">
                                                    <span style="font-size: 30px;font-weight: 500">${data.todayOrderCount}</span>
                                                </div>
                                                <div>
                                                    <span>昨日${data.yesterdayOrderCount}单</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-md6">
                                        <div class="panel-body-div">
                                            <div>
                                                <span>营业额</span>
                                            </div>
                                            <div>
                                                <span style="font-size: 30px;font-weight: 500">${data.todayRevenue}</span>
                                            </div>
                                            <div>
                                                <span>昨日${data.yesterdayRevenue}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md5">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="layui-row">
                                    <div class="layui-col-md2">
                                        <h3 class="panel-title">活动</h3>
                                    </div>
                                    <div class="layui-col-md3 layui-col-md-offset7">
                                        <h3 class="panel-title" style="float: right">
                                            <a class="a_" href="${sysPath}merchant/integral/getNewActivePage">平台活动<i class="layui-icon">&#xe602;</i></a>
                                        </h3>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="layui-row layui-col-space15">
                                    <div class="layui-col-md6">
                                        <div class="panel-body-div">
                                            <div class="panel-body-div">
                                                <div>
                                                    <span>已创建</span>
                                                </div>
                                                <div class="panel-body-div-fg">
                                                    <span style="font-size: 30px;font-weight: 500;">${data.hasBeenCreatedActives}</span>
                                                </div>
                                                <div>
                                                    <span>进行中(${data.nowActives})</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-md6">
                                        <%--<div class="panel-body-div">--%>
                                            <%--<div>--%>
                                                <%--<span>可报名</span>--%>
                                            <%--</div>--%>
                                            <%--<div>--%>
                                                <%--<span style="font-size: 30px;font-weight: 500">0</span>--%>
                                            <%--</div>--%>
                                        <%--</div>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="layui-row">
                                <div class="layui-col-md2">
                                    <h3 class="panel-title">通知中心</h3>
                                </div>
                                <%--<div class="layui-col-md2 layui-col-md-offset8">--%>
                                    <%--<h3 class="panel-title" style="float: right">--%>
                                        <%--<a class="a_">全部通知<i class="layui-icon">&#xe602;</i></a>--%>
                                    <%--</h3>--%>
                                <%--</div>--%>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="layui-row layui-col-space15">
                                <div class="layui-col-md12">
                                    <div style="display: none">
                                        <p>
                                            <span style="width: 100%">
                                                <span>【中绿粗粮王豆奶赠饮活动】火热报名中!</span>
                                                <span style="float: right;color: #999">08-14</span>
                                            </span>
                                        </p>
                                        <hr class="layui-bg-gray">
                                        <p>
                                            <span style="width: 100%">
                                                <span>领5元红包,只需花一分钟</span>
                                                <span style="float: right;color: #999">08-13</span>
                                            </span>
                                        </p>
                                        <hr class="layui-bg-gray">
                                        <p>
                                            <span style="width: 100%">
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                            </span>
                                        </p>
                                        <hr class="layui-bg-gray">
                                        <p>
                                            <span style="width: 100%">
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                            </span>
                                        </p>
                                    </div>
                                    <div>
                                        <p>
                                            <span style="width: 100%">
                                                <span style="text-align: center">暂无通知消息!</span>
                                                <span style="float: right;color: #999"></span>
                                            </span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp"%>
</div>
</body>
<%@include file="js.jsp"%>
<script type="text/javascript">

    layui.use(['element','util','layer'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        //监听导航点击
        element.on('nav(demo)', function (elem) {
            console.log(elem);
        });
        //固定块
//        util.fixbar({
//            bar1: true
//            ,css: {right: 50, bottom: 100}
//            ,bgcolor: '#393D49'
//            ,click: function(type){
//                if(type === 'bar1'){
//                    layer_msg("点击了浮动球",'info');
//                }
//            }
//        });
    });
</script>
</html>
