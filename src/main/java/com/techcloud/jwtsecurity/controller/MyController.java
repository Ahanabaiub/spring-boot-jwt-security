package com.techcloud.jwtsecurity.controller;

import com.techcloud.jwtsecurity.dto.GetAllResponse;
import com.techcloud.jwtsecurity.dto.GetResponse;
import com.techcloud.jwtsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MyController {

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/get")
    public ResponseEntity<GetResponse> get(){
        System.out.println(jwtUtil.getClaims());
        GetResponse getResponse = new GetResponse();
        getResponse.setValue("BOOK");
        return new ResponseEntity<>(getResponse, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<GetAllResponse> getAll(){
        System.out.println(jwtUtil.getClaims());
        GetAllResponse getAllResponse = new GetAllResponse();
        getAllResponse.setValues(Arrays.asList("Book","PEN"));
        return new ResponseEntity<>(getAllResponse, HttpStatus.OK);
    }
}
