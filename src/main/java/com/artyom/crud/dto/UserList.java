package com.artyom.crud.dto;

import com.artyom.crud.entity.User;

public interface UserList extends Iterable<UserList.Item> {
    static UserList of(Iterable<Item> items) {
        return items::iterator;
    }

    record Item(Long id, String name, String lastName, Byte age) {
        public static Item from(User user) {
            return new Item(user.getId(), user.getName(), user.getLastname(), user.getAge());
        }
    }
}
