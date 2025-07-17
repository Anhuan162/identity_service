package com.huan.identity_service.controller;

import com.huan.identity_service.dto.request.UserCreatedRequest;
import com.huan.identity_service.dto.request.UserUpdatedRequest;
import com.huan.identity_service.entity.User;
import com.huan.identity_service.exception.ApiResponse;
import com.huan.identity_service.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired private UserService userService;

  @PostMapping
  ApiResponse<User> createUser(@RequestBody @Valid UserCreatedRequest userCreatedRequest) {
    ApiResponse<User> apiResponse = new ApiResponse<>();
    apiResponse.setResult(userService.createUser(userCreatedRequest));
    return apiResponse;
  }

  @GetMapping("/{id}")
  User getUser(@PathVariable String id) {
    return userService.getUser(id);
  }

  @GetMapping
  List<User> getUsers() {
    return userService.getUsers();
  }

  @PutMapping("{id}")
  User updateUser(@PathVariable String id, @RequestBody UserUpdatedRequest userUpdatedRequest) {
    return userService.updateUser(userUpdatedRequest, id);
  }

  @DeleteMapping("{id}")
  void deleteUser(@PathVariable String id) {
    userService.deleteUser(id);
  }
}
