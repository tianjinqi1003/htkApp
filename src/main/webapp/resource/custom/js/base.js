var baseUrl = getRootPath();

//js获取项目根路径  http://localhost:8081/htkApp
function getRootPath() {
    //获取当前网址，如： http://localhost:8081/htkApp/index.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： htkApp/index.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8081
    var localhostPath = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/htkApp
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

    return (localhostPath + projectName);
}

var goEasyKey = getGoEasyKey();

//返回goEasy_key值
function getGoEasyKey() {
    return "BC-71831bbaf07c4ecba5a026bec6cc5f4e";
}

//适应IE9浏览器中placeholder显示问题
$(function () {
    $('input, textarea').placeholder();
});

//共用post 方法
function http_post(params) {
    //打开loading
    layer_loadingOpen(1);
    if (params === undefined || params === null || params === '') {
        return false;
    } else {
        $.post(params.url, params.params_, function (result, status) {
            if (status === 'success') {
                //关闭loading
                layer_loadingClose();
                //判断返回码－code
                if (result.code === 2) {
                    layer_msg(result.message, 'success');
                    //倒计时跳转
                    setTimeout(function () {
                        window.location.href = baseUrl + result.url;
                    }, 1000)
                } else if (result.code === -1) {
                    //错误  显示错误信息
                    layer_msg(result.message, 'error');
                    return false;
                } else {
                    //显示错误信息
                    layer_msg(result.message, 'error');
                    return false;
                }
            } else {
                //关闭loading
                layer_loadingClose();
                layer_msg("网络连接失败,请稍后再试", 'exception');
                return false;
            }
        }, "json");
        return false;
    }
}

//退出登陆按钮点击
$(".logout_").on('click', function () {
    var type = $(this).attr("data-type");
    var params = {
        tips: "确定要退出吗？",
        btn1: "确定",
        btn2: "取消",
        params: {
            url: baseUrl + "/"+type+"/logout",
            params_: {}
        }
    };
    //出现提示窗口(并传入请求数据和接口,退出请求取消不做处理)
    layer_confirm(params);
});

//向前偏移日期函数
function offsetDateMethod(date, val) {
    if (val > 0) {
        //向后偏移　加
        return new Date(date.getTime() - (val * 24 * 60 * 60 * 1000));
    } else {
        //向前偏移  减
        return new Date(date.getTime() - ((Math.abs(val)) * 24 * 60 * 60 * 1000));
    }
}

//改变店铺状态(1是正常营业，0是停止营业)
$(".changeState").on("click", function () {
    var stateId = $(this).attr("data-id");
    // var userId = $(".userId").val();
    var url = baseUrl + "/merchant/changeShopState_Page";
    var params = {
        stateId: stateId
    };
    var stateText = $(".status");
    var openState = $(".openState");
    var stopState = $(".stopState");
    if (stateId === "0") {
        //点击了停止营业
        $.post(url, params, function (result, status) {
            if (status === 'success') {
                if (result && result.code === 0) {
                    //改变页面DOM对象
                    $(stateText).html('已停止营业<i class="fa fa-angle-down"></i></a>');
                    //改变按钮状态
                    if (!stopState.hasClass("cur")) {
                        stopState.addClass("cur");
                    }
                    if (openState.hasClass("cur")){
                        openState.removeClass("cur");
                    }
                } else {
                    //显示提示信息
                    layer_msg(result.message, 'error');
                }
            } else {
                layer_msg("网络连接失败", 'exception');
            }
        }, "json");
    } else if (stateId === "1") {
        //点击了营业中
        $.post(url, params, function (result, status) {
            if (status === 'success') {
                if (result && result.code === 0) {
                    //改变页面DOM对象
                    $(stateText).html('营业中<i class="fa fa-angle-down"></i></a>');
                    //改变按钮状态
                    if (!openState.hasClass("cur")) {
                        openState.addClass("cur");
                    }
                    if(stopState.hasClass("cur")){
                        stopState.removeClass("cur");
                    }
                } else {
                    //显示提示信息
                    layer_msg(result.message, 'error');
                }
            } else {
                layer_msg("网络连接失败", 'exception');
            }
        }, "json");
    }
});

