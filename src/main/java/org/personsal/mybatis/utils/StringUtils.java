package org.personsal.mybatis.utils;

import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;

/** created by: maharjananil created on: 11/26/2024 */
public class StringUtils {
  @Value("${config.otp.length}")
  private static int otpLength = 6;

  private StringUtils() {}

  public static String generateOTP() {
    SecureRandom random = new SecureRandom();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < otpLength; i++) {
      sb.append(random.nextInt(10)); // Generates a random digit (0-9)
    }
    return sb.toString();
  }

  public static boolean isBlankOrNull(String str) {
    int strLen;
    if (str != null && (strLen = str.length()) != 0) {
      for (int i = 0; i < strLen; ++i) {
        if (!Character.isWhitespace(str.charAt(i))) {
          return false;
        }
      }
    }
    return true;
  }
}
