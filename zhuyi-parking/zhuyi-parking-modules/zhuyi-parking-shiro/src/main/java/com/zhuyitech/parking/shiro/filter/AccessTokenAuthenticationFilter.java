package com.zhuyitech.parking.shiro.filter;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AccessTokenAuthenticationFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 获取Authorization字段
        System.out.println("isAuthenticated");
        System.out.println(this.getSubject(httpServletRequest, response).isAuthenticated());

        String authorization = httpServletRequest.getHeader("Authorization");
        if (authorization != null) {
            try {
//                OAuth2AuthenticationToken token = new OAuth2AuthenticationToken(authorization);
//                // 提交给realm进行登入，如果错误他会抛出异常并被捕获
//                getSubject(request, response).login(token);
                return true;
            } catch (Exception e) {
//                response401(request, response);
                return false;
            }
        } else {
//            response401(request, response);
            return false;
        }
    }
}
