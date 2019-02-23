<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = getServletContext().getContextPath(); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入jQuery -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js?${date}"></script>
<script type="text/javascript">
var path='<%=path%>';
$(function(){
	$.post(path+"/merchant/autoEnterReceipt",
			function(){
				
			}
		,"json");
	
	window.opener=null;
	window.open('','_self');
	window.close();
});
</script>
<title>Insert title here</title>
</head>
<body>

</body>
</html>