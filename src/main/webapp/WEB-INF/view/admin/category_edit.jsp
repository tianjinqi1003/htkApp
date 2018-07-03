<%--
  Created by IntelliJ IDEA.
  User: yinqilei
  Date: 17-8-15
  Time: 上午10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>回头客管理中心－分类修改页面</title>
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
                                <div class="panel-title">
                                    <div class="panel-heading">
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <span>分类信息</span>
                                            </div>
                                            <div class="col-sm-6">
                                                <span style="float: right"><a class="span_coursor"
                                                                              href="${sysPath}admin/takeoutCategory">返回</a></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
                                             class="demo-ruleForm">
                                        <el-form-item label="分类名称" prop="name" style="width: 60%">
                                            <el-input v-model="ruleForm.name" placeholder="请输入分类名称"></el-input>
                                        </el-form-item>
                                        <el-form-item label="分类图片" prop="photo">
                                            <el-upload
                                                    class="avatar-uploader"
                                                    action="${sysPath}admin/uploadCategoryPhoto"
                                                    :show-file-list="false"
                                                    :on-success="handleAvatarSuccess" v-model="ruleForm.photo"
                                                    :before-upload="beforeAvatarUpload">
                                                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                                            </el-upload>
                                        </el-form-item>
                                        <el-form-item label="分类描述" prop="desc" style="width: 60%">
                                            <el-input type="textarea" v-model="ruleForm.desc"
                                                      placeholder="请输入分类描述，不得多于50个字"></el-input>
                                        </el-form-item>
                                        <el-form-item v-show="t_add">
                                            <el-button type="primary" @click="t_addCategoryFormSubmit('ruleForm')">
                                                立即创建
                                            </el-button>
                                            <el-button @click="resetForm('ruleForm')">重置</el-button>
                                        </el-form-item>
                                        <el-form-item v-show="t_edit">
                                            <el-button type="primary" @click="t_saveCategoryFormSubmit('ruleForm')">
                                                保存修改
                                            </el-button>
                                            <el-button @click="cancelEditCategoryBtn">取消</el-button>
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
<script src="${staticFilePath}resource/admin/admin_category.js?v=${version}"></script>
<script src="${staticFilePath}resource/admin/jquery.app.js?v=${version}"></script>
<script src="${staticFilePath}resource/common/js/base.js?v=${version}"></script>
</body>
</html>
