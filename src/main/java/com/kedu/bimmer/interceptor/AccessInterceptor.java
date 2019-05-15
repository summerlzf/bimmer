package com.kedu.bimmer.interceptor;

import com.kedu.bimmer.base.CookieHolder;
import com.kedu.bimmer.base.SystemContext;
import com.kedu.bimmer.constant.CookieName;
import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.util.CommonUtil;
import com.kedu.bimmer.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Jef
 */
public class AccessInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AccessInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        logger.info("Request URL: {}", url);

        UserBasicInfo vo = CookieHolder.getUser();
        request.setAttribute("isLogin", vo != null);
        if (vo != null) {
            SystemContext.setUser(vo);
            CookieHolder.setUser(vo);
            request.setAttribute("username", vo.getUserName());
            return true;
        }
        SystemContext.clearUser();

        if (isNeedLogin(url)) {
            if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) { // Ajax请求
				response.setHeader("REDIRECT", "REDIRECT");
				// 重定向的路径：登录页
				response.setHeader("CONTEXTPATH", "/login");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else {
				// 跳转到登录页
				response.sendRedirect("/login?callbackUrl=" + URLEncoder.encode(url, "utf-8"));
			}
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null) {
            // 将每次请求的页面URL地址都写入cookie
            CookieUtil.setCookie(response, CookieName.URL, request.getRequestURI());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        //
    }

    /**
     * 判断是否需要登录
     * @param url
     * @return
     */
    private boolean isNeedLogin(String url) {
        // 需要登录的URL列表
        List<String> list = CommonUtil.asList("/comment/addComment");
        return url.startsWith("/admin/") || list.contains(url);
    }
}
