package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.core.dto.PaginationDto;
import com.cosmotech.inventorymgmt.core.service.FileService;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private FileService fileService;



    @CacheEvict(value = "users",allEntries = true)
    @Override
    @Transactional

    public ApiResponse<?> createSupplier(CreateSupplierRequest createSupplierRequest, MultipartFile profilePicture) {
        Supplier supplier = supplierMapper.createSupplier(createSupplierRequest);

        if(profilePicture!=null && !profilePicture.isEmpty()){
            long maxSize = 10 * 1024 * 1024;
            if (profilePicture.getSize() > maxSize){
                throw new RuntimeException("file size is larger than max size");
            }
            String fileName = fileService.uploadFile(profilePicture);
            supplier.setProfilePicture(fileName);
        }

        supplierRepo.save(supplier);

        emailTempletSevice.sendWelcomeMail(supplier);

        return new ApiResponse<>(true, "Supplier added successfully", 200, LocalDateTime.now());



    }

    @Override
    @Transactional
    @Cacheable(
            value = "users",
            key = "#paginationDto.page + '-' + #paginationDto.size + '-' + (#paginationDto.keyword != null ? #paginationDto.keyword : '')"
    )
    public ApiResponse<?> listSupplier(PaginationDto paginationDto) {
        Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize());
        Page<Supplier> suppliers;
        if (paginationDto.getKeyword() != null && !paginationDto.getKeyword().trim().isEmpty()) {
            suppliers = supplierRepo.searchSuppliers(paginationDto.getKeyword().trim(), pageable);
        } else {
            suppliers = supplierRepo.findAll(pageable);
        }
        List<ListSupplierResponse> listSupplierResponses = supplierMapper.listSupplierResponses(suppliers);

    return new ApiResponse<>(true, "supplier listed successful", 200, LocalDateTime.now(), listSupplierResponses);

    }

    @CacheEvict(value = "users",allEntries = true)
    @Override
    @Transactional
    public ApiResponse<?> updateSupplier(UpdateSupplierRequest updateSupplierRequest) {
        Optional<Supplier> supplier = supplierRepo.findById(updateSupplierRequest.getId());
        Supplier updateSupplier = supplier.get();
        updateSupplier.setName(updateSupplier.getName());
        updateSupplier.setAddress(updateSupplier.getAddress());
        updateSupplier.setPhone(updateSupplier.getPhone());
        updateSupplier.setEmail(updateSupplier.getEmail());
        supplierRepo.save(updateSupplier);
       return new ApiResponse<>(true, "supplier update successfully", 200, LocalDateTime.now());

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
