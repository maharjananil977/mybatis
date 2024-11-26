package org.personsal.mybatis.domain.auth;

import lombok.Data;
import org.personsal.mybatis.common.request.Request;

@Data
public class LoginRequest implements Request {
    private String email;
    private String password;
}
