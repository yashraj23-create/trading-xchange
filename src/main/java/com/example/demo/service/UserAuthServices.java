package com.example.demo.service;


import com.example.demo.Mapper.UserMapper;
import com.example.demo.Repository.RoleRepo;
import com.example.demo.Repository.UserRepo;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserAuthServices implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User Register(UserResponse userDTO) {
        Optional<User> user = userRepo.findByUsername(userDTO.getUsername());
        if(user.isPresent()){
            throw new RuntimeException("Username already there");
        }
        Role role = roleRepo.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("role not there"));
        User user1 = userMapper.toEntity(userDTO,role);
        user1.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        user1 = userRepo.save(user1);
        return user1;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email is not regidstere"));
        return new UserPrinciple(user);
    }

}
