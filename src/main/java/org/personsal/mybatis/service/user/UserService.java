package org.personsal.mybatis.service.user;

import org.personsal.mybatis.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    User getUserById(int id);

    List<User> getAllUsers();

    String insertUser();

    void verifyUser(String email);
}
