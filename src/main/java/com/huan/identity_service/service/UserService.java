package com.huan.identity_service.service;

import com.huan.identity_service.dto.request.UserCreatedRequest;
import com.huan.identity_service.dto.request.UserUpdatedRequest;
import com.huan.identity_service.entity.User;
import com.huan.identity_service.exception.AppException;
import com.huan.identity_service.exception.ErrorCode;
import com.huan.identity_service.mapper.UserMapper;
import com.huan.identity_service.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired private UserRepository userRepository;
  @Autowired private UserMapper userMapper;

  public User createUser(UserCreatedRequest userCreatedRequest) {
    if (userRepository.existsByUsername(userCreatedRequest.getUsername()))
      throw new AppException(ErrorCode.USER_EXISTED);
    User user = userMapper.fromUserCreated(userCreatedRequest);

    return userRepository.save(user);
  }

  public User getUser(String id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user"));
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public User updateUser(UserUpdatedRequest userUpdatedRequest, String id) {
    User user = getUser(id);
    userMapper.updateUser(user, userUpdatedRequest);
    return userRepository.save(user);
  }

  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }
}
