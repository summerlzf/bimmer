package com.kedu.bimmer.base;

import com.kedu.bimmer.constant.CookieName;
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
        String json = CookieUtil.getCookie(request, CookieName.USER), timeout = CookieUtil.getCookie(request, CookieName.TIMEOUT);
        if (timeout == null) {
            return null;
        }
        long ts = CommonUtil.getLong(timeout, 0);
        return ts > System.currentTimeMillis() ? JSONUtils.fromJson(json, UserBasicInfo.class) : null;
    }

    public static void setUser(UserBasicInfo vo) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String json = JSONUtils.toJson(vo);
        CookieUtil.setCookie(response, CookieName.USER, json);
        // 登录超时时间（单位：分钟）
        int minutes = 10;
        long ts = vo.isRemember() ? 365 * 86400000L : minutes * 60000L;
        CookieUtil.setCookie(response, CookieName.TIMEOUT, String.valueOf(System.currentTimeMillis() + ts));
    }

    public static void clearUser() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtil.removeCookie(response, CookieName.USER);
        CookieUtil.removeCookie(response, CookieName.TIMEOUT);
    }
}
