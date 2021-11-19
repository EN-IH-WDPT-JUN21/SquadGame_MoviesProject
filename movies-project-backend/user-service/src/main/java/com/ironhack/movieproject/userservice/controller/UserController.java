package com.ironhack.movieproject.userservice.controller;

import com.ironhack.movieproject.userservice.dto.UpdateUserDTO;
import com.ironhack.movieproject.userservice.dto.UserDTO;
import com.ironhack.movieproject.userservice.dto.UserDetailsDTO;
import com.ironhack.movieproject.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDetailsDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/user_details/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDetailsDTO getUserDetails(@PathVariable Long id, @RequestHeader (name="Authorization") String token){
        return userService.getUserDetails(id, token);
    }
    @PutMapping("/user_details/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDetailsDTO updateUser(@PathVariable Long id, @RequestHeader (name="Authorization") String token,@RequestBody UpdateUserDTO updateUserDTO){
        return userService.updateUser(id,token,updateUserDTO);
    }
}