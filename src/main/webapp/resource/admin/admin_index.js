$(function () {
    login.submitForm();
    $(".replace").bind("click", function () {
        $('#kaptchaImage').hide().attr('src', baseUrl_ + '/login/captcha?' + Math.floor(Math.random() * 100) + "&identity=admin").fadeIn();
    });
});

var Main = {
    data() {
        return {
            //====================顶部搜索框开始
            searchInput: '',
            //====================顶部搜索框结束
            login: {
                loading: true
            }
        }
    },

    methods: {
        //===================================顶部开始
        //顶部搜索框，搜索按钮
        searchIconClick() {

        },
        //顶部下拉菜单
        handleCommand: function (command) {
            dropMenuBtn(this, command);
        },
        //===================================顶部结束


        //==============登陆开始================//
        //登陆提交form
        login_submit() {
            submitLoginForm(this);
        },
        //更换验证码
        replace
        //==============登陆结束================//
    }
};

//========================================登陆页面开始
//登陆button提交
function submitLoginForm(sel) {
    $(":button").attr("disabled", "disabled");
    var kaptInput = $("#kaptcha").val();
    if (kaptInput === null || kaptInput === "") {
        $(":button").removeAttr("disabled");
        Feng.errorTips("请输入验证码");
        return false;
    } else {
        var url = baseUrl_ + "/admin/login";
        var userName = $("#userName").val();
        var password = $("#password").val();
        var loginKaptcha = $("#kaptcha").val();
        var params_ = {
            userName: userName,
            password: MD5(userName + "#" + password),
            loginKaptcha: loginKaptcha,
            role: "E"
        };
        $.post(url, params_, function (result, status) {
            if (status === 'success') {
                if (result && result['code'] === 2) {
                    setTimeout(function () {
                        window.location.href = baseUrl_ + result['data'];
                    }, 2000);
                } else if (result && result['code'] === -1) {
                    $(":button").removeAttr("disabled");
                } else {
                    $(":button").removeAttr("disabled");
                }
            } else {
                $(":button").removeAttr("disabled");
            }
        }, "json");
    }
}
//========================================登陆页面结束


//========================================index开始
//下拉菜单按钮
function dropMenuBtn(sel, command) {
    if (command === "loginOut") {
        enterAdminSignOut(sel);
    }
}

//========================================index结束
var Ctor = Vue.extend(Main);
new Ctor().$mount("#wrapper");






