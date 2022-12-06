package com.stackroute.authenticationservice.dto;

import com.stackroute.authenticationservice.model.Role;

public class UserDTO {
    private String email;
    private String password;
    private String username;
    private Role role;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "UserDTO [email=" + email + ", password=" + password + ", username=" + username + ", role=" + role + "]";
    }
    public UserDTO(String email, String password, String username, Role role) {
        super();
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }
    public UserDTO() {
        // TODO Auto-generated constructor stub
    }
}
