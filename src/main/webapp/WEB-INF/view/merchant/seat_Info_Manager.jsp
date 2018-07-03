<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="IE_lang.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>座位信息管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<%@include file="head.jsp"%>
<style>
* {
	font-family: "微软雅黑";
}

.seatName {
	position: relative;
	right: 30px;
	bottom: 10px;
}

.orderAmount {
	position: relative;
	right: 30px;
	bottom: 10px;
}

.numberSeat {
	position: relative;
	left: 15px;
	top: 30px;
}

.orderTime {
	position: relative;
	right: 25px;
	bottom: 10px;
}

.seatInfo {
	border: 1px solid #000;
	width: 120px;
	height: 120px;
	margin: 20px 8px;
	padding: 20px;
	float: left;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius: 10px;
}

.tabListDiv {
	width: 80%;
	height: 100%;
	background-color: white;
	margin: auto;
	text-align: center;
}

#myModal {
	width: 50%;
	height: 60%;
	background-color: #fff;
	text-align: center;
	position: fixed;
	top: 10%;
	left: 450px;
}

.tableContent {
	border-collapse: collapse;
	margin: auto;
	width: 100%;
	height: 80%;
	font-size: 20px;
}

.tableContent, .titleInfo, .titleInfo>tr>th, .bodyInfo>tr>td {
	border: 1px solid #d2d2d2;
	text-align: center;
	height: 40px;
}

.toolDiv {
	text-align: right;
	margin: 20px;
}

.orderContent {
	width: 100%;
	height: 100%;
	overflow: scroll;
}

.selectBtn {
	position: fixed;
right: 205px;
bottom: 70px;
}
</style>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<%@include file="top.jsp"%>
		<div class="layui-body" style="background-color: #f3f3f4">
			<div class="tabListDiv">
				<c:choose>
					<c:when test="${list!=null}">
						<!-- 有东西 -->
						<c:forEach items="${list}" var="each" varStatus="i">
							<c:if test="${each.seatStatus==1 }">
								<div class="seatInfo" style="background-color: #cc5b67"
									seatStatus="${each.seatStatus }" shopId="${each.shopId }">
									<span class="seatName">${each.seatName}</span><br />
									<c:if test="${each.bfo!=null }">
										<span class="orderAmount">¥${each.bfo.orderAmount }</span>
										<br />
									</c:if>
									<c:choose>
										<c:when test="${each.bfo.orderTime!=null}">
											<span class="orderTime">${each.bfo.orderTime }</span>
										</c:when>
										<c:when test="${each.useSeatTime!=null}">
											<span class="orderTime">${each.useSeatTime }</span>
										</c:when>
									</c:choose>
									<span class="numberSeat">${each.numberSeat }</span><br />

								</div>
							</c:if>
							<c:if test="${each.seatStatus==0 }">
								<div class="seatInfo" style="background-color: #b0b0b0"
									seatStatus="${each.seatStatus }" shopId="${each.shopId }">
									<span class="seatName">${each.seatName}</span><br />
									<c:if test="${each.bfo!=null }">
										<span class="orderAmount">¥${each.bfo.orderAmount }</span>
										<br />
									</c:if>
									<span class="numberSeat">${each.numberSeat }</span><br />
								</div>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<span>目前没有任何座位，请添加座位</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<input type="button" value="选择订单" class="selectBtn btn btn-primary btn-lg" seatName="">
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
	<%@include file="js.jsp"%>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="orderContent">
			<div>
				<table class="tableContent">
					<thead class="titleInfo">
						<tr>
							<th>序号</th>
							<th>人数</th>
							<th>预定人</th>
							<th>预定时间</th>
							<th>预定手机号</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody class="bodyInfo">
					</tbody>
				</table>
			</div>
			<div class="toolDiv">
				<input type="button" value="安排座位"
					class="btn btn-primary selectInfoBtn" seatName="" />
				<button type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
