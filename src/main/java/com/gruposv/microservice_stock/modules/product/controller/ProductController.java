package com.gruposv.microservice_stock.modules.product.controller;

import com.gruposv.microservice_stock.dto.ApiResponseDTO;
import com.gruposv.microservice_stock.modules.product.dto.*;
import com.gruposv.microservice_stock.modules.product.enums.ProductStatus;
import com.gruposv.microservice_stock.modules.product.enums.ProductType;
import com.gruposv.microservice_stock.modules.product.enums.UnitOfMeasure;
import com.gruposv.microservice_stock.modules.product.mapper.ProductStatusMapper;
import com.gruposv.microservice_stock.modules.product.mapper.ProductTypeMapper;
import com.gruposv.microservice_stock.modules.product.mapper.UnitOfMeasureMapper;
import com.gruposv.microservice_stock.modules.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<ReturnProductDTO>> createProduct(@RequestBody @Valid ProductDTO productDTO){
        ReturnProductDTO product = this.productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<>("success", HttpStatus.CREATED.value(), product, "Produto criado com sucesso!"));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<ReturnProductDTO>>> findAllProducts(@ModelAttribute @Valid ListProductsDTO listProductsDTO){
        Page<ReturnProductDTO> products = this.productService.findAllProducts(listProductsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), products, "Lista de produtos."));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponseDTO<ReturnProductDTO>> findProductById(@PathVariable("id") Long id){
        ReturnProductDTO product = this.productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), product, "Produto encontrado com sucesso!"));
    }

    @GetMapping("/skucode/{skucode}")
    public ResponseEntity<ApiResponseDTO<ReturnProductDTO>> findProductBySkuCode(@PathVariable("skucode") String skuCode){
        ReturnProductDTO product = this.productService.findBySkuCode(skuCode);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), product, "Produto encontrado com sucesso!"));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponseDTO<ReturnProductDTO>> findProductByName(@PathVariable("name") String name){
        ReturnProductDTO product = this.productService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), product, "Produto encontrado com sucesso!"));
    }

    @PutMapping("/skucode/{skucode}")
    public ResponseEntity<ApiResponseDTO<ReturnProductDTO>> updateProduct(@PathVariable("skucode") String skuCode, @RequestBody ProductDTO productDTO){
        ReturnProductDTO product = this.productService.updateProduct(skuCode, productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<>("success", HttpStatus.CREATED.value(), product, "Produto criado com sucesso!"));
    }

    @GetMapping("/unit_of_measures")
    public ResponseEntity<ApiResponseDTO<List<UnitOfMeasureDTO>>> returnUnitOfMeasureList(){
        List<UnitOfMeasureDTO> unitOfMeasureList = Arrays.stream(UnitOfMeasure.values()).map(UnitOfMeasureMapper::toDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.CREATED.value(), unitOfMeasureList, ""));
    }

    @GetMapping("/product_types")
    public ResponseEntity<ApiResponseDTO<List<ProductTypeDTO>>> returnProductTypeList(){
        List<ProductTypeDTO> productTypeList = Arrays.stream(ProductType.values()).map(ProductTypeMapper::toDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.CREATED.value(), productTypeList, ""));
    }

    @GetMapping("/product_status")
    public ResponseEntity<ApiResponseDTO<List<ProductStatusDTO>>> returnProductStatusList(){
        List<ProductStatusDTO> productStatusList = Arrays.stream(ProductStatus.values()).map(ProductStatusMapper::toDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.CREATED.value(), productStatusList, ""));
    }

}
