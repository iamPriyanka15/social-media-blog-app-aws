package com.priyanka.learning.socialmediablogapp.service.impl;

import com.priyanka.learning.socialmediablogapp.dto.LoginDto;
import com.priyanka.learning.socialmediablogapp.dto.RegisterDto;
import com.priyanka.learning.socialmediablogapp.entity.Role;
import com.priyanka.learning.socialmediablogapp.entity.User;
import com.priyanka.learning.socialmediablogapp.exception.BlogApIException;
import com.priyanka.learning.socialmediablogapp.repository.RoleRepository;
import com.priyanka.learning.socialmediablogapp.repository.UserRepository;
import com.priyanka.learning.socialmediablogapp.security.JwtTokenUtility;
import com.priyanka.learning.socialmediablogapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtility jwtTokenUtility;

    @Override
    public String login(LoginDto loginDto) {
        //Authenticate the user using spring Authentication Manager
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

       // Set the Authentication object into SecurityContext Holder
        SecurityContextHolder.getContext().setAuthentication(authentication);
       String jwtToken = jwtTokenUtility.generateJwtToken(authentication);

       return jwtToken;

       // return "User Logged in successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {
        //check whether username is already exist in DB
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogApIException(HttpStatus.BAD_REQUEST, "Username is already Registered!!");
        }


        //check whether email is already exist in DB
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogApIException(HttpStatus.BAD_REQUEST, "Email is already Registered!!");
        }


        //Map RegisterDto to User and create new User Entity
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        //Map the Roles for the user
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        //save the user into DB
        userRepository.save(user);

        return "User Registered successfully";
    }
}
