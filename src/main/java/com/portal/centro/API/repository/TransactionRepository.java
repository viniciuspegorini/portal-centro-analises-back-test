package com.portal.centro.API.repository;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository  extends GenericRepository<Transaction,Long> {
}
