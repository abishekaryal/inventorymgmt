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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/list")
    public ResponseEntity<ApiResponse<?>> getAllSuppliers(@RequestBody @Valid PaginationDto paginationDto) {

        ApiResponse<?> apiResponse = supplierService.listSupplier(paginationDto);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<?>> updateSupplier(@RequestBody @Valid UpdateSupplierRequest updateSupplierRequest) {
       ApiResponse<?> apiResponse = supplierService.updateSupplier(updateSupplierRequest);
       return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


    @DeleteMapping("/Delete")
    public ResponseEntity<ApiResponse<?>> deleteSupplier(@RequestBody @Valid DeleteSupplierRequest deleteSupplierRequest) {
        return supplierService.deleteSuplier(deleteSupplierRequest);
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> createUser(
            @RequestPart(value = "data") @Valid CreateSupplierRequest createSupplierRequest,
            @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture
    ) {
        ApiResponse<?> apiResponse = supplierService.createSupplier(createSupplierRequest, profilePicture);
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }
}
