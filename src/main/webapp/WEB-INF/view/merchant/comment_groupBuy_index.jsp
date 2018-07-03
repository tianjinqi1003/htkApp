<%@ page import="com.htkapp.core.OtherUtils" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/30
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>团购评论页面</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body" style="background-color: #f3f3f4">
        <div class="body-content">
            <div class="comment-content">
                <div class="top-commentText clearfix shadow_">
                    <div class="textDiv">
                        <div class="inline_block gkText">
                            <span>顾客评价</span>
                        </div>
                        <div class="inline_block updateTime">
                            <span>评价数据更新时间 : ${dataUpdateTime}</span>
                        </div>
                    </div>
                    <div class="scope-content">
                        <div class="inline_block sText">
                            <div class="scopeVal"><h3>${scopeVal}</h3></div>
                            <div class="scopeText">
                                <span>综合评分</span>
                                <i class="fa fa-question-circle i_left" title="这是什么？"></i></div>
                        </div>
                        <div class="inline_block treeBlock">
                            <div class="treeBlock_text">
                                <div style="font-size: 20px;">
                                    全部评价数据
                                </div>
                            </div>
                            <div class="starItem-rate">
                                <div class="item_s">
                                    <div class="inline_block"><span>5星</span></div>
                                    <div class="layui-progress layui-progress-big inline_block" lay-showPercent="yes">
                                        <div class="layui-progress-bar layui-bg-red"
                                             lay-percent="${fiveStarPercentage}%"></div>
                                    </div>
                                </div>
                                <div class="item_s">
                                    <div class="inline_block"><span>4星</span></div>
                                    <div class="layui-progress layui-progress-big inline_block" lay-showPercent="yes">
                                        <div class="layui-progress-bar layui-bg-red"
                                             lay-percent="${fourStarPercentage}%"></div>
                                    </div>
                                </div>
                                <div class="item_s">
                                    <div class="inline_block"><span>3星</span></div>
                                    <div class="layui-progress layui-progress-big inline_block" lay-showPercent="yes">
                                        <div class="layui-progress-bar layui-bg-red"
                                             lay-percent="${threeStarPercentage}%"></div>
                                    </div>
                                </div>
                                <div class="item_s">
                                    <div class="inline_block"><span>2星</span></div>
                                    <div class="layui-progress layui-progress-big inline_block" lay-showPercent="yes">
                                        <div class="layui-progress-bar layui-bg-red"
                                             lay-percent="${twoStarPercentage}%"></div>
                                    </div>
                                </div>
                                <div class="item_s">
                                    <div class="inline_block"><span>1星</span></div>
                                    <div class="layui-progress layui-progress-big inline_block" lay-showPercent="yes">
                                        <div class="layui-progress-bar layui-bg-red"
                                             lay-percent="${oneStarPercentage}%"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="inline_block resultText">
                            <div class="res-item">
                                <div class="c-rate c-rate1"><span>评价回复率 : 0% (较差)</span></div>
                                <div class="c-text c-text1"><p>您还没有回复过用户评价哦~赶紧搭建和用户沟通的桥梁吧！</p></div>
                            </div>
                            <div class="res-item">
                                <div class="c-rate c-rate2"><span>差评回复率 : 0% (较差)</span></div>
                                <div class="c-text c-text1"><p>您还没有回复过顾客差评哦~动起来吧！</p></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="conditionBtn shadow_">
                    <div class="dateTimeDiv">
                        <div class="dateItemOne"><span>评价日期</span></div>
                        <c:choose>
                            <c:when test="${fn:length(queryDate) == 1}">
                                <div class="dateItemBtn dateItemFirst ${queryDate eq '0' ? "sel" : ""}">
                                    <button class="layui-btn layui-btn-primary" data-date="0">昨日(${yesterday})</button>
                                </div>
                                <div class="dateItemBtn dateItem ${queryDate eq '1' ? "sel" : ""}">
                                    <button class="layui-btn layui-btn-primary" data-date="1">近七天(${lastWeek})</button>
                                </div>
                                <div class="dateItemBtn dateItem mar-lf-5 ${queryDate eq '2' ? "sel" : ""}">
                                    <button class="layui-btn layui-btn-primary" data-date="2">近30天(${lastMonth})
                                    </button>
                                </div>
                                <div class="dateItemBtn dateItem ${queryDate eq '3' ? "sel" : ""}">
                                    <button class="layui-btn layui-btn-primary" data-date="3">近90天(${firstThreeMonth})
                                    </button>
                                </div>
                                <div class="dateItemBtn dateItem">
                                    <input placeholder="开始日期" readonly id="startDate" name="startDate" value>
                                    <span class="beforeIco"><i class="fa fa-calendar-check-o"
                                                               aria-hidden="true"></i></span>
                                </div>
                                <div class="dateItemBtn dateItem"><span>到</span></div>
                                <div class="dateItemBtn dateItem">
                                    <input placeholder="结束日期" readonly id="stopDate" name="stopDate" value>
                                    <span class="beforeIco"><i class="fa fa-calendar-check-o"
                                                               aria-hidden="true"></i></span>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:set value="${ fn:split(queryDate, '~') }" var="str"/>
                                <div class="dateItemBtn dateItemFirst">
                                    <button class="layui-btn layui-btn-primary" data-date="0">昨日(${yesterday})</button>
                                </div>
                                <div class="dateItemBtn dateItem">
                                    <button class="layui-btn layui-btn-primary" data-date="1">近七天(${lastWeek})</button>
                                </div>
                                <div class="dateItemBtn dateItem mar-lf-5">
                                    <button class="layui-btn layui-btn-primary" data-date="2">近30天(${lastMonth})
                                    </button>
                                </div>
                                <div class="dateItemBtn dateItem">
                                    <button class="layui-btn layui-btn-primary" data-date="3">近90天(${firstThreeMonth})
                                    </button>
                                </div>
                                <div class="dateItemBtn dateItem focusInput">
                                    <input placeholder="开始日期" readonly id="startDate" name="startDate"
                                           value="${str[0]}">
                                    <span class="beforeIco"><i class="fa fa-calendar-check-o"
                                                               aria-hidden="true"></i></span>
                                </div>
                                <div class="dateItemBtn dateItem"><span>到</span></div>
                                <div class="dateItemBtn dateItem focusInput">
                                    <input placeholder="结束日期" readonly id="stopDate" name="stopDate" value="${str[1]}">
                                    <span class="beforeIco">
                                        <i class="fa fa-calendar-check-o" aria-hidden="true"></i></span>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="starsDiv">
                        <div class="starItemOne"><span>评价星级</span></div>
                        <div class="starItemBtn starItemFirst ${starRating eq '0' ? "sel" : ""}">
                            <button class="layui-btn layui-btn-primary" data-star="0">全部</button>
                        </div>
                        <div class="starItemBtn starItem ${starRating eq '1' ? "sel" : ""}">
                            <button class="layui-btn layui-btn-primary" data-star="1">一星(${oneStar})</button>
                        </div>
                        <div class="starItemBtn starItem mar-lf-5 ${starRating eq '2' ? "sel" : ""}">
                            <button class="layui-btn layui-btn-primary" data-star="2">二星(${twoStar})</button>
                        </div>
                        <div class="starItemBtn starItem mar-lf-5 ${starRating eq '3' ? "sel" : ""}">
                            <button class="layui-btn layui-btn-primary" data-star="3">三星(${threeStar})</button>
                        </div>
                        <div class="starItemBtn starItem mar-lf-5 ${starRating eq '4' ? "sel" : ""}">
                            <button class="layui-btn layui-btn-primary" data-star="4">四星(${fourStar})</button>
                        </div>
                        <div class="starItemBtn starItem mar-lf-5 ${starRating eq '5' ? "sel" : ""}">
                            <button class="layui-btn layui-btn-primary" data-star="5">五星(${fiveStar})</button>
                        </div>
                    </div>
                </div>
                <div class="comment-list shadow_">
                    <div class="list-title-text">
                        <span class="checkIco"><i class="fa fa-commenting" aria-hidden="true"></i></span>
                        <span class="comment-downText">评论显示区域</span>
                    </div>
                    <div class="list-content">
                        <c:choose>
                            <c:when test="${data != null}">
                                <c:forEach items="${data}" var="each">
                                    <!-- 评价列表显示　开始 -->
                                    <div class="c-item">
                                        <div class="top-item">
                                            <div class="top-item-content clearfix">
                                                <div class="inline_block readyOnlyStar"
                                                     data-star="${each.commentsStars}"></div>
                                                <div class="inline_block content-date"><span>${each.commentTime}</span>
                                                </div>
                                                <div class="inline_block pName">
                                                    <span>${each.productNameStr}</span>
                                                </div>
                                                <div class="commentText">
                                                    <span>${each.content}</span>
                                                </div>
                                                <c:if test="${each.replyContent == null}">
                                                    <div class="commentBtn">
                                                        <button class="layui-btn-normal layui-btn"
                                                                data-commentId=${each.id}>
                                                            回复
                                                        </button>
                                                    </div>
                                                </c:if>
                                                <c:if test="${each.replyContent == null}">
                                                    <div class="replyInput" style="display: none">
                                                        <div class="replyInputChildDiv">
                                                            <textarea class="inputTextarea"
                                                                      placeholder="回复菜品评价"></textarea>
                                                        </div>
                                                        <div class="replyBtnDiv clearfix">
                                                            <div class="replyBtnItem1 replyBtnItem inline_block">
                                                                <button class="layui-btn layui-btn-normal noInputStyle"
                                                                        disabled="disabled">发布
                                                                </button>
                                                            </div>
                                                            <div class="replyBtnItem2 replyBtnItem inline_block">
                                                                <button class="layui-btn layui-btn-primary">取消</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:choose>
                                                    <c:when test="${each.replyContent != null}">
                                                        <div class="replyDiv">
                                                            <span class="rContent">
                                                                <span class="fGX"></span>
                                                                <span class="merRepText">您的回复：</span>
                                                                <span class="replyText">${each.replyContent}</span>
                                                            </span>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="replyDiv" style="display: none">
                                                            <span class="rContent">
                                                                <span class="fGX"></span>
                                                                <span class="merRepText">您的回复：</span>
                                                                <span class="replyText">${each.replyContent}</span>
                                                            </span>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                                <hr/>
                                            </div>
                                        </div>
                                        <div></div>
                                    </div>
                                </c:forEach>
                                <!-- 评价列表显示　结束 -->
                            </c:when>
                            <c:otherwise>
                                <!-- 无评价显示 开始 -->
                                <div class="showNoDataText">
                                    <span>暂无用户评价，继续加油</span>
                                </div>
                                <!-- 无评价显示 结束 -->
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <c:if test="${data != null}">
                    <div class="comment-list-page" style="height: 50px;background-color: #fff">
                        <div id="c-page"></div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
