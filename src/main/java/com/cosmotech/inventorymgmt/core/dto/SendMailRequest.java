package com.cosmotech.inventorymgmt.core.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailRequest {
    @NotBlank(message = "mail id is required")
    private String recipient;
    @NotBlank(message = "subject is required")
    private String subject;
    @NotBlank(message = "message is required")
    private String message;
}
