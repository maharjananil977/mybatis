package org.personsal.mybatis.domain.otp;

import lombok.Data;
import org.personsal.mybatis.common.enums.OTPType;
import org.personsal.mybatis.common.request.Request;

/** created by: maharjananil created on: 11/26/2024 */
@Data
public class OtpRequest implements Request {
  private String email;
  private String otp;
  private OTPType otpType;
}
