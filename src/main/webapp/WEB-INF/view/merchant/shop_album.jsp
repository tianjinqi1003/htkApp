<%--
  Created by IntelliJ IDEA.
  User: terabithia
  Date: 11/19/17
  Time: 7:35 PM
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

        .imgItem {
            padding: 10px 10px;
        }

        .imgContainer {
            background-color: #fff;
            padding: 20px 0;
        }

        .iconDiv {
            padding-top: 5px;
            z-index: 10000;
        }

        .layui-bg-orange {
            background-color: red !important;
        }

        .iconSpan {
            cursor: pointer;
        }

        .imgDiv {
            padding: 10px;
            border: 1px dashed #ccc;
        }

        .radioDiv {
            position: relative;
            top: 50px;
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
                    <a href="${sysPath}merchant/shopInfo/shopAlbumPage" class="cur">店铺相册</a>
                </span>
            </div>
            <div class="s_info topOneItem margin-20">
                <div class="imgContainer">
                    <div class="layui-fluid">
                        <div class="layui-row">
                            <c:if test="${data != null}">
                                <c:forEach items="${data}" var="each">
                                    <div class="layui-col-md3 imgItem">
                                        <div class="imgDiv">
                                            <img src="${each.imgUrl}">
                                            <div class="iconDiv">
                                    <span style="width: 100%">
                                        <span style="font-weight: 600;width: 48%">类型：${each.flag == 0 ? "外卖" : "团购"}</span>
                                        <span class="layui-badge layui-bg-orange iconSpan closeIcon"
                                              data-id="${each.id}" style="float: right">
                                        <i class="fa fa-times" aria-hidden="true"></i>
                                        </span>
                                    </span>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <div class="layui-col-md3 imgItem" style="text-align: center">
                                <div class="imgDiv" style="padding-top: 47px">
                                    <span class="radioDiv">
                                        <label>外卖</label>
                                    </span>
                                    <img src="${staticFilePath}resource/images/uploadIcon.jpeg" id="uploadTakeout"
                                         style="cursor: pointer" title="点击上传">
                                </div>
                            </div>
                            <div class="layui-col-md3 imgItem" style="text-align: center">
                                <div class="imgDiv" style="padding-top: 47px">
                                    <img src="${staticFilePath}resource/images/uploadIcon.jpeg" id="uploadGroup"
                                         style="cursor: pointer" title="点击上传">
                                    <span class="radioDiv">
                                        <label>团购</label>
                                    </span>
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
    var upload;
    layui.use(['element', 'util', 'layer', 'upload'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        upload = layui.upload;
        upload.render({
            elem: "#uploadTakeout" //绑定元素
            , url: baseUrl + '/merchant/shopInfo/uploadAlbumInterface' //上传接口
            , data: {flag: 0}
            , auto: true
            , done: function (res) {
                window.location.href = baseUrl + "/merchant/shopInfo/shopAlbumPage";
            }
            , error: function () {
                //请求异常回调
                layer_msg("exception", "上传失败");
            }
        });
        upload.render({
            elem: "#uploadGroup" //绑定元素
            , url: baseUrl + '/merchant/shopInfo/uploadAlbumInterface' //上传接口
            , data: {flag: 1}
            , auto: true
            , done: function (res) {
                window.location.href = baseUrl + "/merchant/shopInfo/shopAlbumPage";
            }
            , error: function () {
                //请求异常回调
                layer_msg("exception", "上传失败");
            }
        });
    });

    //删除图片接口
    $(".closeIcon").on("click", function () {
        var ele = $(this);
        const id = $(ele).attr("data-id");
        const url = baseUrl + "/merchant/shopInfo/deleteAlbumById";
        var params = {
            id: id
        }
        $.post(url, params, function (result, status) {
            if (status === 'success') {
                if (result && result.code === 0) {
                    window.location.href = baseUrl + "/merchant/shopInfo/shopAlbumPage";
                } else {
                    layer_msg('error', result.message);
                }
            } else {
                layer_msg("exception", "网络连接异常")
            }
        })
    })
</script>
</html>
