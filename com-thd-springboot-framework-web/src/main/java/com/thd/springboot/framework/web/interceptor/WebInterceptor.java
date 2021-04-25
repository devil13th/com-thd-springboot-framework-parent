package com.thd.springboot.framework.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * com.thd.springboottest.interceptor.interceptor.WebInterceptor
 *
 * @author: wanglei62
 * @DATE: 2019/9/30 17:32
 **/
@Component
public class WebInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /*
     * 视图渲染之后的操作
     */
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        logger.debug(" completion ");
    }

    /*
     * 处理请求完成后视图渲染之前的处理操作
     */
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        logger.debug(" postHandle ");

    }

    /*
     * 进入controller层之前拦截请求
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        logger.debug("preHandle getContextPath:" + request.getContextPath());
        logger.debug("preHandle getServletPath:" + request.getServletPath());
        logger.debug("preHandle getRequestURI:" + request.getRequestURI());
        logger.debug("preHandle getRequestURL:" + request.getRequestURL());
        logger.debug("preHandle getRealPath:" + request.getSession().getServletContext().getRealPath("image"));

        return true;


//        response.getWriter().write("there's a error in interceptor");
//        return false;
    }
}
