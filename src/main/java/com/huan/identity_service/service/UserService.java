package com.huan.identity_service.service;

import com.huan.identity_service.dto.request.UserCreatedRequest;
import com.huan.identity_service.entity.User;
import com.huan.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreatedRequest userCreatedRequest) {
        User user = new User();
        if (userRepository.existsByUsername(userCreatedRequest.getUsername()))
            throw new RuntimeException("User existed");

        user.setDob(userCreatedRequest.getDob());
        user.setEmail(userCreatedRequest.getEmail());
        user.setFirstName(userCreatedRequest.getFirstName());
        user.setLastName(userCreatedRequest.getLastName());
        user.setPassword(userCreatedRequest.getPassword());
        user.setUsername(userCreatedRequest.getUsername());
        return userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found user"));
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User updateUser(UserCreatedRequest userCreatedRequest, String id) {
        User user = getUser(id);
        user.setDob(userCreatedRequest.getDob());
        user.setEmail(userCreatedRequest.getEmail());
        user.setFirstName(userCreatedRequest.getFirstName());
        user.setLastName(userCreatedRequest.getLastName());
        user.setPassword(userCreatedRequest.getPassword());
        user.setUsername(userCreatedRequest.getUsername());
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
