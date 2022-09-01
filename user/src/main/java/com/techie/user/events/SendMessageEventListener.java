package com.techie.user.events;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.techie.user.dtos.requests.VerificationMessageRequest;
import com.techie.user.dtos.responses.MailResponse;
import com.techie.user.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class SendMessageEventListener {
    @Qualifier("mailgun_sender")
    private final EmailService emailService;
    private final Environment env;

    public SendMessageEventListener(EmailService emailService, Environment env) {
        this.emailService = emailService;
        this.env = env;
    }

    @EventListener
    public void handleSendMessageEvent(SendMessageEvent event) throws UnirestException, ExecutionException,
            InterruptedException, UnirestException, ExecutionException {
        VerificationMessageRequest messageRequest = (VerificationMessageRequest) event.getSource();

        String verificationLink = messageRequest.getDomainUrl()+"api/v1/auth/verify/"+messageRequest.getVerificationToken();

        log.info("Message request --> {}",messageRequest);

        if (Arrays.asList(env.getActiveProfiles()).contains("prod")){
            log.info("Message Event -> {}", event.getSource());
//            messageRequest.setBody(templateEngine.process("registration_verification_mail.html", context));
            MailResponse mailResponse = emailService.sendHtmlMail(messageRequest).get();
            log.info("Mail Response --> {}", mailResponse);
        } else{
            messageRequest.setBody(verificationLink);
            MailResponse mailResponse = emailService.sendSimpleMail(messageRequest).get();
            log.info("Mail Response --> {}", mailResponse);
        }
    }
}
