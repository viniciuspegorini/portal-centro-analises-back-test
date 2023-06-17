package com.portal.centro.API.repository;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.model.StudentSolicitation;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSolicitationRepository extends GenericRepository<StudentSolicitation, Long> {
}
