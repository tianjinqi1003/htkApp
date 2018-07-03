<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/9
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>商家后台首页</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="0">
    <link rel="stylesheet" href="${staticFilePath}resource/custom/plugins/layui/css/layui.css?${date}" media="all">
    <link rel="stylesheet" href="${staticFilePath}resource/custom/css/index.css?${date}">
    <link id="layuicss-skinlayercss" rel="stylesheet" href="${staticFilePath}resource/custom/plugins/layui/css/modules/layer/default/layer.css?${date}" media="all">
    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body class="beg-login-bg">
<div class="beg-login-box">
    <header>
        <h1>回头客商家后台登录</h1>
    </header>
    <div class="beg-login-main">
        <form class="layui-form" method="post">
            <div class="layui-form-item">
                <label class="beg-login-icon">
                    <i class="layui-icon">&#xe612;</i>
                </label>
                <input type="text" name="userName" placeholder="请输入登录名" class="layui-input"
                       lay-verify="required|userName" autocomplete="off" value="">
            </div>
            <div class="layui-form-item">
                <label class="beg-login-icon">
                    <i class="layui-icon">&#xe642;</i>
                </label>
                <input type="password" name="password" placeholder="请输入密码" class="layui-input"
                       lay-verify="required|password_" autocomplete="off" value="">
            </div>
            <div class="layui-form-item">
                <div class="layui-row layui-col-space8">
                    <div class="layui-col-md4">
                        <input type="text" name="loginVCode" lay-verify="verifyCode" autocomplete="off"
                               placeholder="验证码" lay-verify="required"
                               class="layui-input" style="padding-left: 9px">
                    </div>
                    <div class="layui-col-md5">
                        <img src="${sysPath}login/captcha?identity=merchant" id="loginVCode" style="width: 115px;height: 39px"/>
                    </div>
                    <div class="layui-col-md3">
                        <span style="font-size: 14px;line-height: 39px;float: right"><a href="javascript:void(0)"
                         class="replace_code" style="color: #FFF;cursor: pointer">换一张?</a></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="beg-pull-left beg-login-remember">
                    <label>记住账号？</label>
                    <input type="checkbox" name="rememberMe" value="true" lay-skin="switch" checked=""
                           lay-filter="switchTest" title="记住账号">
                    <div class="layui-unselect layui-form-switch layui-form-onswitch" lay-skin="_switch"></div>
                </div>
                <div class="beg-pull-right">
                    <button class="layui-btn layui-btn-primary" lay-submit="" lay-filter="login">
                        <i class="layui-icon">&#xe650;</i> 登录
                    </button>
                </div>
                <div class="beg-clear"></div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="${staticFilePath}resource/custom/plugins/layui/layui.js?${date}"></script>
<!-- 引入jQuery -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js?${date}"></script>
<!-- 引入兼容IE9 Input placeholder插件 -->
<script type="text/javascript" src="http://www.ijquery.cn/js/jquery.placeholder.min.js?${date}"></script>
<script type="text/javascript" src="${staticFilePath}resource/custom/js/base.js?${date}"></script>
<script type="text/javascript" src="${staticFilePath}resource/custom/js/layer_.js?${date}"></script>
<script type="text/javascript" src="${staticFilePath}resource/custom/plugins/MD5/MD5.js?${date}"></script>
<script type="text/javascript">
    //更换验证码
    $(".replace_code").bind("click",function () {
        $("#loginVCode").hide().attr('src', baseUrl + "/login/captcha?" + Math.floor(Math.random() * 100) + "&identity=merchant").fadeIn();
    });
    //form提交
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer,
            $ = layui.jquery,
            form = layui.form;
        //监听提交按钮
        form.on('submit(login)', function (data) {
            $(data.elem).attr('class', 'layui-btn layui-btn-disabled');
            var params = {
                url: baseUrl + "/merchant/login",
                params_: {
                    userName: data.field.userName,
                    password: MD5(data.field.password).toUpperCase(),
                    rememberMe : data.field.rememberMe,
                    role: "S",
                    loginVCode:data.field.loginVCode
                }
            };
            http_post(params);  //执行异步请求
            return false;
        });

        //自定义验证规则
        form.verify({
            userName: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                    return '用户名首尾不能出现下划线\'_\'';
                }
                if(!/^1[3|4|5|7|8][0-9]{9}$/.test(value)){
                    return '请输入正确用户名（手机号）';
                }
            },
            verifyCode: function (value) {
                if (value === '') {
                    return '验证码不能为空'
                } else if (value.length > 4) {
                    return '请输入四个字母验证码'
                } else if (value.length < 4) {
                    return '请输入四个字母验证码'
                }
            }
        });
        //form检测switch 状态
        form.on('switch(switchTest)', function (data) {
            layer.msg((this.checked ? '已记住当前账号' : '未记住当前账号'), {
                offset: '15px'
            });
        })
    });
</script>
</body>
</html>
