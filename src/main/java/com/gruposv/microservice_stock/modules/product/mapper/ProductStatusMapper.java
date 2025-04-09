package com.gruposv.microservice_stock.modules.product.mapper;

import com.gruposv.microservice_stock.modules.product.dto.ProductStatusDTO;
import com.gruposv.microservice_stock.modules.product.enums.ProductStatus;

public class ProductStatusMapper {

    public static ProductStatusDTO toDTO(ProductStatus productStatus){
        return new ProductStatusDTO(productStatus);
    }

}
