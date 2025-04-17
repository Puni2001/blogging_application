package com.blog.blogging_application.controllers;

import java.security.Principal;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.blog.blogging_application.entities.User;
import com.blog.blogging_application.exceptions.ApiException;
import com.blog.blogging_application.payloads.JwtAuthRequest;
import com.blog.blogging_application.payloads.JwtAuthResponse;
import com.blog.blogging_application.payloads.UserDto;
import com.blog.blogging_application.repositories.UserRepo;
import com.blog.blogging_application.security.JwtTokenHelper;
import com.blog.blogging_application.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    // LOGIN ENDPOINT
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails.getUsername());

        // Load the actual User entity
        User user = this.userRepo.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ApiException("User not found with email: " + userDetails.getUsername()));

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setUser(this.mapper.map(user, UserDto.class));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // REGISTER NEW USER
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto registeredUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // GET CURRENT LOGGED-IN USER
    @GetMapping("/current-user/")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        User user = this.userRepo.findByEmail(principal.getName())
                .orElseThrow(() -> new ApiException("User not found with email: " + principal.getName()));
        return new ResponseEntity<>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
    }

    // AUTHENTICATION HANDLER
    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new ApiException("Invalid username or password!");
        }
    }
}
