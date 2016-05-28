/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author user
 */
@lombok.Data
public class User {
    private String username;
    private List<String> roles;
    
    public User(String username, String ... roles){
        this.username = username;
        this.roles = Arrays.asList(roles);
    }
}
