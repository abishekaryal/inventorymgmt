package com.cosmotech.inventorymgmt.repository;

import com.cosmotech.inventorymgmt.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier,Integer> {
        Page<Supplier> findAll (Pageable pageable);
    @Query("""
            SELECT u FROM Supplier u
            WHERE (:keyword IS NULL OR LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')))
               OR (:keyword IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')))
        """)
    Page<Supplier> searchSuppliers (String keyword, Pageable pageable);

    Optional<Supplier> findByEmail(String email);
}


