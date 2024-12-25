package com.rapporbit.filter;


import com.rapporbit.utils.JwtUtils;
import com.rapporbit.utils.CurrentHolder;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求路径
        String requestURI = request.getRequestURI();
        //2.判断是否为登录请求
        if(requestURI.contains("/login")){
            log.info("登录请求，放行");
            filterChain.doFilter(request,response);
            return;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否为空
        if(token == null || token.isEmpty()){
            log.info("token为空，拦截");
            response.setStatus(401);
            return;
        }
        //5.判断token是否有效
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
        } catch (Exception e) {
            log.info("token校验失败，拦截");
            response.setStatus(401);
            return;
        }
        //6.放行
        log.info("令牌合法, 放行");
        filterChain.doFilter(request,response);
        CurrentHolder.remove();
    }
}
