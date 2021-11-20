package com.ironhack.movieproject.userservice.service;

import com.ironhack.movieproject.userservice.dao.UserEntity;
import com.ironhack.movieproject.userservice.dto.UpdateUserDTO;
import com.ironhack.movieproject.userservice.dto.UserDTO;
import com.ironhack.movieproject.userservice.dto.UserDetailsDTO;
import com.ironhack.movieproject.userservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final Environment environment;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, Environment environment) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.environment = environment;
    }

    public UserDetailsDTO createUser(UserDTO userDTO) {
        Optional<UserEntity> existingUser = userRepository.findByLogin(userDTO.getLogin());
        if (existingUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User already exists!");
        }
        UserEntity userEntity = new UserEntity(userDTO.getLogin(),encoder.encode(userDTO.getPassword()),"New user", userDTO.getEmail(),"","No bio");
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return new UserDetailsDTO(savedUserEntity.getId(), savedUserEntity.getLogin(), savedUserEntity.getName(), savedUserEntity.getEmail(),savedUserEntity.getImageUrl(),savedUserEntity.getBio());
    }

    public UserDetailsDTO getUserDetailsByLogin(String login){
        Optional<UserEntity> user = userRepository.findByLogin(login);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to login!");
        }else{
            return new UserDetailsDTO(user.get().getId(),user.get().getLogin(),user.get().getName(),user.get().getEmail(),user.get().getImageUrl(),user.get().getBio());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByLogin(s);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Failed to login!");
        }
        else{
            return new User(user.get().getLogin(),user.get().getPassword(), true, true, true, true, new ArrayList<>());
        }
    }

    public UserDetailsDTO getUserDetails(String token) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        Optional<UserEntity> user = userRepository.findById(tokenSubject);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }else{
            return new UserDetailsDTO(user.get().getId(),user.get().getLogin(),user.get().getName(),user.get().getEmail(),user.get().getImageUrl(),user.get().getBio());
            }
    }

    public UserDetailsDTO updateUser(String token, UpdateUserDTO updateUserDTO) {
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        Optional<UserEntity> user = userRepository.findById(tokenSubject);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }else{
            user.get().setBio(updateUserDTO.getBio());
            user.get().setName(updateUserDTO.getName());
            user.get().setImageUrl(updateUserDTO.getImageUrl());
            user.get().setEmail(updateUserDTO.getEmail());

            UserEntity updatedUser = userRepository.save(user.get());
            return new UserDetailsDTO(
                updatedUser.getId(),
                updatedUser.getLogin(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getImageUrl(),
                updatedUser.getBio()
            );
        }
    }

    private boolean userHasAccessToResource(String token, Long id){
        token = token.replace("Bearer","");
        Long tokenSubject = Long.parseLong(Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
        return tokenSubject.equals(id);
    }
}
