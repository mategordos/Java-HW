package com.epam.training.ticketservice.core.user.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;

import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.Objects;

@Entity
@Table(name = "Users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public enum Role {
        ADMIN,
        USER
    }
}
