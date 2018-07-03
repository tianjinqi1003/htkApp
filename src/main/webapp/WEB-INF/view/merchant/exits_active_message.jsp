<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/15
  Time: 16:42
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

        .exit-active-content {
            width: 65%;
            margin: auto;
        }

        .content-child {
            width: 80%;
            padding: 15px;
        }

        .active-status {
            background-color: #fff;
            margin-bottom: 15px;
        }

        .active-item {
            background-color: #fff;
            margin-bottom: 15px;
        }

        .active-com {
            padding: 15px;
        }

        .btnGroup > a.aItem {
            margin: 0 30px;
            color: #a7a4a4;
        }

        .btnGroup > a.cur {
            background-color: #1E9FFF;
            color: #fff;
            padding: 3px 8px;
        }

        .item-com {
            padding: 5px 25px;
        }

        .active-content {
            padding: 0;
        }

        .imgSpan {
            width: 40px;
            height: 40px;
            border:1px solid #c4c4d2;
            border-radius: 3px;
        }

        .title_name {
            font-size: 15px;
            font-weight: 600;
            position: relative;
            top: 12px;
            padding-left: 15px;
        }

        .name {
            display: block;
        }

        .title_time {
            display: block;
            color: #a7a4a4;
            font-size: 14px;
            font-weight: 500;
        }

        .btn-item > button{
            height: 35px;
            line-height: 35px;
        }

        .textItem {
            display: block;
            padding: 5px 0;
        }

        .textItem > :first-child {
            color: #a7a4a4;
            width: 50px;
        }

        .item-content {
            padding-bottom: 15px;
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
                    <a href="${sysPath}merchant/integral/getNewActivePage">新建</a>
                    <a href="${sysPath}merchant/integral/getExitsActiveProcessPage" class="cur">已创建</a>
                </span>
            </div>
            <div class="exit-active-content s_info topOneItem">
                <div class="content-child">
                    <div class="active-status active-com shadow_">
                        <label style="font-size: 14px;font-weight: 500">活动状态：</label>
                        <div class="btnGroup" style="display: inline-block">
                            <a class="aItem" href="${sysPath}merchant/integral/getExitsActiveNoStartPage">待开始</a>
                            <a class="aItem" href="${sysPath}merchant/integral/getExitsActiveProcessPage">进行中</a>
                            <a class="aItem" href="${sysPath}merchant/integral/getExitsActiveStopPage">已结束</a>
                            <a class="aItem cur" href="${sysPath}merchant/integral/getExitsActiveMessagePage">资讯</a>
                        </div>
                    </div>
                    <div class="layui-fluid active-content">
                        <div class="active-item shadow_">
                            <c:choose>
                                <c:when test="${data != null}">
                                    <c:forEach items="${data}" var="each">
                                        <div class="exits-item">
                                            <div class="layui-row item-com">
                                                <div class="layui-col-md7">
                                    <span class="spanTitle" style="padding-top: 10px;">
                                        <span class="imgSpan">
                                            <img src="${staticFilePath}resource/images/active_icon3.png"/>
                                        </span>
                                        <span class="title_name" style="position: inherit">
                                            <span class="name">${each.title}</span>
                                        </span>
                                    </span>
                                                </div>
                                                <div class="layui-col-md5 btnGroup" style="text-align: right">
                                                    <div style="padding-top: 12px;">
                                                        <div class="btn-item" style="display: inline-block; margin-right: 5px;">
                                                            <button class="layui-btn-normal showDetail layui-btn">查看详情</button>
                                                            <input hidden="hidden" id="showDetail" value='${each.jsonStr}'>
                                                        </div>
                                                        <div class="btn-item" style="display: inline-block;margin-left: 5px;">
                                                            <button class="layui-btn closeTheDisplay layui-btn-primary" data-id="${each.id}">${each.state == -1 ? "开启显示" : "关闭显示"}</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <hr/>
                                            <div class="item-content item-com">
                                                <span class="textItem">
                                                    <span>描述：</span>
                                                    <span>${each.describe_}</span>
                                                </span>
                                                <span class="textItem">
                                                    <span>日期：</span>
                                                    <span>${each.createTime} 发布 </span>
                                                </span>
                                                <span class="textItem">
                                        <span>标题图：</span>
                                        <span style="display: block;padding-left: 50px;position: relative;top: -13px;width: 300px;height: 200px;">
                                            <img src="${each.imgUrl}" style="height: inherit">
                                        </span>
                                    </span>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <div style="text-align: center">没有数据</div>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${data != null}">
                                <div>
                                    <div id="page"></div>
                                </div>
                            </c:if>
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
    var form;
    var edit;
    var upload;
    var index;
    var editIndex;
    layui.use(['element', 'util', 'layer', 'laypage', 'upload', 'form', 'layedit'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        var page = layui.laypage;
        form = layui.form;
        edit = layui.layedit;
        upload = layui.upload;

        //不显示首页尾页
        page.render({
            elem: 'page'
            , count: ${pageInfo == null ? 0: pageInfo.pages * 10}
            , groups: 3
            , curr: ${pageInfo == null ? 1 : pageInfo.pageNum} //获取起始页
            , prev: "<"
            , next: ">"
            , theme: '#1E9FFF'
            , first: false
            , last: false
        });

        form.on('submit(message)', function(data){
            console.log(data.field);
            //取编辑器内容
            if(editIndex !== undefined){
                data.field.htmlContent = edit.getContent(editIndex);
            }
            const url = baseUrl + "/merchant/integral/updateMes";
            $.post(url, data.field, function (result, status) {
                if(status === 'success'){
                    if(result && result.code === 0){
                        layer_loadingClose(index);
                        window.location.href = baseUrl + "/merchant/integral/getExitsActiveMessagePage";
                    }else {
                        layer_msg(result.message, "error");
                    }
                }else {
                    layer_msg("网络连接失败", "exception");
                }
            })
            return false;
        });
    });

    $(".closeTheDisplay").on("click", function () {
        var ele = this;
        var id = $(ele).attr("data-id");
        const url = baseUrl + "/merchant/integral/closeTheDisplay";
        $.post(url, {id: id}, function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.href = baseUrl + "/merchant/integral/getExitsActiveMessagePage";
                }else {
                    layer_msg(result.message, "error");
                }
            }else {
                layer_msg("网络连接失败","exception");
            }
        });
    });

    $(document).on("click", ".closeBtn", function () {
        if(index !== undefined){
            //关闭弹窗
            layer_loadingClose(index);
        }
    })

    $(document).on("click", ".uploadImg", function () {
        var ele = this;
        //上传资讯图片
        var uploadInst = upload.render({
            elem: ele //绑定元素
//            ,url: baseUrl + '/merchant/integral/uploadMsgImg' //上传接口
            ,url: baseUrl + '/merchant/integral/uploadMsgTitleImg' //上传接口
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


    $(".showDetail").on("click", function () {
        var ele = $(this);
        var eleMes = $(ele).siblings("input");
        console.log(eleMes.val());
        var jsonStr = JSON.parse(eleMes.val());
        <%--var rootUrl = "${staticFilePath}";--%>
        var imgUrlPath = jsonStr.imgUrl;
        var imgUrl = imgUrlPath.replace("http://120.27.5.36:8500/", "");
        const contentStr = "<div>" +
            "<div></div>" +
            "<div style='padding: 10px;margin-top: 10px;'>" +
            "<form class=\"layui-form\" style='width: 90%'>\n" +
                "<input hidden='hidden' name='id' value='"+jsonStr.id+"'>" +
            "  <div class=\"layui-form-item\">\n" +
            "    <label class=\"layui-form-label\">标题</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "      <input type=\"text\" name=\"title\" value='"+jsonStr.title+"' lay-verify=\"required\" placeholder=\"请输入标题\" autocomplete=\"off\" class=\"layui-input\">\n" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class=\"layui-form-item layui-form-text\">\n" +
            "    <label class=\"layui-form-label\">图片</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "<div style='border: 1px dashed #ccc;max-width: 180px;border-radius: 3px;'>" +
            "<label style='cursor: pointer'>" +
            "            <img style='width:100px;height:100px' src='"+jsonStr.imgUrl+"' class='uploadImg' id='uploadImg'/>  " +
            "<input hidden='hidden' name='imgUrl' id='imgUrl_' value='"+imgUrl+"' />"+
            "</label>" +
            "       </div>" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class=\"layui-form-item layui-form-text\">\n" +
            "    <label class=\"layui-form-label\">描述</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "      <textarea placeholder=\"例：健康体验，今天来说说这个事。。\" lay-verify=\"required\" name='describe_' autocomplete='off' class=\"layui-textarea\" style='resize: none;min-height: 60px;'>"+jsonStr.describe_+"</textarea>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class=\"layui-form-item layui-form-text\">\n" +
            "    <label class=\"layui-form-label\">详情</label>\n" +
            "    <div class=\"layui-input-block\">\n" +
            "      <textarea placeholder=\"例：健康体验，今天来说说这个事。。\" name='htmlContent' id='textareaEle' autocomplete='off' class=\"layui-textarea\" style='resize: none;min-height: 60px;'>"+jsonStr.htmlContent+"</textarea>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "  <div class=\"layui-form-item\">\n" +
            "    <div class=\"layui-input-block\">\n" +
            "      <button class=\"layui-btn\" lay-submit lay-filter=\"message\">确认修改</button>\n" +
            "      <button type=\"button\" class=\"layui-btn closeBtn layui-btn-primary\">关闭</button>\n" +
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
        index = layer_pageTier(params);
        //刷新动态添加的html
//        edit.set({
//            uploadImage: {
//                url: 'server/index',
//                type: 'post'
//            }
//        });
        edit.set({
            uploadImage: {
                url: baseUrl + '/merchant/integral/uploadNewsContentImg',
                type: 'post'
            }
        });
        editIndex = edit.build('textareaEle');
        form.render();
    });
</script>
</html>
