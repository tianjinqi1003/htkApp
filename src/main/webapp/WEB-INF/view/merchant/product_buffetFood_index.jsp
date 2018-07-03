<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/9/9
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/url.jsp"%>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp"%>
<head>
<title>自助点餐商品主页</title>
<meta charset="utf-8" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<script
	src="${staticFilePath}resource/custom/plugins/floating-button/dist/lib/modernizr.touch.js"></script>
<link
	href="${staticFilePath}resource/custom/plugins/floating-button/dist/mfb.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${staticFilePath}resource/custom/plugins/floating-button/ionicons.min.css">
<link rel="stylesheet"
	href="${staticFilePath}resource/custom/plugins/floating-button/normalize.min.css">
<%@include file="head.jsp"%>
<style>
@media screen and (min-width: 1300px) and (max-width: 1366px) {
	.goodsItem {
		width: 33.333333%;
	}
	.childCat {
		width: 82% !important;
	}
}

@media screen and (min-width: 1900px) and (max-width: 1920px) {
	.goodsItem {
		width: 20%;
	}
	.childCat {
		width: 87.5% !important;
	}
}
</style>
</head>
<body>
	<div class="layui-layout layui-layout-admin">
		<%@include file="top.jsp"%>
		<div class="layui-body"
			style="background-color: #f3f3f4; overflow: auto">
			<div class="body-content">
				<div class="childCat clearfix tab">
					<div style="position: relative">
						<a href="" class="cur">商品管理</a>
					</div>
				</div>
				<div class="continerCont pdtp10">
					<div class="goodsSearch clearfix">
						<input type="text" id="search" placeholder="搜索商品"
							autocomplete="off" /> <a href="javascript:;;">搜索</a>
					</div>
					<div class="goods clearfix">
						<div class="category" style="overflow: scroll">
							<div style="background-color: #fff; height: 100%;">
								<div class="catList">
									<a href="" class="cur empty">暂无分类</a>
								</div>
							</div>
						</div>
						<div class="goodsList" style="overflow: scroll">
							<div class="catTitle clearfix">
								<span title=""></span> <a href="javascript:void(0);"
									class="editCat fa fa-pencil"> 编辑</a>
								<div class="action">
									<a href="javascript:void(0);" class="on">上架</a> <a
										href="javascript:void(0);" class="off">下架</a>
									<!--  <a href="javascript:void(0);" class="del">删除</a>-->
									<a href="javascript:void(0);" class="act">批量管理</a>
								</div>
							</div>
							<div class="goodsContiner">
								<p class="emptyGoods">暂无数据</p>
							</div>
							<div class="add">
								<a href="javascript:void(0);"><i class="fa fa-plus"></i>添加</a>
								<div class="addButton clearfix">
									<a href="${sysPath}merchant/buffetFood/product/addProduct">添加商品</a>
									<a href="javascript:void(0);" class="addCategory">添加分类</a>
								</div>
								<div class="pd"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="layer">
			<div class="layerCont addCats">
				<h1>
					添加分类<a href="javascript:void(0);" class="fa fa-remove close"></a>
				</h1>
				<form method="post" id="addCat">
					<div class="layerItem">
						<p>
							<i>*</i>分类名称
						</p>
						<input type="text" placeholder="请输入20字以内的分类名称" name="catName"
							maxlength="20" autocomplete="off" />
					</div>
					<div class="layerItem">
						<p>分类描述</p>
						<input type="text" placeholder="请输入50字以内的分类描述（非必填）" name="catDes"
							maxlength="50" autocomplete="off" />
					</div>
					<div class="layerButton">
						<input type="submit" value="保存" />
					</div>
				</form>
			</div>
			<div class="layerCont editCats">
				<h1>
					编辑分类<a href="javascript:void(0);" class="fa fa-remove close"></a>
				</h1>
				<form method="post" id="editCat">
					<div class="layerItem">
						<p>
							<i>*</i>分类名称
						</p>
						<input type="text" placeholder="请输入20字以内的分类名称" name="catName"
							maxlength="20" autocomplete="off" />
					</div>
					<div class="layerItem">
						<p>分类描述</p>
						<input type="text" placeholder="请输入50字以内的分类描述（非必填）" name="catDes"
							maxlength="50" autocomplete="off" />
					</div>
					<div class="layerButton">
						<input type="button" value="删除分类" id="delCat" /> <input
							type="submit" value="保存修改" />
					</div>
				</form>
			</div>
			<%@include file="footer.jsp"%>
		</div>
	</div>
