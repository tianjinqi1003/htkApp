<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/15
  Time: 17:06
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

        .account-label {
            color: #a7a4a4;
            font-size: 15px;
            font-weight: 500;
        }

        .account_item {
            color: #565252;
            font-size: 15px;
            font-weight: 500;
        }

        .account-updateText {
            color: #1E9FFF;
            font-size: 14px;
            font-weight: 500;
            cursor: pointer;
            line-height: 25px;
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

        .topOneItem {
            top:85px;
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
                    <a href="${sysPath}merchant/integral/getNewActivePage" class="cur">新建</a>
                    <a href="${sysPath}merchant/integral/getExitsActiveProcessPage">已创建</a>
                </span>
            </div>
            <div class="active-contentP s_info topOneItem">
                <div class="shopInfo" style="background-color: #fff;width: 80%;padding: 15px;">
                    <div class="" style="margin-bottom: 15px;"><span
                            style="font-size: 15px;font-weight: 600">请选择活动类型</span></div>
                    <div class="layui-fluid active-Div">
                        <div class="layui-row">
                            <div class="layui-col-md4 single">
                                <div class="singleNewActive">
                                    <div class="active_icon">
                                        <span class="icon_span">
                                            <img src="${staticFilePath}resource/images/active_icon2.png" title="兑换活动"/>
                                        </span>
                                    </div>
                                    <div class="active_content">
                                        <h2 class="content-title">兑换活动</h2>
                                        <p class="content-des">积分满足活动要求，即可兑换优惠券</p>
                                    </div>
                                    <div class="createDiv">
                                        <p class="text-tips">已有<span class="numText">1</span>位商户创建</p>
                                        <button class="layui-btn layui-btn-normal createBtn" data-type="exchange">新建
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-md4 single">
                                <div class="singleNewActive">
                                    <div class="active_icon">
                                        <span class="icon_span">
                                            <img src="${staticFilePath}resource/images/active_icon3.png" title="平台消息"/>
                                        </span>
                                    </div>
                                    <div class="active_content">
                                        <h3 class="content-title">店铺资讯</h3>
                                        <p class="content-des">发布店铺最新消息，即可在会员尊享中看到</p>
                                    </div>
                                    <div class="createDiv">
                                        <p class="text-tips">已有<span class="numText">1</span>位商户创建</p>
                                        <button class="layui-btn layui-btn-normal createBtn" data-type="message">新建
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-col-md4 single">
                                <div class="singleNewActive" style="display: none">
                                    <div class="active_icon">
                                        <span class="icon_span"><i class="fa fa-check-square i_size"
                                                                   aria-hidden="true"></i></span>
                                    </div>
                                    <div class="active_content">
                                        <h3 class="content-title">兑换活动</h3>
                                        <p class="content-des">积分满足活动要求，即可兑换优惠券</p>
                                    </div>
                                    <div class="createDiv">
                                        <p class="text-tips">已有<span class="numText">1</span>位商户创建</p>
                                        <button class="layui-btn layui-btn-normal createBtn">新建</button>
                                    </div>
                                </div>
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
    $(function () {
        $('input, textarea').placeholder();
    });
    var date;
    var form;
    var upload;
    var edit;
    var editIndex;
    layui.use(['element', 'util', 'layer', 'laydate', 'form', 'upload', 'layedit'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        date = layui.laydate;
        form = layui.form;
        upload = layui.upload;
        edit = layui.layedit;
        form.on('submit(message)', function(data){
        	debugger
            //取编辑器内容
//            alert("========================layui.element:"+layui.element+" layui.util:"+layui.util+" layui.form:"+layui.form+" layui.upload:"+layui.upload+" layui.layedit:"+layui.layedit)
            if(editIndex !== undefined){
                data.field.htmlContent = edit.getContent(editIndex);
            }
            var imgValue = $("#imgUrl_").val();
            if(imgValue.length <= 0){
                layer.mes("请上传图片",{icon:5, anim: 6});
                return false;
            }
            const url = baseUrl + "/merchant/integral/createNewActive";
            $.post(url, data.field, function (result, status) {
                if(status === 'success'){
                    if(result && result.code === 0){
                        window.location.href = baseUrl + "/merchant/integral/getNewActivePage";
                    }else {
                        layer_msg("error", result.message);
                    }
                }else {
                    layer_msg("exception", "网络连接失败");
                }
            })
            return false;
        });

        form.on('submit(exchange)', function(data){
            const url = baseUrl + "/merchant/integral/createExchangeActivity";
            $.post(url, data.field, function (result, status) {
                if(status === 'success'){
                    if(result && result.code === 0){
                        window.location.href = baseUrl + "/merchant/integral/getNewActivePage";
                    }else {
                        layer_msg("error", result.message);
                    }
                }else {
                    layer_msg("exception", "网络连接失败");
                }
            })
            return false;
        });

        form.verify({

        });
    });

    $(document).on("click", ".uploadImg", function () {
        var ele = this;
        //上传资讯图片
//        alert("========================asdadasd:" + baseUrl + '/merchant/integral/uploadMsgImg')
        var uploadInst = upload.render({
            elem: ele //绑定元素
            ,url: baseUrl + '/merchant/integral/uploadMsgImg' //上传接口
            ,accept: 'images'
            ,exts: 'jpg|png|gif|bmp|jpeg'
            ,auto: true
            ,done: function(res){
                //上传完毕回调
                $("#imgUrl_").val(res.data);
                <%--const rootDirectory = "${staticFilePath}";--%>
                const rootDirectory = "http://120.27.5.36:8500/htkApp/";
                $(ele).attr('src',rootDirectory.replace("htkApp", "") + res.data);
            }
            ,error: function(){
                //请求异常回调
                layer_msg("exceptin", "上传图片失败");
            }
        });
    });

    $(".createDiv > .createBtn").on("click", function () {
        var ele = $(this);
        const clickType = ele.attr("data-type");
        if (clickType === 'exchange') {
            exchange();
        } else if (clickType === 'message') {
            message();
        }
    });

    function exchange() {
        const contentStr = "" +
            "<form class='layui-form' style='height: 200px;padding: 15px;'>" +
            "<div style='margin-bottom: 20px;'><h2 style='font-size: 16px;font-weight: 600'>竞换活动</h2></div>" +
            "<div>" +
            "<div class='form-item'><label class='labelText'>活动名称</label>" +
            "<div class='rightContent'>" +
            "<input class='inputEle' name='tName' lay-verify=\"required\" placeholder='例：200积分兑换5元' />" +
            "</div>" +
            "</div>" +
            "<div class='form-item'><label class='labelText'>活动日期</label>" +
            "<div class='rightContent'>" +
            "<input class='inputEle pageTierStartTime pageTierTime' name='startTime' lay-verify=\"required\" readonly placeholder='选择日期' /> <span>至</span> " +
            "<input readonly lay-verify=\"required\" name='endTime' class='inputEle pageTierTime pageTierEndTime' placeholder='选择日期' />" +
            "</div>" +
            "</div>" +
            "<div class='form-item'><label class='labelText'>活动内容</label>" +
            "<div class='rightContent'><input class='inputEle' name='integralValue' lay-verify=\"required\" placeholder='请输入需要的积分' /> <span>换</span> " +
            "<input class='inputEle' lay-verify=\"required\" name='tMoney' placeholder='请输入兑换的金额' /></div>" +
            "</div>" +
            "<div class='form-item'>" +
            "<label class='labelText'>使用金额限制</label>" +
            "<div class='rightContent'>" +
            "<input class='inputEle' name='tUseMoney' lay-verify=\"required\" placeholder='请输入最低消费额度' />" +
            "</div>" +
            "</div>" +
            "<div style='margin-left: 100px;margin-top: 20px;'>" +
            "<button class='layui-btn layui-btn-normal' lay-submit lay-filter=\"exchange\">创建活动</button></div>" +
            "</div>" +
            "</form>";
        var params = {
            title: false,
            content: contentStr,
//            offset: '50px',
            fixed: true,
            area: {width: '550px', height: '350px'}
        };
        layer_pageTier(params);
        form.render();
    }

    function message() {
        const contentStr = "<div>" +
            "<div></div>" +
            "<div style='padding: 10px;margin-top: 10px;'>" +
            "<form class=\"layui-form\" style='width: 90%'>\n" +
            "  <div class=\"layui-form-item\">\n" +
            "    <label class=\"layui-form-label\">标题</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "      <input type=\"text\" name=\"title\" lay-verify=\"required\" placeholder=\"请输入标题\" autocomplete=\"off\" class=\"layui-input\">\n" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class=\"layui-form-item layui-form-text\">\n" +
            "    <label class=\"layui-form-label\">图片</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "<div style='border: 1px dashed #ccc;max-width: 180px;border-radius: 3px;'>" +
            "<label style='cursor: pointer'>" +
            "            <img style='width:100px;height:100px' src='${staticFilePath}resource/images/uppic.jpg' class='uploadImg' id='uploadImg'/>  " +
                "<input hidden='hidden' name='imgUrl' id='imgUrl_' />"+
            "</label>" +
            "       </div>" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class=\"layui-form-item layui-form-text\">\n" +
            "    <label class=\"layui-form-label\">描述</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "      <textarea placeholder=\"例：健康体验，今天来说说这个事。。\" lay-verify=\"required\" name='describe_' autocomplete='off' class=\"layui-textarea\" style='resize: none;min-height: 60px;'></textarea>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class=\"layui-form-item layui-form-text\">\n" +
            "    <label class=\"layui-form-label\">描述</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "      <textarea placeholder=\"例：健康体验，今天来说说这个事。。\" name='htmlContent' id='textareaEle' autocomplete='off' class=\"layui-textarea\" style='resize: none;min-height: 60px;'></textarea>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class=\"layui-form-item\">\n" +
            "    <div class=\"layui-input-block\">\n" +
            "      <button class=\"layui-btn\" lay-submit lay-filter=\"message\">立即提交</button>\n" +
            "      <button type=\"reset\" class=\"layui-btn layui-btn-primary\">重置</button>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</form>" +
            "</div>" +
            "</div>";
        var params = {
            title: false,
            content: contentStr,
            fixed: true,
            area: {width: '650px', height: '600px'}
        };
        layer_pageTier(params);
        //刷新动态添加的html
        //初始化富文本编辑器(要在edit.build之前)
        edit.set({
            uploadImage: {
                url: baseUrl + '/merchant/integral/uploadNewsContentImg',
                type: 'post'
            }
        });
        editIndex = edit.build('textareaEle');
        form.render();
    }

    //开始日期
    $(document).on("click", ".pageTierTime", function () {
        date.render({
            elem: this
            , show: true
            , type: 'datetime'
            , closeStop: '#test26-1'
        });
    });

</script>
</html>
