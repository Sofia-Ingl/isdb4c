package org.example.isdb4c.security.services;


import org.example.isdb4c.model.UserEntity;
import org.example.isdb4c.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }
        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles()
                .build();
    }
}