package com.example.demo.controller;

import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.security.UserPrinciple;
import com.example.demo.service.UserAuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserAuthServices userAuthServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody UserResponse request) {

        User user = userAuthServices.Register(request);

        UserPrinciple principle = new UserPrinciple(user);
        String token = jwtUtil.generateToken(principle);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@RequestBody UserResponse userDTO) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(userDTO.getEmail(),
                    userDTO.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok("wrong credential");
        }
        UserPrinciple principle = (UserPrinciple) authentication.getPrincipal();
        assert principle != null;
        String token = jwtUtil.generateToken(principle);
        return ResponseEntity.ok(token);
    }

}
