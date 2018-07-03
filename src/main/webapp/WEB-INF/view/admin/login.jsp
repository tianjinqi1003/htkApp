<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/20
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>管理员后台登陆页</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${staticFilePath}resource/custom/css/index.css?${date}">
    <%@include file="head.jsp"%>
</head>
<body class="beg-login-bg">
<div class="beg-login-box">
    <header>
        <h1>管理员后台登录</h1>
    </header>
    <div class="beg-login-main">
        <form class="layui-form" method="post">
            <div class="layui-form-item">
                <label class="beg-login-icon">
                    <i class="layui-icon">&#xe612;</i>
                </label>
                <input type="text" name="userName" placeholder="请输入登录名" class="layui-input"
                       lay-verify="required|userName" autocomplete="off" value="" style="color: black">
            </div>
            <div class="layui-form-item">
                <label class="beg-login-icon">
                    <i class="layui-icon">&#xe642;</i>
                </label>
                <input type="password" name="password" placeholder="请输入密码" class="layui-input"
                       lay-verify="required|password_" autocomplete="off" value="" style="color: black">
            </div>
            <div class="layui-form-item">
                <div class="layui-row layui-col-space8">
                    <div class="layui-col-md4">
                        <input type="text" name="loginVCode" lay-verify="verifyCode" autocomplete="off"
                               placeholder="验证码" lay-verify="required"
                               class="layui-input" style="padding-left: 9px;color: black">
                    </div>
                    <div class="layui-col-md5">
                        <img src="${sysPath}login/captcha?identity=admin" id="loginVCode" style="width: 115px;height: 39px"/>
                    </div>
                    <div class="layui-col-md3">
                        <span style="font-size: 14px;line-height: 39px;float: right">
                            <a href="javascript:void(0)" class="replace_code" style="color: #FFF;cursor: pointer">换一张?</a></span>
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
<%@include file="js.jsp"%>
<script type="text/javascript" src="${staticFilePath}resource/custom/plugins/MD5/MD5.js?${date}"></script>
<script type="text/javascript" src="http://www.ijquery.cn/js/jquery.placeholder.min.js?${date}"></script>
<script type="text/javascript">
    //更换验证码
    $(".replace_code").bind("click",function () {
        $("#loginVCode").hide().attr('src', baseUrl + "/login/captcha?" + Math.floor(Math.random() * 100) + "&identity=admin").fadeIn();
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
                url: baseUrl + "/admin/login",
                params_: {
                    userName: data.field.userName,
                    password: MD5(data.field.password).toUpperCase(),
                    rememberMe : data.field.rememberMe,
                    role: "E",
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