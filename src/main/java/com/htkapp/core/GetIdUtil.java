package com.htkapp.core;

import com.htkapp.core.utils.Globals;
import org.apache.shiro.session.Session;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSession;

/**
 * Created by yinqilei on 17-4-17.
 */
@Configuration
public class GetIdUtil {

    //传入session取session中的商铺id
    public Integer getShopIbBySession(HttpSession session) {
        Object s = session.getAttribute(Globals.API_HTML_SESSION_SHOP_ID);
        Integer shopId_ = null;
        if (s != null) {
            shopId_ = Integer.parseInt(s.toString());
        } else {
            shopId_ = null;
        }
        return shopId_;
    }
}