//全局搜索功能
$(".globalSearch").on("click", function () {
    window.location.href = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder"
});

//播放声音
function playSounds(src) {
    var auto = document.createElement("audio");
    auto.preload = "auto";
    auto.src = src;
    auto.play();
}

//加密请求参数
function encryptMethod(val) {
    return Base64.encode(val);
}


//推送消息类型
//外卖订单消息====    新订单消息,催单消息,取消单消息   t
//处理执行：根据类型加跳转参数访问后台接口
//团购订单消息====    新订单、消费        g
//处理执行：跳转到相关页面
//自助点餐订单消息====   新订单、换菜、退菜、加菜    b
//新订单是跳转到相关页面
//其它则是播放声音提醒

//消息状态  消息有两种状态，已读、未读
//1.点击消息提示窗口，改变消息状态为已读
//2.未点击提示窗口，10秒后自动关闭，消息为未读状态，可以右上角点击消息图标查看消息总列表

//消息处理中转
function tipsHandle(obj, baseUrl_) {
    if (obj !== null && obj !== undefined) {
        if (obj.classifyId === 't') {
            //外卖
            tOrderHandle(obj.statusCode, obj.message, baseUrl_);
        } else if (obj.classifyId === 'g') {
            //团购
            gOrderHandle(obj.statusCode, obj.message, baseUrl_);
        } else if (obj.classifyId === 'b') {
            //自助点餐
            bOrderHandle(obj.statusCode, obj.message, baseUrl_)
        }
    } else {
        return false;
    }
}


