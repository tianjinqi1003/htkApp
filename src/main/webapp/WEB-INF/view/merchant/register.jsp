<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/16
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>商家后台注册页面</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${staticFilePath}resource/custom/plugins/layui/css/layui.css?${date}" media="all">
    <link rel="stylesheet" href="${staticFilePath}resource/custom/css/index.css?${date}">
    <link id="layuicss-skinlayercss" rel="stylesheet"
          href="${staticFilePath}resource/custom/plugins/layui/css/modules/layer/default/layer.css?${date}" media="all">
    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <style>
        .register_content {
            width: 53%;
            margin: auto;
            background-color: #fff;
            border-radius: 3px;
        }

        body.beg-login-bg {
            position: inherit;
            overflow: auto;
        }

        .register_title {
            padding: 10px 0 20px 0;
        }

        .register {
            margin-top: 100px;
        }

        .register-form {
            padding: 20px;
            border-radius: 5px;
            width: 90%;
            max-width: 1080px;
            margin: auto;
        }

        .title {
            font-size: 21px;
            font-weight: 600;
            text-align: center;
        }

        .select-input {
            width: 120px !important;
            padding-top: 9px;
        }

        .select-input > select {
            width: 100px;
            height: 35px;
            border-radius: 2px;
            padding-left: 8px;
            font-size: 13px;
        }

        .layui-form-item > label {
            font-size: 14px;
            width: 90px;
            font-weight: 600;
        }

        .layui-form-item > .layui-input-block {
            margin-left: 120px;
        }
    </style>

