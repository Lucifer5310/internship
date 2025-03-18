package com.example.internship.service;

import com.example.internship.dao.entity.Users;
import com.example.internship.dao.repository.ClientRepository;
import com.example.internship.dao.repository.UserRepository;
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
    private final ClientRepository clientRepository;

    @Transactional
    public Users save (Users users) {
        return userRepository.save(users);
    }

    @Transactional
    public void delete(long id) {
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (optionalUsers.isPresent()){
            Users user = optionalUsers.get();

            if (user.getClient() != null){
                clientRepository.delete(user.getClient());
            }
            userRepository.save(user);
            userRepository.deleteById(id);
            log.info("User is deleted");
        } else {
            throw new RuntimeException("User with ID " + id + " not found");
        }
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

    @Transactional(readOnly = true)
    public Users getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional(readOnly = true)
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }
}
