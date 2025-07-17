package com.huan.identity_service.dto.request;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AuthenticationRequest {
  String username;
  String password;
}
