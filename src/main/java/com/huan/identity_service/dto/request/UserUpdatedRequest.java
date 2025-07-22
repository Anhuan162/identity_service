package com.huan.identity_service.dto.request;

import java.util.Date;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdatedRequest {
  String password;
  String firstName;
  String lastName;
  String email;
  Date dob;
}
