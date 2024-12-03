package org.personsal.mybatis.service.user;

import org.personsal.mybatis.common.page.CustomPage;
import org.personsal.mybatis.common.response.BaseResponse;
import org.personsal.mybatis.domain.user.UserSearchRequest;
import org.personsal.mybatis.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    BaseResponse<User> getUserById(int id);

    BaseResponse<List<User>> getAllUsers(UserSearchRequest userSearchRequest);

    String insertUser();

    void verifyUser(String email);
}
