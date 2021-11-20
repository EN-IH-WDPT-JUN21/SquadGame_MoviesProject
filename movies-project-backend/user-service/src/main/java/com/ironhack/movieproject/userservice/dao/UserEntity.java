package com.ironhack.movieproject.userservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String name;
    private String email;
    private String imageUrl;
    private String bio;

    public UserEntity(String login, String password, String name, String email, String imageUrl, String bio) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
        this.bio = bio;
    }
}
