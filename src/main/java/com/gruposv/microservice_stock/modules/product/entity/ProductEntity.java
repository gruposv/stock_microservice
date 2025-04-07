package com.gruposv.microservice_stock.modules.product.entity;

import com.gruposv.microservice_stock.modules.product.enums.ProductStatus;
import com.gruposv.microservice_stock.modules.product.enums.UnitOfMeasure;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_PRODUCTS")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_id")
    private Long id;

    @Column(name = "skuCode", length = 20, nullable = false, unique = true)
    private String skuCode;

    @Column(name = "product_name", length = 125, nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "ncm_code")
    private String ncmCode;

    @Column(name = "unit_of_measure", nullable = false)
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure;

    @Column(name = "product_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    public ProductEntity() {
    }

    public ProductEntity(Long id, String skuCode, String name, String description, String ncmCode, UnitOfMeasure unitOfMeasure, ProductStatus productStatus, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.skuCode = skuCode;
        this.name = name;
        this.description = description;
        this.ncmCode = ncmCode;
        this.unitOfMeasure = unitOfMeasure;
        this.productStatus = productStatus;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNcmCode() {
        return ncmCode;
    }

    public void setNcmCode(String ncmCode) {
        this.ncmCode = ncmCode;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
