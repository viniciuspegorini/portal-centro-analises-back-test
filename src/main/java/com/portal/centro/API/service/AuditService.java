package com.portal.centro.API.service;

import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.Audit;
import com.portal.centro.API.model.User;
import com.portal.centro.API.repository.AuditRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import static com.portal.centro.API.enums.Type.STUDENT;

@Service
public class AuditService extends GenericService<Audit, Long> {

    private final AuditRepository auditRepository;
    private final UserService userService;

    @Override
    public List<Audit> getAll() {
        User user = userService.findSelfUser();

        if(user.getRole() == STUDENT) {
            return auditRepository.findAllByUpdatedBy(user);
        } else {
            return super.getAll();
        }
    }

    public AuditService(AuditRepository auditRepository, UserService userService) {
        super(auditRepository);
        this.auditRepository = auditRepository;
        this.userService = userService;
    }

    public void saveAudit(Audit audit) {
        audit.setChangeDate(LocalDateTime.now());

        audit.setUpdatedBy(userService.findSelfUser());
        auditRepository.save(audit);
    }
}
