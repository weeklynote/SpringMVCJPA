package com.mar.interceptor;

import com.mar.controller.UserController;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mar.utils.StringUtils.isEmpty;

/**
 * @Author: 刘劲
 * @Date: 2020/4/21 18:11
 */
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getParameter("token");
        if (isEmpty(token)){
            token = request.getHeader("token");
        }
        if (isEmpty(token) || !UserController.tokens.contains(token)){
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            String resp = "{\"code\":404,\"message\":\"未登录或者登录过期\"}";
            response.getWriter().write(resp);
            return false;
        }
        return true;
    }
}
