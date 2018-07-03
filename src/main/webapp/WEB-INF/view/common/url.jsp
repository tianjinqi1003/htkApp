<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    String staticFilePath = "http://120.27.5.36:8080/htkApp/";
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    response.flushBuffer();
%>
<c:set var="sysPath" value="<%=basePath %>" />



<c:set var="staticFilePath" value="<%=basePath%>" />
<%--马鹏昊修改--%>
<%--<c:set var="staticFilePath" value="<%=staticFilePath%>" />--%>
