package com.example.demo.Mapper;


import com.example.demo.dto.UserResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapper {

    UserResponse toResponse(User user);

    User toEntity(UserResponse userResponse, Role role);
}
