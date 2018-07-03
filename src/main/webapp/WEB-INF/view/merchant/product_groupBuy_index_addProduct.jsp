<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/15
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>团购添加商品页面</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
    <style>
        @media screen and (min-width: 1300px) and (max-width: 1366px) {
            .goodsItem {
                width: 33.333333%;
            }

            .childCat {
                width: 82% !important;
            }
        }

        @media screen and (min-width: 1900px) and (max-width: 1920px) {
            .goodsItem {
                width: 20%;
            }

            .childCat {
                width: 87.5% !important;
            }
        }

        .groupBuyGs {
            /*margin-left: 200px;*/
            height: 100%;
            overflow: hidden;
        }

        .groupBuyGs {
            background-color: #f3f3f4;
        }

        .groupBuyGs-top {
            padding-top: 80px;
        }

        .groupBuyContent {
            width: 60%;
            margin: auto;
            background-color: #fff;
        }

        .groupBuyContent {
            margin-top: 20px;
            border-radius: 5px;
        }

        .groupBuyContent > .layui-form {
            width: 55%;
        }

        .groupBuyContentChild {
            width: 80%;
            margin: auto;
            padding-top: 20px;
        }

        .layui-input-block {
            margin-left: 80px;
        }

        .layui-form-item > .layui-input-block > input {
            width: 55%;
        }

        .detailTab > .tabItem > div {
            display: inline-block;
        }

        .detailTab > .tabItem > div.tabItem-left {
            width: 55%;
        }

        .detailTab > .tabItem > div.tabItem-right {
            width: 44%;
        }

        .detailTab > .tabItem > div > span.left {
            float: left;
        }

        .detailTab > .tabItem > div > span.right {
            float: right;
        }

        .tabItem-right {
            padding-left: 90px;
        }

        .productDetail {
            padding-bottom: 5px;
            text-align: left;
            font-size: 18px;
            width: 115px;
            font-weight: bold;
        }

        .tabItem {
            padding: 3px 0;
        }

        .endItem {
            margin-top: 20px;
        }

        .layui-form-label {
            padding: 7px 7px;
            font-size: 16px;
        }
    </style>

    <style>
        .shoContent {
            background-color: #fff;
            margin: auto;
            padding: 30px;
        }

        .layui-unselect.layui-form-select {
            width: 40%;
            display: inline-block;
        }

        .selectProductPrice {
            display: inline-block;
            margin-left: 20px;
        }

        .selectProductPrice > span.priceContentDiv > span {
            font-size: 16px;
            font-weight: 500;
        }

        .selectProductPrice > span.priceContentDiv > span.priceText {
            color: red;
        }

    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="crumbs" style="top:50px;">
                <div class="crumbsContiner clearfix">
                    <a href="${sysPath}merchant/groupBuy/product/homePage">商品管理</a>
                    <i class="spacer">/</i>
                    <a href="">添加商品</a>
                </div>
            </div>
            <div class="shoContent shadow_" style="margin-top: 75px;width: 80%;">
                <div class="topContent" style="padding-bottom: 30px;">
                    <div class="layui-form">
                        <div class="layui-form-item">
                            <label class="layui-form-label">商品类型</label>
                            <div class="layui-input-block">
                                <input type="radio" name="pType" value="0" title="外卖商品" lay-filter="radio">
                                <input type="radio" name="pType" value="2" title="自助商品" checked lay-filter="radio">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">选择商品</label>
                            <div class="layui-input-block selectParent" id="type-2">
                                <select name="productId" class="productId" lay-filter="selected" lay-verify="required" style="width: 40%;">
                                    <c:choose>
                                        <c:when test="${buffetFoodProductList != null}">
                                            <option value="0">请选择</option>
                                            <c:forEach items="${buffetFoodProductList}" var="each">
                                                <option value="${each.id}"
                                                        data-price="${each.price}">${each.productName}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="0">请选择</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                <div class="selectProductPrice">
                                        <span class="priceContentDiv">
                                            <span class="priceText"></span>
                                        </span>
                                </div>
                            </div>
                            <div class="layui-input-block selectParent" id="type-0" style="display: none">
                                <select name="productId" class="productId" lay-filter="selected" lay-verify="required"
                                        style="width: 40%;">
                                    <c:choose>
                                        <c:when test="${takeoutProductList != null}">
                                            <option value="0">请选择</option>
                                            <c:forEach items="${takeoutProductList}" var="each">
                                                <option value="${each.id}"
                                                        data-price="${each.price}">${each.productName}</option>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="0">请选择</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                <div class="selectProductPrice">
                                        <span class="priceContentDiv">
                                            <span class="priceText"></span>
                                        </span>
                                </div>
                            </div>
                        </div>
                        <input hidden="hidden" name="productName" id="productName" value="">
                        <div class="layui-form-item">
                            <label class="layui-form-label">加入价格</label>
                            <div class="layui-input-block">
                                <input type="text" name="price" id="price" autocomplete="off"
                                       placeholder="请输入正常价格" class="layui-input" style="width: 40%;">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="enterJoin">确认加入</button>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="downContent" style="padding-top: 30px;padding-bottom: 100px;">
                    <c:if test="${buyPackageProductList != null}">
                        <div>
                            <span style="font-size: 16px;font-weight: 700">加入团购的商品清单列表</span>
                        </div>
                    </c:if>
                    <div class="dataTable" style="padding-bottom: 30px;">
                        <c:choose>
                            <c:when test="${buyPackageProductList != null}">
                                <table class="layui-table">
                                    <colgroup>
                                        <col width="150">
                                        <col width="200">
                                        <col>
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th>商品名</th>
                                        <th>价格</th>
                                        <th>所属类型</th>
                                        <th>加入时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${buyPackageProductList}" var="each">
                                        <tr>
                                            <td>${each.productName}</td>
                                            <td>${each.price}</td>
                                            <td>${each.pType == 0 ? "外卖" : "自助点餐"}商品</td>
                                            <td>${each.gmtCreate}</td>
                                            <td><a class="deleteBtn" data-id="${each.id}">删除</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <div class="showNoDataText" style="text-align: center;">
                                    <div class="noOrder" style="padding-top: 20px;">
                                        <div class="noOrder-content">
                                            <div class="noOrder-content-div">
                                                <div class="wane">
                                                    <i class="fa fa-file-text-o wane-icon" aria-hidden="true"
                                                       style="font-size: 100px;line-height: 170px;color: #fff"></i>
                                                </div>
                                                <p class="noOrder-p">暂无加入团购的商品</p>
                                                <span class="noOrder-span">暂时没有加入团购列表的其它商品!</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <c:if test="${buyPackageProductList != null}">
                        <div id="page"></div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
