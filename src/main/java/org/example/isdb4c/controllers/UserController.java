package org.example.isdb4c.controllers;

import org.example.isdb4c.security.jwt.JwtProvider;
import org.example.isdb4c.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public UserController(@Autowired UserService userService, @Autowired JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }
    @PostMapping("/auth")
    public void auth() {

    }
}