//外卖订单推送处理
function tOrderHandle(statusCode, message, soundsUrl_) {
    var title = "";
    var url = "";
    var delay = 20000;
    //查看当前页面，如果在订单页面则自动刷新
    const curUrl = window.document.location.href;
    //如果停留在外卖页面
    if(curUrl.indexOf('merchant/takeout/order/realTimeTakeoutOrder') > 0){
        switch (statusCode){
            case 1:
                //新订单
                url = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=0";
                title = "新订单推送";
                playSounds(soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3");
                orderPushHint(title, message, url, delay);
                // playSounds(soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3");
                break;
            case 2:
                //已接单
                // playSounds(soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3");
                break;
            case 3:
                //配送订单
                // playSounds(soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3");
                break;
            case 4:
                //已收货消息
                url = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=3";
                title = "已收货消息";
                playSounds( soundsUrl_ + "resource/custom/sounds/7815.wav");
                orderPushHint(title, message, url, delay)
                // playSounds(soundsUrl_ + "resource/custom/sounds/7815.wav");
                break;
            case 5:
                //取消订单
                url = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=5";
                title = "订单取消消息";
                playSounds(soundsUrl_ + "resource/custom/sounds/8858.wav");
                orderPushHint(title, message, url, delay);
                // playSounds(soundsUrl_ + "resource/custom/sounds/8858.wav");
                break;
            case 6:
                //催单消息
                url = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=4";
                title = "催单消息";
                playSounds(soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3");
                orderPushHint(title, message, url, delay)
                // playSounds(soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3");
                break;
            default :
                break;
        }
        // window.location.reload();
    }else {
        if (statusCode === 1) {
            //新订单
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3";
            url = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=0";
            title = "新订单推送";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        } else if (statusCode === 6) {
            //催单消息
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3";
            url = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=4";
            title = "催单消息";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        } else if (statusCode === 4) {
            //已收货消息
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/7815.wav";
            url = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=3";
            title = "已收货消息";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        } else if (statusCode === 2) {
            //已接单
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3";
            url = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=1";
            title = "已接单消息";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        } else if (statusCode === 3) {
            //配送订单
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3";
            url = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=2";
            title = "已配送消息";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        } else if (statusCode === 5) {
            //取消订单
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/8858.wav";
            url = baseUrl + "/merchant/takeout/order/realTimeTakeoutOrder?statusCode=5";
            title = "订单取消消息";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        }

    }
}

//团购订单推送处理
function gOrderHandle(statusCode, message, soundsUrl_) {
    var title = "";
    var url = "";
    var delay = 20000;
    //查看当前页面，如果在订单页面则自动刷新
    const curUrl = window.document.location.href;
    if(curUrl.indexOf('merchant/groupBuy/getGroupBuyOrderQueryPage') > 0){
        switch (statusCode){
            case 10:
                playSounds(soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3");
                break;
            case 11:
                playSounds(soundsUrl_ + "resource/custom/sounds/7815.wav");
                break;
            case 12:
                playSounds(soundsUrl_ + "resource/custom/sounds/8858.wav");
                break;
            default :
                break;
        }
        window.location.reload();
    }else {
        if(statusCode === 10){
            //下单成功
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3";
            url = baseUrl + "/merchant/groupBuy/getGroupBuyOrderQueryPage";
            title = "新订单推送";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        }else if(statusCode === 11) {
            //消费成功
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/7815.wav";
            url = baseUrl + "/merchant/groupBuy/getGroupBuyOrderQueryPage";
            title = "团购订单消费";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        }else if(statusCode === 12){
            //取消成功
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/8858.wav";
            url = baseUrl + "/merchant/groupBuy/getGroupBuyOrderQueryPage";
            title = "团购订单取消";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        }
    }
}

//自助点餐订单推送处理
function bOrderHandle(statusCode, message, soundsUrl_) {
    var title = "";
    var url = "";
    var delay = 20000;
    //查看当前页面，如果在订单页面则自动刷新
    const curUrl = window.document.location.href;
    if(curUrl.indexOf('merchant/groupBuy/getGroupBuyOrderQueryPage') > 0){
        switch (statusCode){
            case 0:
                break;
            case 1:
                playSounds(soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3");
                break;
            case 2:
            	playSounds(soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3");
                break;
            case 3:
                playSounds(soundsUrl_ + "resource/custom/sounds/7815.wav");
                break;
            case 4:
                playSounds(soundsUrl_ + "resource/custom/sounds/8858.wav");
                break;
            default :
                break;
        }
        window.location.reload();
    }else {
        //0 初始订单   1下单成功   2已结算
        if(statusCode === 0){
            //初始订单下单
            // url = baseUrl + "/merchant/buffetFood/order/new";
            // title = "自助点餐订单初始下单";
            // playSounds(soundsUrl);
            // orderPushHint(title, message, url, delay)
        }else if(statusCode === 1){
            //下单成功
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3";
            url = baseUrl + "/merchant/buffetFood/order/new";
            title = "新自助点餐订单";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        }else if(statusCode === 2){
            //已结算
        	const soundsUrl = soundsUrl_ + "resource/custom/sounds/xinDuanXiaoXi.mp3";
        	 url = baseUrl + "/merchant/integral/seatOrder";
        	 title = "新订座订单";
        	  orderPushHint(title, message, url, delay)
        }else if(statusCode === 4){
            //催单
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/7815.wav";
            url = baseUrl + "/merchant/buffetFood/order/reminder";
            title = "自助点餐订单催单";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        }else if(statusCode === 3){
            //调单
            const soundsUrl = soundsUrl_ + "resource/custom/sounds/8858.wav";
            url = baseUrl + "/merchant/buffetFood/order/edit";
            title = "自助点餐订单调单";
            playSounds(soundsUrl);
            orderPushHint(title, message, url, delay)
        }
    }
}


//正则表达式
//匹配只能是整数数字
function onlyNumber(value) {
    var reg = "^[1-9]//d*$";
    if (!reg.test(value)) {
        return false;
    } else {
        return true;
    }
}

//乘法函数，用来得到精确的乘法结果
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：accMul(arg1,arg2)
//返回值：arg1乘以arg2的精确结果
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length
    } catch (e) {
    }
    try {
        m += s2.split(".")[1].length
    } catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
}

//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accAdd(arg1, arg2) {
    var r1, r2, m;
    try {
        r1 = arg1.toString().split(".")[1].length
    } catch (e) {
        r1 = 0
    }
    try {
        r2 = arg2.toString().split(".")[1].length
    } catch (e) {
        r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2));
    return (arg1 * m + arg2 * m) / m
}