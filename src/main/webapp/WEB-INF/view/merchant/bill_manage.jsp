<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/26
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>账单资金</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>

    <style>
        .textSpan > .spanItem > .firstName {
            width: 105px;
            text-align: right;
        }

        .textSpan > .spanItem {
            display: block;
        }

        .withdrawDepositInput {
            width: 100px;
            border-radius: 2px;
            border: 1px solid #ccccce;
            height: 25px;
            padding-left: 5px;
        }

        .alipayAccountInput {
            width: 100px;
            border-radius: 2px;
            border: 1px solid #ccccce;
            height: 25px;
            padding-left: 5px;
        }

        .notEnterA:hover {
            color: #949498;
            cursor: not-allowed
        }

        .notEnterA {
            color: #949498;
        }

        .enterA:hover {
            color: #337ab7;
            cursor: pointer;
        }

        .enterA {
            color: #337ab7;
        }

        div.dateDiv > div.but > div.butDate.sel > button {
            background-color: #1E9FFF;
            color: #fff;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="layui-fluid" style="padding: 13px 0 0 0!important">
                <div class="layui-row">
                    <div class="layui-col-lg7 bac_col">
                        <div class="layui-row">
                            <div class="layui-col-lg12">
                                <span class="span_text">账户余额(元)<i class="fa fa-question-circle i_left"
                                                                  title="账户总余额（包含已入账、未入账金额）"></i></span>
                                <div class="span_amount">
                                    <span><strong>${accountBalance}</strong></span>
                                </div>
                            </div>
                        </div>
                        <div class="layui-row" style="border-top: 1px solid #f3f3f4;">
                            <div class="layui-col-lg12">
                                <span class="span_text_down">可用余额<i class="fa fa-question-circle i_left"
                                                                    title="账户可用余额（已入账金额）"></i></span>
                                <div class="span_text_amount_down">
                                    <span><strong>${availableBalance}</strong></span>
                                    <button class="layui-btn layui-btn-primary ${availableBalance < 1 ? "layui-btn-disabled" : ""} withdrawDeposit"
                                            style="margin-left: 20px;height: 25px;line-height: 25px;" ${availableBalance < 1 ? "disabled='disabled'" : ""}>
                                        提现
                                    </button>
                                    <i class="fa fa-question-circle i_left"
                                       title="提现会扣除0.6%的平台手续费"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-lg2 bac_col" style="border-left: 1px solid #f3f3f4;">
                        <div style="text-align: center;padding-top: 20px">
                            <span class="span_text_lg1">待入账金额(元)
                                <i class="fa fa-question-circle i_left" title="交易完成即自动归入已入账"></i>
                            </span>
                        </div>
                        <div class="text_center span_amount_center">
                            <span><strong>${remainingBalance}</strong></span>
                        </div>
                        <div class="text_center dRZ_bottom">
                        </div>
                    </div>
                    <div class="layui-col-lg3 bac_col" style="border-left: 1px solid #f3f3f4;padding-bottom: 58px">
                        <div style="border-bottom: 1px solid #f3f3f4;padding-top: 37px">
                            <p class="right_text">结算信息</p>
                            <p class="right_text">第三方账户：
                                <a>${alipayAccount}(支付宝)</a>
                                <button style="margin-left: 10px;display:block-inline" class="switchAccount">更改
                                </button>
                            </p>
                            <p class="right_text">结算方式：在线结算</p>
                        </div>
                    </div>
                </div>
                <div class="layui-row mar_tp-20 bac_col">
                    <div class="layui-col-lg12 pd_tp-20">
                        <div style="position: relative;border-bottom: 1.5px solid #c7c7c7">
                            <div class="yeLsDiv">
                                <span>可用余额流水</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="bac_col pd_tp-20 pd_bo-20 btnPDiv">
                    <div style="width: 96%;margin: auto">
                        <div class="inline_block">
                            <div class="selectCat">
                                <select name="categoryId" lay-verify="required" class="sel_category">
                                    <c:choose>
                                        <c:when test="${type == 0}">
                                            <option value="0" selected="selected">全部类型</option>
                                            <option value="1">收入</option>
                                            <option value="2">支出</option>
                                        </c:when>
                                        <c:when test="${type == 1}">
                                            <option value="0">全部类型</option>
                                            <option value="1" selected="selected">收入</option>
                                            <option value="2">支出</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="0">全部类型</option>
                                            <option value="1">收入</option>
                                            <option value="2" selected="selected">支出</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${fn:length(dateVal) == 1}">
                                <div class="inline_block dateDiv">
                                    <div class="but">
                                        <div class="butDate ${dateVal eq '1' ? "sel" : ""}">
                                            <button class="layui-btn layui-btn-primary border_radius_5 " data-date="1">近&nbsp;7&nbsp;天
                                            </button>
                                        </div>
                                        <div class="butDate ${dateVal eq '2' ? "sel" : ""}">
                                            <button class="layui-btn layui-btn-primary border_radius_5 " data-date="2">近&nbsp;30&nbsp;天
                                            </button>
                                        </div>
                                        <div class="butDate ${dateVal eq '3' ? "sel" : ""}">
                                            <button class="layui-btn layui-btn-primary border_radius_5 " data-date="3">近&nbsp;90&nbsp;天
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="inline_block inputDateDiv">
                                    <div class="inline_block">
                                        <input id="startDate" name="startDate" readonly value="" placeholder="开始时间"/>
                                        <span class="icon_cal"><i class="fa fa-calendar-check-o" aria-hidden="true"></i></span>
                                    </div>
                                    <span style="font-size: 16px">至</span>
                                    <div class="inline_block">
                                        <input id="endDate" name="endDate" readonly value="" placeholder="结束时间"/>
                                        <span class="icon_cal"><i class="fa fa-calendar-check-o" aria-hidden="true"></i></span>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:set value="${ fn:split(dateVal, '~') }" var="str"/>
                                <div class="inline_block dateDiv">
                                    <div class="but">
                                        <div class="butDate">
                                            <button class="layui-btn layui-btn-primary border_radius_5 " data-date="1">近&nbsp;7&nbsp;天
                                            </button>
                                        </div>
                                        <div class="butDate">
                                            <button class="layui-btn layui-btn-primary border_radius_5 " data-date="2">近&nbsp;30&nbsp;天
                                            </button>
                                        </div>
                                        <div class="butDate">
                                            <button class="layui-btn layui-btn-primary border_radius_5 " data-date="3">近&nbsp;90&nbsp;天
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="inline_block inputDateDiv">
                                    <div class="inline_block focusInput">
                                        <input id="startDate" name="startDate" readonly value="${str[0]}"
                                               placeholder="开始时间"/>
                                        <span class="icon_cal"><i class="fa fa-calendar-check-o" aria-hidden="true"></i></span>
                                    </div>
                                    <span style="font-size: 16px">至</span>
                                    <div class="inline_block focusInput">
                                        <input id="endDate" name="endDate" readonly value="${str[1]}"
                                               placeholder="结束时间"/>
                                        <span class="icon_cal"><i class="fa fa-calendar-check-o" aria-hidden="true"></i></span>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div class="inline_block exportBtn" style="float: right">
                            <div class="inline_block">
                                <button class="layui-btn layui-btn-primary border_radius_5 layui-btn-disabled"
                                        title="导出按钮不可用">导出
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class=" bac_col" style="height: 350px;">
                <c:choose>
                    <c:when test="${data != null}">
                        <div class="tabDiv">
                            <table class="layui-table" lay-size="lg">
                                <colgroup>
                                    <col width="140">
                                    <col width="100">
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>日期</th>
                                    <th>类型</th>
                                    <th>描述</th>
                                    <th>金额(元)</th>
                                    <th>余额(元)</th>
                                    <th>状态</th>
                                    <th>备注</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${data}" var="each">
                                    <tr>
                                        <td>${fn:substring(each.gmtCreate, 0,10)}</td>
                                        <td>${each.recordType == 1 ? "收入" : "支出"}</td>
                                        <td>${each.description}</td>
                                        <td>${each.sumAmount}</td>
                                        <td>${each.balance}</td>
                                        <td>${each.status == 1 ? "成功" : "失败"}</td>
                                        <td>${each.remark}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <c:if test="${data != null}">
                                <div class="pageMes">
                                    <span>当前第1 - 3条记录,共3条</span>
                                    <div id="page" class="inline_block"></div>
                                </div>
                            </c:if>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="showNoDataText" style="text-align: center;">
                            <div class="noOrder" style="padding-top: 20px;">
                                <div class="noOrder-content">
                                    <div class="noOrder-content-div">
                                        <div class="wane">
                                            <i class="fa fa-file-text-o wane-icon" aria-hidden="true"
                                               style="font-size: 100px;line-height: 170px;color: #fff"></i>
                                        </div>
                                        <p class="noOrder-p">暂无流水记录</p>
                                        <span class="noOrder-span">暂时没有该筛选条件的记录!</span>
                                    </div>
                                    <div class="pageMes" style="width: 96%;text-align: left;margin: auto">
                                        <span>当前第0 - 0条记录,共0条</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
<%@include file="js.jsp" %>
<script type="text/javascript">
    layui.use(['element', 'util', 'layer', 'laydate', 'laypage'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        var date = layui.laydate;
        var page = layui.laypage;

        //开始时间
        date.render({
            elem: '#startDate',
            theme: '#20A0FF',
            showBottom: false,
            trigger: 'click',
            min: -365,
            max: 0,
            ready: function () {
                //控件初始打开的回调   去除日期按钮的选中状态,改变日期输入框颜色
                var dateCondition = $("div.dateDiv > div.but > div.butDate.sel");  //日期筛选条件中的按钮
                $(dateCondition).removeClass("sel");
                var inputDateList = $("div.inputDateDiv > div.inline_block > input");  //获取日期选择插件对象,是一个数组
                if (inputDateList.length > 0) {
                    $(inputDateList).each(function (index, item) {
                        if (item.placeholder === '开始日期') {
                            //为当前选中的input 输入框父Dom对象添加 选中类样式
                            var itemParent = $(item).parent();
                            if (!$(itemParent).hasClass("curFocusInput")) {
                                $(item).parent().addClass("curFocusInput");
                            }
                        }
                        var ele = $(this).parent();
                        ele.addClass("focusInput");
                    });
                }
            },
            done: function (value) {
                //选择结束回调函数
                var stopDateEle = $("#endDate");  //得到结束日期Dom对象
                var startDateEle = $("#startDate");  //得到开始日期Dom对象
                if (stopDateEle.val() == '') {
                    return false;
                } else if (stopDateEle.val() < value) {
                    //开始日期不能大于结束日期
                    layer.msg("开始日期不能大于截止日期", {anim: 6, time: 3000}, function () {
                            //把开始日期置为空
                            startDateEle.val("");
                        }
                    );
                }
            }
        });

        //结束时间
        date.render({
            elem: '#endDate',
            theme: '#20A0FF',
            showBottom: false,
            trigger: 'click',
            min: -365,
            max: 0,
            ready: function () {
                var dateCondition = $("div.dateDiv > div.but > div.butDate.sel");  //日期筛选条件中的按钮
                $(dateCondition).removeClass("sel");
                var inputDateList = $("div.inputDateDiv > div.inline_block > input");  //获取日期选择插件对象,是一个数组
                if (inputDateList.length > 0) {
                    $(inputDateList).each(function (index, item) {
                        if (item.placeholder === '开始日期') {
                            //为当前选中的input 输入框父Dom对象添加 选中类样式
                            var itemParent = $(item).parent();
                            if (!$(itemParent).hasClass("curFocusInput")) {
                                $(item).parent().addClass("curFocusInput");
                            }
                        }
                        var ele = $(this).parent();
                        ele.addClass("focusInput");
                    });
                }
            },
            done: function (value) {
                var startDateEle = $("#startDate");  //得到开始日期Dom对象
                var stopDateEle = $("#endDate");  //截止日期Dom对象
//                var starCondition = $("div.starsDiv > div.starItemBtn.sel > button");  //星级筛选条件
                var optionVal = $("div.selectCat > select.sel_category").children('option:selected').val();
//                var starVal = $(starCondition).attr("data-star");
                if (startDateEle.val() === undefined || startDateEle.val() === "") {
                    //日期开始值为undefined 或　为空字符串
                    layer.msg("开始日期值不能为空,默认改为截止日期的前七天", {anim: 6, time: 2000}, function () {
                            //关闭后的回调,设置开始日期
                            var offsetDate = offsetDateMethod(new Date(), -7);
                            var startDateVal = offsetDate.getFullYear() + "-" + (offsetDate.getMonth() + 1) + "-" + (offsetDate.getDate() <= 9 ? "0" + offsetDate.getDate() : offsetDate.getDate());
                            startDateEle.val(startDateVal);
                            setTimeout(function () {
                                window.location.href = baseUrl + "/merchant/bill/billMoney" + "?date=" + (startDateVal + "~" + stopDateEle.val()) + "&type=" + optionVal;
                            }, 1500)
                        }
                    );
                } else if (startDateEle.val() > value) {
                    //开始日期不能大于结束日期
                    layer.msg("截止日期不能小于开始日期", {anim: 6, time: 3000}, function () {
                            //重置开始日期值
                            stopDateEle.val("");
                        }
                    );
                } else {
                    //按照条件筛选评论数据
                    window.location.href = baseUrl + "/merchant/bill/billMoney" + "?date=" + (startDateEle.val() + "~" + stopDateEle.val()) + "&type=" + optionVal;
                }
            }
        });

        //不显示首页尾页
        page.render({
            elem: 'page'
            , count: ${page == null ? 0: page.pages * 10}
            , curr: ${page == null ? 1 : page.pageNum}
            , groups: 3
            , theme: '#1E9FFF'
            , first: false
            , last: false
            , jump: function (obj, first) {
                if (!first) {
                    var val = $(".butDate.sel > button").attr("data-date");
                    if (val === undefined) {
                        //取输入框的值
                        val = $("#startDate").val() + "~" + $("#endDate").val();
                    }
                    window.location.href = baseUrl + "/merchant/bill/billMoney?date=" + val + "&pageNum=" + obj.curr
                }
            }
        });
    });

    var index;

    //提现弹窗口按钮绑定点击事件
    $("div.span_text_amount_down > button.withdrawDeposit").on("click", function () {
        var params = {
            title: ["修改支付宝账户确认", "background-color:#1E9FFF;color:#fff"],
            content: "<div class=\"withdrawDepositContent\" style=\"padding: 20px 30px;\">\n" +
            "                    <div class=\"contentTop\" style=\"padding-bottom: 20px;\">\n" +
            "                        <div class=\"topDiv\">\n" +
            "                            <span class=\"textSpan\">\n" +
            "                                <span class=\"spanItem\" style=\"padding: 8px 0\">\n" +
            "                                    <span class=\"firstName\" style=\"font-size: 16px;\">第三方账户:</span>\n" +
            "                                    <span style=\"margin-left: 10px;font-size: 15px;font-weight: bold\">${alipayAccount}</span>\n" +
//            "                                    <input style='ime-mode:disabled' type='hidden' onpaste=\"return false;\"  class=\"alipayAccountInput\" id=\"alipayAccount\" value=\"\">\n" +
//            "                                    <button style='margin-left: 10px;display:block-inline' class='switchAccount'>更改支付宝账户</button>\n" +
            "                                </span>\n" +
            "                                <span class=\"spanItem\" style=\"display: block;padding: 8px 0\">\n" +
            "                                    <span class=\"firstName\" style=\"font-size: 16px;\">可提现金额:</span>\n" +
            "                                    <span class='usableBalance' style=\"margin-left: 10px;font-size: 15px;font-weight: bold\">${availableBalance}</span>\n" +
            "                                </span>\n" +
            "                            </span>\n" +
            "                        </div>\n" +
            "                        <div></div>\n" +
            "                    </div>\n" +
            "                    <hr style=\"width: 490px;margin: 0px;\"/>\n" +
            "                    <div class=\"contentDown\" style=\"padding-top: 20px;\">\n" +
            "                        <div class=\"downDiv\">\n" +
            "                            <span class=\"textSpan\">\n" +
            "                                <span class=\"spanItem\">\n" +
            "                                    <span class=\"firstName\" style=\"font-size: 16px;\">提现金额:</span>\n" +
            "                                    <span style=\"margin-left: 10px;font-size: 15px;font-weight: bold\">\n" +
            "                                        <input style='ime-mode:disabled\"' onpaste=\"return false;\"  class=\"withdrawDepositInput\" id=\"withdrawDepositAmount\" value=\"\">\n" +
            "                                        <span style='margin-left: 10px' id='confirmTheAmount'><a class='notEnterA'>确认金额</a></span>\n" +
            "                                    </span>\n" +
            "                                </span>\n" +
            "                                <span class=\"spanItem\" style='padding: 12px 0'>\n" +
            "                                    <span class=\"firstName\" style=\"font-size: 16px;\">预计到账时间:</span>\n" +
            "                                    <span style=\"margin-left: 10px;font-size: 15px;font-weight: bold\">\n" +
            "                                        <span style='margin-left: 10px' id='confirmTheAmount'>2小时之内</span>\n" +
            "                                    </span>\n" +
            "                                </span>\n" +
            "                            </span>\n" +
            "                        </div>\n" +
            "                        <div style=\"text-align: center;padding-top: 15px;\">\n" +
            "                            <button class='toConfirmWithdrawal layui-btn-disabled' disabled='disabled' id='toConfirmWithdrawal'" +
            " style=\"width: 200px;height: 40px;font-size: 20px;background-color: orange;border: 1px solid #fff; border-radius: 5px;font-weight: bold\">\n" +
            "                                确认提现\n" +
            "                            </button>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                </div>",
            offset: '150px',
            fixed: true,
            area: {
                width: '550px',
                height: '340px'
            }
        };
        index = layer_pageTier(params);
    })

    var index2;

    //更改账户弹窗口按钮绑定点击事件
    $("button.switchAccount").on("click", function () {
        var params = {
            title: ["更改支付宝账户确认", "background-color:#1E9FFF;color:#fff"],
            content: "<div class=\"Content\" style=\"padding: 20px 30px;\">\n" +
            "                    <div class=\"contentTop\" style=\"padding-bottom: 20px;\">\n" +
            "                        <div class=\"topDiv\">\n" +
            "                            <span class=\"textSpan\">\n" +
            "                                <span class=\"spanItem\" style=\"padding: 8px 0\">\n" +
            "                                    <span class=\"firstName\" style=\"font-size: 16px;\">第三方账户:</span>\n" +
            "                                    <input style='ime-mode:disabled\"' onpaste=\"return false;\"  class=\"alipayAccountInput\" id=\"alipayAccount\" value=\"\">\n" +
            "                                </span>\n" +
            "                            </span>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"contentTop\" style=\"padding-bottom: 20px;\">\n" +
            "                        <div class=\"topDiv\">\n" +
            "                            <span class=\"textSpan\">\n" +
            "                                <span class=\"spanItem\" style=\"padding: 8px 0\">\n" +
            "                                    <span class=\"firstName\" style=\"font-size: 16px;\">手机号:</span>\n" +
            "                                    <span class=\"\" style=\"font-size: 16px;\">"+'${merchantUser.userName}'+"</span>\n" +
            "                                    <span class=\"firstName\" style=\"font-size: 16px;\">"+
            "                          <button class='toSendSms' id='toSendSms'" +
            " style=\"width: 100px;height: 30px;font-size: 16px;background-color: orange;border: 1px solid #fff; border-radius: 5px;font-weight: bold\">\n" +
            "                                发送验证码\n" +
            "                          </button>" +
            " 									 </span>\n" +
            "                                </span>\n" +
            "                            </span>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"contentTop\" style=\"padding-bottom: 20px;\">\n" +
            "                        <div class=\"topDiv\">\n" +
            "                            <span class=\"textSpan\">\n" +
            "                                <span class=\"spanItem\" style=\"padding: 8px 0\">\n" +
            "                                    <span class=\"firstName\" style=\"font-size: 16px;\">验证码:</span>\n" +
            "                                    <input style='ime-mode:disabled\"' onpaste=\"return false;\"  class=\"valCode\" id=\"valCode\" value=\"\">\n" +
            "                                </span>\n" +
            "                            </span>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div style=\"text-align: center;padding-top: 15px;\">\n" +
            "                          <button class='toConfirmSwitch' id='toConfirmSwitch'" +
            " style=\"width: 200px;height: 40px;font-size: 20px;background-color: orange;border: 1px solid #fff; border-radius: 5px;font-weight: bold\">\n" +
            "                                确认修改\n" +
            "                          </button>\n" +
            "                    </div>\n" +
            "          </div>",
            offset: '150px',
            fixed: true,
            area: {
                width: '550px',
                height: '340px'
            }
        };
        index2 = layer_pageTier(params);
    })

    //为确认修改支付宝账户按钮绑定点击事件
    $(document).on("click", ".toConfirmSwitch", function () {
    	if(!checkValCode()){
    		return false;
    	}
        //@author 马鹏昊
        // 向后台发起post请求，修改商户支付账户
        var url = baseUrl + '/merchant/modifyAccount';
        var getNewAccount = $(".alipayAccountInput").val();
        var params = {newAccount: getNewAccount};
        $.post(url, params, function (result, status) {
            //关闭弹窗
            layer_loadingClose(index2);
            if (status === 'success') {
                if (result && result.code === 0) {
                    //获取最新数据
                    location.reload();
                    return false;
                } else {
                    layer_msg(result.message, 'error');
                    return false;
                }
            } else {
                layer_msg('网络连接失败', 'exception');
                return false;
            }
        }, "json");
    });
    
    var endTime=60;
    
    $(document).on("click",".toSendSms",function(){
    	//alert(checkValCode());
    	//startTimeCount();
    	var phone='${merchantUser.userName}';
    	var url = baseUrl + '/API/AccountMessage/sendSms/'+phone;
    	var params = {phone: phone};
    	$.post(url,params,function(result, status){
    		//alert(result+","+status);
    		if (status === 'success'){
    			startTimeCount();
    		}
    	},"json");
    });
    
    function startTimeCount(){
    	$("#toSendSms").attr("disabled",true);
    	setTimeout("setToSendSmsText()",1000);
    }
    
    function setToSendSmsText(){
    	//console.log(endTime);
    	if(endTime<=0){
    		endTime=60;
        	$("#toSendSms").attr("disabled",false);
	    	$("#toSendSms").text("重新验证");
    	}
    	else{
	    	endTime--;
	    	$("#toSendSms").text(endTime+"s");
	    	setTimeout("setToSendSmsText()",1000);
    	}
    }
    
    function checkValCode(){
    	var flag=false;
    	var url = baseUrl + '/API/AccountMessage/appAccountLoginByCode';
    	var phone='${merchantUser.userName}';
    	var code=$("#valCode").val();
    	var params = {phone: phone,code:code};
    	$.ajaxSetup({async:false});
    	$.post(url,params,function(result, status){
    		if (status === 'success'){
        		if(result.code==100){
        			flag=true;
        		}
        		else{
        			alert("验证码错误！");
        			flag=false;
        		}
    		}
    	},"json");
    	return flag;
    }

    //确认输入金额按钮
    $(document).on("click", ".enterA", function () {
        //删除input 元素,将输入的金额置为读金额数字
        var inputEle = $(".withdrawDepositInput");  //获取金额输入框元素
        var usableBalance = parseFloat($(".usableBalance").text());  //取可用余额值
        var inputVal = parseFloat(inputEle.val());
        if (inputVal > 1 && inputVal <= usableBalance) {
            var inputParentEle = $(inputEle).parent();  //金额输入框的父元素
            var val = inputEle.val();
            var ind = val.indexOf(".");
            if (ind > 0) {
                //输入了小数点,判断小数点后面有几位数
                var subStrVal = val.substring(ind + 1, val.length);
                if (subStrVal === '') {
                    //小数点后没有值
                    val += "00";
                } else if (subStrVal.length == 1) {
                    val += "0";
                } else {
                    val += "";
                }
            } else {
                //没有小数点,加小数点
                val += ".00";
            }
            inputParentEle.prepend("<span style='margin-left: 30px;cursor: pointer;color:#337ab7' class='updateAmount'>修改金额</span>");
            inputParentEle.prepend("<span style='margin-left: 10px;color: red' class='finalMoney'>" + val + "</span>");
            inputEle.remove();  //删除输入框元素
            $(this).remove();  //删除被点击元素的本身
            //置确认提现按钮为可点击状态
            var btnEle = $("#toConfirmWithdrawal");
            if ($(btnEle).length > 0) {
                btnEle.removeClass("layui-btn-disabled");
                btnEle.attr("disabled", false);
            }
        } else if (inputVal <= 1) {
            //输入数字无效
            var params = {
                mes: "提现金额必须大于1元!",
                ele: "#withdrawDepositAmount"
            };
            //置确认提现按钮为不可点击状态
            var btnEle = $("#toConfirmWithdrawal");
            if ($(btnEle).length > 0) {
                btnEle.addClass("layui-btn-disabled");
                btnEle.attr("disabled", true);
            }
            layer_tips(params);
        } else if (inputVal > usableBalance) {
            //输入金额大于现有可用余额
            var params = {
                mes: "输入金额大于现有可用余额,请重新输入!",
                ele: "#withdrawDepositAmount"
            };
            //置确认提现按钮为不可点击状态
            var btnEle = $("#toConfirmWithdrawal");
            if ($(btnEle).length > 0) {
                btnEle.addClass("layui-btn-disabled");
                btnEle.attr("disabled", true);
            }
            layer_tips(params);
        }
    });

    //修改输入金额按钮
    $(document).on("click", ".updateAmount", function () {
        var ele = $(this);  //要删除的元素：第一
        var eleParent = ele.parent();
        var firstChild = eleParent.children(":first-child"); //要删除的元素 ：第二
        //删除当前元素和父元素下的第一个元素
        ele.remove();
        firstChild.remove();
        var insetStr = "<input style='ime-mode:disabled' onpaste=\"return false;\"  class=\"withdrawDepositInput\" id=\"withdrawDepositAmount\" value=\"\"><span style='margin-left: 10px' id='confirmTheAmount'><a class='notEnterA'>确认金额</a></span>";
        eleParent.prepend(insetStr);
        //置确认提现按钮为可点击状态
        var btnEle = $("#toConfirmWithdrawal");
        if ($(btnEle).length > 0) {
            btnEle.addClass("layui-btn-disabled");
            btnEle.attr("disabled", true);
        }
    });

    //为确认提现按钮绑定点击事件
    $(document).on("click", ".toConfirmWithdrawal", function () {
        //点击确认提现按钮，关闭弹窗，并显示提示信息给用户
        layer_loadingClose(index);
        layer.msg("已发起请求,可在当前页面支出中查看提现结果", {
            icon: 1,
            offset: '80px'
        })
        //ajax　

        //@author 马鹏昊
        // 向后台发起post请求，提现到商户支付账户
        var url = baseUrl + '/merchant/withdraw';
        var money = $(".finalMoney").text();
        var params = {money: money};
        $.post(url, params, function (result, status) {
            //关闭弹窗
            layer_loadingClose(index);
            if (status === 'success') {
                if (result && result.code === 0) {
                    //改变页面元素显示值
                    window.location.reload();
                    return false;
                } else {
                    layer_msg(result.message, 'error');
                    return false;
                }
            } else {
                layer_msg('网络连接失败', 'exception');
                return false;
            }
        }, "json");

    })


    //监听输入金额输入框
    $(document).on("keyup", ".withdrawDepositInput", function () {
        var obj = this;
        obj.value = obj.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
        obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
        obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            obj.value = parseFloat(obj.value);
        }
        var aEleN = $(".notEnterA");
        var aEle = $(".enterA");
        if (obj.value.length > 0) {
            //置确认金额按钮为可点击状态
            if (aEleN.length > 0) {
                $(aEleN).removeClass("notEnterA");
                $(aEleN).addClass("enterA")
            }
        } else {
            if (aEle.length > 0) {
                $(aEle).removeClass("enterA");
                $(aEle).addClass("notEnterA");
            }
        }
    });

    //金额输入框获取到了焦点
    $(document).on("focus", ".withdrawDepositInput", function () {
        var ele = this;
        var params = {
            mes: "小数位只能保留至小数点后两位!",
            ele: ele
        };
        layer_tips(params);
    });

    //为日期筛选绑定点击事件
    $("div.dateDiv > div.but > div.butDate > button").on("click", function () {
        var dateVal = $(this).attr("data-date");
        var optionVal = $("div.selectCat > select.sel_category").children('option:selected').val();
        window.location.href = baseUrl + "/merchant/bill/billMoney?date=" + dateVal + "&type=" + optionVal;
    });

    //select 类型筛选绑定点击事件
    $("div.selectCat > select.sel_category").on("change", function () {
        var dateVal = $("div.dateDiv > div.but > div.butDate > button").attr("data-date");
        var optionVal = $(this).children('option:selected').val();
        window.location.href = baseUrl + "/merchant/bill/billMoney?type=" + optionVal + "&date=" + dateVal;
    })


</script>
</html>
