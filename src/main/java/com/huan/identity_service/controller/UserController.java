package com.huan.identity_service.controller;

import com.huan.identity_service.dto.request.UserCreatedRequest;
import com.huan.identity_service.entity.User;
import com.huan.identity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody UserCreatedRequest userCreatedRequest) {
        return userService.createUser(userCreatedRequest);
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
    User updateUser(@PathVariable String id, @RequestBody UserCreatedRequest userCreatedRequest) {
        return userService.updateUser(userCreatedRequest, id);
    }

    @DeleteMapping("{id}")
    void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
