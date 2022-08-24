package com.example.userservice.vo;

public record ResponseUser(
    String email,
    String name,
    String userId
) {
}
