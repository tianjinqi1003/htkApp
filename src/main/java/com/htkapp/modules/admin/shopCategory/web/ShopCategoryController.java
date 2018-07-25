package com.htkapp.modules.admin.shopCategory.web;

import com.htkapp.core.OtherUtils;
import com.htkapp.core.config.FTPConfig;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.admin.shopCategory.dto.CategoryObj;
import com.htkapp.modules.admin.shopCategory.entity.ShopCategory;
import com.htkapp.modules.admin.shopCategory.service.ShopCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinqilei on 17-6-26.
 */
@Controller
@RequestMapping("/admin/category/")
public class ShopCategoryController {

    @Resource
    private ShopCategoryService shopCategoryService;


    //=============================================page开始
    //分类添加页面
    @RequestMapping("/takeoutCategoryAdd")
    public String categoryAdd() {
        return "admin/category_add";
    }

    //分类修改页面
    @RequestMapping("/editCategoryPage")
    public String editCategoryPage() {
        return "admin/category_edit";
    }
    //=============================================page结束


    //=============================================方法开始

    //通过标识查找分类列表
//    @RequestMapping("/getCategoryListJsonData")
//    @ResponseBody
//    public AjaxResponseModel getCategoryListJsonData(int mark) {
//        List<ShopCategory> resultList = shopCategoryService.getCategoryListData(mark);
//        if (resultList != null) {
//            CategoryObj obj = new CategoryObj();
//            List<ShopCategory> takeoutCategory = new ArrayList<>();
//            List<ShopCategory> groupCategory = new ArrayList<>();
//            for (ShopCategory each : resultList){
//                if(each.getMark() == 0){
//                    takeoutCategory.add(each);
//                }else {
//                    groupCategory.add(each);
//                }
//            }
//            obj.setTakeoutCategory(takeoutCategory);
//            obj.setGroupCategory(groupCategory);
//            return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", obj);
//        } else {
//            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "失败");
//        }
//    }

    @RequestMapping("/getCategoryListJsonData")
    @ResponseBody
    public AjaxResponseModel getCategoryListJson(int mark) {
        List<ShopCategory> resultList = shopCategoryService.getCategoryListData(mark);
        return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", resultList);
    }

    //通过分类id查找大分类下的小分类
    @RequestMapping("/getChildCategoryListById")
    @ResponseBody
    public AjaxResponseModel getChildCategoryListById(Integer parentId, Integer mark) {
        try {
            List<ShopCategory> resultList = shopCategoryService.getChildCategoryById(mark, parentId);
            if (resultList != null) {
                return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", resultList);
            } else {
                return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", null);
            }
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
        }
    }

    //添加分类下的小分类
    @RequestMapping("/addChildCategoryById")
    @ResponseBody
    public AjaxResponseModel addChildCategoryById(ShopCategory shopCategory) {
        try {
            shopCategoryService.addChildCategoryById(shopCategory);
            return new AjaxResponseModel(Globals.COMMON_SUCCESS_AND_REFRESH_CURRENT_PAGE, "成功");
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
        }
    }

    //通过id获取一级分类信息
    @RequestMapping("/editCategoryById")
    @ResponseBody
    public AjaxResponseModel editCategoryById(Integer categoryId, HttpServletRequest request) {
        try {
            ShopCategory category = shopCategoryService.getShopCategoryDataById(categoryId);
            category.setCategoryUrl(OtherUtils.getRootDirectory() + category.getCategoryUrl());
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", category);
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "失败");
        }
    }

    //添加一级分类
    @RequestMapping("/addCategoryByMark")
    @ResponseBody
    public AjaxResponseModel addCategoryByMark(ShopCategory category) {
        try {
            shopCategoryService.addCategoryByMark(category);
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESS_AND_JUMP_URL, "成功", "/admin/takeoutCategory");
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "失败");
        }
    }

    //上传分类图片
    @RequestMapping("/uploadCategoryPhoto")
    @ResponseBody
    public String uploadCategoryPhoto(MultipartFile file, HttpServletRequest request) {
        try {
            return FileUploadUtils.appUploadAvatarImg(file, "admin/shopCategory/", FTPConfig.port_to);
        } catch (Exception e) {
            return "";
        }
    }

    //保存修改的一级分类信息
    @RequestMapping("/saveCategoryById")
    @ResponseBody
    public AjaxResponseModel saveCategoryById(ShopCategory category) {
        try {
            shopCategoryService.saveCategoryById(category);
            return new AjaxResponseModel<>(Globals.COMMON_SUCCESS_AND_JUMP_URL, "成功", "/admin/takeoutCategory");
        } catch (Exception e) {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
        }
    }

    //通过二级分类id删除二级分类
    @RequestMapping("/delChildCategoryById")
    @ResponseBody
    public AjaxResponseModel delChildCategoryById(Integer childCId) {
        if (childCId != null) {
            //调用方法删除分类
            try {
                shopCategoryService.delCategoryId(childCId);
                return new AjaxResponseModel(Globals.COMMON_SUCCESS_AND_REFRESH_CURRENT_PAGE, "删除成功");
            } catch (Exception e) {
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
            }
        } else {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }

    //============================================方法结束


}
