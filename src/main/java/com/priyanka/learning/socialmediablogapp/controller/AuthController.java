package com.priyanka.learning.socialmediablogapp.controller;

import com.priyanka.learning.socialmediablogapp.dto.JWTAuthResponse;
import com.priyanka.learning.socialmediablogapp.dto.LoginDto;
import com.priyanka.learning.socialmediablogapp.dto.RegisterDto;
import com.priyanka.learning.socialmediablogapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // HTTP POST -- "/login", "/signin"
/*
    @PostMapping(value = { "/login", "/signin" })
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    } */

    @PostMapping(value = { "/login", "/signin" })
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);

    }

    //HTTP POST - "/register" , "/signup"
    @PostMapping(value = {"/register" , "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return  new ResponseEntity<>(response, HttpStatus.CREATED);

    }


}
