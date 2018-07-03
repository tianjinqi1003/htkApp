<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/14
  Time: 8:33
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

        .spendingText {
            /*padding-left: 15px;*/
            /*line-height: 15px;*/
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="childCat clearfix tab">
                <a href="" class="cur">商品列表</a>
                <a href="${sysPath}merchant/groupBuy/product/addProductPage" style="padding-left: 75%;">添加商品</a>
                <a href="${sysPath}merchant/groupBuy/product/addGroupBuyProduct">发布团购</a>
            </div>
            <div class="continerCont groupBuyGs-top">
                <div class="goods clearfix">
                    <div class="groupBuyGs">
                        <c:choose>
                            <c:when test="${data != null}">
                                <div class="goodsContiner clearfix" style="display: block">
                                    <c:forEach items="${data}" var="each">
                                        <div class="goodsItem">
                                            <div class="goodsDetail">
                                                <div class="info clearfix">
                                                    <div class="fleft">
                                                        <h3>${each.packageName}</h3>
                                                        <p class="price">￥${each.retailPrice}</p>
                                                        <p style="margin-left: 15px;line-height: 35px;color: #999">${each.validityTime}</p>
                                                    </div>
                                                    <div class="fright productPicture" id="productPicture" data-pId="${each.id}">
                                                        <img src="${each.imgUrl}" style="cursor: pointer;height: 78px;"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="noOrder">
                                    <div class="noOrder-content">
                                        <div class="noOrder-content-div">
                                            <div class="wane">
                                                <i class="fa fa-file-text-o wane-icon"
                                                   aria-hidden="true"></i>
                                            </div>
                                            <p class="noOrder-p">暂无团购商品</p>
                                            <span class="noOrder-span">暂时没有团购商品,点击发布团购发布商品吧!</span>
                                        </div>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${data != null}">
                            <div style="margin-right: 150px">
                                <div id="pages">
                                </div>
                            </div>
                        </c:if>
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
    layui.use(['element', 'util', 'layer', 'laydate', 'laypage'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        var date = layui.laydate;
        var page = layui.laypage;

        //常规用法
        date.render({
            elem: '#startDate'
        });

        date.render({
            elem: '#endDate'
        })

//不显示首页尾页
        page.render({
            elem: 'pages'
            , count: ${pageInfo == null ? 0: pageInfo.pages * 10}
            , groups: 3
            , curr: ${pageInfo == null ? 1 : pageInfo.pageNum} //获取起始页
            , prev: "<"
            , next: ">"
            , theme: '#1E9FFF'
            , first: false
            , last: false
        });
    });

    $(".productPicture").on("mouseover", function () {
        //为产品图片加tips
        layer.tips('点击图片编辑团购商品', this, {
            tips: 1
        });
    })

    $(".productPicture").on("click", function () {
        var pId = $(this).attr("data-pId");
        window.location.href = baseUrl + "/merchant/groupBuy/product/getGroupBuyDetailById?id="+pId;
    })
</script>
</html>