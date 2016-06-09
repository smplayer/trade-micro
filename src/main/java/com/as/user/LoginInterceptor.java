package com.as.user;

import com.as.user.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yrx on 2016/5/27.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String[] IGNORE_URI = {"/login.jsp", "/user/login"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURL().toString();
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) flag = true;
            else response.sendRedirect(request.getContextPath() + "/user/login");
        }
        return flag;
    }
}
