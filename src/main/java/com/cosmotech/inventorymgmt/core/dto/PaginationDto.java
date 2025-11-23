package com.cosmotech.inventorymgmt.core.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaginationDto {
    @NotNull(message ="cant be null")@Min(value =0,message = "must be start from 0")
    private int page;
    @NotNull(message = "cant be null")@Min(value =1,message = "must be started from 1")

    private int Size;
    private String keyword;


}