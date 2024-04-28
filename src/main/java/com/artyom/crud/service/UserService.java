package com.artyom.crud.service;

import com.artyom.crud.dto.UserInfo;
import com.artyom.crud.dto.UserList;
import com.artyom.crud.dto.UserRequest;

public interface UserService {
    void create(UserRequest request);
    UserInfo updateById(Long id, UserRequest request);
    UserInfo findById(Long id);
    UserList findAll();
    void deleteById(Long id);
}
