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

th{
background-color: #c9defd;
}

.useBtn{
margin: auto 5px;
}

.buttons {
	width: 80%;
	margin: auto;
}

.tabListDiv {
	width: 50%;
	height: 100%;
	background-color: white;
	margin: auto;
	padding-top: 10px;
	text-align: center;
}

.mainTab {
	border-collapse: collapse;
	margin: auto;
	width: 80%;
	height: 80%;
	font-size: 20px;
}

.mainTab, .seatTab, .seatTab>td, .seatTab>th {
	border: 1px solid #d2d2d2;
	text-align: center;
	height: 40px;
}
.tog {
	width: 100%;
	padding: 0px;
}

</style>
<style>
* {
	font-family: "微软雅黑";
}


.szhong {
	font-size: 13px;
	text-align: right;
	height: 20px;
	text-align: right;
}

.tog {
	width: 100%;
	padding: 0px;
}

.xiangqing {
	margin-top: 0px;
}

.row {
	margin-top: 20px;
	background-color: white;
	height: 100%;
	background-color: white;
	width: 100%;
	float: right;
	margin-right: 1px;
	padding-left: 0px;
	padding-right: 0px;
	padding-bottom: 10px;
	border-bottom: 2px solid #ddd;
}



.xuhao {
	background-color: lightgray;
	margin-top: 0px;
	height: 40px;
	line-height: 40px;
	padding-left: 0px;
}

.bt {
	width: 10%;
	height: 30px;
	background-color: white;
	border: 1px solid dodgerblue;
	border-radius: 5px;
	color: lightskyblue;
	margin-top: 12px;
}

.bt-primary {
	background-color: dodgerblue;
	color: white;
}
</style>
<style>


.tog {
	width: 100%;
	padding: 0px;
}

.xiangqing {
	margin-top: 0px;
}

.row {
	margin-top: 20px;
	background-color: white;
	height: 100%;
	background-color: white;
	width: 100%;
	float: right;
	margin-right: 1px;
	padding-left: 0px;
	padding-right: 0px;
	padding-bottom: 10px;
	border-bottom: 2px solid #ddd;
}


.xuhao {
	background-color: lightgray;
	margin-top: 0px;
	height: 40px;
	line-height: 40px;
	padding-left: 0px;
}

.bt {
	width: 10%;
	height: 30px;
	background-color: white;
	border: 1px solid dodgerblue;
	border-radius: 5px;
	color: lightskyblue;
	margin-top: 12px;
}

