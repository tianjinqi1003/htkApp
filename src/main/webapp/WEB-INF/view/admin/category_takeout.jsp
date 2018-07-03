<%--
  Created by IntelliJ IDEA.
  User: yinqilei
  Date: 17-8-14
  Time: 下午6:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>回头客管理中心－外卖分类管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="Coderthemes" name="author"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <%@include file="title.jsp" %>
    <%@include file="head.jsp" %>
    <style>
        .active_ {
            background-color: #f3f3f4;
        }

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
                        <div class="row" v-if="categoryList != null">
                            <div class="col-sm-2 left_list"
                                 style="box-shadow:rgba(0, 0, 0, 0.1) 0px 0px 7px !important;width: 13%;position: relative">
                                <ul class="list-group categoryList">
                                    <li class="list-group-item" v-bind:class="{active_ : each.show}"
                                        @click="clickCategory(each.id, each.mark)" v-for="each in categoryList">
                                        <a href="javascript:void(0)">{{ each.categoryName }}</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-sm-10 div_background_color" style="width:85%">
                                <div class="div_s_x" id="top" style="padding-left: 15px">
                                    <el-row type="flex" class="row-bg">
                                        <el-col :span="6">
                                            <div class="grid-content">
                                                <h3>{{ firstCategoryObj.categoryName }}&nbsp;&nbsp;
                                                    &nbsp;&nbsp;
                                                    <el-button size="small" @click="categoryEdit(firstCategoryObj.id)">
                                                        <i class="fa fa-pencil fa-fw"></i>编辑
                                                    </el-button>
                                                </h3>
                                            </div>
                                        </el-col>
                                        <el-col :span="6">
                                            <div class="grid-content bg-purple-light">
                                                <el-button size="small" @click="categoryAdd">
                                                    <i class="fa fa-pencil fa-fw"></i>添加分类
                                                </el-button>
                                            </div>
                                        </el-col>
                                        <el-col :span="6">
                                            <div class="grid-content bg-purple-light">
                                                <el-button size="small" @click="addChildCategory = true">
                                                    <i class="fa fa-pencil fa-fw"></i>添加子分类
                                                </el-button>
                                            </div>
                                        </el-col>
                                        <el-col :span="6">
                                            <div class="grid-content bg-purple-light">
                                                <el-button size="small" @click="addChildCategory = true">
                                                    <i class="fa fa-pencil fa-fw"></i>切换至团购分类
                                                </el-button>
                                            </div>
                                        </el-col>
                                    </el-row>
                                </div>
                                <div class="container">
                                    <div class="row">
                                        <template>
                                            <el-table
                                                    :data="tableData5"
                                                    border
                                                    style="width: 100%">
                                                <el-table-column
                                                        label="序号"
                                                        width="180">
                                                    <template scope="scope">
                                                        <span style="margin-left: 10px">{{ scope.$index}}</span>
                                                    </template>
                                                </el-table-column>
                                                <el-table-column
                                                        label="分类名称"
                                                        width="180">
                                                    <template scope="scope">
                                                        <div slot="reference" class="name-wrapper">
                                                            <el-tag>{{ scope.row.categoryName }}</el-tag>
                                                        </div>
                                                    </template>
                                                </el-table-column>
                                                <el-table-column
                                                        label="父分类ID"
                                                        width="180">
                                                    <template scope="scope">
                                                        <div slot="reference" class="name-wrapper">
                                                            <el-tag>{{ scope.row.parentId }}</el-tag>
                                                        </div>
                                                    </template>
                                                </el-table-column>
                                                <el-table-column label="操作">
                                                    <template scope="scope">
                                                        <el-button
                                                                size="small"
                                                                @click="handleEdit(scope.$index, scope.row)">编辑
                                                        </el-button>
                                                        <el-button
                                                                size="small"
                                                                type="danger"
                                                                @click="delCategoryById(scope.row.id)">删除
                                                        </el-button>
                                                    </template>
                                                </el-table-column>
                                            </el-table>
                                        </template>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div style="text-align: center">
                                    <p>当前下没有分类</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@include file="footer.jsp" %>
                </div>
            </div>
        </div>
    </div>
    <el-dialog
            title="添加新的子分类"
            :visible.sync="addChildCategory"
            size="tiny">
        <el-form :model="ruleForm"
                 :rules="rules" ref="ruleForm">
            <el-form-item label="子分类名称" prop="name">
                <el-input v-model="ruleForm.name"
                          placeholder="请输入5字以内的子分类名称"></el-input>
            </el-form-item>
            <el-form-item label="子分类描述" prop="desc">
                <el-input type="textarea" placeholder="请输入20字以内子分类描述（非必填）"
                          v-model="ruleForm.desc"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer" style="padding-top: 0">
    <el-button type="primary" @click="addChildSubmitForm('ruleForm',0)">保　存</el-button>
  </span>
    </el-dialog>
</div>
<%@include file="footer_js.jsp" %>
<script src="${staticFilePath}resource/admin/admin_category.js?v=${version}"></script>
<script src="${staticFilePath}resource/admin/jquery.app.js?v=${version}"></script>
<script src="${staticFilePath}resource/common/js/base.js?v=${version}"></script>
</body>
</html>
