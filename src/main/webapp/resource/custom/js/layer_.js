//layer设定公共方法

//提示
function layer_msg(data, type) {
    if (type === 'success') {
        //成功 1
        layer.msg(data, {icon: 1});
    } else if (type === 'exception') {
        //异常(叉号) 2
        layer.msg(data, {icon: 2});
    } else if (type === 'warring') {
        //警告 0
        layer.msg(data, {icon: 0});
    } else if (type === 'info') {
        //疑问 3
        layer.msg(data, {icon: 3});
    } else if (type === 'lock') {
        //锁定 4
        layer.msg(data, {icon: 4});
    } else if (type === 'error') {
        //错误 5
        layer.msg(data, {icon: 5});
    }
}

//加载loading层
function layer_loadingOpen(type) {
    if (type === 1) {
        //加载层-默认风格
        layer.load();
    } else if (type === 2) {
        //加载层-风格2
        layer.load(1);
    } else if (type === 3) {
        //加载层-风格3
        layer.load(2);
    } else if (type === 4) {
        //加载层-风格4
        layer.msg('处理中', {
            icon: 16
            , shade: 0.01
        });
    }
}

//关闭加载层
function layer_loadingClose(params) {
    if (!params) {
        layer.closeAll('loading');
    } else {
        //根据index 关闭弹窗
        layer.close(params);
    }
}

//询问框
function layer_confirm(params) {
    layer.confirm(params.tips, {
        btn: [params.btn1,params.btn2] //按钮
    }, function(){
        //异步调接口退出
        http_post(params.params);
    }, function(){
    });
}

//页面层
//layer页面层弹窗口
function layer_pageTier(params) {
    return layer.open({
        type: 1,
        offset: params.offset,
        fixed: params.fixed,
        title: params.title,
        area: [params.area.width, params.area.height], //宽高
        content: params.content
    });
}

//layer页面层弹窗口关闭
function closePageTier(index) {
    layer.close(index);
}

//tips层
function layer_tips(params) {
    layer.tips(params.mes, params.ele, {tips: 1});
}