.bt-primary {
	background-color: dodgerblue;
	color: white;
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
						<div>
							<table class="mainTab">
								<tr class="seatTab">
									<th class="num">编号</th>
									<th>座位名称</th>
									<th>座位人数</th>
									<th>备注</th>
									<th></th>
								</tr>
								<c:forEach items="${list}" var="each" varStatus="i">
									<tr class="seatTab">
										<td value="${each.id }" class="num">${i.index+1}</td>
										<td>${each.seatName }</td>
										<td>${each.numberSeat }</td>
										<td></td>
										<td><input type="checkbox" name="info"
											shopId="${data.shopId }" class="checkBtn"></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<table border="0" class="mainTab">
								<tr>
									<th></th>
									<th>编号</th>
									<th>操作</th>
									<th>座位编号</th>
									<th>座位人数</th>
								</tr>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="buttons">
					<input type="button" value="添加座位" class="btn btn-info useBtn"id="addSeatInformation" shopId="${data.shopId }"> 
					<input type="button" value="删除座位" class="btn btn-danger useBtn" id="delSeatInfo"> 
					<input type="button" value="调整座位" class="btn btn-info useBtn" id="changeSeatInfo" shopId="${data.shopId }">
				</div>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
	<%@include file="js.jsp"%>
	<div class="modal fade " id="message" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<!--这里id和上面那个动态值一样-->
		<div class="modal-dialog">
			<div style="border-radius: 6px;" class="row col-md-12">
				<!--序号-->
				<h3 style="padding-left: 0px; border-radius: 6px 6px 0 0;"
					class="xuhao col-md-12">
					<b class="col-md-12" id="opMessage"></b>
				</h3>
				<hr />
				<div class="tog">
					<span class="xiangqing col-md-12" style="" id="delSeatInfoName"></span>
				</div>
				<span class="xiangqing col-md-12" style="">
					<button style="width: 15%; border-radius: 6px;" id="delMessage"
						type="button" class="bt bt-primary col-md-4">确定</button> <input
					style="float: left; margin-left: 30px; width: 15%; border-radius: 6px;"
					data-dismiss="modal" type="button" class="bt bt-default col-md-4 "
					value="取消" />
				</span>
			</div>
		</div>
	</div>

	<div class="modal fade " id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<!--这里id和上面那个动态值一样-->
		<div class="modal-dialog">
			<div style="border-radius: 6px;" class="row col-md-12">
				<!--序号-->
				<h3 style="padding-left: 0px; border-radius: 6px 6px 0 0;"
					class="xuhao col-md-12">
					<b class="col-md-12" id="changeMsg"></b>
				</h3>
				<hr />
				<div class="tog">
					<form action="" method="post" class="xiangqing col-md-12">
						座位编号： <input type="text" id="seatName" value=""></br> 座位人数： <input
							type="text" id="numberSeat" value=""></br>
					</form>
				</div>
				<span class="xiangqing col-md-12" style="">
					<button style="width: 15%; border-radius: 6px;" id="myModalc"
						type="button" class="bt bt-primary col-md-4">添加</button> <input
					style="float: left; margin-left: 30px; width: 15%; border-radius: 6px;"
					data-dismiss="modal" type="button" class="bt bt-default col-md-4 "
					value="取消" />
				</span>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		$("#addSeatInformation").on("click",function(){
			$("#myModalc").unbind();
			$("#myModal").modal('show')
			$("#changeMsg").html("添加座位信息");
			$("#myModalc").on("click",function(){
				submitData()
			})
		})
		
		
	$("body").on("click","#changeSeatInfo",function(){
		$("#myModalc").unbind();
		var infometion=$(".checkBtn[type='checkbox']:checked").parent().parent();
		if(infometion.length>0){
			if(infometion.length>1){
				alert("请勿修改多个座位")
				return;
			}
			$("#myModal").modal('show')
			var seatName=$(infometion).find("td:eq(1)").html();
			var numberSeat=$(infometion).find("td:eq(2)").html();
			$("#seatName").val(seatName);
			$("#numberSeat").val(numberSeat);
		$("#changeMsg").html("修改座位信息");
		$("#myModalc").on("click",function(){
			updataSeatInfo(infometion)
		})
		}else{
			alert("请选择一个需要修改的座位")
		}
	})
	
	$("body").on("click","#delSeatInfo",function(){
		$("#delMessage").unbind();
		var infometion=$(".checkBtn[type='checkbox']:checked").parent().parent();
		if(infometion.length>0){
			if(infometion.length>1){
				alert("请勿一次删除多个座位")
				return;
			}
			$("#message").modal("show")
			var seatName=$(infometion).find("td:eq(1)").html();
				$("#opMessage").html("删除操作");
				$("#delSeatInfoName").html("确定要删除‘"+seatName+"’么？");
				$("#delMessage").on("click",function(){
					delSeatInfo()
				})
		}else{
			alert("请选择一个需要删除的座位")
		}
		})

	
	//修改座位信息
	function updataSeatInfo(infometion){
		var seatName=$(infometion).find("td:eq(1)").html();
		var newSeatNum=$("#numberSeat").val()
		var newName=$("#seatName").val();
		var shopId=$(".checkBtn[type='checkbox']:checked").attr("shopId");
		const url = baseUrl + "/merchant/buffetFood/updata";
		params={
				shopId:shopId,
				seatName:newName,
				numberSeat:newSeatNum,
				oldName:seatName
		}
		$.post(url,params,function(data){
			if(data.code==0){
				alert("修改成功")
				$(infometion).find("td:eq(1)").html(newName);
				$(infometion).find("td:eq(2)").html(newSeatNum);
				$("#myModal").modal("hide");
			}else{
				alert("修改失败")
			}
		})
	}
	//删除座位
	function delSeatInfo(){
		var infometion=$(".checkBtn[type='checkbox']:checked").parent().parent();
		var seatName=$(infometion).find("td:eq(1)").html();
		var shopId=$(".checkBtn[type='checkbox']:checked").attr("shopId");
		var numberSeat=$(infometion).find("td:eq(2)").html();
		const url = baseUrl + "/merchant/buffetFood/delSeatInfo";
		params={
				shopId:shopId,
				seatName:seatName,
				numberSeat:numberSeat
		}
		$.post(url,params,function(data){
			if(data.code==0){
			$("#message").modal("hide");
				initNum(infometion)
			}else{
				alert("请联系管理员")
			}
		})
	}
	function initNum(infometion){
		$(infometion).remove();
		var index=$(".mainTab").find(".num");
		for(var a=1;a<index.length;a++){
			$(index.get(a)).html(a);
		}
	}
	//添加座位
function submitData(){
	var shopId=$("#addSeatInformation").attr("shopId");
	var seatName=$("#seatName").val();
	var numberSeat=$("#numberSeat").val();
	if(parseInt(numberSeat).isNaN||parseInt(numberSeat)>0){
	const url = baseUrl + "/merchant/buffetFood/addSeatInfo";
	var params={
			shopId:shopId,
			seatName:seatName,
			numberSeat:numberSeat
	}
	$.post(url,params,function(data){
		if(data.code==0){
				addTR(data.data)
			}else{
				alert(data.message)
			}
		})
	}else{
		alert("请输入座位人数")
	}
}
	function addTR(data){
		debugger
		var index=$(".mainTab").find(".num");
		console.log(index)
		if(index<=1){
			index=1;
		}else{
			index=$(index.get(index.length-1)).html();
		}
		$("#myModal").modal("hide");
		var tab=$(".mainTab")
		var tr="<tr class='seatTab'>"+
		"<td class='num' value='"+data.id+"'>"+(index==1?index:(parseInt(index)+1))+"</td>"+
		"<td>"+data.seatName+"</td>"+
		"<td>"+data.numberSeat+"</td>"+
		"<td></td>"+
		"<td><input type='checkbox' name='info' shopId='"+data.shopId+"' class='checkBtn' ></td>"+
		"</tr>"
		tab.append(tr);
	}
	})
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