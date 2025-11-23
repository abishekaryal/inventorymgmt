package com.cosmotech.inventorymgmt.service;

import com.cosmotech.inventorymgmt.core.dto.ApiResponse;
import com.cosmotech.inventorymgmt.dto.product.request.AddProductRequest;
import com.cosmotech.inventorymgmt.core.dto.PaginationDto;
import com.cosmotech.inventorymgmt.dto.product.request.DeleteProductRequest;
import com.cosmotech.inventorymgmt.dto.UpdateProductResponse;
import com.cosmotech.inventorymgmt.dto.product.request.ViewProductRequest;
import com.cosmotech.inventorymgmt.dto.supplier.CreateSupplierRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ResponseEntity<ApiResponse<?>> createProduct (AddProductRequest addProductRequest) ;
    ResponseEntity<ApiResponse<?>> listProducts ( PaginationDto paginationDto) ;
    ResponseEntity<ApiResponse<?>> viewProduct (ViewProductRequest viewProductRequest) ;
    ResponseEntity<ApiResponse<?>> updateProduct(UpdateProductResponse productUpdateResponse) ;
    ResponseEntity<ApiResponse<?>> deleteProduct(DeleteProductRequest deleteProductRequest) ;
}
