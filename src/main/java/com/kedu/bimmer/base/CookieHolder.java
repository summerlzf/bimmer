package com.kedu.bimmer.base;

import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.util.CommonUtil;
import com.kedu.bimmer.util.CookieUtil;
import com.kedu.bimmer.util.JSONUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jef
 */
public class CookieHolder {

    public static UserBasicInfo getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String json = CookieUtil.getCookie(request, "bm-user"), timeout = CookieUtil.getCookie(request, "bm-timeout");
        if (timeout == null) {
            return null;
        }
        long ts = CommonUtil.getLong(timeout, 0);
        return ts > System.currentTimeMillis() ? JSONUtils.fromJson(json, UserBasicInfo.class) : null;
    }

    public static void setUser(UserBasicInfo vo) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String json = JSONUtils.toJson(vo);
        CookieUtil.setCookie(response, "bm-user", json);
        // 登录超时时间（单位：分钟）
        int minutes = 10;
        long ts = vo.isRemember() ? 365 * 86400000 : minutes * 60000;
        CookieUtil.setCookie(response, "bm-timeout", String.valueOf(System.currentTimeMillis() + ts));
    }

    public static void clearUser() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtil.removeCookie(response, "bm-user");
        CookieUtil.removeCookie(response, "bm-timeout");
    }
}
