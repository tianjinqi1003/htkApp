<%--
  Created by IntelliJ IDEA.
  User: yinqilei
  Date: 17-8-14
  Time: 下午6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>回头客管理中心－app版本管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="Coderthemes" name="author"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <%@include file="title.jsp" %>
    <%@include file="head.jsp" %>
    <style>
        .content-page {
            margin-left: 0;

        }

        .content-page > .content {
            margin-top: 0;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin" id="wrapper">
    <%@include file="top.jsp" %>
    <div class="layui-body">
        <div class="content-page" style="margin-left: 0">
            <div class="content" style="margin-top: 0">
                <div class="container">
                    <div class="wrapper wrapper-content animated fadeInRight">
                        <div class="row" style="margin: auto;width: 95%;">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="col-sm-6">
                                        <el-input
                                                placeholder="请输入版本号查找"
                                                icon="search"
                                                v-model="searchI"
                                                :on-icon-click="vSearchClick">
                                        </el-input>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <el-button @click="vAddBtn">添加版本号</el-button>
                                            </div>
                                            <div class="col-sm-4">
                                                <el-button>用户app</el-button>
                                            </div>
                                            <div class="col-sm-4">
                                                <el-button>商家app</el-button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <template>
                                <el-table
                                        :data="versionTableData"
                                        border
                                        style="width: 100%">
                                    <el-table-column
                                            prop="appId"
                                            label="appId"
                                            width="100">
                                    </el-table-column>
                                    <el-table-column
                                            label="app名称"
                                            width="120">
                                        <template scope="scope">
                                            <div slot="reference" class="name-wrapper">
                                                <el-tag>{{ scope.row.appName }}</el-tag>
                                            </div>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            label="app版本号"
                                            width="120">
                                        <template scope="scope">
                                            <div slot="reference" class="name-wrapper">
                                                <el-tag>{{ scope.row.clientVersion }}</el-tag>
                                            </div>
                                        </template>
                                    </el-table-column>
                                    <el-table-column
                                            prop="downloadUrl"
                                            :formatter="subStringDownloadUrl"
                                            label="下载地址"
                                            width="200">
                                    </el-table-column>
                                    <el-table-column
                                            prop="uploadLog"
                                            :formatter="subStringUploadLog"
                                            label="更新日志"
                                            width="200">
                                    </el-table-column>
                                    <el-table-column label="操作">
                                        <template scope="scope">
                                            <div style="text-align: center">
                                                <el-button
                                                        size="small"
                                                        @click="vEditBtn(scope.row.id)">编辑
                                                </el-button>
                                                <el-button
                                                        size="small"
                                                        type="danger"
                                                        @click="vDelBtn(scope.row.id)">删除
                                                </el-button>
                                            </div>
                                        </template>
                                    </el-table-column>
                                </el-table>
                            </template>
                        </div>
                    </div>
                    <%@include file="footer.jsp" %>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="footer_js.jsp" %>
<script src="${staticFilePath}resource/admin/admin_version.js?v=${version}"></script>
<script src="${staticFilePath}resource/admin/jquery.app.js?v=${version}"></script>
<script src="${staticFilePath}resource/common/js/base.js?v=${version}"></script>
</body>
</html>
