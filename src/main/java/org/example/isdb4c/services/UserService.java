package org.example.isdb4c.services;

import org.example.isdb4c.model.UserEntity;
import org.example.isdb4c.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(@Autowired UserRepository repository, @Autowired PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public UserEntity getByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, passwordEncoder.encode(password));
    }
}
