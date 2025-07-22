package com.huan.identity_service.dto.response;

import java.util.Date;
import java.util.Set;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResponse {
  String username;
  String password;
  String firstName;
  String lastName;
  String email;
  Date dob;
  Set<String> roles;
}