</body>
<%@include file="js.jsp"%>
<script type="text/javascript">
    $(function () {
        $('input, textarea').placeholder();
    });
    layui.use(['element', 'util', 'layer'], function () {
        var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
        var util = layui.util;
        var layer = layui.layer;
    });

    //全局变量
    var data_ = [];

    $(function () {
        //查询数据
        var data = ajax(baseUrl + "/merchant/buffetFood/product/getProductData", {actionName: 'getData'});
        setData(data);

        function setData(data) {
            data_ = data;
            if (data) {
                var catHtml = "";
                var goodsHtml = "";
                $.each(data, function (index, item) {
                    catHtml += '<a href="javascript:void(0)" class="cValue"  data-id="' + item.categoryId + '" >' + item.categoryName + '</a>';
                    goodsHtml += '<div class="goodsContiner clearfix">';
                    if (item.buffetFoodProductList) {
                        $.each(item.buffetFoodProductList, function (indexb, itemb) {
                        	 var ifCanBuyStr = (itemb.state == 1)?"已上架":"已下架";
                            goodsHtml += '<div class="goodsItem" data-id="' + itemb.id + '">' +
                            '<div class="goodsDetail">' +
                            //是否上架
                            '<p class="ifCanBuy" style="background-color:#FF7F24;color:white;margin-bottom:10px;padding:10px">'+ifCanBuyStr+'</p>' +
                            '<div class="info clearfix">' +
                            '<div class="fleft">' +
                            '<h3>' + itemb.productName + '</h3>' +
                            '</div>' +
                            '<div class="fright pSize">' +
                            '<img src="' + itemb.imgUrl + '"style="max-width:300px;_width:expression(this.width > 300 ? "300px" : this.width);">' +
                            '</div>' +
                            '</div>' +
                                '<div class="goodsButton clearfix">' +
                                '<span style="line-height: 32px;font-size: 16px;color: red;text-align: left;width: 67%">' + '￥' + itemb.price + '</span>' +
                                '<a href="${sysPath}merchant/buffetFood/product/editProduct?productId=' + itemb.id + '">编辑</a>' +
                                '</div>' +
                                '<input name="checkGoods" value="1" type="checkbox">' +
                                '</div>' +
                                '<div style="font: 0px/0px sans-serif;clear: both;display: block"> </div>'+
                                '</div>';
                        });
                        goodsHtml += '<div class="pageNotice">' +
                            '已在当前分类底部，<a href="">下一个分类:小吃单点</a>' +
                            '</div>'
                    } else {
                        goodsHtml += '<p class="emptyGoods">暂无数据</p>';
                        goodsHtml += '<div class="pageNotice">' +
                            '已在当前分类底部，<a href="">下一个分类:小吃单点</a>' +
                            '</div>';
                    }
                    goodsHtml += '</div>';

                });
                //删除被选元素，包括文本和所有子元素
                $(".goodsList .goodsContiner").remove();
                //在被选元素前插入内容
                $(".goodsList .add").before(goodsHtml);
                //类选择器选中下标是0的元素添加类
                $(".goodsList .goodsContiner").eq(0).addClass("cur");
                //类选择器选中元素,并修改html标签,  在所有子元素中的查找a标签，在下标是0的元素上添加类，并添加绑定
                $(".catList").html(catHtml).find("a").eq(0).addClass("cur").click();
            }
        }

        //添加分类
        $("#addCat").submit(function () {
            var ele = $(this);
            var url = baseUrl + "/merchant/buffetFood/product/addCategory";
            var name = ele.find("input[name='catName']").val();
            var des = ele.find("input[name='catDes']").val();
//            var params = {categoryName: name};
            var params = {categoryName: name,mark:2};
            if (ele.find("input[name='catName']").val() === "") {
                ele.find("input[name='catName']").focus();
                return false;
            } else {
                $.post(url, params, function (result, status) {
                    if (status === 'success') {
                        if (result && result.code === 0) {
//                            $(".catList").append("<a href='javascript:void(0);' data-des='" + des + "'>" + name + "</a>");
//                            var next = $(".catList a").eq(0).text();
//                            $(".goodsList .add").before('<div class="goodsContiner clearfix"><p class="emptyGoods">暂无数据</p><div class="pageNotice">已在当前分类底部，<a href="">下一个分类:' + next + '</a></div></div>');
//                            if ($(".catList a").length === 1) {
//                                $(".catList a").eq(0).click();
//                            }
//                            $(".layer,.layerCont").hide();
//                            $(".layer input[type='text']").val("");
                            location.reload();
                            return false;
                        } else {
                            layer_msg(result.code, 'error');
                            return false;
                        }
                    } else {
                        layer_msg("网络连接异常", "exception");
                        return false;
                    }
                }, "json");
                return false;
            }
        });

        //编辑分类
        $("#editCat").submit(function () {
            var url = baseUrl + "/merchant/buffetFood/product/saveCategory";
            var ele = $(this);
            var name = $(this).find("input[name='catName']").val();
            var des = $(this).find("input[name='catDes']").val();
            var categoryId = $(".cValue.cur").attr("data-id");  //类别id
            var params = {categoryId: categoryId, categoryName: name};
            $.post(url, params, function (result, status) {
                if (status === 'success') {
                    if (result && result.code === 0) {
//                        if (ele.find("input[name='catName']").val() === "") {
//                            ele.find("input[name='catName']").focus();
//                            return false;
//                        }
//                        $(".catList .cur").text(name).attr("data-des", des);
//                        $(".catTitle>span").text(name).attr("title", des);
//                        $(".layer,.layerCont").hide();
//                        $(".layer input[type='text']").val("");
                        location.reload();
                        return false;
                    } else {
                        layer_msg(result.message, 'error');
                        return false;
                    }
                } else {
                    layer_msg('网络连接异常', 'exception');
                    return false;
                }
            }, "json");
            return false;
        });

        //删除分类
        $("#delCat").click(function () {
            layer.confirm('确定删除该分类吗？此操作会删除分类下数据', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                //异步调接口退出
                var categoryId = $(".cValue.cur").attr("data-id");  //类别id
                var url = baseUrl + "/merchant/buffetFood/product/delCategoryById";
                var params = {categoryId: categoryId};
                $.post(url, params, function (result, status) {
                    if (status === 'success') {
                        if (result && result.code === 0) {
//                            $(".catList .cur").remove();
//                            $(".goodsList .goodsContiner.cur").remove();
//                            $(".catTitle>span").text("").attr("title", "");
//                            if ($(".catList a").length > 0) {
//                                $(".catList a:first").click();
//                            }
//                            $(".layer,.layerCont").hide();
//                            $(".layer input[type='text']").val("");
                            location.reload();
                            layer.closeAll('dialog'); //关闭信息框
                        } else {
                            layer_msg(result.message, 'error');
                            layer.closeAll('dialog'); //关闭信息框
                        }
                    } else {
                        layer_msg("网络连接异常", 'exception');
                        layer.closeAll('dialog'); //关闭信息框
                    }
                }, "json");
                return false;
            }, function () {

            });
        });
     // @author 马鹏昊
        // @desc 上架点击事件
        $(".goodsList .action .on").click(function(){
        	debugger
            var allGoodItems = $(".goodsItem");
            var selectedIds = "";
            for(var i=0;i<allGoodItems.length;i++){
                var single = allGoodItems[i];
                var ifChecked = single.firstChild.lastChild.checked;
                if (ifChecked){
                    selectedIds = selectedIds+","+single.dataset.id;
                }
            }
            //如果没有选中的则什么也不做
            if(selectedIds=="")
                return false;
            var url = baseUrl + "/merchant/buffetFood/product/buffetFoodOn";
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
        });

        
        // @author 马鹏昊
        // @desc 下架点击事件
        $(".goodsList .action .off").click(function(){
            var allGoodItems = $(".goodsItem");
            var selectedIds = "";
            for(var i=0;i<allGoodItems.length;i++){
                var single = allGoodItems[i];
                var ifChecked = single.firstChild.lastChild.checked;
                if (ifChecked){
                    selectedIds = selectedIds+","+single.dataset.id;
                }
            }
            //如果没有选中的则什么也不做
            if(selectedIds=="")
                return false;
            var url = baseUrl + "/merchant/buffetFood/product/buffetFoodOff";
            var params = {"selectedIds":selectedIds.substring(1,selectedIds.length)};
            $.post(url,params,function (result, status) {
                if(status === 'success'){
                    if (result && result.code === 0){
                        // $(this).siblings("a").css("display","none");
                        // $(".goodsItem input[type='checkbox']").attr("checked",false).hide();
                        // $(this).removeClass("edit");
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

        });
		

        //搜索
        $(".goodsSearch a").click(function () {
            var searchVal = $("#search").val();
            if (searchVal === '') {
                return false;
            } else if (searchVal.length > 10) {
                return false;
            } else {
                var productList = [];
                var row = "";
                $.each(data_, function (index, item) {
                    if (item.takeoutProductList) {
                        $.each(item.takeoutProductList, function (index_, item_) {
                            if (item_.productName === searchVal) {
                                row = index;
                            }
                            productList.push(item_);
                        })
                    }
                });
                $.each(productList, function (index, item) {
                    if (item.productName === searchVal && row !== "") {
                        $(".catList a").eq(row).click();
                        return false;
                    } else {
                        if (productList.length === index + 1) {
                            layer_msg("未查找到商品", "error");
                            return false;
                        }
                        return true;
                    }
                })
            }
        })

    });

    function ajax(url, datas) {
        var ajax_data = [];
        $.ajax({
            type: "POST",
            url: url,
            data: datas,
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.code === 0 && data.data !== null) {
                    //获取数据成功
                    ajax_data = data.data;
                } else {
                    //后台执行异常,外卖下分类为空(显示提示信息)
                    layer_msg(data.message, 'exception');
                }
            },
            cache: false,
            error: function (status) {
                //访问后台失败(网络连接异常等等。。)
                layer_msg(status.message, "error");
            }
        });
        return ajax_data;
    }
</script>
</html>
