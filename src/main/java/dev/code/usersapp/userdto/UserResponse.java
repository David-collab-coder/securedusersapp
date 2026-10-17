package dev.code.usersapp.userdto;

import dev.code.usersapp.model.UserRole;

import java.util.List;

public record UserResponse(

        Long id,

        String email,
        String username,
        List<UserRole> roles,
        boolean active


) {
}
