package com.huan.identity_service.controller;

import com.huan.identity_service.dto.request.AuthenticationRequest;
import com.huan.identity_service.dto.request.IntrospectRequest;
import com.huan.identity_service.dto.response.AuthenticationResponse;
import com.huan.identity_service.dto.response.IntrospectResponse;
import com.huan.identity_service.exception.ApiResponse;
import com.huan.identity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
  AuthenticationService authenticationService;

  @PostMapping("log-in")
  ApiResponse<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest authenticationRequest) {
    var result = authenticationService.authenticate(authenticationRequest);

    return ApiResponse.<AuthenticationResponse>builder().result(result).build();
  }

  @PostMapping("introspect")
  ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws JOSEException, ParseException {
    var result = authenticationService.introspect(introspectRequest);
    return ApiResponse.<IntrospectResponse>builder().result(result).build();
  }
}
