package com.artyom.crud.dto;

public record UserInfo(
        Long id,
        String name,
        String lastName,
        Byte age
) {
}
