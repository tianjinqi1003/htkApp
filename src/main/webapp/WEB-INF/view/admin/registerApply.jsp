<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/17
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>申请列表</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
    <style>
        .layui-body {
            background-color: #f3f3f4;
        }

        .leftChart {
            height: 300px;
        }

        .rightChart {
            height: 300px;
        }

        .body-content {
            padding: 15px;
            background-color: #fff;
            margin: 15px;
        }

        .leftTips > h2, .rightTips > h2 {
            color: orange;
            font-size: 15px;
        }

        .leftTips, .rightTips {
            padding: 0 10px;
        }

        .dynamic.item {
            margin-left: -10px;
            padding: 10px 30px;
        }

        .body-content {
            height: auto;
        }

        .page {
            text-align: right;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body">
        <div class="body-content">
            <div class="content">
                <div class="data-table">
                    <c:choose>
                        <c:when test="${data != null}">
                            <table class="layui-table">
                                <thead>
                                <tr>
                                    <th>负责人手机号</th>
                                    <th>负责人身份证号</th>
                                    <th>店铺名</th>
                                    <th>店铺位置</th>
                                    <th>申请时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${data}" var="each">
                                    <tr>
                                        <td class="phone">${each.phone}</td>
                                        <td class="identity">${each.identity}</td>
                                        <td class="shopName">${each.shopName}</td>
                                        <td class="address">${each.address}</td>
                                        <td class="applyTime">${each.applyTime}</td>
                                        <%--<td><a class="distribution b" data-type="category">分配</a><input--%>
                                                <%--hidden="hidden"/>--%>
                                        <%--</td>--%>
                                        <td class="open"><a href="javascript:void(0)" data-id="${each.accountShopId}">开通</a></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p>暂时没有数据</p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:if test="${data != null}">
                    <div class="pageDiv">
                        <div id="page" class="page"></div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
<%@include file="js.jsp" %>
<script>
    var form;
    var pEle;
    //JavaScript代码区域
    layui.use(['element', 'laypage', 'layer', 'form'], function () {
        var element = layui.element;
        var page = layui.laypage;
        var layer = layui.layer;
        form = layui.form;

        //不显示首页尾页
        page.render({
            elem: 'page'
            , count: ${pageInfo == null ? 0: pageInfo.pages * 10}
            , curr: ${pageInfo == null ? 1 : pageInfo.pageNum} //获取起始页
            , groups: 3
            , prev: "<"
            , next: ">"
            , theme: '#1E9FFF'
            , first: false
            , last: false
            , jump: function (obj, first) {
            }
        });
    });

    /**
     * 如果分配为外卖，则只能分配外卖下的分类，团购同样,
     * 如果只有自助点餐，则没有分类
     */
    //分配弹窗
    $(".distribution").on("click", function () {
        var ele = $(this);
        var eleType = ele.attr("data-type");
        if (eleType === 'permissions') {
            //权限
            pEle = ele;
            Permissions();
        } else if (eleType === 'category') {
            //根据当前元素获取父元素的同级元素
            var findEle = $(this).parent().prev();
            //获取隐藏的input 元素
            var input = findEle.find("input");
            var value = $(input).val();
            if (value.length <= 0) {
                layer.msg('请先分配权限', {icon: 5, anim: 6})
                return;
            }
            //判断是显示外卖分类还是团购分类?
            var a;
            if (value === 10001) {
                //外卖
                a = 0;
            } else if (value === 1002) {
                //团购
                a = 1;
            } else if (value === 10004 || value === 10007) {
                //显示两个分类
                a = 2;
            }
            //分类
            category(a);
        }
    });

    //店铺权限
    function Permissions() {
        //调后台拿角色数据
        const url = baseUrl + "/admin/getPermissions";
        var params_ = {};
        $.post(url, params_, function (result, status) {
            if (status === 'success') {
                if (result && result.code === 0) {
                    var child = "";
                    $(result.data).each(function (index, item) {
                        child += "<option value=\"" + item.id + "\">" + item.roleDes + "</option>\n";
                    })
                    var contentStr = "<div>" +
                        "<form class=\"layui-form\" style='padding: 20px 30px 10px 0'>\n" +
                        "  <div class=\"layui-form-item\">\n" +
                        "    <label class=\"layui-form-label\">店铺权限</label>\n" +
                        "    <div class=\"layui-input-block\">\n" +
                        "      <select name=\"interest\" id='permission_' lay-filter=\"aihao\">\n" +
                        "        <option value=\"0\">-- 请选择 --</option>\n" +
                        child +
                        "      </select>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "  <div class=\"layui-form-item\">\n" +
                        "    <div class=\"layui-input-block\">\n" +
                        "      <button class=\"layui-btn permissionsBtn\" type='button'>确定</button>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "</form>" +
                        "</div>";
                    var params = {
                        title: ["分配店铺权限", "background-color:#1E9FFF;color:#fff"],
                        content: contentStr,
                        offset: '250px',
                        fixed: true,
                        area: {width: '380px', height: '300px'}
                    };
                    layer_pageTier(params);
                    form.render();
                } else {
                    layer_msg('exception', data.message);
                }
            } else {
                layer_msg('exception', '网络连接失败');
            }
        });
    }

    //店铺权限确认
    $(document).on("click", ".permissionsBtn", function () {
        var select = $("#permission_");
        //获取选中值
        var selVal = select.children('option:selected').val();
        if (pEle !== undefined) {
            var curPhone = $(pEle).parent().siblings('.phone').text();
            const url = baseUrl + "/admin/enterPermissions";
            var params = {
                phone: curPhone,
                roleId: selVal
            };
            //请求后台
            $.post(url, params, function (result, status) {
                if (status === 'success') {
                    if (result && result.code === 0) {
                        var a = pEle.parent().find('.distribution.a');
                        $(a).find('input').val(selVal);
                        layer_msg("success", result.message)
                        window.location.reload();
                    } else {
                        layer_msg("error", result.message)
                        setTimeout(function () {
                            window.location.reload();
                        },2000)
                    }
                } else {
                    layer_msg("exception", "网络连接异常");
                }
            })
        }
    });

    //店铺分类
    function category(type) {
        var mark = type;
        const url = baseUrl + "/admin/category/getCategoryListJsonData";
        $.post(url, {mark: mark}, function (result, status) {
            if (status === 'success') {
                if (result && result.code === 0) {
                    var content = "";
                    $(result.data).each(function (index, item) {
                        var childOption = "";
                        $(item.takeoutCategory).each(function (index_, item_) {
                            childOption += "<option value=\"" + item_.id + "\">" + item_.categoryName + "</option>\n"
                        })
                        content += "<div class=\"layui-form-item\">\n" +
                            "       <label class=\"layui-form-label\">分类选择</label>\n" +
                            "       <div class=\"layui-input-inline\">\n" +
                            "           <select name=\"quiz1\">\n" +
                            "               <option value=\"\">--一级分类--</option>\n" +
                            childOption +
                            "           </select>\n" +
                            "       </div>\n" +
                            "    <div class=\"layui-input-inline\">\n" +
                            "      <select name=\"quiz2\">\n" +
                            "        <option value=\"\">--二级分类--</option>\n" +
                            "      </select>\n" +
                            "    </div>" +
                            "</div>"
                    })
                    var contentStr = "<div>" +
                        "<form class=\"layui-form\" style='padding: 20px 30px 10px 0'>\n" +
                        content +
                        "  <div class=\"layui-form-item\">\n" +
                        "    <div class=\"layui-input-block\">\n" +
                        "      <button class=\"layui-btn\" type='button' lay-submit lay-filter=\"*\">确定</button>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "</form>" +
                        "</div>";
                    var params = {
                        title: ["分配店铺分类", "background-color:#1E9FFF;color:#fff"],
                        content: contentStr,
                        offset: '250px',
                        fixed: true,
                        area: {width: '380px', height: '500px'}
                    };
                    layer_pageTier(params);
                    form.render();
                } else {
                    layer_msg("error", data.message)
                }
            } else {
                layer_msg("exception", "网络连接异常")
            }
        });
    }

    $(".open").on("click", function () {
        const url = baseUrl + "/admin/changeAccountState";
        const id = $(this).children().attr("data-id");
        $.post(url, {id: id}, function (result) {
            if(result && result.code == 0){
                window.location.reload();
            }else {
                layer_msg("失败", 'error');
                setTimeout(function () {
                    window.location.reload();
                },2000)
            }
        })
    })
</script>
</body>
</html>
