package com.kedu.bimmer.interceptor;

import com.kedu.bimmer.base.CookieHolder;
import com.kedu.bimmer.base.SystemContext;
import com.kedu.bimmer.dto.UserBasicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jef
 */
public class AccessInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AccessInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        logger.info("Request URL: {}", url);

        UserBasicInfo vo = CookieHolder.getUser(request);
        if (vo != null) {
            SystemContext.setUser(vo);
            CookieHolder.setUser(response, vo);
        }
        if (!url.startsWith("/admin/")) {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        //
    }

    private void signin(HttpServletRequest request) {
    }
}
