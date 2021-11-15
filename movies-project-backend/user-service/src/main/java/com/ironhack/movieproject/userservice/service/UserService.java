package com.ironhack.movieproject.userservice.service;

import com.ironhack.movieproject.userservice.dao.UserEntity;
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
        UserEntity userEntity = new UserEntity(userDTO.getLogin(),encoder.encode(userDTO.getPassword()),userDTO.getName(), userDTO.getEmail());
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return new UserDetailsDTO(savedUserEntity.getId(), savedUserEntity.getLogin(), savedUserEntity.getName(), savedUserEntity.getEmail());
    }

    public UserDetailsDTO getUserDetailsByLogin(String login){
        Optional<UserEntity> user = userRepository.findByLogin(login);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to login!");
        }else{
            return new UserDetailsDTO(user.get().getId(),user.get().getLogin(),user.get().getName(),user.get().getEmail());
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

    public UserDetailsDTO getUserDetails(Long id, String token) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }else{
            token = token.replace("Bearer","");
            Long tokenSubject = Long.parseLong(Jwts.parser()
                    .setSigningKey(environment.getProperty("token.secret"))
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject());
            if (!tokenSubject.equals(id)){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"You don't have access to this resource!");
            }
            else{
                return new UserDetailsDTO(user.get().getId(),user.get().getLogin(),user.get().getName(),user.get().getEmail());
            }
        }
    }
}
