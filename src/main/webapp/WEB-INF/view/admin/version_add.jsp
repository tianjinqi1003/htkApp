
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>回头客管理中心－添加app版本记录</title>
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
        <div class="content-page">
            <div class="content">
                <div class="container">
                    <div class="wrapper wrapper-content animated fadeInRight">
                        <div class="row" style="width: 80%;margin: auto">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-sm-6">
                                                    <span>基本信息</span>
                                                </div>
                                                <div class="col-sm-6">
                                                    <a style="float: right;" class="span_coursor"
                                                       href="${sysPath}admin/appVersionManage/index">返回</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <el-form :model="versionRuleForm" :rules="versionRules" ref="versionRuleForm"
                                             label-width="100px" style="width: 90%">
                                        <el-form-item label="版本id" prop="name" style="width: 40%;">
                                            <!--<el-input v-model="versionRuleForm.name"></el-input>-->
                                            <el-input
                                                    placeholder="appId"
                                                    v-model="versionId"
                                                    :disabled="true">
                                            </el-input>
                                        </el-form-item>
                                        <el-form-item label="app名称" prop="region" style="width: 40%;">
                                            <el-input
                                                    placeholder="app名称"
                                                    v-model="appName"
                                                    :disabled="true">
                                            </el-input>
                                        </el-form-item>
                                        <el-form-item label="当前版本号" required style="width: 40%;">
                                            <el-input
                                                    placeholder="请输入内容"
                                                    v-model="oldVersionNumber"
                                                    :disabled="true">
                                            </el-input>
                                        </el-form-item>
                                        <el-form-item label="新版本号" required>
                                            <el-input-number v-model="versionNumber" @change="versionAddNumber"
                                                             :min="1.0" :max="10"></el-input-number>
                                        </el-form-item>
                                        <el-form-item label="下载地址" required style="width: 80%;">
                                            <el-input placeholder="请输入内容" v-model="appDownloadUrl" :disabled="true">
                                                <template slot="prepend">Http://</template>
                                            </el-input>
                                        </el-form-item>
                                        <el-form-item label="更新日志" prop="desc" style="width: 80%;">
                                            <el-input type="textarea" v-model="versionRuleForm.desc"
                                                      placeholder="请输入更新日志"></el-input>
                                        </el-form-item>
                                        <el-form-item label="上传文件" required>
                                            <el-upload
                                                    :drag="true"
                                                    :show-file-list="false"
                                                    action="https://jsonplaceholder.typicode.com/posts/"
                                                    :multiple="false">
                                                <i class="el-icon-upload"></i>
                                                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                                                <div class="el-upload__tip" slot="tip">只能上传apk文件</div>
                                            </el-upload>
                                        </el-form-item>
                                        <el-form-item>
                                            <el-button type="primary" @click="versionSubmitForm('versionRuleForm')">
                                                立即添加
                                            </el-button>
                                            <el-button @click="versionResetForm('versionRuleForm')">重置全部</el-button>
                                        </el-form-item>
                                    </el-form>
                                </div>
                            </div>
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
