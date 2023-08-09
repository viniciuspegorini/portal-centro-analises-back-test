package com.portal.centro.API.security.controller;

import com.portal.centro.API.service.EmailCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("emailconfirm")
public class EmailConfirmController {

    final EmailCodeService emailCodeService;

    public EmailConfirmController(EmailCodeService emailCodeService) {
        this.emailCodeService = emailCodeService;
    }

    @GetMapping("code/{code}")
    public ResponseEntity confirmEmail(@PathVariable("code") String hash) throws Exception {
        return ResponseEntity.ok(this.emailCodeService.confirmEmail(hash));
    }


}
