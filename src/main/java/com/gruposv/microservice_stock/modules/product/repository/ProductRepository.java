package com.gruposv.microservice_stock.modules.product.repository;

import com.gruposv.microservice_stock.modules.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findBySkuCode(String skuCode);
    boolean existsByName(String name);
    boolean existsBySkuCode(String name);
}
