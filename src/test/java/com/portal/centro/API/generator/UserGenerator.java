package com.portal.centro.API.generator;

import com.github.javafaker.Faker;
import com.portal.centro.API.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class UserGenerator {

    @Autowired
    private final Faker faker;

    public User normalUser() {
        return User.builder()
                .username(faker.name().toString())
                .password(faker.internet().password())
                .email(faker.internet().emailAddress())
                .build();
    }

    public User userInvalid() {
        return User.builder()
                .username(null)
                .password(null)
                .email(null)
                .role(null)
                .build();
    }
}
