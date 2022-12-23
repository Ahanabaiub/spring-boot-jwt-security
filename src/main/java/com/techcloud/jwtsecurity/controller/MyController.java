package com.techcloud.jwtsecurity.controller;

import com.techcloud.jwtsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/get")
    public String get(){
        System.out.println(jwtUtil.getClaims());
        return "Book";
    }
}
