package com.artyom.crud.service;

import com.artyom.crud.dao.UserDAO;
import com.artyom.crud.dto.UserInfo;
import com.artyom.crud.dto.UserList;
import com.artyom.crud.dto.UserRequest;
import com.artyom.crud.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Objects;

@Repository
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void create(UserRequest request) {
        userDAO.save(User.newOf(request));
    }

    @Override
    public UserInfo updateById(Long id, UserRequest request) {
        return null;
    }

    @Override
    public UserInfo findById(Long id) {
        return null;
    }

    @Override
    public UserList findAll() {
        return UserList.of(userDAO.fetchAll()
                .stream()
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(UserList.Item::from)
                .toList()
        );
    }

    @Override
    public void deleteById(Long id) {

    }
}
