package com.cosmotech.inventorymgmt.dto.product.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewProductRequest {
    @NotNull(message = "id cant be null")
    private Integer id;
}
