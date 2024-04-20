package com.thanhnt.cinemasystem.controller;

import com.thanhnt.cinemasystem.request.LoginRequest;
import com.thanhnt.cinemasystem.responsitory.UserRepository;
import com.thanhnt.cinemasystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class UserController {

  private AuthenticationManager authenticationManager;

  private UserService userService;

  @Autowired
  public UserController(
      AuthenticationManager authenticationManager,
      UserService userService,
      UserRepository userRepository,
      PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
  }

  @PostMapping("login")
  private ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
    try {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequest.getEmail(), loginRequest.getPassword()));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      return ResponseEntity.ok("Success");
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }
  }
}
