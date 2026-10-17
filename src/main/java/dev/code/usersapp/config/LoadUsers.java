package dev.code.usersapp.config;

import dev.code.usersapp.model.User;
import dev.code.usersapp.model.UserRepository;
import dev.code.usersapp.model.UserRole;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.util.List;

@Configuration
@Profile("testData")
public class LoadUsers {

    private final UserRepository userRepository;

    public LoadUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener(value = ApplicationReadyEvent.class)
    public void addUsers(){
        var user1 = new User("dvd@com","David","1234",List.of(UserRole.ADMIN),true);
        var user2 = new User("yamaguchi@com","yamaguchi","1234",List.of(UserRole.USER),true);
        var user3 = new User("yuki@com","Ishikawa","1234",List.of(UserRole.INFO),true);

        this.userRepository.saveAll(List.of(user1,user2,user3));
    }
}
