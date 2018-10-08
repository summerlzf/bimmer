package com.kedu.bimmer.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jef
 */
public class CookieUtil {

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cks = request.getCookies();
        if (cks == null) {
            return null;
        }
        for(Cookie ck : cks) {
            if (ck.getName().equals(name)) {
                return ck.getValue();
            }
        }
        return null;
    }

    public static void setCookie(HttpServletResponse response, String name, String value) {
        Cookie ck = new Cookie(name, value);
        ck.setPath("/");
        ck.setMaxAge(86400 * 365);
        response.addCookie(ck);
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie ck = new Cookie(name, null);
        ck.setPath("/");
        ck.setMaxAge(0);
        response.addCookie(ck);
    }
}
