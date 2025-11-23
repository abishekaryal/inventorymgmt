package com.cosmotech.inventorymgmt.dto.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.grammars.hql.HqlParser;

@Data
public class AddProductRequest {
    @NotBlank(message = "insert name")
    private String name;

    private String category;
    private Double price;
    @NotBlank(message = "Quantity cant be blank")
    private String quantity;
//    @NotNull(message = "supplier cannot be null")
//    private int supplierId;
}
