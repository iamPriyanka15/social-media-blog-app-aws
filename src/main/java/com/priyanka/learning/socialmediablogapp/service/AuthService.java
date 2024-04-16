package com.priyanka.learning.socialmediablogapp.service;

import com.priyanka.learning.socialmediablogapp.dto.LoginDto;
import com.priyanka.learning.socialmediablogapp.dto.RegisterDto;

public interface AuthService {

    //login
    String login(LoginDto loginDto);

    //Register
    String register(RegisterDto registerDto);
}
