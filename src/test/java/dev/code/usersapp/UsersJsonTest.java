package dev.code.usersapp;

import dev.code.usersapp.model.User;
import dev.code.usersapp.model.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class UsersJsonTest {
    @Autowired
    private JacksonTester<User> tester;

    @Test
    void testUserSerialization() throws IOException {
        User user = new User("dvd@com","David","1234", List.of(UserRole.ADMIN),true);

        JsonContent<User> write = tester.write(user);

        assertThat(write).isNotNull();
        assertThat(write).extractingJsonPathBooleanValue("$.active").isTrue();
        assertThat(write).extractingJsonPathArrayValue("$.roles").isEqualTo(List.of("ADMIN"));
        assertThat(write).extractingJsonPathStringValue("@.email").isEqualTo("dvd@com");
    }

    @Test
    void deserializeUserTest() throws IOException{

        String content = """
                {
                "email":"dvd@com",
                "username":"David",
                "password":"1234",
                "roles":["ADMIN"],
                "active":true
                
                }
                """;
        User user = this.tester.parseObject(content);

        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("David");
        assertThat(user.getRoles()).size().isEqualTo(1);
    }
}
