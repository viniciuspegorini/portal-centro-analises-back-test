package com.portal.centro.API.service;

import com.portal.centro.API.dto.EmailDto;
import com.portal.centro.API.provider.ConfigProvider;
import com.portal.centro.API.utils.EmailMessageGenerator;
import com.portal.centro.API.utils.UtilsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class EmailService {

    final private UtilsService utilsService;
    final private ConfigProvider configProvider;
    final private EmailMessageGenerator emailMessageGenerator;

    public EmailService (UtilsService utils, ConfigProvider configProvider, EmailMessageGenerator emailMessageGenerator) {
        this.utilsService = utils;
        this.configProvider = configProvider;
        this.emailMessageGenerator = emailMessageGenerator;
    }

    public void sendEmail(EmailDto emailTo) {
        HtmlEmail htmlEmail = new HtmlEmail();
        try{

            htmlEmail.setHostName(configProvider.getProtocol());
            htmlEmail.setSmtpPort(configProvider.getPort());
            htmlEmail.setAuthenticator(new DefaultAuthenticator(configProvider.getAddress(), configProvider.getPassword()));
            htmlEmail.setStartTLSEnabled(true);
            htmlEmail.setFrom(configProvider.getAddress());
            htmlEmail.addTo(emailTo.getEmailTo());
            htmlEmail.setSubject(emailTo.getSubject());
            htmlEmail.setHtmlMsg(emailMessageGenerator.generateHTML(emailTo.getSubjectBody(), emailTo.getContentBody(), ""));

            log.info("enviando email....");
            htmlEmail.send();
            log.info("email enviado com sucesso!");

        } catch (Exception e) {
            log.info("erro ao enviar email....");
            System.out.println(e.getMessage());
        }

    }
}