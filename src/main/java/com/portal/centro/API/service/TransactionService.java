package com.portal.centro.API.service;

import com.portal.centro.API.enums.Type;
import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Transaction;
import com.portal.centro.API.model.User;
import com.portal.centro.API.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TransactionService  extends GenericService<Transaction, Long> {

    private final TransactionRepository transactionRepository;
    private final UserService userService;

    public TransactionService(GenericRepository<Transaction, Long> genericRepository, TransactionRepository transactionRepository, UserService userService) {
        super(genericRepository);
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    public List<Transaction> getAllByUserId(Long id){
       return transactionRepository.findAllByUser_IdOrderByCreatedAtDesc(id);
    }

    @Override
    public List<Transaction> getAll() {
        User selfUser = userService.findSelfUser();
        if (Objects.equals(selfUser.getRole(), Type.ADMIN))
            return transactionRepository.findAllByOrderByUserCreatedAtDesc();

        return transactionRepository.findAllByUser_IdOrderByCreatedAtDesc(selfUser.getId());
    }
}
