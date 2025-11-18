package com.cosmotech.inventorymgmt.dto.supplier;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateSupplierRequest {
    @NotBlank(message = "name cant be blank")
    private String name;
    @NotBlank(message = "email cant be blank")
    private String email;
    @NotBlank(message = "phone cant be blank")
    private String phone;
    @NotBlank(message = "address cant be blank")
    private String address;


}
