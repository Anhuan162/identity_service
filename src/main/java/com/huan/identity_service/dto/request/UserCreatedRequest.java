package com.huan.identity_service.dto.request;

import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreatedRequest {
  @Size(min = 4, message = "USERNAME_INVALID")
  String username;

  @Size(min = 8, message = "PASSWORD_INVALID")
  String password;

  String firstName;
  String lastName;
  String email;
  Date dob;
}
