package com.portal.centro.API.service;

import com.portal.centro.API.generic.crud.GenericService;
import com.portal.centro.API.model.EmailCode;
import com.portal.centro.API.model.User;
import com.portal.centro.API.repository.EmailCodeRepository;
import com.portal.centro.API.repository.UserRepository;
import com.portal.centro.API.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EmailCodeService extends GenericService<EmailCode, Long> {
    private final HashingService hashingService;
    private final EmailCodeRepository emailCodeRepository;
    private final UserRepository userRepository;

    @Autowired
    public EmailCodeService(EmailCodeRepository emailCodeRepository, HashingService hashingService, UserRepository userRepository) {
        super(emailCodeRepository);
        this.hashingService = hashingService;
        this.emailCodeRepository = emailCodeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public EmailCode save(EmailCode emailCode) throws Exception {
        return super.save(emailCode);
    }

    public EmailCode createCode(User user) throws Exception {
        LocalDateTime dateTime = LocalDateTime.now();
        String hashKey = this.hashingService.generateHashKey(user.getUsername());

        EmailCode emailCode = EmailCode.builder().code(hashKey).user(user).createdAt(dateTime).build();

        //ENVIO DE EMAIL
        return this.save(emailCode);
    }

    public boolean confirmEmail(String hash) throws Exception {
        boolean isSuccess = false;

        LocalDateTime dateTime = LocalDateTime.now();

        EmailCode emailCode = this.emailCodeRepository.findEmailCodeByCode(hash);

        if (emailCode != null) {
            User user = emailCode.getUser();
            user.setEmailVerified(true);
            userRepository.save(user);
            emailCode.setValidateAt(dateTime);

            save(emailCode);

            isSuccess = true;
        }

        return isSuccess;
    }
}

