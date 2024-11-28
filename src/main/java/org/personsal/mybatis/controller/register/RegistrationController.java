package org.personsal.mybatis.controller.register;

import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.common.response.BaseResponse;
import org.personsal.mybatis.domain.auth.SignUpRequest;
import org.personsal.mybatis.domain.auth.SignUpResponse;
import org.personsal.mybatis.domain.otp.OtpRequest;
import org.personsal.mybatis.service.auth.AuthService;
import org.personsal.mybatis.service.otp.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** created by: maharjananil created on: 11/28/2024 */
@RestController
@RequestMapping("register")
@RequiredArgsConstructor
public class RegistrationController {
  private final AuthService authService;
  private final OtpService otpService;

  @PostMapping
  public ResponseEntity<BaseResponse<SignUpResponse>> register(@RequestBody final SignUpRequest request) {
    return ResponseEntity.ok(this.authService.signup(request));
  }

  @Transactional
  @PostMapping("validate/otp")
  public ResponseEntity<String> validateOtp(@RequestBody OtpRequest otpRequest) {
    return ResponseEntity.ok(this.otpService.validateOtp(otpRequest));
  }
}
