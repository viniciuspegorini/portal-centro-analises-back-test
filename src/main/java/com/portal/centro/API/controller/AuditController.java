package com.portal.centro.API.controller;

import com.portal.centro.API.generic.crud.GenericController;
import com.portal.centro.API.model.Audit;
import com.portal.centro.API.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("audit")
public class AuditController extends GenericController<Audit, Long> {

    @Autowired
    public AuditController(AuditService auditService) {
        super(auditService);
    }

}
