package com.portal.centro.API.service;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionService  extends GenericService<Transaction, Long> {

    public TransactionService(GenericRepository<Transaction, Long> genericRepository) {
        super(genericRepository);
    }
}
