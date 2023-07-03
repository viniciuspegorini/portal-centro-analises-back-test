package com.portal.centro.API.repository;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository  extends GenericRepository<Transaction,Long> {

    @Query(nativeQuery = true,value ="Select sum(case when type = 0 then value when type = 1 then -value  else 0 end) From transaction where user_id=:userId")
    Long balanceByUserId(Long userId);

    List<Transaction> findAllByUser_Id(Long userId);

}
