package com.kedu.bimmer.base;

import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.util.CommonUtil;
import com.kedu.bimmer.util.CookieUtil;
import com.kedu.bimmer.util.JSONUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jef
 */
public class CookieHolder {

    public static UserBasicInfo getUser(HttpServletRequest request) {
        String json = CookieUtil.getCookie(request, "bm-user"), timeout = CookieUtil.getCookie(request, "bm-timeout");
        if (timeout == null) {
            return null;
        }
        long ts = CommonUtil.getLong(timeout, 0);
        return ts > System.currentTimeMillis() ? JSONUtils.fromJson(json, UserBasicInfo.class) : null;
    }

    public static void setUser(HttpServletResponse response, UserBasicInfo vo) {
        String json = JSONUtils.toJson(vo);
        CookieUtil.setCookie(response, "bm-user", json);
        // 登录超时时间：20分钟
        int minutes = 20;
        CookieUtil.setCookie(response, "bm-timeout", String.valueOf(System.currentTimeMillis() + minutes * 60000));
    }
}
