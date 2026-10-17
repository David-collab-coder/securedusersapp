package dev.code.usersapp.userdto;

import dev.code.usersapp.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateUserRequest(

        @NotBlank String email,

        @NotBlank String name,

        @NotNull List<UserRole> roles,

        @NotBlank @Size(min = 4,message = "At least four characters") String password,

        boolean active
) {
}
