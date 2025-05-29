package com.example.pickupservice.config;
import com.example.pickupservice.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
@Component

/*
Client Request
   ↓
Spring Security Filter Chain
   ↓
✔️ JwtAuthenticationFilter (→ check token, set SecurityContext)
   ↓
Controller gọi đến Service
   ↓
Service (ví dụ: PickupService)

 */

public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final JwtTokenUtil jwtUtil;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String username;
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);  /// Bỏ qua xác thực JWT
//            // chạy đến hàm Bước 3 authorizeHttpRequests() trong SecurityConfig
//            return;
//        }
//        jwt = authHeader.substring(7);
//        username = jwtUtil.extractUsername(jwt);
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            // Kiểm tra xem token có hợp lệ với thông tin user đó không (hết hạn chưa, chữ ký đúng không, v.v.)
//            if (!jwtUtil.isTokenValid(jwt, username)) {
//                throw new IllegalArgumentException(" token hết hạn");
//            }
//        }
//        // Tiếp tục chuyển request đến filter tiếp theo trong chuỗi (ví dụ: authorizeHttpRequests)
//        filterChain.doFilter(request, response);
//    }


    @Autowired
    private JwtTokenUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String username = jwtUtil.extractUsername(token);
            List<String> roles = jwtUtil.extractRoles(token);

            List<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

}