<%@include file="js.jsp" %>
<!-- 星级js -->
<script src="${staticFilePath}resource/custom/plugins/star/jquery.raty.min.js"></script>
<script type="text/javascript">
    layui.use(['element', 'util', 'layer', 'laydate', 'laypage'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var date = layui.laydate;
        var page = layui.laypage;

        var startDate = date.render({
            elem: "#startDate",
            theme: '#20A0FF',
            showBottom: false,
            trigger: 'click',
            min: -365,
            max: 0,
            //选中日期的回调
            ready: function () {
                //控件初始打开的回调   去除日期按钮的选中状态,改变日期输入框颜色
                var dateCondition = $("div.dateTimeDiv > div.dateItemBtn.sel");  //日期筛选条件中的按钮
                $(dateCondition).removeClass("sel");
                var inputDateList = $("div.dateTimeDiv > div.dateItemBtn > input");  //获取日期选择插件对象,是一个数组
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
                //选中日期后的回调
                var stopDateEle = $("#stopDate");  //得到结束日期Dom对象
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

        var stopDate = date.render({
            elem: "#stopDate",
            theme: '#20A0FF',
            showBottom: false,
            min: -365,
            max: 0,
            ready: function () {
                var dateCondition = $("div.dateTimeDiv > div.dateItemBtn.sel");  //日期筛选条件中的按钮
                $(dateCondition).removeClass("sel");
                var inputDateList = $("div.dateTimeDiv > div.dateItemBtn > input");  //获取日期选择插件对象,是一个数组
                if (inputDateList.length > 0) {
                    //curFocusInput
                    $(inputDateList).each(function (index, item) {
                        if (item.placeholder === '结束日期') {
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
                //小tips
                layer.tips('结束日期选择完成后，会自动按照筛选条件筛选评论数据', '#stopDate', {
                    tips: [1, '#3595CC']
                });
            },
            done: function (value) {
                //选中日期后的回调
                var startDateEle = $("#startDate");  //得到开始日期Dom对象
                var stopDateEle = $("#stopDate");  //截止日期Dom对象
                var starCondition = $("div.starsDiv > div.starItemBtn.sel > button");  //星级筛选条件
                var starVal = $(starCondition).attr("data-star");
                if (startDateEle.val() === undefined || startDateEle.val() === "") {
                    //日期开始值为undefined 或　为空字符串
                    layer.msg("开始日期值不能为空,默认改为截止日期的前七天", {anim: 6, time: 2000}, function () {
                            //关闭后的回调,设置开始日期
                            var offsetDate = offsetDateMethod(new Date(), -7);
                            var startDateVal = offsetDate.getFullYear() + "-" + (offsetDate.getMonth() + 1) + "-" + (offsetDate.getDate() <= 9 ? "0" + offsetDate.getDate() : offsetDate.getDate());
                            startDateEle.val(startDateVal);
                            setTimeout(function () {
                                window.location.href = baseUrl + "/merchant/groupBuy/comment/index" + "?date=" + (startDateVal + "~" + stopDateEle.val()) + "&starRating=" + starVal;
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
                    window.location.href = baseUrl + "/merchant/groupBuy/comment/index" + "?date=" + (startDateEle.val() + "~" + stopDateEle.val()) + "&starRating=" + starVal;
                }
            }
        });

        //不显示首页尾页
        page.render({
            elem: 'c-page'
            , count: ${page == null ? 0: page.pages * 10}
            , groups: 3
            , curr: ${page == null ? 1 : page.pageNum} //获取起始页
            , prev: "<"
            , next: ">"
            , theme: '#1E9FFF'
            , first: false
            , last: false
            , jump: function (obj, first) {
                if (!first) {
                    //不是第一页
                    //翻页时取得当前的条件：评价日期条件，评价星级条件
                    var curDateVal = "";
                    var dateBtnCondition = $("div.dateTimeDiv > div.dateItemBtn.sel");  //日期按钮条件
                    var dateInputCondition = $("div.dateTimeDiv > div.dateItemBtn.focusInput");  //日期输入框条件
                    if (dateBtnCondition.length === 1) {
                        curDateVal = $(dateBtnCondition).children().attr("data-date");
                    } else {
                        $(dateInputCondition).each(function (index, childItem) {
                            curDateVal += $(childItem).find("input").val() + "~";
                        });
                    }

                    var starCondition = $("div.starsDiv > div.starItemBtn.sel > button");  //星级筛选条件
                    var starVal = $(starCondition).attr("data-star");
//                    $(dateCondition).each(function (index, item) {
//                        if ($(item).hasClass("sel")) {
//                            curDateVal = $(item).children().attr("data-date");
//                        }
//                    });
//                    outerloop : $(dateCondition).each(function (index, item) {
//                        if ($(item).hasClass("focusInput")) {
//                            var siblingElements = $(item).siblings(".focusInput");
//                            innerloop : $(siblingElements).each(function (index, childItem) {
//                                if ($(childItem).find("input").val() !== "") {
//                                    curDateVal = $(childItem).find("input").val() + "~";
//                                }
//                            });
//                            layer.msg("当前时间条件为空,请选择时间条件", {anim: 6, time: 2000});
//                            return false;
//                        }
//                    })
                    window.location.href = baseUrl + "/merchant/groupBuy/comment/index" + "?date=" + curDateVal + "&starRating=" + starVal + "&pageNum=" + obj.curr;
                }
            }
        });

    });


    jQuery(function () {
        $(".readyOnlyStar").each(function (index, item) {
            var ele = $(this);
            var starVal = $(ele).attr("data-star");
            $(ele).raty({readOnly: true, score: starVal, path: baseUrl + '/resource/custom/images'});
        })
    });

    //日期筛选按钮点击
    $("div.dateTimeDiv > div.dateItemBtn > button").on("click", function () {
        var dateVal = $(this).attr("data-date");
        //取得星级条件
        var starCondition = $("div.starsDiv > div.starItemBtn.sel > button");
        var starVal = $(starCondition).attr("data-star");
        //携带当前条件查询数据
        window.location.href = baseUrl + "/merchant/groupBuy/comment/index" + "?date=" + dateVal + "&starRating=" + starVal;
    });

    //星级筛选按钮点击
    $("div.starsDiv > div.starItemBtn > button").on("click", function () {
        var starVal = $(this).attr("data-star");
        var dateVal = "";
        //取得日期条件
        var butEle = $("div.dateTimeDiv > div.dateItemBtn.sel > button");
        if ($(butEle).length == 1) {
            //选择器得到了对象
            dateVal = $(butEle).attr("data-date");
        } else {
            //取输入框的开始时间和结束时间值
            var startDateVal = $("#startDate").val();
            var stopDateVal = $("#stopDate").val();
            dateVal = startDateVal + "~" + stopDateVal;
        }
        //携带当前条件查询数据
        window.location.href = baseUrl + "/merchant/groupBuy/comment/index" + "?date=" + dateVal + "&starRating=" + starVal;
    });

    var commentId = 0;

    //绑定回复按钮点击
    $(".top-item-content > .commentBtn > button").on("click", function () {
        commentId = $(this).attr("data-commentId");
        var sel = $(this).parent();
        var sibling = sel.siblings(".replyInput");
        sel.css("display", "none"); //把回复按钮父div元素置为 不可见
        sibling.css("display", "block");  //回复输入框置为 可见
    });

    //回复输入框的发布按钮
    $(".top-item-content > .replyInput > .replyBtnDiv > .replyBtnItem1 > button").on("click", function () {
        var sel = $(this).parents("div.top-item-content").children("div.replyDiv");
        var ele = $(this);
        sel.css("display", "block");
        var resEle = sel.find("span.replyText");
        var inputVal = $(this).parents("div.replyInput").find("textarea.inputTextarea").val();
        //ajax异步请求后台，插入评论数据
        const url = baseUrl + "/merchant/replyMessageGiveUser";
        var params = {
            commentId: commentId,
            replyContent: inputVal
        };
        $.post(url, params, function (result, status) {
            if (status === "success") {
                if (result && result.code === 0) {
                    resEle.text(inputVal);
                    $(ele).parents("div.replyInput").css("display", "none");
                } else {
                    layer_msg(result.message, "error");
                }
            } else {
                layer_msg("网络连接失败", "exception");
            }
        });
        return false;
    });

    //回复输入框的取消按钮
    $(".top-item-content > .replyInput > .replyBtnDiv > .replyBtnItem2 > button").on("click", function () {
        var sel = $(this);
        $(sel).parents("div.replyInput").css("display", "none"); //输入框置为不可见
        $(sel).parents("div.top-item-content").children("div.commentBtn").css("display", "block");  //回复按钮置为　可见
    });

    //监听回复输入框
    $(".replyInputChildDiv > textarea").on("keyup", function () {
        var sel = $(this);
        var btnEle = sel.parent().parents("div.replyInput").children("div.replyBtnDiv").children(":first").children(":first");
        if (sel.val().length > 0) {
            btnEle.removeAttr("disabled");
            btnEle.removeClass("noInputStyle");
        } else {
            if (!btnEle.hasClass("noInputStyle")) {
                btnEle.attr("disabled", "disabled");
                btnEle.addClass("noInputStyle");
            }
        }
    })
</script>
</html>
