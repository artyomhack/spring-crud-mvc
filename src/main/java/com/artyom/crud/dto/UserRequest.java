package com.artyom.crud.dto;

import com.artyom.crud.entity.User;

public record UserRequest(
        Long id,
        String name,
        String lastName,
        Byte age
) {
    public static UserRequest from(User user) {
        return new UserRequest(user.getId(), user.getName(), user.getLastname(), user.getAge());
    }
}
