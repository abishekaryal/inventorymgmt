package com.cosmotech.inventorymgmt.dto.supplier;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSupplierRequest {
    @NotNull(message = "Id")
    private Integer id;
    @NotBlank(message = "name cant be blank")
    private String name;
    @NotBlank(message = "email cant be blank")
    private String email;
    @NotBlank(message = "phone cant be blank")
    private String phone;
    @NotBlank(message = "address cant be blank")
    private String address;


}
