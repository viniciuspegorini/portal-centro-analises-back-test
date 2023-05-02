package com.portal.centro.API.service;

import com.portal.centro.API.dto.RecoverPasswordDTO;
import com.portal.centro.API.enums.Type;
import com.portal.centro.API.exceptions.NotFoundException;
import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.RecoverPassword;
import com.portal.centro.API.model.SendEmailCodeRecoverPassword;
import com.portal.centro.API.model.User;
import com.portal.centro.API.repository.UserRepository;
import com.portal.centro.API.responses.DefaultResponse;
import com.portal.centro.API.utils.DateTimeUtil;
import com.portal.centro.API.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class UserService extends GenericService<User, Long> {

    BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UtilsService utilsService;
    private final RecoverPasswordService recoverPasswordService;

    @Autowired
    public UserService(UserRepository userRepository, UtilsService utilsService, RecoverPasswordService recoverPasswordService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.utilsService = utilsService;
        this.recoverPasswordService = recoverPasswordService;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User save(User requestBody) throws Exception {
        encryptPassword(requestBody);
        Type role = utilsService.getRoleType(requestBody.getEmail());
        requestBody.setAuthorities(utilsService.getPermissionsByRole(role));
        requestBody.setRole(role);
        return super.save(requestBody);
    }

    public SendEmailCodeRecoverPassword sendEmailCodeRecoverPassword(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user))
            throwExceptionUserNotFound();

        Integer codigo = new Random().nextInt(1000000);
        recoverPasswordService.addCode(username, new RecoverPassword(username, codigo, DateTimeUtil.getCurrentDateTime()));

//        sendEmailService.send(
//                "Recuperação de senha",
//                "O código para recuperação da sua senha no sistema de Newsletter é <b>"+codigo+"</b>.",
//                configEmailService.getConfigEmailByUsernameUser(username),
//                user.getEmail());

        return new SendEmailCodeRecoverPassword("Código enviado com sucesso para o e-mail " + user.getEmail() + ".", user.getEmail());
    }

    public DefaultResponse recoverPassword(RecoverPasswordDTO recoverPasswordDTO) throws Exception {
        User user = userRepository.findByUsername(recoverPasswordDTO.getUsername());
        if (Objects.isNull(user))
            throwExceptionUserNotFound();

        RecoverPassword recoverPassword = recoverPasswordService.getCodeSentByEmail().getOrDefault(recoverPasswordDTO.getUsername(), new RecoverPassword());
        Boolean codesMatch = Objects.equals(recoverPasswordDTO.getCode(), recoverPassword.getCode());
        if (!codesMatch)
            return new DefaultResponse(HttpStatus.BAD_REQUEST.value(), "Código inválido.");

        updateUserNewPasswordByUsername(user, recoverPasswordDTO.getNewPassword());
        recoverPasswordService.getCodeSentByEmail().remove(recoverPasswordDTO.getUsername());
        return new DefaultResponse(HttpStatus.OK.value(), "Senha alterada com sucesso.");
    }

    private void updateUserNewPasswordByUsername(User user, String newPassword) throws Exception {
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

}
