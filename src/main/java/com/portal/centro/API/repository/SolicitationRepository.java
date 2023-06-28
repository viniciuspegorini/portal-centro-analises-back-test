package com.portal.centro.API.repository;

import com.portal.centro.API.enums.SolicitationStatus;
import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.model.Solicitation;
import com.portal.centro.API.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitationRepository extends GenericRepository<Solicitation, Long> {

    List<Solicitation> findAllByCreatedByAndStatus(User user, SolicitationStatus status);
}
