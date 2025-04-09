package com.gruposv.microservice_stock.modules.product.mapper;

import com.gruposv.microservice_stock.modules.product.dto.ProductTypeDTO;
import com.gruposv.microservice_stock.modules.product.enums.ProductType;

public class ProductTypeMapper {

    public static ProductTypeDTO toDTO(ProductType productType) {
        return new ProductTypeDTO(productType);
    }

}
