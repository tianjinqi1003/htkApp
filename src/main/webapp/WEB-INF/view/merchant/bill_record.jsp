<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/26
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title></title>
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
            <div class="content">
                <div class="myBillContent">
                    <div class="text-h3">
                        <h3>我的账单</h3>
                    </div>
                    <div class="dateBtnDiv">
                        <c:choose>
                            <c:when test="${fn:length(queryDate) == 1}">
                                <div class="itemBtn inline_block">
                                    <a data-date="1" class="${queryDate eq '1' ? "sel" : ""}">近 7 天</a>
                                </div>
                                <div class="itemBtn inline_block">
                                    <a data-date="2" class="${queryDate eq '2' ? "sel" : ""}">近 1 个月</a>
                                </div>
                                <div class="itemBtn inline_block">
                                    <a data-date="3" class="${queryDate eq '3' ? "sel" : ""}">近 3 个月</a>
                                </div>
                                <div class="inputDate inline_block">
                                    <input name="inputDate" id="inputDate" placeholder="选择日期范围" class="inputDate_"
                                           style="width: 260px"/>
                                    <span class="icon_cal"><i class="fa fa-calendar-check-o"
                                                              aria-hidden="true"></i></span>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="itemBtn inline_block">
                                    <a data-date="1">近 7 天</a>
                                </div>
                                <div class="itemBtn inline_block">
                                    <a data-date="2">近 1 个月</a>
                                </div>
                                <div class="itemBtn inline_block">
                                    <a data-date="3">近 3 个月</a>
                                </div>
                                <div class="inputDate inline_block">
                                    <input name="inputDate" id="inputDate" placeholder="选择日期范围" class="inputDate_"
                                           style="width: 260px" value="${queryDate}"/>
                                    <span class="icon_cal">
                                        <i class="fa fa-calendar-check-o" aria-hidden="true"></i></span>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="tableDiv">
                    <div class="tabTopBtn clearfix">
                        <span>待入账 0.00 元。如果有退单、取消等 , 待入账金额可能会与实际入账金额不同。</span>
                        <div class="inline_block">
                            <button class="layui-btn-primary layui-btn layui-btn-disabled" disabled="disabled">导出账单
                            </button>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${data != null}">
                            <div class="table-content">
                                <table class="layui-table">
                                    <colgroup>
                                        <col width="150">
                                        <col width="200">
                                        <col>
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th>账单日期</th>
                                        <th class="sr">本期订单收入</th>
                                        <th class="zc">本期订单支出</th>
                                        <th class="rz">入账金额</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${data}" var="each">
                                        <tr>
                                            <td>${each.gmtCreate}</td>
                                            <td class="sr">+${each.orderIncome}</td>
                                            <td class="zc">-${each.spendingOnOrder}</td>
                                            <td class="rz">+${each.amount}</td>
                                            <td class="t-detail"><a
                                                    href="${sysPath}merchant/bill/billRecord/detail?date=${each.gmtCreate}&recordStatus=${each.status}">账单详情</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div class="pageMes" style="padding-bottom: 50px;">
                                    <div id="page" class="inline_block"></div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="showNoDataText" style="text-align: center;">
                                <div class="noOrder" style="padding-top: 20px;background-color: inherit">
                                    <div class="noOrder-content">
                                        <div class="noOrder-content-div">
                                            <div class="wane">
                                                <i class="fa fa-file-text-o wane-icon" aria-hidden="true"
                                                   style="font-size: 100px;line-height: 170px;color: #fff"></i>
                                            </div>
                                            <p class="noOrder-p">暂无账单记录</p>
                                            <span class="noOrder-span">暂时没有该筛选条件的记录!</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
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
        var date = layui.laydate;
        var page = layui.laypage;

        //常规用法
        date.render({
            elem: '#inputDate'
            , type: 'date'
            , theme: '#20A0FF'
            , min: -365
            , max: 0
            , range: '~'
            , format: 'yyyy-MM-dd'
            , ready: function () {
                //控件打开
                var dateCondition = $("div.dateBtnDiv > div.itemBtn > a.sel");  //日期筛选条件中的按钮
                $(dateCondition).removeClass("sel");
                var inputDateList = $("div.dateBtnDiv > div.inputDate > input");  //获取日期选择插件对象,是一个数组
                if (inputDateList.length > 0) {
                    //为当前选中的input 输入框父Dom对象添加 选中类样式
                    var ele = $(inputDateList).parent();
                    ele.addClass("focusInput");
                }
            }
            , done: function (value) {
                //取类型值
                window.location.href = baseUrl + "/merchant/bill/billRecord?date=" + value;
            }
        });

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
                    var val = $(".itemBtn.inline_block > a.sel").attr("data-date");
                    if(val === undefined) {
                        //取输入框的值
                        val = $("#inputDate").val()
                    }
                    window.location.href = baseUrl + "/merchant/bill/billRecord?date="+val+"&pageNum=" + obj.curr
                }
            }
        });

    });

    //日期文字按钮绑定点击事件
    $("div.dateBtnDiv > div.itemBtn > a").on("click", function () {
        var eleVal = $(this).attr("data-date");
//        var typeVal = $("div.stateBtnDiv > div.itemBtn > a.sel").attr("data-type");
        window.location.href = baseUrl + "/merchant/bill/billRecord?date=" + eleVal;
    })
</script>
</html>
