package com.cosmotech.inventorymgmt.mapper;

import com.cosmotech.inventorymgmt.dto.ListProductResponse;
import com.cosmotech.inventorymgmt.dto.ViewProductResponse;
import com.cosmotech.inventorymgmt.dto.product.request.AddProductRequest;
import com.cosmotech.inventorymgmt.dto.supplier.CreateSupplierRequest;
import com.cosmotech.inventorymgmt.dto.supplier.ListSupplierResponse;
import com.cosmotech.inventorymgmt.entity.Product;
import com.cosmotech.inventorymgmt.entity.Supplier;
import jakarta.transaction.Transactional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class SupplierMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Supplier createSupplier(CreateSupplierRequest createSupplierRequest) {
        Supplier supplier = new Supplier();
       supplier.setName(createSupplierRequest.getName());
       supplier.setAddress(createSupplierRequest.getAddress());
       supplier.setPhone(createSupplierRequest.getPhone());
       supplier.setEmail(createSupplierRequest.getEmail());
       supplier.setPassword(passwordEncoder.encode(createSupplierRequest.getPassword()));
       supplier.setRole("SUPPLIER");
       return supplier;
    }

    public abstract ListSupplierResponse listSupplierResponse(Supplier supplier);
    public List<ListSupplierResponse> listSupplierResponses (Page<Supplier> suppliers) {
        return suppliers.getContent().stream().map(this::listSupplierResponse).collect(Collectors.toList());
    }
    public Supplier deleteSupplier (Supplier supplier) {
        supplier.setDeleted(true);
        return supplier;
    }

}
