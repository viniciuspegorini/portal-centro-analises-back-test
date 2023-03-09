package com.portal.centro.API.service;

import com.portal.centro.API.domain.User;
import com.portal.centro.API.dto.user.UserRequest;
import com.portal.centro.API.dto.user.UserResponse;
import com.portal.centro.API.generic.ICRUDService;
import com.portal.centro.API.handler.ObjectInvalidException;
import com.portal.centro.API.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserCRUDService extends ICRUDService<UserRequest, User, UserResponse> {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    BCryptPasswordEncoder encoder;

    public UserCRUDService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<UserResponse> getAll(String username) {
        log.info("Fetching users...");
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponse> getById(Long id) {
        log.info("Trying to find user...");
        Optional<User> user = userRepository.findById(id);

        return Optional.of(modelMapper.map(user.get(), UserResponse.class));
    }

    @Override
    public UserResponse add(UserRequest model) {
        log.info("Adding new user...");

        User user = userRepository.findUserByUsername(model.getUsername());

        if(user != null) throw new ObjectInvalidException("Credentials already used! Please try again with another username and password!");

        try{
            userRepository.save(user);
        }catch (Exception e){
            throw new ObjectInvalidException("Error saving new user!");
        }

        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public void delete(Long id) {}

    @Override
    public UserResponse update(UserRequest model, Long id) {
        return null;
    }
}