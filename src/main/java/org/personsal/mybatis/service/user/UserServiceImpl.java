package org.personsal.mybatis.service.user;

import lombok.RequiredArgsConstructor;
import org.personsal.mybatis.common.exceptionhandler.exceptions.InternalError;
import org.personsal.mybatis.common.exceptionhandler.exceptions.InvalidException;
import org.personsal.mybatis.common.exceptionhandler.exceptions.NotFoundException;
import org.personsal.mybatis.common.page.CustomPage;
import org.personsal.mybatis.common.page.PageRequest;
import org.personsal.mybatis.common.response.BaseResponse;
import org.personsal.mybatis.dao.user.UserDao;
import org.personsal.mybatis.dao.user.UserFilter;
import org.personsal.mybatis.domain.user.UserSearchRequest;
import org.personsal.mybatis.entity.User;
import org.springframework.data.domain.Page;
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
  public BaseResponse<User> getUserById(int id) {
    return new BaseResponse<User>()
        .toBaseResponse(
            this.userDao.findById(id).orElseThrow(() -> new NotFoundException("User not found")));
  }

  @Override
  public BaseResponse<CustomPage<User>> getAllUsers(UserSearchRequest searchRequest) {
    PageRequest pageRequest = new PageRequest().basePageRequest(searchRequest);
    try {
      Page<User> users = this.userDao.findAllWithPagination(pageRequest);
      return new BaseResponse<CustomPage<User>>()
          .toBaseResponse(
              new CustomPage<User>()
                  .toCustomPage(
                      users,
                      searchRequest.getPageSize(),
                      searchRequest.getPageSize(),
                      searchRequest.getPageNumber()));
    } catch (Exception ex) {
      throw new InternalError(ex.getMessage());
    }
  }

  @Override
  public String insertUser() {

    return "Success";
  }

  @Override
  public void verifyUser(String email) {
    User user =
        this.userDao
            .findOne(UserFilter.builder().email(email).build())
            .orElseThrow(() -> new NotFoundException("User not found"));
    user.setVerified(true);
    user.setEnabled(true);
    this.userDao.update(user);
  }
}
