package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.core.security.JwtService;
import com.cosmotech.inventorymgmt.dto.AuthenticationResponse;
import com.cosmotech.inventorymgmt.dto.supplier.LoginRequest;
import com.cosmotech.inventorymgmt.entity.Supplier;
import com.cosmotech.inventorymgmt.repository.SupplierRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private SupplierRepo supplierRepo;

    @Override
    @Transactional
    public ApiResponse<?> authenticate(LoginRequest loginRequest) {
        Optional<Supplier> supplier = supplierRepo.findByEmail(loginRequest.getEmail());
        if (supplier .isEmpty()){
            throw new RuntimeException("Supplier not found");
        }
        try{
            Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            String accessToken = jwtService.generateAccessToken(supplier.get());
            String refreshToken = jwtService.generateRefreshToken(supplier.get());

            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setAccessToken(accessToken);
            authenticationResponse.setRefreshToken(refreshToken);

            return new ApiResponse<>(true, "Logged in successfully",200,LocalDateTime.now(),authenticationResponse);

        }
        catch (BadCredentialsException e){
            throw new BadCredentialsException("Bad Credentials");


}
    }

}
