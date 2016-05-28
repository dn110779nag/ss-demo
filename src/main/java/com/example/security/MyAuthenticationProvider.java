/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author user
 */
public class MyAuthenticationProvider implements AuthenticationProvider{
    
    private Map<String, User> map = new HashMap<>();

    {
        map.put("user", new User("user", "USER"));
        map.put("admin", new User("admin", "USER", "ADMIN"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String username = (String) authentication.getPrincipal();
      String password = (String) authentication.getCredentials();

        if (username==null || password==null) {
            throw new BadCredentialsException("Invalid Domain User Credentials");
        }
        User u = map.get(username);
        if(u==null){
            throw new BadCredentialsException("Invalid Domain User Credentials");
        }
        List<GrantedAuthority> list = u.getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(u.getUsername(), u.getUsername(), list);
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
