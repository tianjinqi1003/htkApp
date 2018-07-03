<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/9
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<script src="${staticFilePath}resource/custom/plugins/layui/layui.js?${date}"></script>
<!-- 引入jQuery -->
<script src="${staticFilePath}resource/plugins/assets/laydate/jquery-3.3.1.js"></script>
<script src="${staticFilePath}resource/plugins/assets/laydate/jQuery.print.js" ></script>
<script src="${staticFilePath}resource/plugins/assets/laydate/jquery.PrintArea.js" ></script>
<!-- 引入兼容IE9 Input placeholder插件 -->
<script type="text/javascript" src="http://www.ijquery.cn/js/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="${staticFilePath}resource/custom/js/base.js?${date}"></script>
<script type="text/javascript" src="${staticFilePath}resource/custom/js/layer_.js?${date}"></script>
<script src="${staticFilePath}resource/custom/js/pNotify.js?${date}"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${staticFilePath}resource/custom/js/public.js?${date}"></script>
<script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
<script src="${staticFilePath}resource/custom/plugins/PNotify/pnotify.custom.min.js"></script>
<script src="${staticFilePath}resource/custom/plugins/baseJs/base64.min.js"></script>
<!--

//-->
<script>
    var goEasy = new GoEasy({
        appkey: goEasyKey
    });
    goEasy.subscribe({
        channel: '${userKey}',
        onMessage: function (message) {
            if (message.content !== null && message.content !== "" && message.content !== undefined) {
                tipsHandle(JSON.parse(message.content), "${staticFilePath}");
                var obj=JSON.parse(message.content)
                var url=window.location.href
                if(url.indexOf("/order/query")!=-1&&obj.statusCode==3){
                	 setTimeout(function(){location.reload()},5000);
                }else if(url.indexOf("/order/new")!=-1&&obj.statusCode==1){
                	 setTimeout(function(){location.reload()},5000);
                }else if(url.indexOf("/order/edit")!=-1&&obj.statusCode==3){
                	 setTimeout(function(){location.reload()},5000);
                }else if(url.indexOf("/order/reminder")!=-1&&obj.statusCode==4){
                	 setTimeout(function(){location.reload()},5000);
                }
            }
            return false;
        }
    });
</script>
