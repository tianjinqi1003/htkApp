//成功
function alertSuccess(text) {
    swal("操作成功", text, "success");
}

//失败
function alertError(text) {
    swal("操作失败", text, "error");
}

//信息
function alertInfo(text) {
    swal("信息", text, "info");
}

//confirm
function alertConfirm(text, type, confirmBtnText) {
    swal({
        title: "确定操作吗？",
        text: text,
        type: type,
        showCancelButton: true,
        confirmButtonColor: '#DD6B55',
        confirmButtonText: confirmBtnText
    }, alertSuccess("这是成功信息。。。。"));
}

//打开  加载中
function loading_(name,description) {
    $('body').loading({
        loadingWidth:240,
        title:'请稍等!',
        name:name,
        description:description,
        direction:'column',
        type:'origin',
        originDivWidth:40,
        originDivHeight:40,
        originWidth:6,
        originHeight:6,
        smallLoading:false,
        loadingMaskBg:'rgba(0,0,0,0.2)'
    });
}

//关闭  加载中
function closeLoading(name) {
    removeLoading(name);
}


