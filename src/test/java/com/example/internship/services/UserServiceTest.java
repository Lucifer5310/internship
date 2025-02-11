package com.example.internship.services;

import com.example.internship.config.TestContainerConfig;
import com.example.internship.dao.entity.Users;
import com.example.internship.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestContainerConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        Users users = userService.createUser(6L,"John Doe", "john@example.com");

        assertNotNull(users);
        assertEquals("John Doe", users.getUsername());
    }
}
