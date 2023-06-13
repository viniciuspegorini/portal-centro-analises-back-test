package com.portal.centro.API.service;

import com.portal.centro.API.dto.StudentSolicitationDTO;
import com.portal.centro.API.enums.StudentSolicitationStatus;
import com.portal.centro.API.enums.Type;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.StudentSolicitation;
import com.portal.centro.API.model.User;
import com.portal.centro.API.repository.StudentSolicitationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class StudentSolicitationService extends GenericService<StudentSolicitation, Long> {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private static final String MESSAGE_VALIDATION_APPROVE_ADVISOR_AND_LAB = "Apenas o orientador e o laboratório podem aprovar ou recusar a solicitação.";

    public StudentSolicitationService(StudentSolicitationRepository studentSolicitationRepository, UserService userService, ModelMapper modelMapper) {
        super(studentSolicitationRepository);
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentSolicitation save(StudentSolicitation requestBody) throws Exception {
        if (Objects.isNull(requestBody.getId()) || Objects.equals(requestBody.getId(), 0)) {
            requestBody.setCreationDate(LocalDateTime.now());
            requestBody.setStatus(StudentSolicitationStatus.PENDING);
        }
        return super.save(requestBody);
    }

    public StudentSolicitation solicit(StudentSolicitationDTO studentSolicitationDTO) throws Exception {
        StudentSolicitation studentSolicitation = modelMapper.map(studentSolicitationDTO, StudentSolicitation.class);
        studentSolicitation.setSolicitatedBy(userService.findSelfUser());
        validateLoggedUserIsSoliciting(studentSolicitation.getSolicitatedBy());
        studentSolicitation.setCreationDate(LocalDateTime.now());
        studentSolicitation.setStatus(StudentSolicitationStatus.PENDING);
        return save(studentSolicitation);
    }

    private void validateLoggedUserIsSoliciting(User user) throws Exception {
        if (!Objects.equals(user.getRole(), Type.STUDENT))
            throw new Exception("Apenas o estudante pode realizar a solicitação.");
    }

    public StudentSolicitation approve(StudentSolicitation studentSolicitation) throws Exception {
        setDefaultValuesWhenApprovingOrRefusing(studentSolicitation, true);
        validateLoggedUserIsApprovingOrRefusing(studentSolicitation.getFinishedBy(), studentSolicitation.getSolicitatedTo());
        return save(studentSolicitation);
    }

    private StudentSolicitationStatus getStudentSolicitationStatusByUserRole(Type userRole, boolean approving) throws Exception {
        if (Objects.equals(userRole, Type.PROFESSOR))
            return approving ? StudentSolicitationStatus.APPROVED_ADVISOR : StudentSolicitationStatus.REFUSED_ADVISOR;

        if (Objects.equals(userRole, Type.ADMIN))
            return approving ? StudentSolicitationStatus.APPROVED_LAB : StudentSolicitationStatus.REFUSED_LAB;

        throw new Exception(MESSAGE_VALIDATION_APPROVE_ADVISOR_AND_LAB);
    }

    private void validateLoggedUserIsApprovingOrRefusing(User userApprove, User solicitedTo) throws Exception {
        if ((!Objects.equals(userApprove.getRole(), Type.PROFESSOR)) &&
                (!Objects.equals(userApprove.getRole(), Type.ADMIN)))
            throw new Exception(MESSAGE_VALIDATION_APPROVE_ADVISOR_AND_LAB);

        if ((Objects.equals(userApprove.getRole(), Type.PROFESSOR)) &&
                (!Objects.equals(userApprove.getId(), solicitedTo.getId())))
            throw new Exception("O Orientador informado não é o mesmo solicitado na solicitação, somente o mesmo pode aprovar ou recusar.");
    }

    public StudentSolicitation refuse(StudentSolicitation studentSolicitation) throws Exception {
        setDefaultValuesWhenApprovingOrRefusing(studentSolicitation, false);
        validateLoggedUserIsApprovingOrRefusing(studentSolicitation.getFinishedBy(), studentSolicitation.getSolicitatedTo());
        return save(studentSolicitation);
    }

    private void setDefaultValuesWhenApprovingOrRefusing(StudentSolicitation studentSolicitation, boolean approving) throws Exception {
        studentSolicitation.setFinishedBy(userService.findSelfUser());
        studentSolicitation.setFinishDate(LocalDateTime.now());
        studentSolicitation.setStatus(getStudentSolicitationStatusByUserRole(studentSolicitation.getFinishedBy().getRole(), approving));
    }

}
