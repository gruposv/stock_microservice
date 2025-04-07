package com.gruposv.microservice_stock.modules.product.controller;

import com.gruposv.microservice_stock.dto.ApiResponseDTO;
import com.gruposv.microservice_stock.modules.product.dto.ListProductsDTO;
import com.gruposv.microservice_stock.modules.product.dto.ProductDTO;
import com.gruposv.microservice_stock.modules.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<ProductDTO>> createProduct(@RequestBody @Valid ProductDTO productDTO){
        ProductDTO product = this.productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<>("success", HttpStatus.CREATED.value(), product, "Produto criado com sucesso!"));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<ProductDTO>>> findAllProducts(@ModelAttribute @Valid ListProductsDTO listProductsDTO){
        Page<ProductDTO> products = this.productService.findAllProducts(listProductsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), products, "Lista de produtos."));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponseDTO<ProductDTO>> findProductById(@PathVariable("id") Long id){
        ProductDTO product = this.productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), product, "Produto encontrado com sucesso!"));
    }

    @GetMapping("/skucode/{skucode}")
    public ResponseEntity<ApiResponseDTO<ProductDTO>> findProductBySkuCode(@PathVariable("skucode") String skuCode){
        ProductDTO product = this.productService.findBySkuCode(skuCode);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), product, "Produto encontrado com sucesso!"));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponseDTO<ProductDTO>> findProductByName(@PathVariable("name") String name){
        ProductDTO product = this.productService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO<>("success", HttpStatus.OK.value(), product, "Produto encontrado com sucesso!"));
    }

    @PutMapping("/skucode/{skucode}")
    public ResponseEntity<ApiResponseDTO<ProductDTO>> updateProduct(@PathVariable("skucode") String skuCode, @RequestBody ProductDTO productDTO){
        ProductDTO product = this.productService.updateProduct(skuCode, productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO<>("success", HttpStatus.CREATED.value(), product, "Produto criado com sucesso!"));
    }

}
