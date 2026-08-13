package com.prasanth.financemanager.Service;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter{
    private final JWTService jwtService;
    private final CustomerUserDetailsService customerUserDetailsService;
    public JWTFilter(JWTService jwtService,CustomerUserDetailsService customerUserDetailsService){
        this.customerUserDetailsService=customerUserDetailsService;
        this.jwtService=jwtService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,
        FilterChain filterChain)throws ServletException,IOException
        {
            String authHeader=request.getHeader("Authorization");
            if(authHeader==null||!authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
            }
            String token=authHeader.substring(7);
            String userName=jwtService.extractUsername(token);
            UserDetails userDetails=customerUserDetailsService.loadUserByUsername(userName);
            if(jwtService.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userName, null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response);
    }
}
