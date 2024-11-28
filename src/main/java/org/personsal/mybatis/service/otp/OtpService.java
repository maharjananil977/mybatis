package org.personsal.mybatis.service.otp;

import org.personsal.mybatis.common.enums.OTPType;
import org.personsal.mybatis.domain.otp.OtpRequest;

/** created by: maharjananil created on: 11/26/2024 */
public interface OtpService {
    String generateOtp(String email, OTPType otpType);

    String validateOtp(OtpRequest otpRequest);
}
