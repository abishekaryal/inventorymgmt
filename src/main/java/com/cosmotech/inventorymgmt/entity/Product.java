package com.cosmotech.inventorymgmt.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "product")
@SQLRestriction("is_deleted=false")
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "id")
    private Integer id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(nullable = false,name = "category")
    private String category;

    @Column(nullable = false,name = "price")
    private Double price;

    @Column(nullable = false,name = "quantity")
    private String quantity;

    @CreatedDate
    @Column(nullable = false,name = "created_at")
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(nullable = false,name = "updated_at")
    private LocalDateTime updated_at;

    @Column(name = "is_deleted",nullable = false)
    private boolean isDeleted =false;

    @ManyToOne
    @JoinColumn(name ="supplier_id",nullable = false)
    private Supplier supplier;
}

