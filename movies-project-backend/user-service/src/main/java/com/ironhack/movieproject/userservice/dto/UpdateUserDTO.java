package com.ironhack.movieproject.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
        private String name;
        private String email;
        private String imageUrl;
        private String bio;
    }