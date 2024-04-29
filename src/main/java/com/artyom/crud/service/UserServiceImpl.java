package com.artyom.crud.service;

import com.artyom.crud.dao.UserDAO;
import com.artyom.crud.dto.UserInfo;
import com.artyom.crud.dto.UserList;
import com.artyom.crud.dto.UserRequest;
import com.artyom.crud.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
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
    public void updateById(Long id, UserRequest request) {
        userDAO.updateById(id, User.newOf(request));
    }

    @Override
    public UserInfo findById(Long id) {
        return UserInfo.from(userDAO.fetchById(id).orElseThrow(() ->
                new NoSuchElementException("User not found.")));
    }

    @Override
    public UserList findAll() {
        return UserList.of( userDAO.fetchAll().orElse(Collections.emptyList())
                .stream()
                .map(UserList.Item::from)
                .toList()
        );
    }

    @Override
    public void deleteById(Long id) {

    }
}
