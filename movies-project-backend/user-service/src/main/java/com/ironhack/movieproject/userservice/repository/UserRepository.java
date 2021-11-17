package com.ironhack.movieproject.userservice.repository;

import com.ironhack.movieproject.userservice.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByLogin(String login);
}
