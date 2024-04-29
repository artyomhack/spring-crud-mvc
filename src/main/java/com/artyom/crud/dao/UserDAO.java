package com.artyom.crud.dao;

import com.artyom.crud.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void save(User user);
    void updateById(Long id, User user);
    Optional<List<User>> fetchAll();
    Optional<User> fetchById(Long id);
    boolean deleteById(Long id);
}
