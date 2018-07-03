<%--
  Created by IntelliJ IDEA.
  User: terabithia
  Date: 1/3/18
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp"%>
<head>
<title>订座订单管理页面</title>
<meta charset="utf-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<%@include file="head.jsp"%>
<style type="text/css">
* {
	font-family: "微软雅黑";
}

.useBtn {
	margin: auto 10px;
}

.seatOrder {
	width: 95%;
	height: 100%;
	background-color: white;
	margin: auto;
	padding-top: 10px;
	text-align: center;
}

.body-content {
	height: 100%;
}

.orderContent {
	margin: auto 80px;
}

.codition {
	margin: auto 80px;
	text-align: left;
}

.dateCondition {
	background-color: white;
	margin: 10px auto;
	width: 95%;
	padding: 10px 0;
}

.contentCondition {
	background-color: #fff;
	padding: 0 65px;
}

.contentCondition>label.labelName {
	padding: 15px;
	font-size: 15px;
	margin-bottom: 0;
}

.contentCondition>div.dateInputDiv {
	display: inline-block;
}

.contentCondition>div.dateInputDiv>input {
	height: 30px;
	border-radius: 2px;
	border: 1px solid #e2e2e2;
	padding-left: 10px;
	width: 220px;
}

.contentCondition>div.dateInputDiv>button {
	height: 30px;
	line-height: 30px;
	/*padding-left: 50px;*/
	margin-left: 50px;
	position: relative;
	top: -2px;
}

.page {
	font-size: 15px;
	text-align: center;
	font-weight: 2px;
}
</style>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<%@include file="top.jsp"%>
		<div class="layui-body" style="background-color: #f3f3f4">
			<div class="body-content">
				<div class="dateCondition">
					<div class="contentCondition">
						<label class="labelName">选择日期:</label>
						<div class="dateInputDiv">
							<input id="date" readonly placeholder="点击选择筛选日期" name="date"
								value="">
							<button class="layui-btn-normal layui-btn nowQueryBtn">查询</button>
							<button class="layui-btn-normal layui-btn cancelOrder">取消预订</button>
						</div>
					</div>
				</div>
				<div class="seatOrder">

					<div class="orderContent">
						<div class="tableContent">
							<table class="layui-table">
								<colgroup>
									<col>
									<col width="150">
								</colgroup>
								<thead>
									<tr>
										<th>序号</th>
										<th>订单号</th>
										<th>人数</th>
										<th>座位名</th>
										<th>预定人</th>
										<th>预定时间</th>
										<th>预定手机号</th>
										<th>备注</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${p.list != null}">
											<c:forEach items="${p.list }" var="each" varStatus="i">
												<tr>
													<td>${i.index }</td>
													<td>${each.orderNumber}</td>
													<td>${each.seatCount}</td>
													<td>${each.seatName }</td>
													<td>${each.scheduledName }</td>
													<td>${each.scheduledTime }</td>
													<td>${each.seatPhone }</td>
													<td>${each.remarks }</td>
													<td>${each.status==0?"未处理":(each.status==1?"已处理":"订单撤销") }</td>
													<td><input type="checkbox" class="doOrder"
														data-orderNumber="${each.orderNumber}"></td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="9">目前没有预定订单</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
						<div class="row item col-md-12 page">
							<p>一共${p.pages}页</p>
							<a href="seatOrder?pageNum=${p.firstPage}">第一页</a> <a
								href="seatOrder?pageNum=${p.nextPage}">下一页</a> <a
								href="seatOrder?pageNum=${p.prePage}">上一页</a> <a
								href="seatOrder?pageNum=${p.lastPage}">最后页</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
<%@include file="js.jsp"%>
<script type="text/javascript">
	$(function() {
		$('input, textarea').placeholder();
	});
	layui.use([ 'element', 'util', 'layer', 'laypage' ], function() {
		var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
		var util = layui.util;
		var page = layui.laypage;

		page.render({
			elem : 'page',
			count : 100,
			groups : 3,
			curr : 1,
			prev : "<"
            , next: ">",
			theme : '#1E9FFF',
			first : false,
			last : false
		});
	});
	layui.use([ 'element', 'util', 'layer', 'laydate', 'laypage' ], function() {
		var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
		var date = layui.laydate;
		date.render({
			elem : '#date',
			theme : '#20A0FF',
			trigger : 'click',
			max : 0,
			min : -365,
			range : '~',
			format : 'yyyy-MM-dd'
		})
	});
	$("button.nowQueryBtn").on(
			"click",
			function() {
				var dateVal = $("#date").val();
				if (dateVal === '' || dateVal === undefined) {
					return false;
				}
				var dateValList = dateVal.split("~");
				window.location.href = baseUrl
						+ "/merchant/integral/seatOrder?startTime="
						+ dateValList[0] + "&endTime=" + dateValList[1];
			})
	$(".cancelOrder").on("click", function() {
		var allOrder = $(".doOrder")
		var selectedIds = "";
		for (var i = 0; i < allOrder.length; i++) {
			var single = allOrder[i];
			var ifChecked = single.checked;
			if (ifChecked) {
				selectedIds = selectedIds + "," + single.dataset.ordernumber;
			}
		}
		if (selectedIds == "")
			return false;
		var url=baseUrl+"/merchant/integral/deleteSeatOrder";
		  var params = {"selectedIds":selectedIds.substring(1,selectedIds.length)};
          $.post(url,params,function (result, status) {
              if(status === 'success'){
                  if (result && result.code === 0){
                  	layer_msg(result.message,'success');
                      location.reload();
                      return false;
                  }else {
                      layer_msg(result.message,'error');
                      return false;
                  }
              }else {
                  layer_msg('网络连接异常','exception');
                  return false;
              }
          },"json");
	})
</script>
</html>
