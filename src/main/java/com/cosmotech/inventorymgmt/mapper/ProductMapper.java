package com.cosmotech.inventorymgmt.mapper;

import com.cosmotech.inventorymgmt.dto.product.request.AddProductRequest;
import com.cosmotech.inventorymgmt.dto.ListProductResponse;
import com.cosmotech.inventorymgmt.dto.ViewProductResponse;
import com.cosmotech.inventorymgmt.entity.Product;
import com.cosmotech.inventorymgmt.entity.Supplier;
import com.cosmotech.inventorymgmt.repository.SupplierRepo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ProductMapper {
    @Autowired
    private SupplierRepo supplierRepo;

    public Product createProduct (AddProductRequest addProductRequest) {
        Optional<Supplier> supplier = supplierRepo.findById(addProductRequest.getSupplierId());
        if (supplier.isEmpty()) {
            throw new RuntimeException("Supplier not found");
        }

        Product product = new Product();
        product.setName(addProductRequest.getName());
        product.setPrice(addProductRequest.getPrice());
        product.setQuantity(addProductRequest.getQuantity());
        product.setCategory(addProductRequest.getCategory());
        product.setSupplier(supplier.get());
        return product;
    }
    public abstract ListProductResponse listProductResponse(Product product);
      public List<ListProductResponse> listProduct (Page<Product> products) {
          return products.getContent().stream().map(this::listProductResponse).collect(Collectors.toList());
      }
      public abstract ViewProductResponse viewProductResponse(Product product);
      public Product deleteProduct (Product product) {
          product.setDeleted(true);
          return product;
      }
}