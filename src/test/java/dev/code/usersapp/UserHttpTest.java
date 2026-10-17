package dev.code.usersapp;

import dev.code.usersapp.model.User;
import dev.code.usersapp.model.UserRepository;
import dev.code.usersapp.model.UserRole;
import dev.code.usersapp.userdto.UserResponse;
import dev.code.usersapp.web.UserControllers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(value = {UserControllers.class})
@AutoConfigureMockMvc
@SpringBootTest
public class UserHttpTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void viewAllTest() throws Exception {
        when(userRepository.findAll())
                .thenReturn(List.of(new User("dvd@com","David","1234",List.of(UserRole.ADMIN),true)));

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().is4xxClientError());

    }
    @Test
//    @WithMockUser(username = "dvd@com",password = "1234")
    void testUserLogin() throws Exception{

//        UserResponse userResponse = new UserResponse(1L,"dvd@com")

        when(userRepository.save(any()))
                .thenReturn(new User("dvd@com","David","1234",List.of(UserRole.ADMIN),true));
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .with(httpBasic("dvd@com","1234"))
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").isString())
                .andExpect(jsonPath("$.active").isBoolean());

    }
}
