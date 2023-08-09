package com.portal.centro.API.security.controller;

import com.portal.centro.API.dto.ChangePasswordDTO;
import com.portal.centro.API.dto.RecoverPasswordDTO;
import com.portal.centro.API.dto.UserDto;
import com.portal.centro.API.generic.crud.GenericController;
import com.portal.centro.API.model.User;
import com.portal.centro.API.responses.DefaultResponse;
import com.portal.centro.API.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController extends GenericController<User, Long> {

    private final UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        super(userService);
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "send-code-recover-password/email/{email}")
    public ResponseEntity sendEmailCodeRecoverPassword(@PathVariable("email") String email) throws Exception {
        return ResponseEntity.ok(userService.sendEmailCodeRecoverPassword(email));
    }

    @PostMapping(path = "recover-password")
    public ResponseEntity recoverPassword(@RequestBody @Valid RecoverPasswordDTO recoverPasswordDTO) throws Exception {
        DefaultResponse defaultResponse = userService.recoverPassword(recoverPasswordDTO);
        return ResponseEntity.status(defaultResponse.getHttpStatus()).body(defaultResponse);
    }

    @PostMapping(path = "change-password")
    public ResponseEntity changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO) throws Exception {
        DefaultResponse defaultResponse = userService.changePassword(changePasswordDTO);
        return ResponseEntity.status(defaultResponse.getHttpStatus()).body(defaultResponse);
    }

    @GetMapping(path = "/findSelfUser")
    public ResponseEntity<UserDto> findSelfUser(){
        return ResponseEntity.ok(convertEntityToDto(userService.findSelfUser()));
    }

    @Override
    public ResponseEntity update(@RequestBody User requestBody) throws Exception {
        return super.update(requestBody);
    }

    @GetMapping(path = "role/{role}")
    public ResponseEntity<List<UserDto>> findUsersByRole(@PathVariable("role") String role) {
        return ResponseEntity.ok(convertEntityListToDto(userService.findUsersByRole(role)));
    }

    private UserDto convertEntityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private List<UserDto> convertEntityListToDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users)
            userDtos.add(convertEntityToDto(user));
        return userDtos;
    }

}
