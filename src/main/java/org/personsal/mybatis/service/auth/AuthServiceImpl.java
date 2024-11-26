package org.personsal.mybatis.service.auth;

import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.common.enums.Role;
import org.personsal.mybatis.common.response.BaseResponse;
import org.personsal.mybatis.dao.UserDao;
import org.personsal.mybatis.domain.auth.LoginRequest;
import org.personsal.mybatis.domain.auth.LoginResponse;
import org.personsal.mybatis.domain.auth.SignUpRequest;
import org.personsal.mybatis.domain.auth.SignUpResponse;
import org.personsal.mybatis.service.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.personsal.mybatis.entity.User;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public BaseResponse<SignUpResponse> signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER.name()).build();
        userRepository.insert(user);
        return BaseResponse.<SignUpResponse>builder().data(new SignUpResponse(request.getEmail(), "otp")).build();
    }

    @Override
    public BaseResponse<LoginResponse> login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return BaseResponse.<LoginResponse>builder().data(new LoginResponse(request.getEmail(), jwt)).build();
    }
}
