package com.portal.centro.API.controller;

import com.portal.centro.API.dto.user.UserRequest;
import com.portal.centro.API.handler.UserNotFound;
import com.portal.centro.API.security.AuthService;
import com.portal.centro.API.security.JwtTokenService;
import com.portal.centro.API.service.UserCRUDService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/authorization")
public class AuthController {

    private final UserCRUDService userCRUDService;
    private final ModelMapper modelMapper;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtTokenService jwtTokenService;

    public AuthController(JwtTokenService jwtTokenService, UserCRUDService userCRUDService, AuthService authService, AuthenticationManager authenticationManager, PasswordEncoder encoder){
        this.jwtTokenService = jwtTokenService;
        this.userCRUDService = userCRUDService;
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUser(@Valid @RequestBody UserRequest userLogin) {
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(Exception e){
            throw new UserNotFound("Crendenciais não encontradas no sistema!");
        }
        return ResponseEntity.ok(jwtTokenService.generateJwtToken(authentication));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserRequest dto) {

        dto.setPassword(encoder.encode(dto.getPassword()));

        userCRUDService.add(dto);

        return ResponseEntity.ok("Registrado com sucesso!");
    }
}