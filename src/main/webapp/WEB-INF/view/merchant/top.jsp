<%@ page import="com.htkapp.modules.common.entity.LoginUser" %>
<%@ page import="com.htkapp.core.utils.Globals" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/9
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js?${date}"></script>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", -1);
    response.flushBuffer();
//    获取/login接口传过来的值
//    String state = (String)request.getSession().getAttribute("status");
	LoginUser loginUser = (LoginUser)request.getSession().getAttribute(Globals.MERCHANT_SESSION_USER);
    String status = (String)request.getSession().getAttribute("status");
    String shopName = (String)request.getSession().getAttribute("shopName");
    System.out.println("这是决定状态的参数"+status+"=================");
    System.out.print("这是决定店铺名字的参数"+shopName+"=================");
    System.out.print("这是登录资料的参数"+loginUser.toString()+"=================");
//    if(status==null||shopName==null){
//    	 response.setHeader("Refresh", "1;url=login");
    	// request.getRequestDispatcher("login").forward(request, response);
  //  	 return;
//    }
%>
<div class="layui-header">
    <div class="layui-row index">
        <div class="layui-col-md4 height">
            <div class="layui-row height">
                <div class="layui-col-md5 height">
                    <div class="index_top_logo margin-height">
                            <span class="index_top_logo_text">
                                <span class="index_top_logo_text_h">回头客</span>
                                <span>|</span>
                                <span>商家版</span>
                            </span>
                    </div>
                </div>
                <div class="layui-col-md7 height">
                    <div class="margin-height" style="margin-top: 1.5%">
                        <label class="search_">
                            <i class="layui-icon search_icon globalSearch">&#xe615;</i>
                        </label>
                        <input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="搜索12小时内订单"
                               class="layui-input search_input">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md5 layui-col-md-offset3 height">
            <div class="top clearfix height">
                <div class="fright otherBut">
                    <div class="shopName">
                        <%--${merchantUser.shopName}--%>
                        ${shopName}
                    </div>
                    <div class="shopStatus">
                        <a href="javascript:void(0);" class="status">
                            <%
                                out.print(status.equals("1")?"营业中" : "停止营业");
                            %>
                            <%--${merchantUser.state == "1" ? "营业中" : "停止营业"}--%>
                            <i class="fa fa-angle-down"></i></a>
                            <%--<i class="userId" type="hidden" value="${merchantUser.userId}"></i>--%>
                        <div class="statusSelect">
                            <div class="statusItem clearfix">
                                <a href="javascript:void(0)"
                                   class="${status =='1' ?'cur' : ''} openState changeState"
                                   data-id="1">营业中</a>
                                <p>当前餐厅处于设置的营业时间内，正常接受新订单</p>
                            </div>
                            <div class="statusItem clearfix">
                                <a href="javascript:void(0)"
                                   class="${status =='0' ? 'cur':'' } stopState changeState" data-id="0">停止营业</a>
                                <p>适用于较长时间停止提供服务，不接受任何订单，手动恢复营业后可正常接受订单</p>
                            </div>
                            <div class="statusItem clearfix">营业时间：9:00-20:00</div>
                        </div>
                    </div>
                    <div class="username">
                        <a href="javascript:void(0);" class="tel">${merchantUser.userName}<i
                                class="fa fa-angle-down"></i></a>
                        <div class="nameSelect">
                            <a href="${sysPath}merchant/shopInfo/account">修改密码</a>
                            <a class="logout_" data-type="merchant" href="javascript:void(0)">退出登录</a>
                        </div>
                    </div>
                    <a href="${sysPath}merchant/getNotificationCenterView" class="notice fa fa-bell-o globalIcon"
                       style="line-height: 50px;font-size: 20px"><span class="layui-badge-dot" style="top:-10px"></span></a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="layui-side layui-bg-black" style="background-color: #FFF!important">
    <ul class="layui-nav layui-nav-tree layui-bg-cyan layui-inline" lay-filter="demo"
        style="background-color: #FFF !important;color: black;margin: 0 !important;">
        <li class="layui-nav-item singleMenu"><a href="${sysPath}merchant/index" class="${m_index ? "sel" : ""}"><i
                class="layui-icon">&#xe68e;</i>首页</a></li>
        <li class="layui-nav-item ${pro_mark ? "layui-nav-itemed" : ""}">
            <a><i class="fa fa-shopping-basket" aria-hidden="true"></i>商品</a>
            <dl class="layui-nav-child">
                <shiro:hasPermission name="TAKEOUT">
                    <dd class="${pro_mark_t ? "layui-this" : ""}">
                        <a href="${sysPath}merchant/takeout/product/homePage">外卖</a>
                    </dd>
                </shiro:hasPermission>
                <shiro:hasPermission name="GROUPBUY">
                    <dd class="${pro_mark_g ? "layui-this" : ""}">
                        <a href="${sysPath}merchant/groupBuy/product/homePage">团购</a>
                    </dd>
                </shiro:hasPermission>
                <shiro:hasPermission name="BUFFETFOOD">
                    <dd class="${pro_mark_b ? "layui-this" : ""}">
                        <a href="${sysPath}merchant/buffetFood/product/homePage">自助点餐</a>
                    </dd>
                </shiro:hasPermission>
            </dl>
        </li>
        <li class="layui-nav-item ${ord_mark ? "layui-nav-itemed" : ""}">
            <a><i class="layui-icon">&#xe63c;</i>订单</a>
            <dl class="layui-nav-child">
                <shiro:hasPermission name="TAKEOUT">
                    <dd class="${ord_mark_t ? "layui-this" : ""}">
                        <a href="${sysPath}merchant/takeout/order/realTimeTakeoutOrder">外卖</a>
                    </dd>
                </shiro:hasPermission>
                <shiro:hasPermission name="GROUPBUY">
                    <dd class="${ord_mark_g ? "layui-this" : ""}">
                        <a href="${sysPath}merchant/groupBuy/getGroupBuyItemMesPage">团购</a>
                    </dd>
                </shiro:hasPermission>
                <shiro:hasPermission name="BUFFETFOOD">
                    <dd class="${ord_mark_b_q ? "layui-this" : ""}">
                        <a href="${sysPath}merchant/buffetFood/order/query">自助订单查询</a>
                    </dd>
                    <dd class="${ord_mark_b_h ? "layui-this" : ""}">
                        <a href="${sysPath}merchant/buffetFood/order/new">自助订单处理</a>
                    </dd>
                </shiro:hasPermission>
            </dl>
        </li>
        <li class="layui-nav-item ${com_mark ? "layui-nav-itemed" : ""}">
            <a><i class="fa fa-comments" aria-hidden="true"></i>售后评论</a>
            <dl class="layui-nav-child">
                <shiro:hasPermission name="TAKEOUT">
                    <dd class="${com_mark_t ? "layui-this" : ""}">
                        <a href="${sysPath}merchant/takeout/comment/index">外卖</a>
                    </dd>
                </shiro:hasPermission>
                <shiro:hasPermission name="GROUPBUY">
                    <dd class="${com_mark_g ? "layui-this" : ""}">
                        <a href="${sysPath}merchant/groupBuy/comment/index">团购</a>
                    </dd>
                </shiro:hasPermission>
                <shiro:hasPermission name="BUFFETFOOD">
                    <dd class="${com_mark_b ? "layui-this" : ""}"><a
                            href="${sysPath}merchant/buffetFood/comment/index">自助点餐</a>
                    </dd>
                </shiro:hasPermission>
            </dl>
        </li>
        <li class="layui-nav-item ${int_mark ? "layui-nav-itemed" : ""}">
            <a><i class="fa fa-tint" aria-hidden="true"></i>积分管理</a>
            <dl class="layui-nav-child">
                <dd class="${int_mark_list ? "layui-this" : ""}"><a href="${sysPath}merchant/integral/list">积分列表</a>
                </dd>
                <dd class="${int_mark_active ? "layui-this" : ""}"><a href="${sysPath}merchant/integral/getNewActivePage">积分活动</a></dd>
                <dd class="${int_mark_seatOrder ? "layui-this" : ""}"><a href="${sysPath}merchant/integral/seatOrder">订座订单</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item ${bil_mark ? "layui-nav-itemed" : ""}">
            <a><i class="fa fa-jpy" aria-hidden="true"></i>财务管理</a>
            <dl class="layui-nav-child">
                <dd class="${bil_mark_m ? "layui-this" : ""}"><a href="${sysPath}merchant/bill/billMoney">账单资金</a></dd>
                <dd class="${bil_mark_r ? "layui-this" : ""}"><a href="${sysPath}merchant/bill/billRecord">账单记录</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item ${sto_mark ? "layui-nav-itemed" : ""}">
            <a><i class="fa fa-link" aria-hidden="true"></i>门店信息</a>
            <dl class="layui-nav-child">
                <dd class="${sto_mark_s ? "layui-this" : ""}"><a href="${sysPath}merchant/shopInfo/store">店铺</a></dd>
                <dd class="${sto_mark_seat_manage ? "layui-this":""}"><a href="${sysPath}merchant/shopInfo/SeatInfoManage">座位管理</a></dd>
				<dd class="${sto_mark_seat_info ? "layui-this" : ""}"><a href="${sysPath}merchant/shopInfo/setSeatInfo">座位信息管理</a></dd>
            </dl>
        </li>
    </ul>
</div>
