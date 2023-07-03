package com.portal.centro.API.service;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Transaction;
import com.portal.centro.API.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService  extends GenericService<Transaction, Long> {

    private final TransactionRepository transactionRepository;

    public TransactionService(GenericRepository<Transaction, Long> genericRepository, TransactionRepository transactionRepository) {
        super(genericRepository);
        this.transactionRepository = transactionRepository;
    }

    public Long getBalanceByUserId(Long id) {
        return transactionRepository.balanceByUserId(id);
    }

    public List<Transaction> getAllByUserId(Long id){
       return transactionRepository.findAllByUser_Id(id);
    }
}
