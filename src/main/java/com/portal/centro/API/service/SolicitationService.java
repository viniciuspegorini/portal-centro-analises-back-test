package com.portal.centro.API.service;

import com.portal.centro.API.enums.SolicitationStatus;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Audit;
import com.portal.centro.API.model.Solicitation;
import com.portal.centro.API.model.User;
import com.portal.centro.API.repository.SolicitationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitationService extends GenericService<Solicitation, Long> {

    private final AuditService auditService;
    private final UserService userService;
    private final SolicitationRepository solicitationRepository;

    public SolicitationService(SolicitationRepository solicitationRepository, AuditService auditService, UserService userService) {
        super(solicitationRepository);
        this.solicitationRepository = solicitationRepository;
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

    public List<Solicitation> getPending() {
        User user = userService.findSelfUser();

        return solicitationRepository.findALLByCreatedByAndStatus(user, SolicitationStatus.PENDING_ADVISOR);
    }

    public ResponseEntity approveProfessor(Long id) { //Professor
        Optional<Solicitation> solicitation = solicitationRepository.findById(id);
        solicitation.get().setStatus(SolicitationStatus.PENDING_LAB);

        return ResponseEntity.ok(solicitationRepository.save(solicitation.get()));
    }

    public ResponseEntity approveLab(Long id) { //Lab
        Optional<Solicitation> solicitation = solicitationRepository.findById(id);
        solicitation.get().setStatus(SolicitationStatus.PENDING_SAMPLE);

        return ResponseEntity.ok(solicitationRepository.save(solicitation.get()));
    }
}
