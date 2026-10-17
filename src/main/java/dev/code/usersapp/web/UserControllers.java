package dev.code.usersapp.web;

import dev.code.usersapp.model.*;
import dev.code.usersapp.userdto.CreateUserRequest;
import dev.code.usersapp.userdto.UserMapper;
import dev.code.usersapp.userdto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControllers {

    private final UserRepository users;

    public UserControllers(UserRepository users) {
        this.users = users;
    }
    @GetMapping
    public Iterable<UserResponse> users(){
        return this.users
                .findAll()
                .stream()
                .map(UserMapper::userResponse)
                .toList();
    }
    @GetMapping("/{email}")
    public UserResponse getUser(@PathVariable("email") String email){
        return UserMapper.userResponse(this.users.findByEmail(email).get());
    }
    @PostMapping
    public UserResponse user(@Valid @RequestBody CreateUserRequest userRequest){
        User entity = UserMapper.toEntity(userRequest);
        users.save(entity);
        return UserMapper.userResponse(entity);

    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("email") String email){
        this.users.deleteByEmail(email);
    }
}
