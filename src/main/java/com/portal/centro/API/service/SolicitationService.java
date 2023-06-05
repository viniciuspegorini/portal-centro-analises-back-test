package com.portal.centro.API.service;

import com.portal.centro.API.enums.SolicitationStatus;
import com.portal.centro.API.generic.crud.GenericRepository;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Audit;
import com.portal.centro.API.model.Solicitation;
import org.springframework.stereotype.Service;

@Service
public class SolicitationService extends GenericService<Solicitation, Long> {

    private final AuditService auditService;
    private final UserService userService;


    public SolicitationService(GenericRepository<Solicitation, Long> genericRepository, AuditService auditService, UserService userService) {
        super(genericRepository);
        this.auditService = auditService;
        this.userService = userService;
    }

    @Override
    public Solicitation save(Solicitation requestBody) throws Exception {

        requestBody.setCreatedBy(userService.findSelfUser());

        Solicitation output = super.save(requestBody);
        Audit audit = new Audit();
        audit.setNewStatus(requestBody.getStatus());
        audit.setSolicitation(output);

        auditService.saveAudit(audit);

        return output;
    }

    public Solicitation updateStatus(Long id, SolicitationStatus status) throws Exception {
        Solicitation solicitation = this.findOneById(id);
        solicitation.setStatus(status);

        Audit audit = new Audit();
        audit.setNewStatus(status);
        audit.setSolicitation(solicitation);

        auditService.saveAudit(audit);

        return super.save(solicitation);
    }
}
