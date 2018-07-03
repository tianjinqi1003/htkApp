package com.htkapp.core.dto;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yinqilei on 17-6-23.
 *
 */
public class RequestBasePath {


    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        return request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    }
}
