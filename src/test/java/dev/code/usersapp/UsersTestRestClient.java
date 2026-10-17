package dev.code.usersapp;

import dev.code.usersapp.model.User;
import dev.code.usersapp.userdto.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class UsersTestRestClient {

@LocalServerPort
private int port;

    @Autowired
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp(){
        restTestClient =
                RestTestClient.bindToServer()
                        .baseUrl("http://localhost:"+port)
                        .build();
    }


    @Test
    void shouldReturnUnauthorizedAccess(){
        restTestClient.get().uri("/users")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void shouldAllowAccess(){
        restTestClient.get().uri("/users")
                .headers(head -> head.setBasicAuth("dvd@com","1234"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
//                .expectBody(UserResponse[].class)
                .expectBody(new ParameterizedTypeReference<List<UserResponse>>() {
                })
                .value(value ->{
                   assertThat(value).isNotNull();
                });
    }
    
    @Test
    void shouldReturnAUserResponseTest(){
        restTestClient.get()
                .uri("/users/{email}","dvd@com")
                .headers(head -> head.setBasicAuth("dvd@com","1234"))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<UserResponse>() {
                })
                .value(va ->{
                    assertThat(va).isNotNull();
                });
    }
}
