package com.portal.centro.API.service;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Solicitation;
import org.springframework.stereotype.Service;

@Service
public class SolicitationService extends GenericService<Solicitation, Long> {

    public SolicitationService(GenericRepository<Solicitation, Long> genericRepository) {
        super(genericRepository);
    }
}
