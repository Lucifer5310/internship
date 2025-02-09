package com.example.internship.service;

import com.example.internship.dao.Shelf;
import com.example.internship.dao.Users;
import com.example.internship.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Users save (Users users) {
        return userRepository.save(users);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public Iterable<Users> findAll() {
        return userRepository.findAll();
    }

    public Users findById(long id) {
        Optional<Users> byId = userRepository.findById(id);
        return byId.orElseThrow();
    }

    public Users create (Users users) {
        if (userRepository.existsByUsername(users.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (userRepository.existsByEmail(users.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(users);
    }

    public Users getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public Users getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }
}
