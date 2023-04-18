package com.portal.centro.API.repository;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.model.Solicitation;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationRepository extends GenericRepository<Solicitation, Long> {
}
