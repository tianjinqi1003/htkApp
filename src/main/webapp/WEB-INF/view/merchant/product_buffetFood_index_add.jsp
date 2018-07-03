<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/9
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
        pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp"%>
<head>
    <title>自助点餐添加商品页面</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp"%>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp"%>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="crumbs">
                <div class="crumbsContiner clearfix">
                    <a href="${sysPath}merchant/buffetFood/product/homePage">商品管理</a>
                    <i class="spacer">/</i>
                    <a href="">添加商品</a>
                </div>
            </div>
            <div class="continerCont pdtp10">
                <div class="addFormCont">
                    <div class="formCont">
                        <form action="${sysPath}merchant/buffetFood/product/addProduct" method="POST" enctype="multipart/form-data">
                            <div class="formItem">
                                <h3>基本信息</h3>
                                <div class="formList">
                                    <div class="formLine clearfix">
                                        <div class="formTit"><i>*</i>商品名称</div>
                                        <div class="formInput fontNum">
                                            <input type="text" placeholder="请输入商品名称" name="productName" maxlength="20"/>
                                            <input type="hidden" name="mark" value="2">
                                            <span class="num">0/20</span>
                                        </div>
                                    </div>
                                    <div class="formLine clearfix">
                                        <div class="formTit"><i>*</i>店内分类</div>
                                        <div class="formInput">
                                            <select name="categoryId">
                                                <c:choose>
                                                    <c:when test="${data != null}">
                                                        <option value="">请选择商品分类</option>
                                                        <c:forEach items="${data}" var="each">
                                                            <option value="${each.id}">${each.categoryName}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="">请选择商品分类</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="formLine clearfix">
                                        <div class="formTit">图片</div>
                                        <div class="formInput">
                                            <label class="uploadImage">
                                                <img src="${sysPath}resource/custom/images/uppic.jpg"/>
                                                <input type="file" accept=".jpg,.png" onchange="preview(this);" name="imgFile"/>
                                            </label>
                                            <p class="fieldNotice">提示：请上传jpg/png文件，且不超过 <i>5MB</i></p>
                                        </div>
                                    </div>

                                    <div class="formLine clearfix">
                                        <div class="formTit">描述</div>
                                        <div class="formInput fontNum">
                                            <textarea placeholder="请输入商品描述" maxlength="250" style="line-height:2.0" name="productDetail"></textarea>
                                            <span class="num txar">0/250</span>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="formItem">
                                <h3>价格</h3>
                                <div class="formList">
                                    <div class="formLine clearfix">
                                        <div class="likeTable">
                                            <div class="likeTh clearfix">
                                                <span><i>产品</i>价格</span>
                                            </div>
                                            <div class="likeTr clearfix">
                                                <span style="text-align: left"><i class="rmb">￥</i>
                                                    <input type="text" class="money" name="price"/></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="formItem">
                                <h3>积分</h3>
                                <div class="formList">
                                    <div class="formLine clearfix">
                                        <div class="likeTable">
                                            <div class="likeTh clearfix">
                                                <span><i>产品</i>积分</span>
                                            </div>
                                            <div class="likeTr clearfix">
                                                <span style="text-align: left"><i class="rmb">￥</i>
                                                    <input type="text" class="money" name="integral"/></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="subFrm">
                                <input type="submit" value="保存"/>
                                <input type="reset" onclick="openIndex()" value="取消"/>
                            </div>
                        </form>
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
    $(function () {$('input, textarea').placeholder();});
    layui.use(['element', 'util', 'layer', 'laydate'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        var date = layui.laydate;
        //日期
        date.render({
            elem: '.selectTime'
            ,type: 'datetime'
            ,range: true
            ,theme: '#0091EA'
        });

        //固定块
        util.fixbar({
            bar1: true
            , css: {right: 50, bottom: 100}
            , bgcolor: '#393D49'
            , click: function (type) {
                if (type === 'bar1') {
                    layer_msg("点击了浮动球", 'info');
                }
            }
        });
    });

    function preview(file){
        if (file.files && file.files[0]){
            var reader = new FileReader();
            reader.onload = function(evt){

                var f=file.value;
                var allExt=".jpg,.png";
                var ext = f.split('.');//通过\分隔字符串，成字符串数组
                ext="."+ext[ext.length-1];
                if(allExt.indexOf(ext)>-1){
                }else{
                    alert("文件格式有误");
                    return false;
                }
                $(".uploadImage img").attr("src",evt.target.result);
            }
            reader.readAsDataURL(file.files[0]);
        }else{
            $(".uploadImage img").attr("style","filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=" + file.value);
        }
    }

    function openIndex() {
        window.location.href = baseUrl + "/merchant/buffetFood/product/homePage";
    }
</script>
</html>
