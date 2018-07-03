<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/14
  Time: 8:38
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
            width: 30%;
        }

        .detailTab {
            padding: 0 15px;
        }

        .detailTab > .tabItem > div {
            display: inline-block;
        }

        .detailTab > .tabItem > div.tabItem-left {
            width: 55%;
        }

        .detailTab > .tabItem > div.tabItem-right {
            width: 42%;
        }

        .detailTab > .tabItem > div > span.left {
            text-align: left;
        }

        .detailTab > .tabItem > div > span.right {
            text-align: right;
        }

        .detailTab > .tabItem > div > span {
            width: 49%;
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
            margin-top: 3px;
        }

        .layui-form-label {
            padding: 7px 7px;
            font-size: 16px;
        }

        .addInputDiv > .item {
            padding: 5px 10px;
        }

        .addInputDiv > .item > label {
            font-size: 15px;
            width: 60px;
            text-align: right;
        }

        .addInputDiv > .item > div.formItemInputBlock {
            width: 400px;
            display: inline-block;
            margin-left: 15px;
        }

        .addInputDiv > .item > div.formItemInputBlock > input {
            width: 70%;
            padding: 3px;
            border-radius: 2px;
            border: 1px solid #e2e2e2;
        }

        .addInputDiv > .item > div.formItemInputBlock > select {
            width: 70%;
            padding: 3px;
            border-radius: 2px;
            border: 1px solid #e2e2e2;
        }

        .addDetailBtn {
            background-color: #0091EA;
            border: 1px solid #0091EA;
            color: #fff;
            padding: 5px;
            border-radius: 3px;
        }

        .icon-Div > span {
            cursor: pointer;
            color: #0091ea;
        }

        .animation_input {
            color: red;
            animation: inputTips 0.2s infinite ease-in-out alternate;
        }

        @keyframes inputTips {
            0% {
                border: 1px solid #e2e2e2;
            }
            100% {
                border: 1px solid red;
            }
        }

        @-webkit-keyframes inputTips {
            0% {
                border: 1px solid #e2e2e2;
            }
            100% {
                border: 1px solid red;
            }
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="crumbs">
                <div class="crumbsContiner clearfix">
                    <a href="${sysPath}merchant/groupBuy/product/homePage">商品管理</a>
                    <i class="spacer">/</i>
                    <a href="${sysPath}merchant/groupBuy/product/addGroupBuyProduct">发布团购商品</a>
                </div>
            </div>
            <div class="continerCont pdtp10">
                <div class="addFormCont">
                    <div class="formCont">
                        <form action="${sysPath}merchant/groupBuy/product/addGroupBuyProduct" method="post"
                              enctype="multipart/form-data">
                            <div class="formItem">
                                <h3>基本信息</h3>
                                <div class="formList">
                                    <div class="formLine clearfix">
                                        <div class="formTit"><i>*</i>团购标题</div>
                                        <div class="formInput fontNum">
                                            <input type="text" name="packageName" placeholder="请输入团购标题" maxlength="20"/>
                                            <span class="num">0/20</span>
                                        </div>
                                    </div>
                                    <div class="formLine clearfix">
                                        <div class="formTit"><i>*</i>使用人数</div>
                                        <div class="formInput fontNum">
                                            <input type="text" name="peopleUsedNumber" placeholder="请输入消费人数"
                                                   maxlength="20"/>
                                        </div>
                                    </div>
                                    <div class="formLine clearfix">
                                        <div class="formTit">图片</div>
                                        <div class="formInput">
                                            <label class="uploadImage">
                                                <img src="${staticFilePath}resource/custom/images/uppic.jpg"/>
                                                <input type="file" name="imgFile" accept=".jpg,.png"
                                                       onchange="preview(this);"/>
                                            </label>
                                            <p class="fieldNotice">提示：请上传jpg/png文件，且不超过 <i>5MB</i></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="formItem">
                                <h3>添加套餐商品</h3>
                                <div class="">
                                    <div class="formLine clearfix">
                                        <div>
                                            <div class="addInputDiv">
                                                <div class="item">
                                                    <label>添加商品</label>
                                                    <div class="formItemInputBlock">
                                                        <select class="selectProduct">
                                                            <c:choose>
                                                                <c:when test="${productList != null}">
                                                                    <option value="0" data-price="0.00">请选择</option>
                                                                    <c:forEach items="${productList}" var="each">
                                                                        <option value="${each.id}"
                                                                                data-price="${each.price}">${each.productName}</option>
                                                                    </c:forEach>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="0" data-price="0.00">请选择</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="item">
                                                    <label>数量</label>
                                                    <div class="formItemInputBlock">
                                                        <input placeholder="请输入数量" value="" class="quantity">
                                                    </div>
                                                </div>
                                                <div class="item">
                                                    <label>折扣</label>
                                                    <div class="formItemInputBlock">
                                                        <input placeholder="请输入折扣" value="" class="discount">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="addInputDiv-right">
                                                <div class="item" style="margin-left: 15px">
                                                    <button type="button" class="addDetailBtn">添加明细</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="detailTab" style="margin-top: 15px;">
                                            <h3 style="font-weight: 700;padding-bottom: 5px;width: 30%;display: inline-block">
                                                商品明细</h3>
                                            <span style="width: 69%;text-align: right;color: #0091EA;cursor: pointer;display: none"
                                                  class="pullDown">收起</span>
                                            <span style="text-align: center;display: block">暂无添加的商品!</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="formItem">
                                <h3>使用时间</h3>
                                <div class="formList">
                                    <div class="formLine clearfix">
                                        <div class="formTit"><i>*</i>使用时间</div>
                                        <div class="formInput fontNum">
                                            <input type="text" id="date" name="usageTime" placeholder="请选择消费时间"
                                                   maxlength="20" style="font-size: 15px;"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="formItem">
                                <h3>活动说明</h3>
                                <div class="formList">
                                    <textarea class="layui-textarea layui-hide" name="useDetails" lay-verify="content"
                                              id="LAY_demo_editor"></textarea>
                                </div>
                            </div>
                            <div class="subFrm">
                                <input type="submit" value="保存"/>
                                <input type="button" class="cancel" value="取消"/>
                                <%--<input type="button" value="获取内容" id="getContent" data-type="content">--%>
                            </div>
                        </form>
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
    layui.use(['element', 'util', 'layer', 'form', 'layedit', 'laydate'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;

        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#date'
            , type: 'date'
            , theme: '#20A0FF'
            , max: 365
            , min: 0
            , range: '到'
            , format: 'yyyy-MM-dd'
        });

        //编辑器上传图片
        layedit.set({
            uploadImage: {
                url: '',
                type: 'post'
            }
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //获取编辑内容
        var active = {
            content: function () {
                return layedit.getContent(editIndex);
            },
            text: function () {
                return layedit.getText(editIndex);
            },
            selection: function () {
                return layedit.getSelection(editIndex);
            }
        };


        //自定义验证规则
        form.verify({
            title: function (value) {
                if (value.length < 5) {
                    return '标题至少得5个字符啊';
                }
            }
            , pass: [/(.+){6,12}$/, '密码必须6到12位']
            , content: function (value) {
                layedit.sync(editIndex);
            }
        });

        //监听指定开关
        form.on('switch(switchTest)', function (data) {
            layer.msg('开关checked：' + (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
            layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });

        //监听提交
        form.on('submit(demo1)', function (data) {
            layer.alert(JSON.stringify(data.field), {
                title: '最终的提交信息'
            })
            return false;
        });

        $("#getContent").on("click", function () {
            var type = $(this).data('type');
            var result = active[type] ? active[type].call(this) : '';
            alert(result);
        })
    });

    //点击收起按钮
    $(".pullDown").on("click", function () {
        var ele = $(this);
        if (ele.text() == '收起') {
            $(".tabItem.itemTab").css("display", "none");
            $(ele).addClass("examine");
            $(ele).removeClass("packUp");
            $(".detailTab > hr:first").css("display", "none");
            $(ele).text("查看");
        } else if (ele.text() == '查看') {
            $(".tabItem.itemTab").css("display", "block");
            $(ele).addClass("packUp");
            $(ele).removeClass("examine");
            $(".detailTab > hr:first").css("display", "block");
            $(ele).text("收起");
        }
    });

    //添加明细按钮
    $(".addDetailBtn").on("click", function () {
        //取得select选中的值
        var selectEle = $(".selectProduct").children('option:selected');  //select 选中的对象
        var selectVal = $(selectEle).val();  //选中的value
        var selectName = $(selectEle).text();  //标签内的值
        var selectElePrice = $(selectEle).attr("data-price");  //价格
        var quantityEle = $(".quantity");
        var discountEle = $(".discount");
        var quantity = $(".quantity").val(); //数量值
        var discountPrice = $(".discount").val(); //折扣值
        if (parseInt(selectVal) < 0) {
            $(".selectProduct").addClass("animation_input");
            setTimeout(function () {
                $(".selectProduct").removeClass("animation_input");
            }, 1500);
            return false;
        }

        if (quantity == '' || quantity == undefined) {
            $(quantityEle).addClass("animation_input");
            setTimeout(function () {
                $(quantityEle).removeClass("animation_input");
            }, 1500);
            return false;
        }

        if (discountPrice == '' || discountPrice == undefined) {
            $(discountEle).addClass("animation_input");
            setTimeout(function () {
                $(discountEle).removeClass("animation_input");
            }, 1500);
            return false;
        }

        if (parseFloat(selectElePrice) < 1 && discountPrice < 1) {
            layer_msg("当前商品价格小于1，不能设置折扣", 'error');
            return false;
        }

        var totalPrice = selectElePrice * quantity;
        var value = accMul(totalPrice, discountPrice);
        //插入一条明细
        var children = $(".tabItem.itemTab");
        if (children.length < 1) {
            //插入条目时加入hr
            var spanEle = $(".detailTab > span:first");
            $(".detailTab > span:last").remove();
            var contentStr = '<hr/>\n' +
                '                                            <div class="tabItem itemTab">\n' +
                '                                                <div class="icon-Div">\n' +
                '                                                    <span><i class="fa fa-minus-circle" aria-hidden="true"></i></span>\n' +
                '                                                </div>\n' +
                '                                                <div class="tabItem-left">\n' +
                '                                                    <span class="left">' + selectName + '</span>\n' +
                '<input hidden="hidden" name="list[0].productName" value="' + selectName + '">' +
                '<input hidden="hidden" name="list[0].price" value="' + selectElePrice + '">' +
                '<input hidden="hidden" name="list[0].productId" value="' + selectVal + '">' +
                '                                                    <span class="right">￥' + selectElePrice + '</span>\n' +
                '                                                </div>\n' +
                '                                                <div class="tabItem-right">\n' +
                '                                                    <span class="left">x' + quantity + '</span>\n' +
                '<input name="list[0].quantity" hidden="hidden" value="' + quantity + '">' +
                '<input name="list[0].originalCost" hidden="hidden" value="' + value + '">' +
                '                                                    <span class="right">￥' + value + '</span>\n' +
                '                                                </div>\n' +
                '                                            </div>\n' +
                '                                            <hr/>' +
                '<div class="tabItem endItem">\n' +
                '                                                <div class="tabItem-left">\n' +
                '                                                    <span class="left"\n' +
                '                                                          style="font-size: 15px;font-weight: 600">总计:</span>\n' +
                '                                                </div>\n' +
                '                                                <div class="tabItem-right" style="width: 44%;">\n' +
                '                                                    <span class="left"\n' +
                '                                                          style="font-size: 15px;font-weight: 600">共' + quantity + '件</span>\n' +
                '                                                    <span class="right" style="font-size: 15px;font-weight: 600">合计:' + value + '</span>\n' +
                '                                                </div>\n' +
                '                                            </div>';
            spanEle.after(contentStr);
        } else {
            var count = children.length;
            //已有条目
            var firstEle = $(".detailTab > div:first");
            var allCount = 0;
            var allPrice = 0;
            var siblings = $(".tabItem.itemTab");
            $(siblings).each(function (index, item) {
                var itemEle = $(this).find(".tabItem-right");
                var count = parseFloat($(itemEle).find(".left").text().replace("x", ""));
                var price = parseFloat($(itemEle).find(".right").text().replace("￥", ""));
                allCount += parseInt(count);
                allPrice += parseFloat(price);
            });
            allCount += parseInt(quantity);
            allPrice += parseFloat(value);
            //在被选元素后添加
            var contentStr = '<div class="tabItem itemTab">\n' +
                '                                                <div class="icon-Div">\n' +
                '                                                    <span><i class="fa fa-minus-circle" aria-hidden="true"></i></span>\n' +
                '                                                </div>\n' +
                '                                                <div class="tabItem-left">\n' +
                '                                                    <span class="left">' + selectName + '</span>\n' +
                '                                                    <span class="right">￥' + selectElePrice + '</span>\n' +
                '<input hidden="hidden" name="list[' + count + '].productName" value="' + selectName + '">' +
                '<input hidden="hidden" name="list[' + count + '].price" value="' + selectElePrice + '">' +
                '<input hidden="hidden" name="list[' + count + '].productId" value="' + selectVal + '">' +
                '                                                </div>\n' +
                '                                                <div class="tabItem-right">\n' +
                '                                                    <span class="left">x' + quantity + '</span>\n' +
                '<input name="list[' + count + '].quantity" hidden="hidden" value="' + quantity + '">' +
                '<input name="list[' + count + '].originalCost" hidden="hidden" value="' + value + '">' +
                '                                                    <span class="right">￥' + value + '</span>\n' +
                '                                                </div>\n' +
                '                                            </div>';
            firstEle.before(contentStr);
            //改变总计数量值，总计价格值
            var changeEle = $(".tabItem.endItem > .tabItem-right");
            var countEle = changeEle.find(".left");
            var priceEle = changeEle.find(".right");
            countEle.text("共" + allCount + "件");
            priceEle.text("合计:" + allPrice.toFixed(2));
        }
        var selectEleFirstChild = $(".selectProduct").children('option:first-child');
        quantityEle.val("");
        discountEle.val("");
        selectEleFirstChild.attr("selected", "selected");
    });

    //数量
    $(".quantity").on("keyup", function () {
        var ele = $(this);
        if (!/^[0-9]*$/.test(ele.val())) {
            $(ele).val("");
        }
    });

    //折扣
    $(".discount").on("keyup", function () {
        var obj = $(this).get(0);
        //修复第一个字符是小数点 的情况.
        if (obj.value != '' && obj.value.substr(0, 1) == '.') {
            obj.value = "";
        }
        obj.value = obj.value.replace(/^0*(0\.|[1-9])/, '$1');//解决 粘贴不生效
        obj.value = obj.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
        obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
        obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            if (obj.value.substr(0, 1) == '0' && obj.value.length == 2) {
                obj.value = obj.value.substr(1, obj.value.length);
            }
        }
    });

    //为删除按钮绑定点击事件
    $(document).on("click", ".tabItem.itemTab > .icon-Div > span", function () {

        var ele = $(this).parent().parent();
        var eleChildRight = $(ele).find(".tabItem-right");
        var count = parseFloat($(eleChildRight).find(".left").text().replace("x", ""));
        var price = parseFloat($(eleChildRight).find(".right").text().replace("￥", ""));
        var endEle = $(".tabItem.endItem").find(".tabItem-right");
        ele.remove();
        var siblings = $(".tabItem.itemTab");
        if (siblings.length < 1) {
            $(".pullDown").css("display", "none");
            var hrList = $(".detailTab").find("hr");
            hrList.remove();
            var endItemEle = $(".tabItem.endItem");
            endItemEle.remove();
            //没有商品明细，显示提示信息
            var spanEle = $(".detailTab > span");
            spanEle.after('<span style="text-align: center;display: block">暂无添加的商品!</span>')
        } else {
            //重计算价格和数量
            var countEle = endEle.find(".left");
            var priceEle = endEle.find(".right");
            countEle.text("共" + parseInt(countEle.text().replace("共", "").replace("件", "") - count) + "件");
            priceEle.text("合计:" + (priceEle.text().replace("合计:", "") - price).toFixed(2));
        }
    })

    function preview(file) {
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {

                var f = file.value;
                var allExt = ".jpg,.png";
                var ext = f.split('.');//通过\分隔字符串，成字符串数组
                ext = "." + ext[ext.length - 1];
                if (allExt.indexOf(ext) > -1) {
                } else {
                    alert("文件格式有误");
                    return false;
                }
                $(".uploadImage img").attr("src", evt.target.result);
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            $(".uploadImage img").attr("style", "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=" + file.value);
        }
    }

    $(".cancel").on("click", function () {
        window.location.href = baseUrl + "/merchant/groupBuy/product/addGroupBuyProduct";
    })
</script>
</html>
