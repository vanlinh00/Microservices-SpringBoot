package com.example.deliveryservice.service;

import com.example.deliveryservice.dto.UserRequest;
import com.example.deliveryservice.entity.User;
import com.example.deliveryservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Lưu người dùng, mã hóa mật khẩu trước khi lưu vào DB
    public User saveUser(UserRequest userRequest) {

        // Kiểm tra nếu username đã tồn tại
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            //   throw new RuntimeException("Username is already taken");
            throw new IllegalArgumentException("Username is already taken");

        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRoles(userRequest.getRoles());

        return userRepository.save(user);
    }

    // Tìm người dùng bằng tên đăng nhập
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm người dùng từ cơ sở dữ liệu
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found"));

        // Trả về đối tượng UserDetails
        return new org.springframework.security.core.userdetails.User(user.getUsername(), // Tên người dùng
                user.getPassword(), // Mật khẩu đã mã hóa
                mapRolesToAuthorities(user.getRoles()) // Quyền của người dùng
        );

    }

    // Chuyển danh sách roles thành danh sách quyền (authorities)
    private List<SimpleGrantedAuthority> mapRolesToAuthorities(List<String> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role)) // Tạo authority từ mỗi role
                .collect(Collectors.toList());
    }


}
