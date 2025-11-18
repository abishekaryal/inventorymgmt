package com.cosmotech.inventorymgmt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ViewProductResponse {
    private Integer id;
    private String name;
    private String category;
    private Double price;
    @JsonFormat(pattern ="yyyy-mm-dd hh:mm:ss:a")
    private LocalDateTime createdAt;
    @JsonFormat(pattern ="yyyy-mm-dd hh:mm:ss:a")
    private LocalDateTime updatedAt;
}
