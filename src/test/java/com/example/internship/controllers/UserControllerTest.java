package com.example.internship.controllers;

import com.example.internship.config.TestContainerConfig;
import com.example.internship.controller.UsersController;
import com.example.internship.dao.entity.Users;
import com.example.internship.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsersController.class)
@ContextConfiguration(classes = TestContainerConfig.class)
@AutoConfigureMockMvc
public class UserControllerTest {  // remove public

    @Autowired
    private MockMvc mockMvc;

    @MockBean        // Use MockitoBean
    private UserService userService;

    @Test
    public void testGetUser() throws Exception { // also remove public
        Users user = new Users();
        user.setId(10L);
        user.setUsername("John Doe");
        user.setEmail("john@example.com");
        userService.save(user);
        when(userService.findById(10L)).thenReturn(user);

        mockMvc.perform(get("/users/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
