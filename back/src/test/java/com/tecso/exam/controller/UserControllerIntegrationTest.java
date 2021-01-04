package com.tecso.exam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecso.exam.config.ControllerIntegrationTestConfig;
import com.tecso.exam.domain.User;
import com.tecso.exam.domain.dto.UserLoginDTO;
import com.tecso.exam.domain.dto.UserRegisterDTO;
import com.tecso.exam.service.UserService;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerIntegrationTest extends ControllerIntegrationTestConfig {
    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserService userService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    private String url;

    MockMvc mockMvc;

    @BeforeAll
    void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.url = this.localUrl.concat("/users");

    }

    @Test
    void loginShouldReturnToken() throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setLastname("admin");
        user.setName("admin");
        userService.save(user);

        UserLoginDTO userLogin = new UserLoginDTO("admin", "admin");
        String userJson = objectMapper.writeValueAsString(userLogin);
        mockMvc.perform(post(this.url + "/login")
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void registerShouldPersistUser() throws Exception {
        UserRegisterDTO userRegister = new UserRegisterDTO();
        userRegister.setUsername("test");
        userRegister.setPassword("test");
        userRegister.setName("stest");
        userRegister.setLastname("stest");
        String userJson = objectMapper.writeValueAsString(userRegister);
        mockMvc.perform(post(this.url + "/register")
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
        User test = userService.findByUsername("test");
        Assert.notNull(test);
    }
}
