package com.portal.centro.API.controller;

import com.portal.centro.API.generic.crud.GenericController;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Transaction;
import com.portal.centro.API.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("transaction")
public class TransactionController extends GenericController<Transaction, Long> {

    private final TransactionService transactionService;

    public TransactionController(GenericService<Transaction, Long> genericService, TransactionService transactionService) {
        super(genericService);
        this.transactionService = transactionService;
    }

    @Override
    public ResponseEntity save(@RequestBody @Valid Transaction requestBody) throws Exception {
        requestBody.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(transactionService.save(requestBody));
    }

    @Override
    public ResponseEntity update(@RequestBody @Valid Transaction requestBody) throws Exception {
        requestBody.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(transactionService.save(requestBody));
    }

    @GetMapping(path = "/getBalance/{id}")
    public ResponseEntity<Long> getBalanceByUserId(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.getBalanceByUserId(id));
    }

}
