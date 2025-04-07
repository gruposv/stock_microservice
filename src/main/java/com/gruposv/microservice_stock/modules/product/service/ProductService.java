package com.gruposv.microservice_stock.modules.product.service;

import com.gruposv.microservice_stock.modules.product.dto.ListProductsDTO;
import com.gruposv.microservice_stock.modules.product.dto.ProductDTO;
import com.gruposv.microservice_stock.modules.product.entity.ProductEntity;
import com.gruposv.microservice_stock.modules.product.exception.DuplicateNameProductException;
import com.gruposv.microservice_stock.modules.product.exception.DuplicateSkuCodeException;
import com.gruposv.microservice_stock.modules.product.exception.ProductNotFoundException;
import com.gruposv.microservice_stock.modules.product.mapper.ProductMapper;
import com.gruposv.microservice_stock.modules.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(ProductDTO productDTO){
        // Verificar se o SKU ou o nome é repetido
        if(this.productRepository.existsByName(productDTO.getName())) throw new DuplicateNameProductException("Nome do produto já existente na base de dados. Forneça um nome diferente.");
        if(this.productRepository.existsBySkuCode(productDTO.getSkuCode())) throw new DuplicateSkuCodeException("Código do produto já existente na base de dados. Forneça um código diferente.");

        ProductEntity product = this.productRepository.save(ProductMapper.toEntity(productDTO));
        return ProductMapper.toDto(product);
    }

    public Page<ProductDTO> findAllProducts(ListProductsDTO listProductsDTO){
        Pageable pageable = PageRequest.of(listProductsDTO.getPage(), listProductsDTO.getSize());
        return this.productRepository.findAll(pageable).map(ProductMapper::toDto);
    }

    public ProductDTO findById(Long id){
        return this.productRepository.findById(id).map(ProductMapper::toDto).orElseThrow(() -> new ProductNotFoundException("O produto com o ID " + id + " Não foi encontrado."));
    }

    public ProductDTO findByName(String name){
        return this.productRepository.findByName(name).map(ProductMapper::toDto).orElseThrow(() -> new ProductNotFoundException("O produto com o nome " + name + " Não foi encontrado."));
    }

    public ProductDTO findBySkuCode(String skuCode){
        return this.productRepository.findBySkuCode(skuCode).map(ProductMapper::toDto).orElseThrow(() -> new ProductNotFoundException("O produto com o código " + skuCode + " Não foi encontrado."));
    }

    public ProductDTO updateProduct(String skuCode, ProductDTO productDTO){
         ProductEntity productToUpdate = this.productRepository.findBySkuCode(skuCode).orElseThrow(() -> new ProductNotFoundException("O produto com o código " + skuCode + " Não foi encontrado."));
         ProductEntity productUpdated = this.productRepository.save(ProductMapper.toEntityUpdate(productDTO, productToUpdate));
         return ProductMapper.toDto(productUpdated);
    }

}
