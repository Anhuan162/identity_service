package com.huan.identity_service.service;

import com.huan.identity_service.dto.request.UserCreatedRequest;
import com.huan.identity_service.dto.request.UserUpdatedRequest;
import com.huan.identity_service.dto.response.UserResponse;
import com.huan.identity_service.entity.Role;
import com.huan.identity_service.entity.User;
import com.huan.identity_service.exception.AppException;
import com.huan.identity_service.exception.ErrorCode;
import com.huan.identity_service.mapper.UserMapper;
import com.huan.identity_service.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserService {
  @Autowired private UserRepository userRepository;
  @Autowired private UserMapper userMapper;
  @Autowired private PasswordEncoder passwordEncoder;

  public User createUser(UserCreatedRequest userCreatedRequest) {
    if (userRepository.existsByUsername(userCreatedRequest.getUsername()))
      throw new AppException(ErrorCode.USER_EXISTED);
    User user = userMapper.fromUserCreated(userCreatedRequest);
    user.setPassword(passwordEncoder.encode(userCreatedRequest.getPassword()));

    Set<String> roles = new HashSet<>();
    roles.add(Role.USER.name());

    user.setRoles(roles);
    return userRepository.save(user);
  }

  public User getUser(String id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user"));
  }

  public List<UserResponse> getUsers() {
    var authorization = SecurityContextHolder.getContext().getAuthentication();
    authorization.getAuthorities().forEach(auth -> log.warn(auth.getAuthority()));

    return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
  }

  public User updateUser(UserUpdatedRequest userUpdatedRequest, String id) {
    User user = getUser(id);
    userMapper.updateUser(user, userUpdatedRequest);
    user.setPassword(passwordEncoder.encode(userUpdatedRequest.getPassword()));

    return userRepository.save(user);
  }

  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }
}
