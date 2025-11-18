package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.SendMailRequest;
import com.cosmotech.inventorymgmt.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailTempletSevice {
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    public void sendWelcomeMail(Supplier supplier) {
        Context context = new Context();
        context.setVariable("fullName", supplier.getName());
        context.setVariable("email", supplier.getEmail());
        String message = templateEngine.process("welcome-email", context);

        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setRecipient(supplier.getEmail());
        sendMailRequest.setSubject("your account is created");
        sendMailRequest.setMessage(message);
        mailService.sendMail(sendMailRequest);
    }
}

