package com.kedu.bimmer.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jef
 */
public class CookieUtil {

    public static void setCookie(HttpServletResponse response, String name, String value) {
        Cookie ck = new Cookie(name, value);
        ck.setMaxAge(86400 * 365);
        response.addCookie(ck);
    }

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
}
