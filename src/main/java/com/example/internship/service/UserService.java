package com.example.internship.service;

import com.example.internship.dao.Users;
import com.example.internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Users save (Users users) {
        return userRepository.save(users);
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
        log.info("User is deleted");
    }

    @Transactional(readOnly = true)
    public Iterable<Users> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Users findById(long id) {
        Optional<Users> byId = userRepository.findById(id);
        return byId.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public Users create (Users users) {
        if (userRepository.existsByUsername(users.getUsername())) {
            throw new RuntimeException("User with such username has already existed");
        }

        if (userRepository.existsByEmail(users.getEmail())) {
            throw new RuntimeException("User with such email has already existed");
        }

        return save(users);
    }

    @Transactional(readOnly = true)
    public Users getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional(readOnly = true)
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public Users getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}
