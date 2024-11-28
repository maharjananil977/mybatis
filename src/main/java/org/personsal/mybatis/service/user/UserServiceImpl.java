package org.personsal.mybatis.service.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.common.enums.Role;
import org.personsal.mybatis.dao.user.UserDao;
import org.personsal.mybatis.dao.user.UserFilter;
import org.personsal.mybatis.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserDao userDao;

  @Override
  public UserDetailsService userDetailsService() {
    return new UserDetailsService() {
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao
            .findOne(UserFilter.builder().email(username).build())
            .orElseThrow(() -> new UsernameNotFoundException("User name not found"));
      }
    };
  }

  @Override
  public User getUserById(int id) {
    return this.userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  public List<User> getAllUsers() {
    return this.userDao.findAll();
  }

  @Override
  public String insertUser() {
    for (int i = 0; i < 5; i++) {
      User user = new User();
      user.setEmail("email" + i + "@gmail.com");
      user.setUsername(user.getEmail());
      user.setFirstName("first" + i);
      user.setLastName("lastname" + i);
      user.setPassword("password" + i);
      user.setRole(Role.ADMIN.name());
      this.userDao.insert(user);
    }
    return "Success";
  }

  @Override
  public void verifyUser(String email) {
    User user =
        this.userDao
            .findOne(UserFilter.builder().email(email).build())
            .orElseThrow(() -> new RuntimeException("User not found"));
    user.setVerified(true);
    user.setEnabled(true);
    this.userDao.update(user);
  }
}
