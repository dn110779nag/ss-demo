/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */
@RestController
public class IndexController {
    
    @RequestMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String getIndex(Principal p){
        return "Hello, "+p.getName();
    }
}
