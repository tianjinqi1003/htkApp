package com.htkapp.core.utils;

import com.htkapp.modules.API.entity.AccountFocus;
import com.htkapp.modules.merchant.shop.entity.Shop;

import java.util.Iterator;
import java.util.List;

/**
 * Created by yinqilei on 17-6-26.
 * 把要在service层写的代码拿出来,做一个工具类
 */

public class ServiceMethodUtils {

    public static List<Shop> getShopCollectionMessageByShopList(List<Shop> shopList, List<AccountFocus> focusList) {
        for (AccountFocus every : focusList) {
            for (Shop each : shopList) {
                if (every.getShopId().equals(each.getShopId())) {
                    each.setCollection(true);
                }
            }
        }
        return shopList;
    }

    public static List<Shop> filterByDistanceType(int distanceType, double userLo, double userLa, List<Shop> listMerchant) {
        double disTarget = distanceType == 1 ? (int) (0.5 * 1000) : (int) (distanceType == 2 ? 1 * 1000 : 2 * 1000);
        switch (distanceType) {
            case 1:
                disTarget = 0.5 * 1000;
                break;
            case 2:
                disTarget = 1000;
                break;
            case 3:
                disTarget = 2 * 1000;
                break;
            case 4:
                disTarget = 3 * 1000;
            default:
                    break;
        }
        Iterator<Shop> ite = listMerchant.iterator();
        while (ite.hasNext()) {
            Shop mer = ite.next();
            if (userLo == 0 && userLa == 0) {
                mer.setWithDistance(Integer.MAX_VALUE);
            } else {
                LocationUtil.Point p1 = new LocationUtil.Point(userLo, userLa);
                LocationUtil.Point p2 = new LocationUtil.Point(mer.getLongitude().floatValue(), mer.getLatitude().floatValue());
                float twoDis = LocationUtil.distance(p1, p2);
                if (twoDis > disTarget) {
                    ite.remove();
                } else {
                    mer.setWithDistance(twoDis);
                }
            }

        }
        return null;
    }
}