<%@include file="js.jsp" %>
<script type="text/javascript">
    layui.use(['element', 'util', 'layer', 'form', 'layedit', 'laydate', 'laypage'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate
            , laypage = layui.laypage;

        //日期
        laydate.render({
            elem: '#date'
            , type: 'date'
            , theme: '#20A0FF'
            , max: 0
            , min: -365
            , range: '到'
            , format: 'yyyy-MM-dd'
        });

        //不显示首页尾页
        laypage.render({
            elem: 'page'
            , count: ${page == null ? 0: page.pages * 10}
            , curr: ${page == null ? 1 : page.pageNum}
            , theme: '#1E9FFF'
            , first: false
            , last: false
            , jump: function (obj, first) {
                if(!first){
                    window.location.href = baseUrl + "/merchant/groupBuy/product/addProductPage?pageNum="+obj.curr
                }
            }
        });

        //监听radio
        form.on('radio(radio)', function (data) {
            if (data.value == '0') {
                //外卖
                $("#type-2").css("display", "none");
                $("#type-0").css("display", "block");
            } else if (data.value == '2') {
                //自助点餐
                $("#type-0").css("display", "none");
                $("#type-2").css("display", "block");
            }
        });

        //监听select
        form.on('select(selected)', function (data) {
            var selectEle = $(data.elem).children('option:selected');
            var productNameEle = $("#productName");
            var selectVal = $(selectEle).text();
            productNameEle.val(selectVal);
            if (parseInt(data.value) > 0) {
                var selectPrice = $(selectEle).attr("data-price");
                $(".selectProductPrice > .priceContentDiv > .priceText").html('<span style="color: black">原价:' + selectPrice + "元" + '</span>');
            } else {
                $(".selectProductPrice > .priceContentDiv > .priceText").html('');
            }
        });

        //监听提交
        form.on('submit(enterJoin)', function (data) {
            var productId = $(".productId").children('option:selected').val();
            if(parseInt(productId) > 0){
                data.field.productId = productId;
            }
            const url = baseUrl + "/merchant/groupBuy/product/addProductPage";
            var params = data.field;
            $.post(url, params, function (result, status) {
                if (status === 'success') {
                    if (result && result.code === 0) {
                        window.location.href = baseUrl + "/merchant/groupBuy/product/addProductPage";
                    } else {
                        layer_msg(result.message, 'error');
                    }
                } else {
                    layer_msg("网络连接错误", 'exception')
                }
            });
            return false;
        });
    });

    //表格删除操作按钮绑定点击事件
    $(".deleteBtn").on("click", function () {
        //获取产品id
        var id = $(this).attr('data-id');
        if(id === undefined || id === ''){
            return false;
        }
        //表格删除条目
        const url = baseUrl + "/merchant/groupBuy/product/deleteAddProduct";
        var params = {
            id: id
        };
        $.post(url, params, function (result, status) {
            if(status === 'success'){
                if(result && result.code === 0){
                    window.location.href = baseUrl + "/merchant/groupBuy/product/addProductPage";
                }else {
                    layer_msg(result.message, 'error');
                }
            }else {
                layer_msg('网络连接异常', 'exception');
            }
        })

    })
</script>
</html>
