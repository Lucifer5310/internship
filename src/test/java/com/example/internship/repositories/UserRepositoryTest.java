package com.example.internship.repositories;

import com.example.internship.config.TestContainerConfig;
import com.example.internship.dao.entity.Users;
import com.example.internship.dao.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = TestContainerConfig.class)
public class UserRepositoryTest { // remove public

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() { // remove public
        Users user = new Users();
        user.setId(5L);
        user.setUsername("Test User");
        user.setEmail("test@example.com");

        userRepository.save(user);

        Optional<Users> foundUser = userRepository.findByUsername("test@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals("Test User", foundUser.get().getUsername());
    }
}