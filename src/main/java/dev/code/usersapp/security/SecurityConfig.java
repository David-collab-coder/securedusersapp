package dev.code.usersapp.security;

import dev.code.usersapp.model.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http){

        http.csrf(AbstractHttpConfigurer::disable);
        http.authenticationProvider(daoAuthProvider());
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

         return http.build();

    }

    @Bean
    public AuthenticationProvider daoAuthProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(new SecureUserConfig(userRepository));
        return provider;
    }
}
