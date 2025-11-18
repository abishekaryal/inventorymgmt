package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.SendMailRequest;
import org.springframework.stereotype.Service;


public interface MailService {
     void sendMail(SendMailRequest sendMailRequest);
}
