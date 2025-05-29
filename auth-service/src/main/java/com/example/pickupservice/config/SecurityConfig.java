package com.example.pickupservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/*
Frontend]
     |
     v
[API Gateway] <--> [Authentication Service]
     |
     +--> [Pickup Service]
     |
     +--> [Delivery Service]

 */


/*
🔐 Vai trò của Authentication Service:
Cung cấp các API như:

POST /auth/login → trả về JWT token

POST /auth/register

GET /auth/me → thông tin người dùng

Khi user đăng nhập thành công, token sẽ được trả về → Frontend/Client sẽ gắn token này vào các request tiếp theo.

 */


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    @Lazy
//    private JwtAuthenticationFilter jwtAuthFilter;


    @Bean  //Đây là một bean cấu hình bảo mật
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Bước 1:  nhận vào  http
                .csrf(csrf -> csrf.disable())

                // Bước 2:
                // addFilterBefore(...) chạy trước

                //  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // Bước 3: cho phép các endpoint nào được đi qua
                .authorizeHttpRequests(auth -> auth
                        //không cần JWT
                        .requestMatchers(
                                "/api/public/**",
                                "/auth/**"
                        ).permitAll()  //
                        // các enpoint nào phải kiếm tra JWT
                        .anyRequest().authenticated()
                )
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
        ;
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}

/*🔄 Luồng hoạt động cơ bản:
👤 User login ở authentication-service
👉 Nhận lại accessToken (JWT).

🚀 Client gửi request đến các service khác như:

pickup-service

delivery-service

➕ Gắn accessToken vào header:

http
Copy
Edit
Authorization: Bearer <accessToken>
🛡️ Các service decode JWT → biết user nào, role gì
👉 Có thể chặn nếu không có quyền hoặc token sai.

*/