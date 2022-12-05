package com.zzezze.makaogift.interceptors;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.zzezze.makaogift.exceptions.AuthenticationError;
import com.zzezze.makaogift.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    public AuthenticationInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");

        if(authorization == null || !authorization.startsWith("Bearer ")){
            return true;
        }

        String accessToken = authorization.substring("Bearer ".length());

        try{
            String username = jwtUtil.decode(accessToken);

            request.setAttribute("username", username);

            return true;
        } catch (JWTDecodeException exception){
            throw new AuthenticationError();
        }
    }
}
