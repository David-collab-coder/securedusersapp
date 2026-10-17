package dev.code.usersapp;

import dev.code.usersapp.model.User;
import dev.code.usersapp.model.UserRepository;
import dev.code.usersapp.model.UserRole;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.AutoConfigureTestEntityManager;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDataJpaTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testUserAdd(){
        User user = new User("dvd@com","David","1234", List.of(UserRole.ADMIN),true);

        em.persist(user);

        Optional<User> byEmail1 = userRepository.findByEmail("dvd@com");

        assertThat(byEmail1).isNotEmpty();

        em.remove(user);

        Optional<User> byEmail2 = userRepository.findByEmail("dvd@com");

        assertThat(byEmail2).isEmpty();


    }
}
