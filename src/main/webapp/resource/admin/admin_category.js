var Main = {
    data() {
        return {
            searchInput: '',
            tableData5: [],
            categoryList: [],
            ruleForm: {
                name: '',
                photo: '',
                desc: ''
            },
            rules: {
                name: [
                    {required: true, message: '请输入子分类名称', trigger: 'blur'},
                    {min: 1, max: 5, message: '长度在 1 到 5 个字符', trigger: 'blur'}
                ],
                photo: [
                    {required: true, message: '请上传图片', trigger: 'blur'}
                ],
                desc: [
                    {required: true, message: '请填写子分类描述', trigger: 'blur'},
                    {min: 1, max: 20, message: '长度在 1 到 5 个字符', trigger: 'blur'}
                ]
            },
            imageUrl: '',
            firstCategoryObj: {},
            addChildCategory: false,
            curCategoryId: '',
            t_edit: false,
            t_add: false
        }
    },

    mounted() {
        //执行
        const curPath = window.document.location.href;
        if (curPath.indexOf('admin/category/takeoutCategory') > 0) {
            //外卖分类页面
            this.loadTakeoutCategoryDataList(0);
        } else if (curPath.indexOf('admin/category/editCategoryPage') > 0) {
            //修改分类页面
            this.t_edit = true;
            this.t_add = false;
            this.getCategoryDetailById(localStorage.shop_categoryId);
        } else if (curPath.indexOf('admin/category/takeoutCategoryAdd') > 0) {
            this.t_add = true;
            this.t_edit = false;
        } else if (curPath.indexOf('admin/category/groupBuyCategory') > 0) {
            //团购分类页面
            this.loadTakeoutCategoryDataList(1);
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

        //加载外卖分类
        loadTakeoutCategoryDataList(mark) {
            loadTakeoutData(this, mark);
        },
        //重置表单
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        //上传成功后回调　
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
            this.ruleForm.photo = res;
        },
        //图片上传前
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        },
        //分类添加
        categoryAdd() {
            window.location.href = baseUrl_ + "/admin/category/takeoutCategoryAdd";
        },
        //分类修改
        categoryEdit(id) {
            localStorage.shop_categoryId = id;
            window.location.href = baseUrl_ + "/admin/category/editCategoryPage";
        },
        //点击分类
        clickCategory(id, mark) {
            this.curCategoryId = id;
            loadTakeoutChild(this, id, mark);

        },
        //添加二级分类表单提交
        addChildSubmitForm(formName, mark) {
            var params = {
                url: baseUrl_ + "/admin/category/addChildCategoryById",
                params_: {
                    parentId: this.curCategoryId,
                    categoryName: this.ruleForm.name,
                    description: this.ruleForm.desc,
                    mark: mark
                }
            };
            formBtn(this, formName, params);
        },
        //添加子分类重置按钮
        addChildResetForm(formName) {
            this.$refs[formName].resetFields();
        },
        //编辑页面,通过id获取分类信息
        getCategoryDetailById(id) {
            var params = {
                url: baseUrl_ + "/admin/category/editCategoryById",
                params_: {
                    categoryId: id
                }
            };
            loadCategoryDetailById(this, params)
        },
        //取消修改分类按钮
        cancelEditCategoryBtn() {
            window.location.href = baseUrl_ + "/admin/category/takeoutCategory";
        },
        //添加外卖分类form提交
        t_addCategoryFormSubmit(forName) {
            var params = {
                url: baseUrl_ + "/admin/category/addCategoryByMark",
                params_: {
                    categoryName: this.ruleForm.name,
                    description: this.ruleForm.desc,
                    categoryUrl: this.ruleForm.photo,
                    mark: 0,
                    parentId: 0
                }
            };
            formBtn(this, forName, params);
        },
        //修改分类提交
        t_saveCategoryFormSubmit(forName) {
            var params = {
                url: baseUrl_ + "/admin/category/saveCategoryById",
                params_: {
                    categoryName: this.ruleForm.name,
                    description: this.ruleForm.desc,
                    categoryUrl: this.ruleForm.photo,
                    mark: 0,
                    parentId: 0,
                    id: this.curCategoryId
                }
            };
            formBtn(this, forName, params);
        },
        //通过分类id删除分类
        delCategoryById(id) {
            var url = baseUrl_ + "/admin/category/delChildCategoryById";
            var params_ = {
                childCId: id
            }
            http_post(url, params_, this);
        },
    }
};

//加载分类数据
function loadTakeoutData(sel, mark) {
    var url = baseUrl_ + "/admin/category/getCategoryListJsonData";
    var params_ = {mark: mark};
    sel.$http.post(url, params_, {emulateJSON: true}).then(res => {
        return res.json();
    }).then(data => {
        if (data.code === 0) {
            //code 为0代表成功
            for (var i = 0; i < data.data.length - 1; i++) {
                if (data.data[i].show) {
                    //服务器返回一个分类的状态为true
                    sel.firstCategoryObj = data.data[i];
                    var url = baseUrl_ + "/admin/category/getChildCategoryListById";
                    var params_ = {
                        parentId: data.data[i].id,
                        mark: data.data[i].mark
                    };
                    sel.curCategoryId = data.data[i].id;
                    sel.$http.post(url, params_, {emulateJSON: true}).then(res => {
                        return res.json();
                    }).then(data => {
                        sel.tableData5 = data.data;
                    }).catch(() => {
                        alert("网络连接错误");
                    })
                }
            }
            sel.categoryList = data.data;
        } else {
            alert("获取数据失败");
        }
    }).catch((error) => {
        alert(error.message);
    })
}

//通过外卖分类id查找分类下的小分类
function loadTakeoutChild(sel, id, mark) {
    var url = baseUrl_ + "/admin/category/getChildCategoryListById";
    var params_ = {
        parentId: id,
        mark: mark
    };
    sel.$http.post(url, params_, {emulateJSON: true}).then(res => {
        return res.json();
    }).then(data => {
        if (data.code === 0) {
            for (var i = 0; i < sel.categoryList.length; i++) {
                if (sel.categoryList[i].id === id) {
                    sel.categoryList[i].show = true;
                    sel.tableData5 = data.data;
                    sel.firstCategoryObj = sel.categoryList[i];
                } else {
                    sel.categoryList[i].show = false;
                }
            }
        } else {
            alert("加载二级分类错误")
        }
    }).catch((result) => {
        alert("result.message");
    })
}

//编辑分类页面通过分类ID加载分类
function loadCategoryDetailById(sel, params) {
    sel.$http.post(params.url, params.params_, {emulateJSON: true}).then(res => {
        return res.json();
    }).then(data => {
        if (data.code === 0) {
            sel.ruleForm.name = data.data.categoryName;
            sel.ruleForm.photo = data.data.categoryUrl;
            sel.ruleForm.desc = data.data.description;
            sel.imageUrl = data.data.categoryUrl;
            sel.curCategoryId = data.data.id;
        } else {
            alert(data.message)
        }
    }).catch((error) => {
        alert(error.message);
    })
}
var Ctor = Vue.extend(Main);
new Ctor().$mount("#wrapper");