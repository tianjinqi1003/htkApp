const Main = {
    data() {
        return {
            pageNumber: 1,
            searchInput: '',
            versionTableData: [],
            searchI: "",
            versionRuleForm: {
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            versionRules: {
                name: [
                    {required: true, message: '请输入活动名称', trigger: 'blur'},
                    {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                ],
                region: [
                    {required: true, message: '请选择活动区域', trigger: 'change'}
                ],
                date1: [
                    {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
                ],
                date2: [
                    {type: 'date', required: true, message: '请选择时间', trigger: 'change'}
                ],
                type: [
                    {type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change'}
                ],
                resource: [
                    {required: true, message: '请选择活动资源', trigger: 'change'}
                ],
                desc: [
                    {required: true, message: '请填写活动形式', trigger: 'blur'}
                ]
            },
            versionId: '20170704',
            appName: "回头客用户端",
            oldVersionNumber: "1.0",
            versionNumber: '1.0',
            appDownloadUrl: "www.htk-app.com"

        }

    },

    mounted() {
        const currentPath = window.document.location.href;
        if (currentPath.indexOf('/admin/appVersionManage/versionEditPage') > 0) {
            var obj = {};
            obj = getRequest();
        } else if (currentPath.indexOf('/admin/appVersionManage/index') > 0) {
            this.loadAppVersionData();
        }
    },

    methods: {
        //================================共用开始
        //顶部搜索框，搜索按钮
        searchIconClick() {
        },
        //顶部下拉菜单
        handleCommand: function (command) {
            dropMenuBtn(this, command);
        },
        //================================共用结束

        //用户app页面数据加载
        loadAppVersionData(pageNumber) {
            if (!isNaN(pageNumber)) {
                this.pageNumber = pageNumber;
            }
            var params = {
                url: baseUrl_ + '/admin/appVersionManage/getVersionList',
                params_: {
                    appId: null,
                    pageNumber: this.pageNumber
                }
            };
            loadAppVersionData_(this, params);
        },
        //搜索按钮
        vSearchClick(ev) {
            console.log(ev);
        },
        //编辑按钮
        vEditBtn(id) {
            // localStorage.adminEditVersionId = id;
            window.location.href = baseUrl_ + "/admin/appVersionManage/versionEditPage?id=" + id;
        },
        //删除按钮
        vDelBtn(id) {
            const url = baseUrl_ + "/admin/appVersionManage/delVersionById";
            const params_ = {id: id};
            messageBox(this, "确定要删除吗？", url, params_);
        },
        //添加按钮
        vAddBtn() {
            //跳转到添加页面
            window.location.href = baseUrl_ + "/admin/appVersionManage/versionAddPage";
        },
        //添加表单提交
        versionSubmitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert('submit!');
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        //重置表单
        versionResetForm(formName) {
            this.$refs[formName].resetFields();
        },
        //版本号自增
        versionAddNumber(value) {
            console.log(value);
        },

        //========================格式表格值开始
        //格式化下载地址
        subStringDownloadUrl(value) {
            var downloadUrl = value.downloadUrl;
            if (downloadUrl.length > 10) {
                return downloadUrl.substring(0, 42) + "....";
            } else {
                return value;
            }
        },
        //格式化更新日志
        subStringUploadLog(value) {
            var uploadLog = value.uploadLog;
            if (uploadLog.length > 10) {
                return uploadLog.substring(0, 20) + "....";
            } else {
                return value;
            }
        },
        //========================格式表格值结束

    }
};

//========================================方法开始
//下拉菜单按钮
function dropMenuBtn(sel, command) {
    if (command === "loginOut") {
        enterAdminSignOut(sel);
    }
}


//获取用户app版本数据
function loadAppVersionData_(sel, param) {
    sel.$http.post(param.url, param.params_, {emulateJSON: true}).then(res => {
        return res.json();
    }).then(data => {
        if (data.code === 0) {
            if (data.data !== null) {
                sel.versionTableData = data.data;
            } else {
                sel.versionTableData = [];
            }
        } else {
            alert(data.message);
        }
    }).catch(error => {
        alert(data.message);
    })
}

//通过id查找版本数据
function loadAppVersionDataById(sel, params) {
    sel.$http.post(params.url, params.params_, {emulateJSON: true}).then(res => {
        return res.json();
    }).then(data => {
    }).catch((error) => {
        alert(data.message);
    })
}

//========================================方法结束

var Ctor = Vue.extend(Main);
new Ctor().$mount("#wrapper");