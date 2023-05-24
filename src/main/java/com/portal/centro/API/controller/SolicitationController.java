package com.portal.centro.API.controller;

import com.portal.centro.API.enums.SolicitationStatus;
import com.portal.centro.API.generic.crud.GenericController;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.generic.response.GenericResponse;
import com.portal.centro.API.model.Solicitation;
import com.portal.centro.API.service.SolicitationService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("solicitation")
public class SolicitationController extends GenericController<Solicitation, Long> {

    private final SolicitationService solicitationService;

    public SolicitationController(GenericService<Solicitation, Long> genericService, SolicitationService solicitationService) {
        super(genericService);
        this.solicitationService = solicitationService;
    }

    @PostMapping("/{id}/status")
    public GenericResponse approve(@PathVariable Long id, @RequestBody @Valid String status) throws Exception {
        this.solicitationService.updateStatus(id, SolicitationStatus.valueOf(status));
        return new GenericResponse("Status atualizado.");
    }

}
