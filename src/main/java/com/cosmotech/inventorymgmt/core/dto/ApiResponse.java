package com.cosmotech.inventorymgmt.core.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T> implements Serializable {
    private Boolean success;
    private String message;
    private Integer statusCode;
    @JsonFormat(pattern = "yyyy-MM-dd:hh:mm:s a")
    private LocalDateTime timestamp;
    private T data;

    public ApiResponse(Boolean success, String message, Integer statusCode, LocalDateTime timestamp) {
        this.success = success;
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

}
