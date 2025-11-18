package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.core.dto.PaginationDto;
import com.cosmotech.inventorymgmt.dto.supplier.CreateSupplierRequest;
import com.cosmotech.inventorymgmt.dto.supplier.DeleteSupplierRequest;
import com.cosmotech.inventorymgmt.dto.supplier.UpdateSupplierRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface SupplierService {
    ResponseEntity<ApiResponse<?>> createSupplier (CreateSupplierRequest createSupplierRequest);
    ResponseEntity<ApiResponse<?>> listSupplier (PaginationDto paginationDto);
    ResponseEntity<ApiResponse<?>> updateSupplier (UpdateSupplierRequest updateSupplierRequest);
    ResponseEntity<ApiResponse<?>> deleteSuplier (DeleteSupplierRequest deleteSupplierRequest);
}
