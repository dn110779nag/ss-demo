/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.config;

    
import com.example.security.MyAuthenticationEntryPoint;
import com.example.security.MyAuthenticationFilter;
import com.example.security.MyAuthenticationProvider;
import com.example.security.MyOncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(configureEntryPoint())
                .and()
                .authorizeRequests()
                
                .antMatchers("/**")
                
                .authenticated()
                
                .and()
                .addFilterBefore(new MyOncePerRequestFilter(), BasicAuthenticationFilter.class)
                
                .formLogin().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().disable()
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(configureProvider())
                ;
    }
    


    
    @Bean
    public AuthenticationEntryPoint configureEntryPoint(){
        return new MyAuthenticationEntryPoint();
    }
    
    public MyAuthenticationProvider configureProvider(){
        return new MyAuthenticationProvider();
    }
}

