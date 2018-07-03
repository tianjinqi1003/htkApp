<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/15
  Time: 17:10
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
    </style>

    <style>
        .spanCls {
            color: #1E9FFF;
            font-size: 14px;
            font-weight: 500;
            cursor: pointer;
            line-height: 25px;
        }

        .inputCls {
            border: 1px solid #9595a0;
            border-radius: 2px;
            height: 25px;
            padding-left: 5px;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="childCat topTab clearfix tab shadow_">
                <span class="layui-breadcrumb" lay-separator="/">
                    <a href="${sysPath}merchant/shopInfo/store">门店设置</a>
                    <a href="${sysPath}merchant/shopInfo/account"class="cur">我的账号</a>
                </span>
            </div>
            <div class="s_info topOneItem margin-20">
                <div class="panel panel-default shopInfo">
                    <div class="panel-heading">
                        <div class="layui-row">
                            <div class="layui-col-md6">
                                <h3 class="panel-title">我的账号</h3>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="layui-fluid">
                            <div class="layui-row">
                                <label class="layui-col-md3 account-label">帐号名</label>
                                <div class="account_item layui-col-md6">
                                    <p>18660706071_htk</p>
                                </div>
                                <span class="layui-col-md3"></span>
                            </div>
                            <hr />
                            <div class="layui-row">
                                <label class="layui-col-md3 account-label">登陆密码</label>
                                <div class="account_item layui-col-md6">
                                    <p>**********</p>
                                </div>
                                <span class="layui-col-md3" style="text-align: right">
                                    <span class="account-updateText" data-type="password">修改</span>
                                </span>
                            </div>
                            <hr />
                            <div class="layui-row">
                                <label class="layui-col-md3 account-label">绑定手机</label>
                                <div class="account_item layui-col-md6">
                                    <c:set value="${fn:substring(phone,0 , 3)}" var="beginPhoeNumber"/>
                                    <c:set value="${fn:substring(phone,6, 11)}" var="endPhoneNumber"/>
                                    <p>${beginPhoeNumber}****${endPhoneNumber}</p>
                                </div>
                                <span class="layui-col-md3" style="text-align: right">
                                    <span class="account-updateText" data-type="phone">修改</span>
                                </span>
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
    layui.use(['element', 'util', 'layer'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
    });

    $(document).on("click",".account-updateText", function () {
        var sel = this;
        var ele = $(sel).parent().siblings(".account_item");
        var selParent = $(sel).parent();
        //隐藏文字显示元素
        $(ele).find("p").css("display", "none");
        //判断输入框类型
        var type = $(sel).attr("data-type");
        var name = "";
        if(type === "phone"){
            type = "text";
            name = "phone";
        }else {
            name = type;
        }
        //显示输入框
        var str = "<input class='inputCls' name='"+name+"' data-type='"+type+"' type='"+type+"' id='password' value='' />"
        ele.prepend(str);
        selParent.empty();
        //插入span标签
        var spanStr = "<span class='spanCls enter' style='padding-right: 5px'> 确认 </span>" +
            "<span class='spanCls cancel' style='padding-left: 5px;color: #85858e'> 取消 </span>";
        selParent.prepend(spanStr);
    });

    //取消
    $(document).on("click", ".cancel", function () {
        var sel = this;
        var selParent = $(sel).parent();
        var ele = selParent.siblings(".account_item");
        var inputEle = ele.find("input");
        var type = $(inputEle).attr("data-type");
        var spanStr = "<span class=\"account-updateText\" data-type='"+type+"'>修改</span>";
        selParent.empty();
        selParent.prepend(spanStr);
        //隐藏文字显示元素
        $(ele).find("input").remove();
        //文字显示元素
        $(ele).find("p").css("display", "block");
    });


    //确定
    $(document).on("click", ".enter", function () {
        //取得当前元素父元素输入框值
        var ele = this;
        var eleParent = $(ele).parent();
        var inputEle = $(eleParent).siblings(".account_item").find("input");

        var name = inputEle.attr("name");
        var value = inputEle.val();
        var params ={};
        var url;
        if(value.length === 0 || value === ""){
            layer_msg("请输入值后点击确认", "error");
            return false;
        }
        if (name === "phone"){
            url = baseUrl + "/merchant/shopInfo/updateShopPhone";
            params = {
                phone: value
            }
        }else if(name === "password") {
            url = baseUrl + "/merchant/shopInfo/updatePassword";
            params = {
                password: value
            }
        }
        $.post(url, params, function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    layer_msg(result.message, "success");
                    setTimeout(function () {
                        window.location.reload();
                    },2000);
                }else {
                    layer_msg(result.message, "error");
                }
            }else {
                layer_msg("网络连接失败", "exception");
            }
        });
        return false;
    });
</script>
</html>
