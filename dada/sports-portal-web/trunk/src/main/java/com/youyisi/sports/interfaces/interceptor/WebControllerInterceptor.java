/*
 * 文 件 名:  WebControllerInterceptor.java
 * 版   权: Copyright www.uoss.com Corporation 2013 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2015-04-13
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.youyisi.sports.interfaces.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.youyisi.sports.domain.user.User;
import com.youyisi.sports.interfaces.controller.BaseController;

/**
 * 请求拦截器
 * <功能详细描述>
 * 
 * @author  shiwei
 * @version  [版本号, 2015-04-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WebControllerInterceptor extends BaseController implements HandlerInterceptor
{
    private static final Log logger = LogFactory.getLog(WebControllerInterceptor.class);
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception exception) throws Exception
    {
        if (logger.isDebugEnabled())
            logger.debug(" WebControllerInterceptor afterCompletion....");

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception
    {
        if (logger.isDebugEnabled())
            logger.debug(" WebControllerInterceptor postHandle....");
        // 获取cookie中登录数据

//        if (null == request.getParameter("userName") || null == request.getParameter("password"))
//        {
//            // 获取回话中是否有登录标识
//            if (null == request.getSession().getAttribute("loginName")
//                    && request.getRequestURI().indexOf("login.do") == -1)
//            {
//                response.sendRedirect(request.getSession().getAttribute("basePath").toString() + "login.do");
//            }
//        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
    	String token = request.getParameter("token");
    	User user = getUserByToken(token);
       if(user==null){
    	   request.getRequestDispatcher("/user/tokenerror").forward(request, response);
    	   return false;
       }
        return true;
    }
}
