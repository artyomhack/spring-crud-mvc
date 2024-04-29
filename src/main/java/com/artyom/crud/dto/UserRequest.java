package com.artyom.crud.dto;

import com.artyom.crud.entity.User;

public class UserRequest {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public UserRequest() {
    }

    public UserRequest(Long id, String name, String lastName, Byte age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public static UserRequest from(User user) {
        return new UserRequest(user.getId(), user.getName(), user.getLastname(), user.getAge());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Byte age) {
        this.age = age;
    }
}