$(function(){
	$(".seatInfo").on("click",function(){
		changeSeatState(this)
		var status=$(this).attr("seatStatus");
		if(status==0){
			var seatName=$(this).find(".seatName").html();
			$(".selectInfoBtn").attr("seatName",seatName)
			$(".selectBtn").attr("seatName",seatName)
		}
	})
	$(".selectBtn").on("click",function(){
		var seatName=$(".selectBtn").attr("seatName")
		if(seatName==''||seatName==null){
			alert("请选择闲置的座位")
			return
		}
		$("#myModal").modal('show')
		$(".selectInfoBtn").unbind();
		$(".selectInfoBtn").on("click",function(){
			addSeatOrderInfo(this);
		})
		addSeatOrder()
	})
})

function addSeatOrderInfo(order){
	var seatOrder=$(".bodyInfo").find("tr>td>input:checked").parent().parent();
	var seatName=$(".selectInfoBtn").attr("seatName");
	var orderNumber=$(seatOrder).find("input[class='doOrder']").attr("orderNumber");
	url=baseUrl +"/merchant/integral/updataSeatInfo"
	var params={
			seatName:seatName,
			orderNumber:orderNumber
	}
	$.post(url,params,function(data){
		if(data.code==0){
			alert(data.message)
			$(seatOrder).remove()
			addSeatOrder()
				$("#myModal").modal('hide')
		}
	})
	console.log(seatOrder)
}
function doTab(data,i){
	var table=$(".bodyInfo");
	var tr="<tr>"+
	"<td>"+i+"</td>"+
	"<td>"+data.seatCount+"</td>"+
	"<td>"+data.scheduledName+"</td>"+
	"<td>"+data.scheduledTime+"</td>"+
	"<td>"+data.seatPhone+"</td>"+
	"<td>"+(data.remarks==null?'顾客没有特殊要求':data.remarks)+"</td>"+
	"<td shopId="+data.shopId+"><input type='checkbox' class='doOrder' orderNumber='"+data.orderNumber+"'></td>"+
	"</tr>"
	table.append(tr);
}

function addSeatOrder(){
	url=baseUrl +"/merchant/integral/getSeatInfoByStatus"
	$.post(url,function(data){
			if(data.data!=null){
				$(".bodyInfo").empty()
				for(var i=1;i<=data.data.length;i++){
					doTab(data.data[i-1],i)
				}
			}else{
				$(".bodyInfo").empty()
				var table=$(".bodyInfo");
				var tr="<tr>"+
				"<td colspan='9'>目前没有未处理的订单</td>"+
				"</tr>"
				table.append(tr);
			}
	})
}
function changeSeatState(change){
		var myDate=new Date();
		console.log(change)
		var seatName=$(change).find(".seatName").html();
		var shopId=$(change).attr("shopId");
		var status=$(change).attr("seatStatus");
		var orderTime=myDate.toLocaleTimeString('chinese',{hour12:false})
		var time=$(change).find(".orderTime").html();
		console.log(orderTime);
		if(status==1){
			status=0;
		}else if(status==0){
			status=1;
		}
		const url = baseUrl + "/merchant/buffetFood/changeStatus";
		params={
				seatName:seatName,
				shopId:shopId,
				status:status,
				useSeatTime:orderTime
		}
		$.post(url,params,function(data){
			if(data.code==0){
				if(status==1){
					$(change).css("background-color","#cc5b67")
					$(change).attr("seatStatus",1)
				}else if(status==0){
					$(change).css("background-color","#b0b0b0")
					$(change).attr("seatStatus",0)
				}
				if(time==null&&status==1){
					var div=$(change).find(".seatName")
					var span="<br class='needDel'/><span class='orderTime'>"+orderTime+"</span>"
					div.after(span)
				}
				if(time!=null&&status==0){
					var span=$(change).find(".orderTime");
					var br=$(change).find(".needDel")
					console.log(span)
					span.remove();
					br.remove();
				}
				
			}else{
				aler("修改失败")
			}
		})
}

</script>


</body>
<script type="text/javascript">
    layui.use(['element', 'util', 'layer', 'laydate', 'laypage'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

        var page = layui.laypage;
        page.render({
            elem: 'page'
            , curr: ${page == null ? 1 : page.pageNum}
            , groups: 3
            , count: ${page == null ? 10: page.pages * 10}
            , theme: '#1E9FFF'
            , first: false
            , last: false
        });
    });
</script>

</html>