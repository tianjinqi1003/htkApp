<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<%@include file="IE_lang.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>骑手管理</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<%@include file="head.jsp" %>
	<style>
	    .layui-body {
	        background-color: #f3f3f4;
	    }
	
	    .body-content {
	        padding: 15px;
	        background-color: #fff;
	        margin: 15px;
	    }
	
	    .body-content {
	        height: auto;
	    }
	</style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
	<%@include file="top.jsp" %>
    <div class="layui-body">
        <div class="body-content">
            <div class="content">
                <div>
	                <c:choose>
	                	<c:when test="${data != null}">
	                	a
	                	</c:when>
	                	<c:otherwise>
	                	</c:otherwise>
	                </c:choose>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>