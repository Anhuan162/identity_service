package com.huan.identity_service.mapper;

import com.huan.identity_service.dto.request.UserCreatedRequest;
import com.huan.identity_service.dto.request.UserUpdatedRequest;
import com.huan.identity_service.dto.response.UserResponse;
import com.huan.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
  User fromUserCreated(UserCreatedRequest userCreatedRequest);
  void updateUser(@MappingTarget User user, UserUpdatedRequest userUpdatedRequest);
  UserResponse toUserResponse(User user);
}
