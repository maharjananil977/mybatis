package org.personsal.mybatis.entity;

import lombok.Builder;
import lombok.Data;

/** created by: maharjananil created on: 11/26/2024 */
@Data
@Builder
public class Otp {
    private Integer id;
    private String email;
    private String otp;
    private String otpType;
    private boolean used;
    private long createdOn;
}
