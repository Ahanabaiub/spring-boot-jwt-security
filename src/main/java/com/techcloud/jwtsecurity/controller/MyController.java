package com.techcloud.jwtsecurity.controller;

import com.techcloud.jwtsecurity.dto.GetAllResponse;
import com.techcloud.jwtsecurity.dto.GetResponse;
import com.techcloud.jwtsecurity.dto.JwtAuthRequest;
import com.techcloud.jwtsecurity.dto.JwtTokenResponse;
import com.techcloud.jwtsecurity.service.CustomUserDetailService;
import com.techcloud.jwtsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class MyController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/token")
    public ResponseEntity<JwtTokenResponse> getToken(@RequestBody JwtAuthRequest request) throws Exception {
        System.out.println(request);

        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUserName(), request.getPassword()
            ));
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Bad credentials");
        }

        UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getUserName());
        String token = jwtUtil.generateToken(userDetails);
        JwtTokenResponse response = new JwtTokenResponse();
        response.setToken(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
