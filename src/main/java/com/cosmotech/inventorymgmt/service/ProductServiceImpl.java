package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.core.dto.PaginationDto;
import com.cosmotech.inventorymgmt.dto.*;
import com.cosmotech.inventorymgmt.dto.product.request.AddProductRequest;
import com.cosmotech.inventorymgmt.dto.product.request.DeleteProductRequest;
import com.cosmotech.inventorymgmt.dto.product.request.ViewProductRequest;
import com.cosmotech.inventorymgmt.entity.Product;
import com.cosmotech.inventorymgmt.mapper.ProductMapper;
import com.cosmotech.inventorymgmt.repository.ProductRepo;
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
public class ProductServiceImpl implements ProductService {
    @Autowired
private ProductMapper productMapper;
    @Autowired
    private ProductRepo productRepo;
    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> createProduct (AddProductRequest addProductRequest) {
        Product product = productMapper.createProduct(addProductRequest);
        productRepo.save(product);
        ApiResponse<Product> apiResponse =new ApiResponse<>(true,"product added successfully",200, LocalDateTime.now());
        return ResponseEntity.ok(apiResponse);
    }
@Override
@Transactional
    public ResponseEntity<ApiResponse<?>> listProducts (PaginationDto paginationDto){
    Pageable pageable = PageRequest.of(paginationDto.getPage(), paginationDto.getSize());
    Page<Product> products;
    if (paginationDto.getKeyword()!=null && !paginationDto.getKeyword().trim().isEmpty()){
        products=productRepo.searchProducts(paginationDto.getKeyword().trim(),pageable);
    }
    else {
        products=productRepo.findAll(pageable);
    }
    List<ListProductResponse> listProductResponse = productMapper.listProduct(products);

    ApiResponse<?> apiResponse = new ApiResponse(true,"product listed successful",200, LocalDateTime.now(),listProductResponse);
    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

}
    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> viewProduct (ViewProductRequest viewProductRequest) {
        Optional<Product> product = productRepo.findById(viewProductRequest.getId());
        if (product.isEmpty()) {
            throw new RuntimeException("Product with id " + id + " not found");
        }
         ViewProductResponse viewProductResponse = productMapper.viewProductResponse(product.get());
        ApiResponse<?> response = new ApiResponse<>(true, "Product is fetch successfully",200, LocalDateTime.now(), product.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @Override
    @Transactional
    public ResponseEntity<ApiResponse<?>> updateProduct (UpdateProductResponse  updateProductResponse) {
        Optional<Product> product = productRepo.findById(updateProductResponse.getId());
        Product updateProduct = product.get();
        updateProduct.setName(updateProduct.getName());
        updateProduct.setPrice(updateProduct.getPrice());
        updateProduct.setQuantity(updateProduct.getQuantity());
        updateProduct.setCategory(updateProduct.getCategory());
        updateProduct.setUpdated_at(LocalDateTime.now());
        updateProduct.setCreated_at(LocalDateTime.now());
        productRepo.save(updateProduct);

        ApiResponse<?> response = new ApiResponse<>(true, "Product update successfully",200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    public ResponseEntity<ApiResponse<?>> deleteProduct (DeleteProductRequest deleteProductRequest) {
        Optional<Product> ProductOpt = productRepo.findById(deleteProductRequest.getId());
        if (ProductOpt.isEmpty()) {
            throw new RuntimeException("Product with id " + id + " not found");
        }
        Product productToDelete = productMapper.deleteProduct(ProductOpt.get());
        productRepo.delete(productToDelete);
        ApiResponse<?> response = new ApiResponse<>(true, "Product deleted successfully",200, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
