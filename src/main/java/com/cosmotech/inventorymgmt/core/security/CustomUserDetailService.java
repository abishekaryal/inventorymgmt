package com.cosmotech.inventorymgmt.core.security;

import com.cosmotech.inventorymgmt.entity.Supplier;
import com.cosmotech.inventorymgmt.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private SupplierRepo supplierRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Supplier supplier = supplierRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Supplier not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(supplier.getEmail())
                .password(supplier.getPassword())
                .authorities("ROLE_" + supplier.getRole())
                .build();
    }
}