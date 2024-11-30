package org.personsal.mybatis.service.auth;

import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.common.enums.OTPType;
import org.personsal.mybatis.common.enums.Role;
import org.personsal.mybatis.common.response.BaseResponse;
import org.personsal.mybatis.dao.user.UserDao;
import org.personsal.mybatis.dao.user.UserFilter;
import org.personsal.mybatis.domain.auth.LoginRequest;
import org.personsal.mybatis.domain.auth.LoginResponse;
import org.personsal.mybatis.domain.auth.SignUpRequest;
import org.personsal.mybatis.domain.auth.SignUpResponse;
import org.personsal.mybatis.entity.User;
import org.personsal.mybatis.service.jwt.JwtService;
import org.personsal.mybatis.service.otp.OtpService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final UserDao userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final OtpService otpService;

  @Override
  public BaseResponse<SignUpResponse> signup(SignUpRequest request) {
    var user =
        User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER.name())
            .verified(false)
            .enabled(true)
            .accountNonLocked(false)
            .accountNonExpired(false)
            .credentialsNonExpired(false)
            .build();
    String otp = otpService.generateOtp(request.getEmail(), OTPType.REGISTRATION);
    userRepository.insert(user);
    return new BaseResponse<SignUpResponse>()
        .toCreatedBaseResponse(new SignUpResponse(request.getEmail(), otp));
  }

  @Override
  public BaseResponse<LoginResponse> login(LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user =
        userRepository
            .findOne(UserFilter.builder().email(request.getEmail()).build())
            .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    var jwt = jwtService.generateToken(user);
    return new BaseResponse<LoginResponse>()
        .toBaseResponse(new LoginResponse(request.getEmail(), jwt));
  }
}
