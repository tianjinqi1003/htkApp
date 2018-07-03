$(function(){

    //左侧菜单
    $(".menuItem .parentMenu").click(function(){
        if($(this).parent().hasClass("cur")){
            $(".menuItem").removeClass("cur").find("a").removeClass("cur");
            $(this).parent().addClass("cur");
            $(this).siblings(".childMenu").toggle();
            $(this).parent().toggleClass("open");
        }else{
            $(".menuItem").removeClass("cur open").find(".childMenu").hide().find("a").removeClass("cur");
            $(this).siblings(".childMenu").show().parent().addClass("cur open");
        }
    })

    //简化左侧菜单
    $(".menuToggle").click(function(){
        $(".leftMenu,.rightContiner").toggleClass("toggled");
        $(".menuItem .childMenu").hide();
    })

    // //编辑商品分类名称
    $(".editCat").click(function(){
        var catName=$(this).siblings("span").text();
        var catDes=$(this).siblings("span").attr("title");
        if(!catName)return false;
        $(".layer,.editCats").show();
        $(".layer .editCats input[name='catName']").val(catName).focus();
        $(".layer .editCats  input[name='catDes']").val(catDes).focus().blur();
    });

    //添加分类
    $(".addCategory").click(function(){
        $(".layer,.addCats").show();
    })

    //关闭弹窗
    $(".layerCont .close").click(function(){
        $(".layer,.layerCont").hide();
        $(".layer input[type='text']").val("");
    })

    //批量操作商品
    $(".goodsList .action .act").click(function(){
        if($(this).hasClass("edit")){
            $(this).siblings("a").css("display","none");
            $(".goodsItem input[type='checkbox']").attr("checked",false).hide();
            $(this).removeClass("edit");
        }else{
            $(this).siblings("a").css("display","inline-block");
            $(".goodsItem input[type='checkbox']").attr("checked",false).show();
            $(this).addClass("edit");
        }
    });


    //字数变化
    $(".fontNum input,.fontNum textarea").keyup(function(){
        var l=$(this).val().length;
        var num=$(this).parents(".formInput").find(".num").text();
        var end=num.substr(num.indexOf("/"));
        var nowNum=l+end;
        $(this).parents(".formInput").find(".num").text(nowNum);
    })

    var i=1;

    //增加规格
    $(document).on("click",".addSku",function(){
        var str1='<div class="likeTr clearfix">'+
            '<span><i class="rmb">￥</i><input class="money" type="text" name="list[0].price"></span>'+
            '<span><i class="rmb">￥</i><input class="money" value="0.00" type="text" name="list[0].priceCanhe"></span>'+
            '<span class="big"><input value="10000" type="text" name="list[0].inventory"><i class="xg">/</i><input value="10000" type="text" name="list[0].inventoryCount"></span>'+
            '<i class="fa fa-minus-circle" title="删除"></i>'+
            '</div>';
        var str2='<div class="likeTr clearfix">'+
            '<span><i class="rmb">￥</i><input class="money" type="text" name="list[1].price"></span>'+
            '<span><i class="rmb">￥</i><input class="money" value="0.00" type="text" name="list[1].priceCanhe"></span>'+
            '<span class="big"><input value="10000" type="text" name="list[1].inventory"><i class="xg">/</i><input value="10000" type="text" name="list[1].inventoryCount"></span>'+
            '<i class="fa fa-minus-circle" title="删除"></i>'+
            '</div>';
        var str3='<div class="likeTr clearfix">'+
            '<span><i class="rmb">￥</i><input class="money" type="text" name="list[2].price"></span>'+
            '<span><i class="rmb">￥</i><input class="money" value="0.00" type="text" name="list[2].priceCanhe"></span>'+
            '<span class="big"><input value="10000" type="text" name="list[2].inventory"><i class="xg">/</i><input value="10000" type="text" name="list[2].inventoryCount"></span>'+
            '<i class="fa fa-minus-circle" title="删除"></i>'+
            '</div>';
        var str4='<div class="likeTr clearfix">'+
            '<span><i class="rmb">￥</i><input class="money" type="text" name="list[3].price"></span>'+
            '<span><i class="rmb">￥</i><input class="money" value="0.00" type="text" name="list[3].priceCanhe"></span>'+
            '<span class="big"><input value="10000" type="text" name="list[3].inventory"><i class="xg">/</i><input value="10000" type="text" name="list[3].inventoryCount"></span>'+
            '<i class="fa fa-minus-circle" title="删除"></i>'+
            '</div>';
        var str5='<div class="likeTr clearfix">'+
            '<span><i class="rmb">￥</i><input class="money" type="text" name="list[4].price"></span>'+
            '<span><i class="rmb">￥</i><input class="money" value="0.00" type="text" name="list[4].priceCanhe"></span>'+
            '<span class="big"><input value="10000" type="text" name="list[4].inventory"><i class="xg">/</i><input value="10000" type="text" name="list[4].inventoryCount"></span>'+
            '<i class="fa fa-minus-circle" title="删除"></i>'+
            '</div>';
        var str6='<div class="likeTr clearfix">'+
            '<span><i class="rmb">￥</i><input class="money" type="text" name="list[5].price"></span>'+
            '<span><i class="rmb">￥</i><input class="money" value="0.00" type="text" name="list[5].priceCanhe"></span>'+
            '<span class="big"><input value="10000" type="text" name="list[5].inventory"><i class="xg">/</i><input value="10000" type="text" name="list[5].inventoryCount"></span>'+
            '<i class="fa fa-minus-circle" title="删除"></i>'+
            '</div>';
        if($(".likeTable .likeTr").length ===1){
            $(".likeTable").append(str2);
        }else if($(".likeTable .likeTr").length === 2){
            $(".likeTable").append(str3);
        }else if($(".likeTable .likeTr").length === 3){
            $(".likeTable").append(str4);
        }else if($(".likeTable .likeTr").length === 4){
            $(".likeTable").append(str5);
        }else if($(".likeTable .likeTr").length === 5){
            $(".likeTable").append(str6);
        }
        if($(".likeTable .likeTr").length>=6){
            $(this).remove();
        }
        $(".likeTable .likeTh").show();
    })

    //删除规格
    $(document).on("click",".likeTr>i",function(){
        $(this).parent(".likeTr").remove();
        if($(".likeTable .likeTr").length<6 && $(".addSku").length<=0){
            $(".likeTable").after('<a href="javascript:void(0);" class="addSku addSkuAttr"><i class="fa fa-plus-circle"></i>添加规格（最多6个）</a>');
        }
    })

    //增加属性
    $(document).on("click",".addAttr",function(){
        if($(".attrCont input").length<=0){
            $(this).siblings(".attrCont").append("<span><input type='text' name='propertyList[0].propertyE'/><i class='fa fa-minus-circle' title='删除'></i></span><span><input type='text' name='propertyList[1].propertyE' /><i class='fa fa-minus-circle'></i></span>");
        }else if($(".attrCont input").length === 2){
            $(this).siblings(".attrCont").append("<span><input type='text'name='propertyList[2].propertyE' /><i class='fa fa-minus-circle' title='删除'></i></span>");
        }else if($(".attrCont input").length === 3){
            $(this).siblings(".attrCont").append("<span><input type='text'name='propertyList[3].propertyE' /><i class='fa fa-minus-circle' title='删除'></i></span>");
        }else if($(".attrCont input").length === 4){
            $(this).siblings(".attrCont").append("<span><input type='text'name='propertyList[4].propertyE' /><i class='fa fa-minus-circle' title='删除'></i></span>");
        }else if($(".attrCont input").length === 5){
            $(this).siblings(".attrCont").append("<span><input type='text'name='propertyList[5].propertyE' /><i class='fa fa-minus-circle' title='删除'></i></span>");
        }
        if($(".attrCont input").length>=5){
            $(this).remove();
        }
    })

    //删除属性
    $(document).on("click",".attrCont span i",function(){
        $(this).parent("span").remove();
        if($(".attrCont input").length<5 && $(".addAttr").length<=0){
            $(".attrCont").after('<a href="javascript:void(0);" class="addAttr addSkuAttr"><i class="fa fa-plus-circle"></i>添加属性（最多5个）</a>');
        }
        if($(".attrCont input").length<=1){
            $(".attrCont span").remove();
        }
    })

    //售卖时间
    $(".salesTime input[type='radio']").click(function(){
        $(".salesTime label").removeClass("cur");
        if($(this).is(":checked")){
            $(this).parent().addClass("cur");
        }
        $(".selectTime").hide();
        if($(this).parent().index()){
            $(".selectTime").show();
        }
    })

    //订单管理收起已购买商品
    $(".goodsInfo .uname a").click(function(){
        $(this).toggleClass("close_");
        $(this).parents(".goodsInfo").find(".payed").toggle();
        if($(this).text() === '收起'){
            $(this).html("展开");
        }else {
            $(this).html("收起");
        }
    })

    //取消时间input焦点
    $(".noFocus").focus(function(){
        $(this).blur();
    })

    //商品分类切换
    $(document).on("click",".catList a",function(){
        if($(this).hasClass("empty"))return false;
        var i=$(this).index();

        if($(".catList a").eq(i+1).length<=0){
            var txt=$(".catList a").eq(0).text();
        }else{
            var txt=$(".catList a").eq(i+1).text();
        }
        $(".goodsList .goodsContiner").eq(i).find(".pageNotice").children("a").text("下一个分类："+txt);

        $(this).addClass("cur").siblings().removeClass("cur");
        $(".goodsList .goodsContiner").eq(i).addClass("cur").siblings().removeClass("cur");
        var name=$(this).text();
        var des=$(this).data("des");
        $(".catTitle>span").text(name).attr("title",des);
        return false;
    })

    //下一个分类
    $(document).on("click",".pageNotice a",function(){
        var length=$(".catList a").length;
        //index() 方法返回指定元素相对于其他指定元素的 index 位置。
        var now=$(".catList a.cur").index()+1;
        if(now>=length){
            $(".catList a").eq(0).click();
        }else{
            $(".catList a").eq(now).click();
        }
        return false;
    })
})