package com.stackroute.authenticationservice.filter;

import com.stackroute.authenticationservice.service.CustomUserDetailsService;
import com.stackroute.authenticationservice.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException{
        String authString= httpServletRequest.getHeader("Authorization");
        String username=null;
        String jwtToken=null;
        log.info("Inside JwtFilter class doFilter method");
        if(authString!=null&& authString.startsWith("Bearer "))
        {
            jwtToken=authString.substring(7);
            username=jwtUtil.extractUsername(jwtToken);
            System.out.println("JWTFilter class:"+username);
            UserDetails details=customUserDetailsService.loadUserByUsername(username);
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UsernamePasswordAuthenticationToken authenticationToken=new
                        UsernamePasswordAuthenticationToken(details,null,details.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            else
            {
                System.out.println("Token is not validated");
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);


    }
}
