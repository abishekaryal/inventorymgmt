package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.core.dto.PaginationDto;
import com.cosmotech.inventorymgmt.dto.supplier.CreateSupplierRequest;
import com.cosmotech.inventorymgmt.dto.supplier.DeleteSupplierRequest;
import com.cosmotech.inventorymgmt.dto.supplier.ListSupplierResponse;
import com.cosmotech.inventorymgmt.dto.supplier.UpdateSupplierRequest;
import com.cosmotech.inventorymgmt.entity.Product;
import com.cosmotech.inventorymgmt.entity.Supplier;
import com.cosmotech.inventorymgmt.mapper.SupplierMapper;
import com.cosmotech.inventorymgmt.repository.SupplierRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private EmailTempletSevice emailTempletSevice;

    public SupplierServiceImpl(SupplierMapper supplierMapper, SupplierRepo supplierRepo) {
        this.supplierMapper = supplierMapper;
        this.supplierRepo = supplierRepo;
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> createSupplier(CreateSupplierRequest createSupplierRequest) {
        Supplier supplier = supplierMapper.createSupplier(createSupplierRequest);
        supplierRepo.save(supplier);
        emailTempletSevice.sendWelcomeMail(supplier);
        ApiResponse<Product> apiResponse = new ApiResponse<>(true, "Supplier added successfully", 200, LocalDateTime.now());
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> listSupplier(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize());
        Page<Supplier> suppliers;
        if (paginationDto.getKeyword() != null && !paginationDto.getKeyword().trim().isEmpty()) {
            suppliers = supplierRepo.searchSuppliers(paginationDto.getKeyword().trim(), pageable);
        } else {
            suppliers = supplierRepo.findAll(pageable);
        }
        List<ListSupplierResponse> listSupplierResponses = supplierMapper.listSupplierResponses(suppliers);

        ApiResponse<?> apiResponse = new ApiResponse(true, "supplier listed successful", 200, LocalDateTime.now(), listSupplierResponses);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> updateSupplier(UpdateSupplierRequest updateSupplierRequest) {
        Optional<Supplier> supplier = supplierRepo.findById(updateSupplierRequest.getId());
        Supplier updateSupplier = supplier.get();
        updateSupplier.setName(updateSupplier.getName());
        updateSupplier.setAddress(updateSupplier.getAddress());
        updateSupplier.setPhone(updateSupplier.getPhone());
        updateSupplier.setEmail(updateSupplier.getEmail());
        supplierRepo.save(updateSupplier);
        ApiResponse<?> response = new ApiResponse<>(true, "supplier update successfully", 200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<ApiResponse<?>> deleteSuplier(DeleteSupplierRequest deleteSupplierRequest) {
        return null;
    }

    public ResponseEntity<ApiResponse<?>> deleteSupplier(DeleteSupplierRequest deleteSupplierRequest) {
        Optional<Supplier> SupplierOpt = supplierRepo.findById(deleteSupplierRequest.getId());
        if (SupplierOpt.isEmpty()) {
            throw new RuntimeException("Supplier with id " + id + " not found");
        }
        Supplier supplierToDelete = supplierMapper.deleteSupplier(SupplierOpt.get());
        supplierRepo.delete(supplierToDelete);
        ApiResponse<?> response = new ApiResponse<>(true, "Supplier deleted successfully",200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
