package com.portal.centro.API.repository;

import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.model.Audit;
import com.portal.centro.API.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuditRepository extends GenericRepository<Audit, Long> {

    List<Audit> findAllBySolicitation_CreatedBy(User user);

    List<Audit> findAllBySolicitation_CreatedByOrSolicitation_Project_Teacher(User user, User teacher);

}