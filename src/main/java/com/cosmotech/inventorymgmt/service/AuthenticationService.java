package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.dto.supplier.LoginRequest;

public interface AuthenticationService {
    ApiResponse<?> authenticate(LoginRequest loginRequest);
}
