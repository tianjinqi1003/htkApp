<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/23
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java"
         pageEncoding="UTF-8" %>
<div class="layui-header">
    <div class="layui-logo" style="color: #fff;font-weight: 500">回头客后台管理</div>
    <ul class="layui-nav layui-layout-right">
        <li class="layui-nav-item">
            <a href="javascript:;;">
                <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                回头客
            </a>
            <dl class="layui-nav-child">
                <dd><a href="">基本资料</a></dd>
                <dd><a href="">安全设置</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a href="javascript:void(0)" data-type="admin" class="logout_">退出</a></li>
    </ul>
</div>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test">
            <li class="layui-nav-item ${home_page ? "layui-this" : ""}"><a href="${sysPath}admin/index">管理首页</a></li>
            <li class="layui-nav-item ${advert_page ? "layui-this" : ""}"><a href="javascript:void(0)">广告管理</a></li>
            <li class="layui-nav-item ${app_page ? "layui-this" : ""}"><a href="${sysPath}admin/appVersionManage/index">app管理</a></li>
            <li class="layui-nav-item ${user_page ? "layui-this" : ""}"><a href="${sysPath}admin/manageUser">用户管理</a></li>
            <li class="layui-nav-item ${category_page ? "layui-this" : ""}"><a href="${sysPath}admin/category/takeoutCategory">分类管理</a></li>
            <li class="layui-nav-item ${permission_page ? "layui-this" : ""}"><a href="${sysPath}admin/permissionPage">权限管理</a></li>
            <li class="layui-nav-item ${registerApp_page ? "layui-this" : ""}"><a href="${sysPath}admin/registerApplyList">注册申请列表</a></li>
        </ul>
    </div>
</div>