package com.portal.centro.API.service;

import com.portal.centro.API.dto.ChangePasswordDTO;
import com.portal.centro.API.dto.EmailDto;
import com.portal.centro.API.dto.RecoverPasswordDTO;
import com.portal.centro.API.enums.TransactionType;
import com.portal.centro.API.enums.Type;
import com.portal.centro.API.exceptions.NotFoundException;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.RecoverPassword;
import com.portal.centro.API.model.SendEmailCodeRecoverPassword;
import com.portal.centro.API.model.User;
import com.portal.centro.API.model.UserBalance;
import com.portal.centro.API.repository.UserRepository;
import com.portal.centro.API.responses.DefaultResponse;
import com.portal.centro.API.utils.DateTimeUtil;
import com.portal.centro.API.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService extends GenericService<User, Long> {

    BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UtilsService utilsService;
    private final RecoverPasswordService recoverPasswordService;
    private final EmailCodeService emailCodeService;
    private final EmailService emailService;

    @Autowired
    public UserService(
            UserRepository userRepository,
            UtilsService utilsService,
            EmailCodeService emailCodeService,
            RecoverPasswordService recoverPasswordService,
            EmailService emailService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.utilsService = utilsService;
        this.emailCodeService = emailCodeService;
        this.recoverPasswordService = recoverPasswordService;
        this.emailService = emailService;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User save(User requestBody) throws Exception {
        encryptPassword(requestBody);
        Type role = utilsService.getRoleType(requestBody.getEmail());
        requestBody.setPermissions(utilsService.getPermissionsByRole(role));
        requestBody.setRole(role);
        this.validate(requestBody);
        User user = super.save(requestBody);
        this.emailCodeService.createCode(user);

        return user;
    }

    public User saveAdmin(User requestBody) throws Exception {
        encryptPassword(requestBody);
        requestBody.setPermissions(utilsService.getPermissionsByRole(requestBody.getRole()));
        this.validate(requestBody);
        User user = super.save(requestBody);
        this.emailCodeService.createCode(user);

        return user;
    }

    public User editUserRole(Type role, Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        user.get().setRole(role);

        return super.save(user.get());
    }

    private void validate(User user) throws Exception {
        User userDb = this.userRepository.findByEmail(user.getEmail());
        if (userDb != null && userDb.getId() != user.getId()) {
            throw new Exception("Email já cadastrado.");
        }
    }

    public SendEmailCodeRecoverPassword sendEmailCodeRecoverPassword(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (Objects.isNull(user))
            throwExceptionUserNotFound();
        Integer code = new Random().nextInt(1000000);
        recoverPasswordService.addCode(email, new RecoverPassword(email, code, DateTimeUtil.getCurrentDateTime()));
        emailService.sendEmail(getEmailDtoToSendEmailWithCode(user.getEmail(), code));
        return new SendEmailCodeRecoverPassword("Código enviado com sucesso para o e-mail " + user.getEmail() + ".", user.getEmail());
    }

    private EmailDto getEmailDtoToSendEmailWithCode(String email, Integer code) {
        EmailDto emailDto = new EmailDto();
        emailDto.setEmailTo(email);
        emailDto.setSubject("Recuperação de senha");
        emailDto.setSubjectBody("Recuperação de senha");
        emailDto.setContentBody("O código para recuperação da sua senha no sistema de Newsletter é <b>" + code + "</b>.");
        return emailDto;
    }

    public DefaultResponse recoverPassword(RecoverPasswordDTO recoverPasswordDTO) throws Exception {
        User user = userRepository.findByEmail(recoverPasswordDTO.getEmail());
        if (Objects.isNull(user))
            throwExceptionUserNotFound();

        RecoverPassword recoverPassword = recoverPasswordService.getCodeSentByEmail().getOrDefault(recoverPasswordDTO.getEmail(), new RecoverPassword());
        Boolean codesMatch = Objects.equals(recoverPasswordDTO.getCode(), recoverPassword.getCode());
        if (!codesMatch)
            return new DefaultResponse(HttpStatus.BAD_REQUEST.value(), "Código inválido.");

        updateUserNewPasswordByEmail(user, recoverPasswordDTO.getNewPassword());
        recoverPasswordService.getCodeSentByEmail().remove(recoverPasswordDTO.getEmail());
        return new DefaultResponse(HttpStatus.OK.value(), "Senha alterada com sucesso.");
    }

    public DefaultResponse changePassword(ChangePasswordDTO changePasswordDTO) throws Exception {
        User user = findSelfUser();
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword()))
            return new DefaultResponse(HttpStatus.BAD_REQUEST.value(), "Senha atual inválida.");

        updateUserNewPasswordByEmail(user, changePasswordDTO.getNewPassword());
        return new DefaultResponse(HttpStatus.OK.value(), "Senha alterada com sucesso.");
    }

    private void updateUserNewPasswordByEmail(User user, String newPassword) throws Exception {
        if (Objects.isNull(user))
            return;

        user.setPassword(newPassword);
        encryptPassword(user);
        super.save(user);
    }

    private void encryptPassword(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    }

    private void throwExceptionUserNotFound() {
        throw new NotFoundException("Usuário não encontrado.");
    }

    public User findSelfUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(principal.toString());
        return user;
    }

    public List<User> findUsersByRole(@PathVariable("role") String role) {
        Type type;
        try {
            type = Type.valueOf(role);
        } catch (Exception e) {
            throw new RuntimeException("Role informada não existe.");
        }
        return userRepository.findAllByRole(type);
    }

    public UserBalance updateBalance(Long userId, TransactionType transactionType, BigDecimal value) throws Exception {
        User user = findOneById(userId);
        if (Objects.isNull(user))
            throwExceptionUserNotFound();
        BigDecimal balance = Objects.nonNull(user.getBalance()) ? user.getBalance() : BigDecimal.ZERO;
        UserBalance userBalance = new UserBalance();
        userBalance.setOld(balance);
        switch (transactionType) {
            case DEPOSIT -> balance = balance.add(value);
            case WITHDRAW -> balance = balance.subtract(value);
        }
        userBalance.setCurrent(balance);
        user.setBalance(balance);
        userRepository.save(user);
        return userBalance;
    }

}
