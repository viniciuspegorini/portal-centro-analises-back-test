package com.portal.centro.API.controller;

import com.github.javafaker.Faker;
import com.portal.centro.API.domain.User;
import com.portal.centro.API.generator.UserGenerator;
import com.portal.centro.API.repository.UserRepository;
import com.portal.centro.API.service.UserCRUDService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext context;

    private UserGenerator userGenerator;

    private Faker faker;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mvc;

    @BeforeAll()
    public void setup() {
        this.userGenerator = new UserGenerator(this.faker);
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser
    public void saveUserAndReturnCreated() throws Exception {
        User user = userGenerator.normalUser();

        mvc.perform(MockMvcRequestBuilders.post( "/authorization/register", user)).andExpect(status().isOk());
    }
}
