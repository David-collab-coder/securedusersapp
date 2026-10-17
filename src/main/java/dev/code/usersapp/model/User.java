package dev.code.usersapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Email must be provided")
    private String email;
    @NotBlank(message = "Username must be provided")
    private String username;
    @NotNull(message = "Choose either ADMIN,USER,INFO")
    @Enumerated(EnumType.STRING)
    private List<UserRole> roles;

    @NotBlank
    @Size(min = 4,message = "Minimum of four characters")
    private String password;

    private boolean active;

    public User() {
    }

    public User(String email,String username, String password, List<UserRole> roles, boolean active) {
        this.username = username;
        this.roles = roles;
        this.password = password;
        this.active = active;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
