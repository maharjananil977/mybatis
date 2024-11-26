package org.personsal.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.personsal.mybatis.entity.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserDao {
    List<User> findAll();

    Optional<User> findById(int id);

    void insert(User user);
    Optional<User> findByEmail(String email);
}
