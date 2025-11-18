package com.cosmotech.inventorymgmt.dto.product.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteProductRequest {
    @NotNull(message = "plz fill id")
    private Integer id;
}
