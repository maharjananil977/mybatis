package org.personsal.mybatis.service.auth;

import org.personsal.mybatis.common.response.BaseResponse;
import org.personsal.mybatis.domain.auth.LoginRequest;
import org.personsal.mybatis.domain.auth.LoginResponse;
import org.personsal.mybatis.domain.auth.SignUpRequest;
import org.personsal.mybatis.domain.auth.SignUpResponse;

public interface AuthService {
    BaseResponse<SignUpResponse> signup(SignUpRequest request);

    BaseResponse<LoginResponse> login(LoginRequest request);
}
