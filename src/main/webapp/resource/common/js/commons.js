//ajax方法封装
function ajax_fun(params) {
    $.ajax({
        url:params.url,
        type:params.type,
        timeout: 3000,
        cache:false,
        data:params.data,
        dataType:'json',
        beforeSend:function (data) {
            //发送请求前
            loading_(params.name,params.description);
        },
        success:function (data) {
            //成功函数
            closeLoading(params.name,params.description);
            if(data.code === 0){
                //操作成功
            }else if(data.code === 1){
                //操作成功并刷新本页
            }else if(data.code === 2){
                //操作成功并跳转页面
            }else if(data.code === -1){
                //操作失败
            }else if(data.code === -2){
                //参数错误
            }
        },
        error:function (data) {
            //失败函数
            alertError("网络连接失败");
        },
        complete:function (data) {
            //不管成功或失败都执行

        }
    })
}


//弹窗提示，成功，失败，删除提示
function tips() {

}

var baseUrl_ = getRootPath();

var baseUrl__ = getRootPath_();

//js获取项目根路径  http://localhost:8081/htkApp
function getRootPath() {
    //获取当前网址，如： http://localhost:8081/htkApp/index.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： htkApp/index.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8081
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/htkApp
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

    return (localhostPaht + projectName);
}

//js获取项目根路径  http://localhost:8081/htkApp/
function getRootPath_() {
    //获取当前网址，如： http://localhost:8081/htkApp/index.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： htkApp/index.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8081
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/htkApp
    // var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht);
}