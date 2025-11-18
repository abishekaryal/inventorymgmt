package com.cosmotech.inventorymgmt.controller;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.dto.product.request.AddProductRequest;
import com.cosmotech.inventorymgmt.core.dto.PaginationDto;
import com.cosmotech.inventorymgmt.dto.product.request.DeleteProductRequest;
import com.cosmotech.inventorymgmt.dto.UpdateProductResponse;
import com.cosmotech.inventorymgmt.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
@Autowired
    private ProductService productService;
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<?>> createProduct(@RequestBody @Valid AddProductRequest addProductRequest) {
        return productService.createProduct(addProductRequest);
    }
    @PostMapping ("/list")
    public ResponseEntity<ApiResponse<?>> listProducts(@RequestBody @Valid PaginationDto paginationDto){
        return productService.listProducts(paginationDto);
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<?>> updateProduct(@RequestBody @Valid UpdateProductResponse updateProductResponse) {
        return productService.updateProduct(updateProductResponse);

    }
    @DeleteMapping("/Delete")
    public ResponseEntity<ApiResponse<?>> deleteProduct(@RequestBody @Valid DeleteProductRequest deleteProductRequest) {
        return productService.deleteProduct(deleteProductRequest);
    }
}
