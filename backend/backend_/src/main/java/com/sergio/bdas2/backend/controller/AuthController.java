package com.sergio.bdas2.backend.controller;

import com.sergio.bdas2.backend.model.dto.AuthenticationRequest;
import com.sergio.bdas2.backend.model.dto.RegistrationUserRequest;
import com.sergio.bdas2.backend.model.entity.User;
import com.sergio.bdas2.backend.model.dto.UserDto;
import com.sergio.bdas2.backend.security.JwtTokenProvider;
import com.sergio.bdas2.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

//    @PostMapping(value = "/login")
//    public ResponseEntity<UserDto> login(@RequestBody AuthenticationRequest request) {
//        try {
//            Authentication authenticate = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//
//            User user = (User) authenticate.getPrincipal();
//
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.AUTHORIZATION, jwtTokenProvider.generateAccessToken(user))
//                    .body(user.toUserDto());
//        }  catch (BadCredentialsException ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserDto> login(@RequestBody AuthenticationRequest request, HttpSession session) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();

            // Store user information in the session
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenProvider.generateAccessToken(user))
                    .body(user.toUserDto());
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping(value = "/registration")
    public void register(@RequestBody RegistrationUserRequest request){
        userService.register(request);
    }
}
