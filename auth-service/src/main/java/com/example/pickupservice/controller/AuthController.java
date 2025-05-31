package com.example.pickupservice.controller;


import com.example.pickupservice.dto.AuthRequest;
import com.example.pickupservice.dto.AuthResponse;
import com.example.pickupservice.dto.UserRequest;
import com.example.pickupservice.entity.User;
import com.example.pickupservice.service.UserService;
import com.example.pickupservice.utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        if (request.getUsername() == null || request.getUsername().isBlank() ||
                request.getPassword() == null || request.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Username and password must not be empty");
        }

        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);
/*

✅ BƯỚC 2: authManager.authenticate(...) chạy


DaoAuthenticationProvider.authenticate()

→ UserDetailsService.loadUserByUsername(username)

→ BCryptPasswordEncoder.matches(rawPassword, encodedPasswordFromDB)

→ Kiểm tra tài khoản có bị lock/expire không

→ Trả về Authentication đã xác thực + authorities (roles)




✅ BƯỚC 3: Authentication chứa thông tin user đã xác thực

UsernamePasswordAuthenticationToken [
  Principal = "john123",
  Credentials = [PROTECTED],
  Authenticated = true,
  Authorities = [ROLE_USER]
]

 */
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

