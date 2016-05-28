/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author user
 */
public class MyOncePerRequestFilter  extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(
            HttpServletRequest hsr, 
            HttpServletResponse hsr1, 
            FilterChain fc) throws ServletException, IOException {
        String xAuth = hsr.getHeader("My-Rest-Token");
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(xAuth, xAuth));            
        fc.doFilter(hsr, hsr1);
        
    }
    
}
