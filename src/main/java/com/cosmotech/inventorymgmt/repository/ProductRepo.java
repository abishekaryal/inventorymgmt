package com.cosmotech.inventorymgmt.repository;

import com.cosmotech.inventorymgmt.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Page<Product> findAll (Pageable pageable);
    @Query("""
            SELECT u FROM Product u
            WHERE (:keyword IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
               OR (:keyword IS NULL OR LOWER(u.category) LIKE LOWER(CONCAT('%', :keyword, '%')))
        """)

    Page<Product> searchProducts(String keyword, Pageable pageable);
}

