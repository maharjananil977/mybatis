package org.personsal.mybatis.dao.otp;

import lombok.Builder;
import lombok.Data;
import org.personsal.mybatis.common.filter.Filter;

/** created by: maharjananil created on: 11/28/2024 */
@Data
@Builder
public class OtpFilter implements Filter {
    private String otp;
    private String otpType;
    private String email;
}
