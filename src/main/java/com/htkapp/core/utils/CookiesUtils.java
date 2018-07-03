package com.htkapp.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yinqilei on 17-7-5.
 * cookies操作工具类
 */
public class CookiesUtils {

    /**
     * @return 通过cookie名字获取cookie值
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        } else {
            Cookie cookie = null;
            for (Cookie each : cookies) {
                if (each.getName().equals(name) && !each.getValue().contains("null")) {
                    cookie = each;
                } else {
                    continue;
                }
            }
            return cookie;
        }
    }

    public static void clearCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie each : cookies) {
                each.setValue(null);
                each.setMaxAge(0);
                each.setPath("/");
                response.addCookie(each);
            }
        }
    }
}
