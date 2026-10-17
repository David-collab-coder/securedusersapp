package dev.code.usersapp.userdto;

import dev.code.usersapp.model.User;

import java.util.List;

public class UserMapper {
    public static UserResponse userResponse(User user){
        return new UserResponse(user.getId(),user.getEmail(), user.getUsername(),user.getRoles(),user.isActive());
    }

    public static List<UserResponse> userResponse(List<User> users){
     return users
             .stream()
             .map(UserMapper::userResponse)
             .toList();
    }

    public static User toEntity(CreateUserRequest userRequest){
        User user = new User();
        user.setActive(userRequest.active());
        user.setEmail(userRequest.email());
        user.setPassword(user.getPassword());
        user.setRoles(userRequest.roles());

        return user;
    }
}
