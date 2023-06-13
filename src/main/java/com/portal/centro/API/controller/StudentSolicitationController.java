package com.portal.centro.API.controller;

import com.portal.centro.API.dto.StudentSolicitationDTO;
import com.portal.centro.API.generic.crud.GenericController;
import com.portal.centro.API.model.StudentSolicitation;
import com.portal.centro.API.service.StudentSolicitationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("student-solicitation")
public class StudentSolicitationController extends GenericController<StudentSolicitation, Long> {

    private final StudentSolicitationService studentSolicitationService;

    public StudentSolicitationController(StudentSolicitationService studentSolicitationService) {
        super(studentSolicitationService);
        this.studentSolicitationService = studentSolicitationService;
    }

    @PostMapping("solicit")
    public ResponseEntity<StudentSolicitation> solicit(@RequestBody @Valid StudentSolicitationDTO studentSolicitationDTO) throws Exception {
        return ResponseEntity.ok(studentSolicitationService.solicit(studentSolicitationDTO));
    }

    @PostMapping("approve")
    public ResponseEntity<StudentSolicitation> approve(@RequestBody @Valid StudentSolicitation studentSolicitation) throws Exception {
        return ResponseEntity.ok(studentSolicitationService.approve(studentSolicitation));
    }

    @PostMapping("refuse")
    public ResponseEntity<StudentSolicitation> refuse(@RequestBody @Valid StudentSolicitation studentSolicitation) throws Exception {
        return ResponseEntity.ok(studentSolicitationService.refuse(studentSolicitation));
    }

}
