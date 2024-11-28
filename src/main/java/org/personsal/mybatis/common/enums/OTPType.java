package org.personsal.mybatis.common.enums;

import org.personsal.mybatis.utils.StringUtils;

import java.util.Arrays;

/** created by: maharjananil created on: 11/26/2024 */
public enum OTPType {
  REGISTRATION,
  FORGET_PASSWORD,
  RESET_PASSWORD;

  public static OTPType getOTPTypeByName(String name) {
    if (StringUtils.isBlankOrNull(name)) return null;
    return Arrays.stream(OTPType.values())
        .filter(r -> r.name().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }
}
