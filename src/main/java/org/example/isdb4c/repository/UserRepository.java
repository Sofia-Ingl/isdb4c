package org.example.isdb4c.repository;

import org.example.isdb4c.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByLogin(String login);

    UserEntity findByLoginAndPassword(String login, String password);
}
