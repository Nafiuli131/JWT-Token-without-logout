package com.example.jwtToken.controller;

import com.example.jwtToken.dto.AuthRequestDto;
import com.example.jwtToken.service.UserService;
import com.example.jwtToken.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/")
    public String welcomeMessage() {
        return "Welcome to the nafiul's web";
    }

    @GetMapping("/message")
    public String message() {
        return "This is Nafiul Islam";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequestDto authRequestDto) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUserName(), authRequestDto.getPassword()));
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequestDto.getUserName());
    }


}
