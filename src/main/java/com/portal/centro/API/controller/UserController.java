package com.portal.centro.API.controller;

import com.portal.centro.API.dto.RecoverPasswordDTO;
import com.portal.centro.API.generic.crud.GenericController;
import com.portal.centro.API.model.User;
import com.portal.centro.API.responses.DefaultResponse;
import com.portal.centro.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/users")
public class UserController extends GenericController<User, Long> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @PostMapping(path = "send-code-recover-password/username/{username}")
    public ResponseEntity sendEmailCodeRecoverPassword(@PathVariable("username") String username) throws Exception {
        return ResponseEntity.ok(userService.sendEmailCodeRecoverPassword(username));
    }

    @PostMapping(path = "recover-password")
    public ResponseEntity recoverPassword(@RequestBody @Valid RecoverPasswordDTO recoverPasswordDTO) throws Exception {
        DefaultResponse defaultResponse = userService.recoverPassword(recoverPasswordDTO);
        return ResponseEntity.status(defaultResponse.getHttpStatus()).body(defaultResponse);
    }

}
