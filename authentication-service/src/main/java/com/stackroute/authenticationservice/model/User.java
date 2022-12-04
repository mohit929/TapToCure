package com.stackroute.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @Column(name="user_id")
    private int id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="user_email")
    private String email;
    @Column(name="user_role")
    private String role;
}
