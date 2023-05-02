package com.portal.centro.API.service;

import com.portal.centro.API.model.RecoverPassword;
import com.portal.centro.API.utils.DateTimeUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecoverPasswordService {

    private static final String EVERY_MID_NIGHT = "0 0 0 * * ?";
    private static final Integer MAX_MINUTES_VALID_CODE = 30;

    @Getter
    private Map<String, RecoverPassword> codeSentByEmail = new HashMap<>();

    public void addCode(String username, RecoverPassword recoverPassword) {
        this.codeSentByEmail.put(username, recoverPassword);
    }

    @Scheduled(cron = EVERY_MID_NIGHT)
    public void clearExpiredCodesSentByEmail() {
        for (RecoverPassword recoverPassword : codeSentByEmail.values()) {
            if (codeExpired(recoverPassword))
                codeSentByEmail.remove(recoverPassword.getUsername());
        }
    }

    public Boolean codeExpired(RecoverPassword recoverPassword) {
        return (Duration.between(recoverPassword.getDateTime(), DateTimeUtil.getCurrentDateTime()).toMinutes() >= MAX_MINUTES_VALID_CODE);
    }

}
