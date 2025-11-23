package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.core.dto.PaginationDto;
import com.cosmotech.inventorymgmt.dto.supplier.CreateSupplierRequest;
import com.cosmotech.inventorymgmt.dto.supplier.DeleteSupplierRequest;
import com.cosmotech.inventorymgmt.dto.supplier.UpdateSupplierRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

public interface SupplierService {
    ApiResponse<?> createSupplier (CreateSupplierRequest createSupplierRequest, MultipartFile profilePicture);
    ApiResponse<?> listSupplier (PaginationDto paginationDto);
    ApiResponse<?> updateSupplier (UpdateSupplierRequest updateSupplierRequest);
    ResponseEntity<ApiResponse<?>> deleteSuplier (DeleteSupplierRequest deleteSupplierRequest);
}
