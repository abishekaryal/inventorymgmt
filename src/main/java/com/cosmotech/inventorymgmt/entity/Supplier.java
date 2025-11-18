package com.cosmotech.inventorymgmt.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "supplier")
@SQLRestriction("is_deleted=false")
@EntityListeners(AuditingEntityListener.class)
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(nullable = false,name = "email")
    private String email;

    @Column(nullable = false,name = "phone")
    private String phone;

    @Column(nullable = false,name = "address")
    private String address;

    @CreatedDate
    @Column(nullable = false,name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false,name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted",nullable = false)
    private boolean isDeleted =false;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}

