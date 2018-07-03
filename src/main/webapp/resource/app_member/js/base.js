layui.use(['element', 'layer',], function () {
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    var layer = layui.layer;
});


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
    }else {
        layer.msg(data);
    }
}