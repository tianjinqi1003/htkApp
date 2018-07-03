//订单推送提示
function orderPushHint(title, message, url, delay) {
    PNotify.prototype.options.styling = "bootstrap3";
    new PNotify({
        title: title,
        text: message,
        icon: 'glyphicon glyphicon-info-sign',
        hide: true,
        confirm: {
            confirm: true,
            buttons: [{
                text: '查看',
                addClass: 'btn-primary',
                click: function (notice) {
                    //点击后，改变消息状态为已读
                    // var reqUrl = "";
                    // var params = {};
                    // $.post(reqUrl,params,function (result,status) {
                    //     if(status === 'success'){
                    layer_msg("正在执行跳转页面中。。。", "success");
                    setTimeout(function () {
                        window.location.href = url;
                    }, 1500)
                    // }else {
                    //     layer_msg("网络连接失败",'exception');
                    // }
                    // });
                }
            }, null]
        },
        buttons: {
            closer: false,
            sticker: false
        },
        history: {
            history: false
        },
        delay: delay,
        mouse_reset: true   //鼠标悬浮时，时间重置
    });
}