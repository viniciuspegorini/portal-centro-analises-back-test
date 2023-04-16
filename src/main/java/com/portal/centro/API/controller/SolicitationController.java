package com.portal.centro.API.controller;

import com.portal.centro.API.generic.crud.GenericController;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Solicitation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/solicitation")
public class SolicitationController extends GenericController<Solicitation, Long> {

    public SolicitationController(GenericService<Solicitation, Long> genericService) {
        super(genericService);
    }
}
