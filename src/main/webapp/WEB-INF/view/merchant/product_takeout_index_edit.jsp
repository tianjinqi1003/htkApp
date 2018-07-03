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
    <title>编辑商品页面</title>
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
                    <a href="${sysPath}merchant/takeout/product/homePage">商品管理</a>
                    <i class="spacer">/</i>
                    <a href="">修改商品</a>
                </div>
            </div>
            <div class="continerCont pdtp10">
                <div class="addFormCont">
                    <div class="formCont">
                        <form action="${sysPath}merchant/takeout/product/saveProduct" method="POST" enctype="multipart/form-data">
                            <div class="formItem">
                                <h3>基本信息</h3>
                                <div class="formList">
                                    <div class="formLine clearfix">
                                        <div class="formTit"><i>*</i>商品名称</div>
                                        <div class="formInput fontNum">
                                            <input type="text" placeholder="请输入商品名称" name="productName" maxlength="20" value="${dataPro.productName}"/>
                                            <span class="num">0/20</span>
                                        </div>
                                    </div>
                                    <div class="formLine clearfix">
                                        <div class="formTit"><i>*</i>店内分类</div>
                                        <div class="formInput">
                                            <select name="categoryId">
                                                <%--<option value="${dataPro.categoryId}" selected = "selected">${dataPro.categoryId}</option>--%>
                                                <c:choose>
                                                    <c:when test="${data != null}">
                                                        <c:forEach items="${data}" var="each">
                                                            <c:choose>
                                                                <c:when test="${each.id eq dataPro.categoryId}">
                                                                    <option value="${each.id}" selected = "selected">${each.categoryName}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${each.id}">${each.categoryName}</option>
                                                                </c:otherwise>
                                                            </c:choose>
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
                                                <img src="${dataPro.imgUrl}"/>
                                                <input type="file" accept=".jpg,.png" onchange="preview(this);" name="imgFile"/>
                                            </label>
                                            <p class="fieldNotice">提示：请上传jpg/png文件，且不超过 <i>5MB</i></p>
                                        </div>
                                    </div>
                                    <div class="formLine clearfix">
                                        <div class="formTit">描述</div>
                                        <div class="formInput fontNum">
                                            <textarea placeholder="请输入商品描述" id="description" style="line-height:2.0" maxlength="250" name="description">
                                            </textarea>
                                            <span class="num txar">0/250</span>
                                        </div>
                                    </div>
                                    <%--<div class="formLine clearfix">--%>
                                        <%--<div class="formTit">标签</div>--%>
                                        <%--<div class="formInput fontNum">--%>
                                            <%--<div class="tags clearfix">--%>
                                                <%--<c:choose>--%>
                                                    <%--<c:when test="${fn:contains(dataPro.label, '招牌菜') }">--%>
                                                        <%--<label><input type="checkbox" name="label1" checked="checked" value="招牌菜"/> 招牌菜</label>--%>
                                                    <%--</c:when>--%>
                                                    <%--<c:otherwise>--%>
                                                        <%--<label><input type="checkbox" name="label1" value="招牌菜"/> 招牌菜</label>--%>
                                                    <%--</c:otherwise>--%>
                                                <%--</c:choose>--%>
                                                <%--<c:choose>--%>
                                                    <%--<c:when test="${fn:contains(dataPro.label, '新菜') }">--%>
                                                        <%--<label><input type="checkbox" name="label2" checked="checked" value="新菜"/> 招牌菜</label>--%>
                                                    <%--</c:when>--%>
                                                    <%--<c:otherwise>--%>
                                                        <%--<label><input type="checkbox" name="label2" value="招牌菜"/> 招牌菜</label>--%>
                                                    <%--</c:otherwise>--%>
                                                <%--</c:choose>--%>
                                                <%--<c:choose>--%>
                                                    <%--<c:when test="${fn:contains(dataPro.label, '辣') }">--%>
                                                        <%--<label><input type="checkbox" name="label3" checked="checked" value="辣"/> 招牌菜</label>--%>
                                                    <%--</c:when>--%>
                                                    <%--<c:otherwise>--%>
                                                        <%--<label><input type="checkbox" name="label3" value="辣"/> 辣</label>--%>
                                                    <%--</c:otherwise>--%>
                                                <%--</c:choose>--%>
                                            <%--</div>--%>
                                            <%--<p class="fieldNotice">提示：销量较高的招牌菜会进入您的热销榜</p>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                </div>
                            </div>

                            <div class="formItem">
                                <h3>价格与库存</h3>
                                <div class="formList">
                                    <div class="formLine clearfix">
                                        <div class="likeTable">
                                            <div class="likeTh clearfix">
                                                <span><i>*</i>价格</span>
                                                <span>餐盒费</span>
                                                <span><i>*</i>库存（当前/最大）</span>
                                            </div>
                                            <div class="likeTr clearfix">
                                                <span><i class="rmb">￥</i><input type="text" class="money" name="price" value="${dataPro.price}"/></span>
                                                <span><i class="rmb">￥</i><input type="text" class="money" value="${dataPro.priceCanhe}" name="priceCanhe" /></span>
                                                <span class="big"><input type="text" value="${dataPro.inventory}" name="inventory" /><i class="xg">/</i><input type="text" value="${dataPro.inventoryCount}" name="inventoryCount" /></span>
                                            </div>
                                        </div>
                                        <%--<a href="javascript:void(0);" class="addSku addSkuAttr"><i class="fa fa-plus-circle"></i>添加规格（最多6个）</a>--%>
                                    </div>
                                </div>
                            </div>
                            <c:set value="${ fn:split(dataPro.property, ',') }" var="str" />
                            <div class="formItem">
                                <h3>属性</h3>
                                <div class="formList">
                                    <div class="formLine clearfix">
                                        <div class="description">该商品是否存在不同属性？如微辣、去冰等</div>
                                        <div class="attrCont clearfix">
                                            <c:forEach items="${str}" var="each" varStatus="i">
                                                <span><input type='text'name='propertyList[${i.index}].propertyE' value="${each}"/></span>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="formItem">
                                <h3>积分</h3>
                                <div class="formList">
                                    <div class="formLine clearfix">
                                        <div class="description">用户购买该商品后，返回多少积分?</div>
                                        <div class="attrCont clearfix"></div>
                                        <input name="integral" id="integral" style="height: 30px" autocomplete="off" placeholder="请输入积分数" value="${dataPro.integral}"/>
                                    </div>
                                </div>
                            </div>

                            <div class="subFrm">
                                <input type="submit" value="保存"/>
                                <input type="reset" onclick="openIndex()" value="取消"/>
                            </div>
                            <input name="id" value="${dataPro.id}" hidden="hidden">
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
        var layer = layui.layer;
        //监听导航点击
//        element.on('nav(demo)', function (elem) {
//            $(elem).toggleClass('layui-nav-itemed');
//            $(elem).siblings().removeClass('layui-nav-itemed');
//        });

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
        window.location.href = baseUrl + "/merchant/takeout/product/homePage";
    }

    $(function () {

        var desc = "${dataPro.description}";
        desc = desc.trim();
        $("#description").html(desc);

//        解决初始界面已输入字数为0的问题
        var nowSize = desc.length;
        $(".num.txar").html(nowSize+"/250");
    })
</script>
</html>
