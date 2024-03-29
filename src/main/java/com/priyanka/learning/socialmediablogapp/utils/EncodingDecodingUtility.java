package com.priyanka.learning.socialmediablogapp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodingDecodingUtility {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("piyu1234"));
        System.out.println(passwordEncoder.encode("admin"));

    }
}
