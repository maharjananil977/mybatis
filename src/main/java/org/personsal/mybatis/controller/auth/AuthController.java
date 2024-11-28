package org.personsal.mybatis.controller.auth;

import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.common.response.BaseResponse;
import org.personsal.mybatis.domain.auth.LoginRequest;
import org.personsal.mybatis.domain.auth.LoginResponse;
import org.personsal.mybatis.domain.auth.SignUpRequest;
import org.personsal.mybatis.domain.auth.SignUpResponse;
import org.personsal.mybatis.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<BaseResponse<LoginResponse>> login(@RequestBody final LoginRequest request) {
        return ResponseEntity.ok(this.authService.login(request));
    }
}
