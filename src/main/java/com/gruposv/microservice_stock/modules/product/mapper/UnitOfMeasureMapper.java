package com.gruposv.microservice_stock.modules.product.mapper;

import com.gruposv.microservice_stock.modules.product.dto.UnitOfMeasureDTO;
import com.gruposv.microservice_stock.modules.product.enums.UnitOfMeasure;

public class UnitOfMeasureMapper {

    public static UnitOfMeasureDTO toDTO (UnitOfMeasure unitOfMeasure){
        return new UnitOfMeasureDTO(unitOfMeasure);
    }

}
