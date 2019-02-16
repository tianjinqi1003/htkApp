package com.htkapp.modules.merchant.shop.service.serviceImpl;


import com.htkapp.core.MD5Utils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.config.FTPConfig;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.params.AjaxRequestParams;
import com.htkapp.core.params.RequestParams;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.admin.shopCategory.entity.ShopCategory;
import com.htkapp.modules.admin.shopCategory.service.ShopCategoryService;
import com.htkapp.modules.common.entity.LoginUser;
import com.htkapp.modules.merchant.shop.entity.ShopCategoryData;
import com.htkapp.modules.merchant.shop.dto.ShopMessageData;
import com.htkapp.modules.merchant.shop.entity.*;
import com.htkapp.modules.merchant.shop.service.*;
import com.xiaoleilu.hutool.date.BetweenFormater;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ShopInfoControllerServiceImpl implements ShopInfoControllerService {

    @Resource
    private ShopServiceI shopService;
    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private ShopBulletinService shopBulletinService;
    @Resource
    private ShopCategoryService shopCategoryService;
    @Resource
    private ShopAlbumService shopAlbumService;
    @Resource
    private ShopMessageService shopMessageService;

    /* =======================接口开始============================ */

    @Override
    public AjaxResponseModel getShopCategoryList() {
        try {
            List<ShopCategoryData> data = shopCategoryService.getShopCategoryList();
            return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION, "成功", data);
        }catch (Exception e){
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, "失败",null );
        }
    }

    @Override
    public List<ShopCategoryData> getShopCategory() {
        try {
            List<ShopCategoryData> data = shopCategoryService.getShopCategoryList();
            return data;
        }catch (Exception e){
            return null;
        }
    }

    //获取店铺信息(店名、地址、分类、加入时间、剩余精确时间,店铺编号,店铺二维码等等)
    //店铺头像，我的账号名，营业时间，订餐时间,店铺公告，店铺简介
    @Override
    public void getShopMessageByShopEncryptToken(Model model) {
        try {
            LoginUser user = OtherUtils.getLoginUserByRequest();
            String encryptToken = user.getToken();
            //根据accountShopToken查询店铺数据　
            Shop shop = shopService.getShopDataByAccountShopToken(encryptToken);
            AccountShop accountShop = accountShopService.selectByToken(encryptToken);
            ShopBulletin shopBulletin = shopBulletinService.getShopBulletinByToken(encryptToken);
            if(shop != null){
                ShopCategory shopCategory = shopCategoryService.getShopCategoryDataById(shop.getShopCategoryId());
                String remainingUseTime = DateUtil.formatBetween(new Date(), DateUtil.parseDate(user.getUseEndTime()), BetweenFormater.Level.MINUTE);
                ShopMessageData messageData = new ShopMessageData(
                        shop.getShopName(),shop.getAddress(),
                        shopCategory.getCategoryName(),shop.getShopJoinTime(), remainingUseTime,
                        shop.getShopId().longValue(),OtherUtils.getRootDirectory()+ shop.getShopQrCodeUrl(),
                        OtherUtils.getRootDirectory()+shop.getLogoUrl(), accountShop.getUserName()+"_htk",
                        shop.getOpeningTime(), shop.getPhone(),shopBulletin.getContent(),shop.getIntro(),
                        shop.getState());
                model.addAttribute("data",messageData);
                model.addAttribute("phone", accountShop.getPhone());
            }else {
                model.addAttribute("data",null);
            }
        }catch (Exception e){
            model.addAttribute("data",null);
        }
    }

    //店铺相册上传
    @Override
    public AjaxResponseModel uploadAlbumInterface(AjaxRequestParams params) {
        if(params != null && params.getFile() != null && params.getFlag() != null){
            try {
                String imgUrl = FileUploadUtils.cutUploadImg(params.getFile(),"shop/album/");
                ShopAlbum shopAlbum = new ShopAlbum();
                shopAlbum.setFlag(params.getFlag());
                shopAlbum.setImgUrl(imgUrl);
                LoginUser user = OtherUtils.getLoginUserByRequest();
                Shop shop = shopService.getShopByAccountShopIdAndMark(user.getUserId(), params.getFlag());
                shopAlbum.setShopId(shop.getShopId());
                shopAlbum.setAccountShopToken(user.getToken());
                shopAlbumService.insertShopAlbumByShopId(shopAlbum);
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }


    //店铺相册页面
    @Override
    public void getShopAlbumPage(RequestParams params) {
        if(params != null && params.getModel() != null){
            Model model = params.getModel();
            try {
                LoginUser user = OtherUtils.getLoginUserByRequest();
                    //查询数据
                List<ShopAlbum> resultList = shopAlbumService.getShopAlbumListById(user.getToken());
                if(resultList != null){
                    for (ShopAlbum each : resultList){
                        each.setImgUrl(OtherUtils.getRootDirectory() + each.getImgUrl());
                    }
                }
                model.addAttribute("data", resultList);
            }catch (Exception e){
                return;
            }
        }
    }

    //删除店铺相册接口
    @Override
    public AjaxResponseModel deleteAlbumById(AjaxRequestParams params) {
        if(params != null && params.getId() != null){
            try {
                //根据id 和 商户token 删除图片
                LoginUser user = OtherUtils.getLoginUserByRequest();
                shopAlbumService.deleteAlbumById(user.getToken(), params.getId());
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED, e.getMessage());
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
        }
    }

    //修改密码
    @Override
    public AjaxResponseModel updatePassword(AjaxRequestParams params) {
        if(params != null){
            try {
                AccountShop accountShop = new AccountShop();
                accountShop.setPassword(MD5Utils.getMD5Encode(params.getPassword()));
                LoginUser user = OtherUtils.getLoginUserByRequest();
                accountShop.setId(user.getUserId());
                accountShopService.updatePassword(accountShop);
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    //修改手机号
    @Override
    public AjaxResponseModel updateShopPhone(AjaxRequestParams params) {
        if(params != null){
            try {
                AccountShop accountShop = new AccountShop();
                accountShop.setPhone(params.getPhone());
                LoginUser user = OtherUtils.getLoginUserByRequest();
                accountShop.setId(user.getUserId());
                accountShopService.updateShopPhone(accountShop);
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    //店铺头像上传修改
    @Override
    public AjaxResponseModel updateShopImg(AjaxRequestParams params) {
        if(params != null){
            try {
                //上传图片到FTP服务器，返回的图片地址更新到数据库
                String filePath = FileUploadUtils.appUploadAvatarImg(params.getFile(), "shop/common/", FTPConfig.port_common);
                Shop shop = new Shop();
                shop.setLogoUrl(filePath);
                LoginUser user = OtherUtils.getLoginUserByRequest();
                shop.setAccountShopId(user.getUserId());
                shopService.updateShopImg(shop);
                return new AjaxResponseModel<>(Globals.COMMON_SUCCESSFUL_OPERATION,"成功",filePath);
            }catch (Exception e){
            	e.printStackTrace();
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    //营业时间修改
    @Override
    public AjaxResponseModel updateOpeningTime(AjaxRequestParams params) {
        if(params != null){
            try {
                Shop shop = new Shop();
                shop.setOpeningTime(params.getOpeningTime());
                LoginUser user = OtherUtils.getLoginUserByRequest();
                shop.setAccountShopId(user.getUserId());
                shopService.updateOpeningTime(shop);
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    //订餐电话修改
    @Override
    public AjaxResponseModel updatePhone(AjaxRequestParams params) {
        if(params != null){
            try {
                Shop shop = new Shop();
                shop.setMobilePhone(params.getPhone());
                LoginUser user = OtherUtils.getLoginUserByRequest();
                shop.setAccountShopId(user.getUserId());
                shopService.updatePhone(shop);
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    //店铺公告修改
    @Override
    public AjaxResponseModel updateIntro(AjaxRequestParams params) {
        if(params != null){
            try {
                Shop shop = new Shop();
                shop.setIntro(params.getIntro());
                LoginUser user = OtherUtils.getLoginUserByRequest();
                shop.setAccountShopId(user.getUserId());
                shopService.updateIntro(shop);
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    //店铺简介修改
    @Override
    public AjaxResponseModel updateDes(AjaxRequestParams params) {
        if(params != null){
            try {
                Shop shop = new Shop();
                shop.setDes(params.getDes());
                LoginUser user = OtherUtils.getLoginUserByRequest();
                shop.setAccountShopId(user.getUserId());
                shopService.updateDes(shop);
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    //配送费
    @Override
    public AjaxResponseModel updateDeliveryFee(AjaxRequestParams params) {
        if(params != null){
            try {
                ShopMessage shopMessage = new ShopMessage();
                shopMessage.setDeliveryFee(params.getDeliveryFee());
                LoginUser user = OtherUtils.getLoginUserByRequest();
                Shop shop = shopService.getShopByAccountShopIdAndMark(user.getUserId(),0);
                shopMessage.setShopId(shop.getShopId());
                shopMessageService.updateDeliverFee(shopMessage);
                return new AjaxResponseModel(Globals.COMMON_SUCCESSFUL_OPERATION);
            }catch (Exception e){
                return new AjaxResponseModel(Globals.COMMON_OPERATION_FAILED);
            }
        }else {
            return new AjaxResponseModel(Globals.COMMON_PARAMETER_ERROR);
        }
    }

    /* =======================接口结束========================== */
}
