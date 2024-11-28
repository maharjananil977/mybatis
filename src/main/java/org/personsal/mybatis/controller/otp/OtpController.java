package org.personsal.mybatis.controller.otp;

import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.service.otp.OtpService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** created by: maharjananil created on: 11/27/2024 */
@RestController
@RequestMapping("otp")
@RequiredArgsConstructor
public class OtpController {
    private final OtpService otpService;
}