</head>
<body class="beg-login-bg">
<div class="register">
    <div class="register_content shadow_">
        <form class="layui-form register-form">
            <div class="register_title">
                <h2 class="title">回头客商家注册</h2>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">店铺名称</label>
                <div class="layui-input-block">
                    <input type="text" name="shopName" lay-verify="title|required|name" autocomplete="off"
                           placeholder="请输入店铺名称"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">店铺分类</label>
                <div class="layui-input-inline select-input">
                    <select id="shopCategory" lay-verify="required">
                        <c:forEach items="${data}" var="each">
                            <option value="${each.id}">${each.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">负责人姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="nickName" lay-verify="required" placeholder="请输入负责人姓名" autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">负责人身份证</label>
                <div class="layui-input-block">
                    <input type="text" name="identity" lay-verify="identity|required" placeholder="请输入负责人身份证"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">负责人手机号</label>
                <div class="layui-input-block">
                    <input type="text" name="phone" lay-verify="phone|required" placeholder="请输入负责人手机号"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">请输入密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="pass1" name="password1" lay-verify="pass|required" placeholder="请输入密码"
                           autocomplete="off"
                           class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">确认输入密码</label>
                <div class="layui-input-inline">
                    <input type="password" id="pass2" name="password" lay-verify="pass|required|same_password"
                           placeholder="请再次输入密码" autocomplete="off"
                           class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">店铺位置</label>
                <div class="layui-input-inline select-input">
                    <select lay-ignore id='province' lay-verify="required" onchange="search(this)">
                    </select>
                </div>
                <div class="layui-input-inline select-input">
                    <select id="city" lay-ignore lay-verify="required" onchange="search(this)">
                    </select>
                </div>
                <div class="layui-input-inline select-input">
                    <select id="district" lay-ignore lay-verify="required" onchange="search(this)">
                    </select>
                </div>
                <div class="layui-input-inline select-input">
                    <select id='street' lay-ignore lay-verify="required" onchange="setCenter(this)">
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">详情</label>
                <div class="layui-input-block">
                    <input type="text" name="address" id="searchVal" style="width: 50%;display: inline-block"
                           lay-verify="required" placeholder="请输入详情地址" autocomplete="off"
                           class="layui-input">
                    <button id="searchBtn" class="layui-btn-normal layui-btn" type="button"
                            style="height: 36px;line-height: 36px;position: relative;top: -3px;left: 20px;">确认
                    </button>
                </div>
                <input name="location" hidden="hidden" id="location"/>
            </div>
            <input name="longitude" hidden="hidden" id="longitude"/>
            <input name="latitude" hidden="hidden" id="latitude"/>
            <div class="layui-form-item">
                <label class="layui-form-label">地图位置</label>
                <div class="layui-input-block">
                    <div id="container" style="height: 300px"></div>
                </div>
            </div>
            <div class="layui-form-item" style="margin-top: 20px;">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="register">确认注册</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="${staticFilePath}resource/custom/plugins/layui/layui.js?${date}"></script>
<!-- 引入jQuery -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js?${date}"></script>
<!-- 引入兼容IE9 Input placeholder插件 -->
<script type="text/javascript" src="http://www.ijquery.cn/js/jquery.placeholder.min.js?${date}"></script>
<script type="text/javascript" src="${staticFilePath}resource/custom/js/base.js?${date}"></script>
<script type="text/javascript" src="${staticFilePath}resource/custom/js/layer_.js?${date}"></script>
<script type="text/javascript" src="${staticFilePath}resource/custom/plugins/MD5/MD5.js?${date}"></script>
<script type="text/javascript"
        src="http://webapi.amap.com/maps?v=1.4.1&key=130d9c7f6d892db69a83aa96bf7c3828&plugin=AMap.DistrictSearch,AMap.Geocoder"></script>
<script type="text/javascript">
    var map, district, polygons = [], citycode;
    var citySelect = document.getElementById('city');
    var districtSelect = document.getElementById('district');
    var areaSelect = document.getElementById('street');
    var placeSearch;
    var cityCode_;
    var a1 = "", a2 = "", a3 = "", a4 = "";
    var aVal = "";
    var location_ = {};

    //    var categoryId;

    //获取输入框对象
    var inputEle = $("#searchVal");

    map = new AMap.Map('container', {
        resizeEnable: true,
        center: [116.31, 39.94],
        zoom: 3
    });

    //地址
    AMap.service(["AMap.PlaceSearch"], function () {
        placeSearch = new AMap.PlaceSearch({ //构造地点查询类
            pageSize: 5,
            pageIndex: 1,
//            city: cityCode, //城市
            map: map//,
            //panel: "panel"
        });
    });

    //行政区划查询
    var opts = {
        subdistrict: 1,   //返回下一级行政区
        showbiz: false  //最后一级返回街道信息
    };
    district = new AMap.DistrictSearch(opts);//注意：需要使用插件同步下发功能才能这样直接使用
    district.search('中国', function (status, result) {
        if (status == 'complete') {
            getData(result.districtList[0]);
        }
    });

    function getData(data, level) {
        var bounds = data.boundaries;
        if (bounds) {
            for (var i = 0, l = bounds.length; i < l; i++) {
                var polygon = new AMap.Polygon({
                    map: map,
                    strokeWeight: 1,
                    strokeColor: '#CC66CC',
                    fillColor: '#CCF3FF',
                    fillOpacity: 0.5,
                    path: bounds[i]
                });
                polygons.push(polygon);
            }
            map.setFitView();//地图自适应
        }

        //清空下一级别的下拉列表
        if (level === 'province') {
            citySelect.innerHTML = '';
            districtSelect.innerHTML = '';
            areaSelect.innerHTML = '';
            a1 = data.name;
            if (a2 !== "")
                a2 = "";
            if (a3 !== "")
                a3 = "";
            if (a4 !== "")
                a4 = ""
        } else if (level === 'city') {
            districtSelect.innerHTML = '';
            areaSelect.innerHTML = '';
            a2 = data.name;
            if (a3 !== "")
                a3 = "";
            if (a4 !== "")
                a4 = ""
        } else if (level === 'district') {
            cityCode_ = data.citycode;
            areaSelect.innerHTML = '';
            a3 = data.name
            if (a4 !== "")
                a4 = ""
        }
        aVal = a1 + a2 + a3 + a4;
        inputEle.val(a1 + a2 + a3 + a4);

        var subList = data.districtList;
        if (subList) {
            var contentSub = new Option('--请选择--');
            var curlevel = subList[0].level;
            var curList = document.querySelector('#' + curlevel);
            curList.add(contentSub);
            for (var i = 0, l = subList.length; i < l; i++) {
                var name = subList[i].name;
                var levelSub = subList[i].level;
                var cityCode = subList[i].citycode;
                contentSub = new Option(name);
                contentSub.setAttribute("value", levelSub);
                contentSub.center = subList[i].center;
                contentSub.adcode = subList[i].adcode;
                curList.add(contentSub);
            }
        }

    }

    //    绑定分类选择变化更新选择的分类id
    //    $("#shopCategory").bind("change",function(){
    //        var id = $(this).val();
    //        categoryId = id;
    //    });

    //    初始化的select的默认分类id赋值
    //    $(function () {
    //        var id = $("#shopCategory").val();
    //        categoryId = id;
    //        //这里获取的是null
    //        alert(""+categoryId);
    //    });

    <%--页面初始化的时候把下拉店铺分类填好--%>

    //    function getCategoryData() {
    //        var shopCategoryListJson;
    //        var url = baseUrl + "/merchant/shopInfo/getShopCategoryList";
    //        $.post(url, function (result) {
    //            var r = result.code;
    //            if (result.code === 0 && result.data !== null) {
    //                //获取数据成功
    //                shopCategoryListJson = result.data;
    //                for(var i=0;i<shopCategoryListJson.length;i++){
    //                    var item = shopCategoryListJson[i];
    //                    $("#shopCategory").append("<option value='" + item.id + "'>" + item.categoryName + "</option>");
    //                }
    ////                $.each(shopCategoryListJson, function (index, item) {
    ////                    $("#shopCategory").append("<option value='" + item.id + "'>" + item.categoryName + "</option>");
    ////                });
    //            } else {
    //                //后台执行异常,外卖下分类为空(显示提示信息)
    ////                layer_msg(result.data.message, 'exception');
    //            }
    //        });
    //    }
    //
    //
    //    $(function () {
    //        getCategoryData();
    //    });


    function search(obj) {
        //清除地图上所有覆盖物
        for (var i = 0, l = polygons.length; i < l; i++) {
            polygons[i].setMap(null);
        }
        var option = obj[obj.options.selectedIndex];
        var keyword = option.text; //关键字
        var adcode = option.adcode;
        district.setLevel(option.value); //行政区级别
        district.setExtensions('all');
        //行政区查询
        //按照adcode进行查询可以保证数据返回的唯一性
        district.search(adcode, function (status, result) {
            if (status === 'complete') {
                getData(result.districtList[0], obj.id);
            }
        });
    }

    function setCenter(obj) {
        var a4 = obj[obj.options.selectedIndex].innerHTML;
        if (a4 === "--请选择--")
            a4 = "";
        aVal = a1 + a2 + a3 + a4;
        inputEle.val(a1 + a2 + a3 + a4);
        map.setCenter(obj[obj.options.selectedIndex].center)
    }

    function geocoder(address, citycode) {
        var geocoder = new AMap.Geocoder({
            city: citycode, //城市，默认：“全国”
            radius: 1000 //范围，默认：500
        });
        //地理编码,返回地理编码结果
        geocoder.getLocation(address, function (status, result) {
            if (status === 'complete' && result.info === 'OK') {
                location_ = result['geocodes'][0].location;
                $("#longitude").val(location_.lng);
                $("#latitude").val(location_.lat);
                geocoder_CallBack(result);
            }
        });
    }

    function addMarker(i, d) {
        var marker = new AMap.Marker({
            map: map,
            position: [d.location.getLng(), d.location.getLat()]
        });
        var infoWindow = new AMap.InfoWindow({
            content: d.formattedAddress,
            offset: {x: 0, y: -30}
        });
        marker.on("mouseover", function (e) {
            infoWindow.open(map, marker.getPosition());
        });
    }
    //地理编码返回结果展示
    function geocoder_CallBack(data) {
        var resultStr = "";
        //地理编码结果数组
        var geocode = data.geocodes;
        for (var i = 0; i < geocode.length; i++) {
            //拼接输出html
            resultStr += "<span style=\"font-size: 12px;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\">" + "<b>地址</b>：" + geocode[i].formattedAddress + "" + "&nbsp;&nbsp;<b>的地理编码结果是:</b><b>&nbsp;&nbsp;&nbsp;&nbsp;坐标</b>：" + geocode[i].location.getLng() + ", " + geocode[i].location.getLat() + "" + "<b>&nbsp;&nbsp;&nbsp;&nbsp;匹配级别</b>：" + geocode[i].level + "</span>";
            addMarker(i, geocode[i]);
        }
//        alert(location_);
        map.setFitView();
    }

    //查找按钮
    $("#searchBtn").on("click", function () {
        const val = $("#searchVal").val();
        if (a1 === "" || a2 === "" || a3 === "") {
            layer.msg('请选择完成地址', {icon: 5, anim: 6});
            return
        } else if (aVal.length === val.length) {
            layer.msg('请输入选择完成后的详细地址', {icon: 5, anim: 6});
            return
        }
        $("#location").val(a1);
        $("#longitude").val(location_.lng);
        $("#latitude").val(location_.lat);
        //关键字查询
        geocoder(val, cityCode_);
    })
</script>
<script type="text/javascript" src="http://webapi.amap.com/demos/js/liteToolbar.js"></script>
<script type="text/javascript">
    $(function () {
        $('input, textarea').placeholder();
    });
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer,
            form = layui.form;

        //监听提交按钮
        form.on('submit(register)', function (data) {
//            alert($("#shopCategory").val());
            //逆地理编码
            geocoder(data.field.address, cityCode_);
            data.field.longitude = location_.lng;
            data.field.latitude = location_.lat;

            //把选择的分类给带上
            var categoryId = $("#shopCategory").val();
            if (categoryId == null) {
                layer_msg('请选择分类', 'warring');
                event.returnValue = false;
                return;
            }
            data.field.categoryId = categoryId;

            data.field.password = MD5(data.field.password).toUpperCase();
            const url = baseUrl + "/merchant/register";
            $.post(url, data.field, function (result, status) {
                if (status === 'success') {
                    if (result && result.code === 0) {
                        //注册成功,跳转到登陆页面
                        window.location.href = baseUrl + "/merchant/login";
                        return false;
                    } else {
                        //注册失败，显示错误信息
                        layer_msg(data.message, 'error');
                        return false;
                    }
                } else {
                    layer_msg('网络连接失败', 'exception');
                    return false;
                }
            });
            return false;
        });

        //自定义验证规则
        form.verify({
            //验证店铺名
            name: function (value, item) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                    return '用户名首尾不能出现下划线\'_\'';
                }
            },
            //验证密码是否一样
            same_password: function (value) {
                var pass1 = $("#pass1").val();
                if (value !== pass1) {
                    return '两次输入密码不一致';
                }
            }
        });
    });

    $("#searchVal").on('input', function () {
        var ele = $(this);
        console.log(ele.val().length);
        console.log(aVal.length);
        if (ele.val().length > aVal.length) {
            if (aVal === "") {
                ele.val(aVal)
            }
        } else {
            ele.val(aVal);
        }
    })
</script>
</html>
