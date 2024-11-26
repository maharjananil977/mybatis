package org.personsal.mybatis.domain.auth;

import lombok.Data;
import org.personsal.mybatis.common.request.Request;

@Data
public class SignUpRequest implements Request {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
