package com.techie.user.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.techie.user.dtos.requests.VerificationMessageRequest;
import com.techie.user.dtos.responses.MailResponse;

import java.util.concurrent.CompletableFuture;

public interface EmailService {
    CompletableFuture<MailResponse> sendHtmlMail(VerificationMessageRequest messageRequest) throws UnirestException;
    CompletableFuture<MailResponse> sendSimpleMail(VerificationMessageRequest messageRequest) throws UnirestException;
}
