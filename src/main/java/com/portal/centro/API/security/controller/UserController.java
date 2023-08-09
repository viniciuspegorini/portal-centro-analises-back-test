package com.portal.centro.API.security.controller;

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
@RequestMapping("users")
public class UserController extends GenericController<User, Long> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
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

}
