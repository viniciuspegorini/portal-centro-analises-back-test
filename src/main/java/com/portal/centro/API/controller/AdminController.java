package com.portal.centro.API.controller;

import com.portal.centro.API.enums.Type;
import com.portal.centro.API.model.User;
import com.portal.centro.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static com.portal.centro.API.enums.Type.ADMIN;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("newuser")
    public ResponseEntity<?> addNewUserAdmin(@Valid @RequestBody User user) throws Exception {
        User loggedUser = userService.findSelfUser();

        if(loggedUser.getRole() != ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(userService.saveAdmin(user));
    }

    @PostMapping("edit/role/{id}")
    public ResponseEntity<?> editRoleAdmin(@RequestBody @Valid String role, @PathVariable Long id) throws Exception {
        User loggedUser = userService.findSelfUser();

        if(loggedUser.getRole() != ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        role = role.replaceAll("\"", "");

        return ResponseEntity.ok(userService.editUserRole(Type.valueOf(role), id));
    }
}



