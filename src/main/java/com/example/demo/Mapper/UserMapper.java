package com.example.demo.Mapper;

import com.example.demo.dto.UserResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface UserMapper {

    UserResponse toResponse(User user);

    @Mapping(target = "id", source = "userResponse.id")
    @Mapping(target = "role", source = "role")
    User toEntity(UserResponse userResponse, Role role);

}
