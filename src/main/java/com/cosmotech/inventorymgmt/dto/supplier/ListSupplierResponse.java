package com.cosmotech.inventorymgmt.dto.supplier;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ListSupplierResponse implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    @JsonFormat(pattern ="yyyy-mm-dd hh:mm:ss:a")
    private LocalDateTime createdAt;
    @JsonFormat(pattern ="yyyy-mm-dd hh:mm:ss:a")
    private LocalDateTime updatedAt;
}

