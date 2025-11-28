package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.dto.supplier.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;

public interface AuthenticationService {
    ApiResponse<?> authenticate(LoginRequest loginRequest);
    public ApiResponse<?> refreshToken(HttpServletRequest request, HttpServletResponse response);
}
