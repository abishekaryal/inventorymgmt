package com.cosmotech.inventorymgmt.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "email cant be blank")
    private String email;
    private String password;
}
