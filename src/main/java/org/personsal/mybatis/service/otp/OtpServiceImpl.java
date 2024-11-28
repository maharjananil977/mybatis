package org.personsal.mybatis.service.otp;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.personsal.mybatis.common.enums.OTPType;
import org.personsal.mybatis.dao.otp.OtpDao;
import org.personsal.mybatis.dao.otp.OtpFilter;
import org.personsal.mybatis.domain.notification.NotificationRequest;
import org.personsal.mybatis.domain.otp.OtpRequest;
import org.personsal.mybatis.entity.Otp;
import org.personsal.mybatis.service.notification.email.EmailService;
import org.personsal.mybatis.service.user.UserService;
import org.personsal.mybatis.utils.DateUtils;
import org.personsal.mybatis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/** created by: maharjananil created on: 11/26/2024 */
@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
  private final OtpDao otpDao;
  private final UserService userService;
  private final EmailService emailService;

  @Value("${config.otp.expiresOn}")
  private static int otpExpiresOn;

  @Override
  public String generateOtp(String email, OTPType otpType) {
    String otp = StringUtils.generateOTP();
    Otp otpEntity =
        Otp.builder()
            .email(email)
            .otp(otp)
            .otpType(otpType.name())
            .createdOn(DateUtils.getTimestamp())
            .build();
    try {
      this.otpDao.insert(otpEntity);
      this.emailService.send(
          NotificationRequest.builder()
              .content("Your otp is :: " + otp)
              .title("Registration otp")
              .recipients(List.of(email))
              .build());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return otp;
  }

  @Override
  public String validateOtp(OtpRequest request) {
    if (StringUtils.isBlankOrNull(request.getEmail())
        || StringUtils.isBlankOrNull(request.getOtp())) {
      // todo return exception
    }
    try {
      Otp otpEntity =
          this.otpDao
              .findOne(
                  OtpFilter.builder()
                      .otp(request.getOtp())
                      .otpType(request.getOtpType().name())
                      .email(request.getEmail())
                      .build())
              .orElseThrow(() -> new NotFoundException("Otp not found"));
      if (this.isOtpExpired(otpEntity.getCreatedOn())) {
        // todo throw error;
        System.out.println("expired otp");
        return "Failed";
      }
      otpEntity.setUsed(true);
      this.otpDao.update(otpEntity);
      this.userService.verifyUser(otpEntity.getEmail());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "Success";
  }

  private boolean isOtpExpired(long createdOn) {
    long now = DateUtils.getTimestamp();
    return now < createdOn + otpExpiresOn;
  }
}
