<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/9
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
        pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp"%>
<head>
    <title>门店信息-店铺</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp"%>
    <style>
        .topTab {
            top: 50px;
        }

        .topOneItem {
            position: relative;
            top: 60px;
        }

        .topTwoItem {
            position: relative;
            top: 60px;
        }

        .businessSet {
            padding: 5px;
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

        .pad-left {
            padding-left: 5px;
        }

        .contentText {
            line-height: 50px;
            font-size: 15px;
        }

        .textarea-input {
            border-radius: 3px;
            resize: none;
        }

        .span_tips {
            padding: 3px 10px;
            border: 1px solid #1592e6;
            border-radius: 3px;
            color: #1592e6;
            margin-right: 15px;
        }

        .hour-input {
            border-radius: 2px;
            border: 1px solid #b4b4bb;
            padding-left: 5px;
            height: 30px;
            font-weight: 500;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp"%>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="childCat topTab clearfix tab shadow_">
                <a href="${sysPath}merchant/shopInfo/store">门店设置</a>
            </div>
            <div class="s_info topOneItem margin-20">
                <div class="panel panel-default shopInfo" style="height: 280px;">
                    <div class="panel-heading">
                        <div class="layui-row">
                            <div class="layui-col-md6">
                                <h3 class="panel-title">基本信息</h3>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="layui-fluid" style="padding: 0">
                            <div class="layui-row">
                                <div class="layui-col-md6 info_fg">
                                    <div class="info_content">
                                        <div class="info">
                                            <span>
                                                <span>店名</span>
                                                <span>:</span>
                                                <span class="info_text">${data.shopName}</span>
                                            </span>
                                        </div>
                                        <div class="info">
                                            <span>
                                                <span>地址</span>
                                                <span>:</span>
                                                <span class="info_text">${data.address}</span>
                                            </span>
                                        </div>
                                        <div class="info">
                                            <span>
                                                <span>分类</span>
                                                <span>:</span>
                                                <span class="info_text">${data.shopCategoryName}</span>
                                            </span>
                                        </div>
                                        <div class="info">
                                            <span>
                                                <span>截止时间</span>
                                                <span>:</span>
                                                <span class="info_text">${useRemainingTime}</span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-col-md6">
                                    <div class="info_content_right">
                                        <div class="info">
                                            <span>
                                                <span>店铺号</span>
                                                <span>:</span>
                                                <span class="info_text">${data.shopId}</span>
                                            </span>
                                        </div>
                                        <div class="info">
                                            <div class="rightDiv">
                                                <span>店铺二维码</span>
                                                <span>:</span>
                                                <div class="qrImg" style="width: 116px;height: 116px;">
                                                    <img src="${data.shopQrCodeUrl}?${date}" style="position: relative;top: 40px;left: -96px">
                                                    <img src="${data.bufImg}?${date}" style="position: relative;top: -76px;left: 46px">
                                                </div>
                                                <span style="position: relative;top:9px;left: 87px">
                                                    <a href="${data.shopQrCodeUrl}" download="${data.shopName}" style="position: relative;left: -50px">下载</a>
                                                    <a href="${data.bufImg}" download="${data.shopName}" style="position: relative;left: 50px">下载</a>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="s_setting topTwoItem margin-20" style="display: block">
                <div class="panel panel-default shopInfo">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            营业设置
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="businessSetDiv">
                            <div class="businessSet">
                                <div class="busSetItem">
                                    <label class="promptName">店铺头像</label>
                                    <div class="itemContent clearfix">
                                        <div class="leftContent">
                                            <img id="shopImg" style="height: 50px;width: 81px;" src="${data.logoUrl}"/>
                                        </div>
                                        <div class="rightContent">
                                            <a class="textClickA" id="textClickA" data-clickType="avatarImg">
                                                <span class="textSpan">更改头像<i class="fa fa-chevron-right pad-left"
                                                                              aria-hidden="true"></i>
                                                </span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <hr/>
                                <div class="busSetItem">
                                    <label class="promptName">我的帐号</label>
                                    <div class="itemContent clearfix">
                                        <div class="leftContent">
                                            <span class="contentText">${data.mobilePhone}_htk</span>
                                        </div>
                                        <div class="rightContent">
                                            <a class="textClickA" data-clickType="account">
                                                <span class="textSpan">修改
                                                    <i class="fa fa-chevron-right pad-left" aria-hidden="true"></i>
                                                </span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <hr/>
                                <div class="busSetItem">
                                    <label class="promptName">营业时间</label>
                                    <div class="itemContent clearfix">
                                        <div class="leftContent">
                                            <span class="contentText">
                                                <span style="margin-right: 10px">${data.openingTime}</span>
                                                <%--<span style="margin-left: 10px">16:00-21:00</span>--%>
                                            </span>
                                        </div>
                                        <div class="rightContent">
                                            <a class="textClickA" data-clickType="openTime">
                                                <span class="textSpan">修改<i class="fa fa-chevron-right pad-left"
                                                                            aria-hidden="true"></i>
                                                </span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <hr/>
                                <div class="busSetItem">
                                    <label class="promptName">店铺相册</label>
                                    <div class="itemContent clearfix">
                                        <div class="leftContent">
                                        </div>
                                        <div class="rightContent">
                                            <a class="textClickA" data-clickType="shopAlbum">
                                                <span class="textSpan">设置<i class="fa fa-chevron-right pad-left"
                                                                            aria-hidden="true"></i>
                                                </span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <hr/>
                                <div class="busSetItem">
                                    <label class="promptName">订餐电话</label>
                                    <div class="itemContent clearfix">
                                        <div class="leftContent">
                                            <span class="contentText" id="shopPhone">
                                                ${data.mobilePhone}
                                            </span>
                                        </div>
                                        <div class="rightContent">
                                            <a class="textClickA" data-clickType="callPhone">
                                                <span class="textSpan">修改<i class="fa fa-chevron-right pad-left"
                                                                            aria-hidden="true"></i>
                                                </span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <hr/>
                                <div class="busSetItem">
                                    <label class="promptName">店铺公告</label>
                                    <div class="itemContent clearfix">
                                        <div class="leftContent">
                                            <span class="contentText" id="shopPlacard">${data.intro}</span>
                                        </div>
                                        <div class="rightContent">
                                            <a class="textClickA" data-clickType="shopPlacard">
                                                <span class="textSpan">修改<i class="fa fa-chevron-right pad-left"
                                                                            aria-hidden="true"></i>
                                                </span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <hr/>
                                <div class="busSetItem">
                                    <label class="promptName">店铺简介</label>
                                    <div class="itemContent clearfix">
                                        <div class="leftContent">
                                            <span class="contentText" id="present">${data.des == null ? "" : data.des}</span>
                                        </div>
                                        <div class="rightContent">
                                            <a class="textClickA" data-clickType="present">
                                                <span class="textSpan">修改
                                                    <i class="fa fa-chevron-right pad-left" aria-hidden="true"></i>
                                                </span>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <hr/>
                                <div class="busSetItem">
                                    <label class="promptName">配送费</label>
                                    <div class="itemContent clearfix">
                                        <div class="leftContent">
                                            <span class="contentText" id="deliveryFee">${data.deliveryFee}</span>
                                        </div>
                                        <div class="rightContent">
                                            <a class="textClickA" data-clickType="deliveryFee">
                                                <span class="textSpan">修改
                                                    <i class="fa fa-chevron-right pad-left" aria-hidden="true"></i>
                                                </span>
                                            </a>
                                        </div>
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
    $(function () {
        $('input, textarea').placeholder();
    });
    var upload;
    layui.use(['element', 'util', 'layer', 'upload'], function () {
        upload = layui.upload;

        upload.render({
            elem: "#textClickA" //绑定元素
            ,url: baseUrl + '/merchant/shopInfo/updateShopImg' //上传接口
            ,done: function(res){
                //上传完毕回调,改变店铺图片
//                $("#shopImg").attr("src", baseUrl + "/" + res);
                $("#shopImg").attr("src", "http://120.27.5.36:8080/" + res.data);

            }
            ,error: function(error){
                //请求异常回调
                layer_msg(error, "error");
            }
        });
    });

    var index;

    //修改点击事件
    $("a.textClickA").on("click", function () {
        var aEle = $(this);
        const clickType = aEle.attr("data-clickType");
        switch (clickType) {
            case "account" :
                window.location.href = baseUrl + "/merchant/shopInfo/account";
                break;
            case "shopPlacard" :
                shopPlacard();
                break;
            case "present" :
                present();
                break;
            case "callPhone":
                callPhone();
                break;
            case "openTime":
                openTime();
                break;
            case "shopAlbum":
                window.location.href = baseUrl + "/merchant/shopInfo/shopAlbumPage";
                break;
            case "deliveryFee":
                deliveryFee();
            default:
                break;
        }
    });

    //店铺公告
    function shopPlacard() {
        var shopPlacard = $("#shopPlacard").text().trim();
        var contentStr = "<div style='margin-top: 20px;'>\n" +
            "                <div style=\"width: 80%;margin: auto\">\n" +
            "                    <textarea class=\"layui-textarea textarea-input\" id='intro' placeholder='请输入店铺公告'>"+shopPlacard+"</textarea>\n" +
            "                </div>\n" +
            "<hr style='margin: 15px 0' />" +
            "<div style='margin-top: 15px;margin-left: 15px'><button class='layui-btn-normal shopPlacard layui-btn-small layui-btn saveBtn'>确认并保存</button></div>" +
            "            </div>";
        var params = {
            title: ["店铺公告", "background-color:#fff;color:#000"],
            content: contentStr,
            offset: '250px',
            fixed: true,
            area: {
                width: '550px',
                height: '240px'
            }
        };
        index = layer_pageTier(params)
    }

    //店铺简介
    function present() {
        var present = $("#present").text().trim();
        var contentStr = "<div style='margin-top: 20px;'>\n" +
            "                <div style=\"width: 80%;margin: auto\">\n" +
            "                    <textarea class=\"layui-textarea textarea-input\" id='present_' placeholder='请输入店铺简介'>"+present+"</textarea>\n" +
            "                </div>\n" +
            "<hr style='margin: 15px 0' />" +
            "<div style='margin-top: 15px;margin-left: 15px'><button class='layui-btn-normal present layui-btn-small layui-btn saveBtn'>确认并保存</button></div>" +
            "            </div>";
        var params = {
            title: ["店铺简介", "background-color:#fff;color:#000"],
            content: contentStr,
            offset: '250px',
            fixed: true,
            area: {
                width: '550px',
                height: '240px'
            }
        };
        index = layer_pageTier(params)
    }

    //订餐电话
    function callPhone() {
        var phone = $("#shopPhone").text().trim();
        var contentStr = "<div style='margin-top: 20px;'>\n" +
            "<div style='padding-left: 20px'>" +
            "<p style='font-size: 14px'>顾客联系商家的唯一方式, 平台也会通过电话提醒接单</p>" +
            "<div style='padding: 10px 0'>" +
            "<label style='width: 80px;font-size: 14px;font-weight: 500'>手机号码</label>" +
            "<div style='display: inline-block'>" +
            "<input placeholder='请输入手机联系号码' id='mobilePhone' value='"+phone+"' style='border-radius: 2px;border: 1px solid #d1d1d6; height: 30px;padding-left: 10px;'>" +
            "</div>" +
            "</div>" +
//            "<div style='padding: 10px 0'>" +
//            "<label style='width: 80px;font-size: 14px;font-weight: 500'>添加号码</label>" +
//            "<div style='display: inline-block'>" +
//            "<span class='span_tips'>手机</span><span class='span_tips'>座机</span><span class='span_tips'>其它电话</span>" +
//            "</div>" +
//            "<p style='margin-left: 15%;padding-top: 10px;color: #9a9a9a'>手机或座机可接收订单提醒, 400、800等不能接收订单提醒</p>" +
//            "</div>" +
//            "</div>" +
            "<hr style='margin: 15px 0' />" +
            "<div style='margin-top: 15px;margin-left: 15px'><button class='layui-btn-normal callPhone layui-btn-small layui-btn saveBtn'>确认并保存</button></div>" +
            "            </div>";
        var params = {
            title: ["订餐电话", "background-color:#fff;color:#000"],
            content: contentStr,
            offset: '250px',
            fixed: true,
            area: {
                width: '550px',
                height: '280px'
            }
        };
        index = layer_pageTier(params)
    }

    //营业时间
    function openTime() {
        var contentStr = "<div style='margin-top: 20px;font-size: 14px;font-weight: 600'>\n" +
            "<div style='width:80%;margin: auto'>" +
            "<div>" +
            "<label style='width:80px;text-align: right'>24小时营业</label>" +
            "<div style='display:inline-block;margin-left: 20px;'>" +
            "<span>是</span>" +
            "<input type=\"radio\" name=\"switch\" class='switch' id='switch' value=\"1\">" +
            "<span>否</span>" +
            "<input type='radio' name='switch' class='switch' id='switch' value='0' checked>" +
            "</div>" +
            "</div>" +
            "<div id='openTimeDiv' >" +
            "<label style='width:80px;text-align:right'>营业时间</label>" +
            "<div style='display:inline-block;padding-left: 20px;width: 80%;position: relative;top:18px;'>" +
            "<div style='padding-bottom: 8px;'>" +
            "<input placeholder='选择时间' class='hour-input' id='time1' style='width: 30%' value='11:00'/><span>-</span><input placeholder='选择时间' id='time2' class='hour-input' value='15:00' style='width: 30%'/>" +
            "</div>" +
            "<div>" +
            "<input placeholder='选择时间' value='16:00' id='time3' class='hour-input' style='width: 30%' /><span>-</span><input placeholder='选择时间' id='time4' class='hour-input' value='21:00' style='width: 30%' />" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "<hr style='margin: 15px 0;position: relative;top:15px;' />" +
            "<div style='margin-top: 15px;margin-left: 15px;position: relative;top:15px'><button class='layui-btn-normal openTime layui-btn-small layui-btn saveBtn'>确认并保存</button></div>" +
            "            </div>";
        var params = {
            title: ["营业时间", "background-color:#fff;color:#000"],
            content: contentStr,
            offset: '250px',
            fixed: true,
            area: {
                width: '550px',
                height: '250px'
            }
        };
        index = layer_pageTier(params)
    }

    //配送费
    function deliveryFee() {
        var deliveryFee = $("#deliveryFee").text().trim();
        var contentStr = "<div style='margin-top: 20px;font-size: 14px;font-weight: 600'>\n" +
            "<div style='width:80%;margin: auto'>" +
            "<div>" +
            "<label style='width:80px;text-align:right'>输入配送费</label>" +
            "<div style='display:inline-block;padding-left: 20px;width: 80%;'>" +
            "<input name='deliverFee' value='"+deliveryFee+"' id='deliverFee' style='border-radius: 3px;border: 1px solid #cec7c7;height: 30px;' />"+
            "</div>" +
            "</div>" +
            "</div>" +
            "<hr style='margin: 15px 0;position: relative;top:15px;' />" +
            "<div style='margin-top: 15px;margin-left: 15px;position: relative;top:15px'><button class='layui-btn-normal layui-btn-small deliveryFee layui-btn saveBtn'>确认并保存</button></div>" +
            "</div>";
        var params = {
            title: ["配送费", "background-color:#fff;color:#000"],
            content: contentStr,
            offset: '250px',
            fixed: true,
            area: {
                width: '550px',
                height: '190px'
            }
        };
        index = layer_pageTier(params)
    }

//    $(document).on("click", ".saveBtn", function () {
//        layer_loadingClose(index)
//    });

    //店铺公告确认按钮
    $(document).on("click", ".shopPlacard", function () {
        const url = baseUrl + "/merchant/shopInfo/updateIntro";
        var intro = $("#intro").val();
        var params = {
          intro: intro
        };
        $.post(url, params, function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.reload();
                }else {
                    layer_msg(result.message, "error");
                }
            }else {
                layer_msg("网络连接异常", "exception");
            }
        });
    });

    //店铺简介确认按钮
    $(document).on("click", ".present", function () {
        const url = baseUrl + "/merchant/shopInfo/updateDes";
        var present = $("#present_").val();
        var params = {
            des: present
        };
        $.post(url, params, function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.reload();
                }else {
                    layer_msg(result.message, "error");
                }
            }else {
                layer_msg("网络连接异常", "exception");
            }
        });
    })

    //营业时间确认按钮
    $(document).on("click", ".openTime", function () {
        const url = baseUrl + "/merchant/shopInfo/updateOpeningTime";
        var radioEle = $(".switch");
        var type;
        $(radioEle).each(function (index, item) {
            if(item.checked){
                type = item.value
            }
        });
        var time;
        if(type != undefined){
            if(type == 0){
                var am = $("#time1").val() + "-" + $("#time2").val();
                var pm = $("#time3").val() + "-" + $("#time4").val();
                time = am +"~"+ pm;
            }else {
                time = "24小时营业";
            }
        }
        var params = {
            openingTime: time
        };
        $.post(url, params, function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.reload();
                }else {
                    layer_msg(result.message, "error");
                }
            }else {
                layer_msg("网络连接异常", "exception");
            }
        });
    });

    //订餐电话确认按钮
    $(document).on("click", ".callPhone", function () {
        const url = baseUrl + "/merchant/shopInfo/updatePhone";
        var phone = $("#mobilePhone").val();
        var params = {
            phone: phone
        };
        $.post(url, params, function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.reload();
                }else {
                    layer_msg(result.message, "error");
                }
            }else {
                layer_msg("网络连接异常", "exception");
            }
        });
    });

    //配送费确认按钮
    $(document).on("click", ".deliveryFee", function () {
        const url = baseUrl + "/merchant/shopInfo/updateDeliveryFee";
        var deliveryFee = $("#deliverFee").val();
        var params = {
            deliveryFee: deliveryFee
        };
        $.post(url, params, function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.reload();
                }else {
                    layer_msg(result.message, "error");
                }
            }else {
                layer_msg("网络连接异常", "exception");
            }
        });
    });

    $(document).on("click", ".switch", function () {
        var val = this.value;
        if(val == 0){
            //否  不是24小时
            $("#openTimeDiv").css("display", "block");
        }else {
            //是  24小时
            $("#openTimeDiv").css("display", "none");
        }
//        alert(this.value);
    })

    //layer页面层弹窗口
    function layer_pageTier(params) {
        return layer.open({
            type: 1,
            offset: params.offset,
            fixed: params.fixed,
            title: params.title,
            area: [params.area.width, params.area.height], //宽高
            content: params.content
        });
    }

</script>
</html>
