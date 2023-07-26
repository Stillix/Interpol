package com.example.interpol.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "id_user", updatable = false, nullable = false)
    long id;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "role", nullable = false)
    private String role;
}
