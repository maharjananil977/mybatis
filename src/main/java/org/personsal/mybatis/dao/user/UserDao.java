package org.personsal.mybatis.dao.user;

import org.apache.ibatis.annotations.Mapper;
import org.personsal.mybatis.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserDao {
    List<User> findAll();

    Optional<User> findById(int id);

    void insert(User user);
    Optional<User> findOne(UserFilter filter);
    void update(User user);
    Page<User> findAllWithPagination(Pageable pageable);
}
