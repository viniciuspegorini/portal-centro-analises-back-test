package com.portal.centro.API.security.controller;

import com.portal.centro.API.generic.crud.GenericController;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController extends GenericController<Transaction, Long> {

    public TransactionController(GenericService<Transaction, Long> genericService) {
        super(genericService);
    }
}
