package com.cosmotech.inventorymgmt.controller;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.core.dto.PaginationDto;
import com.cosmotech.inventorymgmt.dto.UpdateProductResponse;
import com.cosmotech.inventorymgmt.dto.product.request.DeleteProductRequest;
import com.cosmotech.inventorymgmt.dto.supplier.CreateSupplierRequest;
import com.cosmotech.inventorymgmt.dto.supplier.DeleteSupplierRequest;
import com.cosmotech.inventorymgmt.dto.supplier.UpdateSupplierRequest;
import com.cosmotech.inventorymgmt.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<?>> createSupplier(@RequestBody @Valid CreateSupplierRequest createSupplierRequest) {
        return supplierService.createSupplier(createSupplierRequest);
    }

    @PostMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllSuppliers(@RequestBody @Valid PaginationDto paginationDto) {

        return supplierService.listSupplier(paginationDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<?>> updateSupplier(@RequestBody @Valid UpdateSupplierRequest updateSupplierRequest) {
        return supplierService.updateSupplier(updateSupplierRequest);
    }
    @DeleteMapping("/Delete")
    public ResponseEntity<ApiResponse<?>> deleteSupplier(@RequestBody @Valid DeleteSupplierRequest  deleteSupplierRequest) {
        return supplierService.deleteSuplier(deleteSupplierRequest);
    }
}
