package com.example.demo.controllers;

import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JWTSuccessResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.security.SecurityConstants;

import com.example.demo.security.jwt.JWTProvider;
import com.example.demo.service.UserService;
import com.example.demo.validators.ResponseErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ResponseErrorValidator responseErrorValidator;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
            public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {

        ResponseEntity<Object> listErrors = responseErrorValidator.mappedValidatorService(bindingResult);
        if (!ObjectUtils.isEmpty(listErrors)) return listErrors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("Registration successfully completed"));
    }

    @PostMapping("/signin")
              public ResponseEntity<Object> authenticationUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult ) {
        ResponseEntity<Object> listErrors = responseErrorValidator.mappedValidatorService(bindingResult);
        if (!ObjectUtils.isEmpty(listErrors)) return listErrors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTSuccessResponse( true, jwt));
    }
}




