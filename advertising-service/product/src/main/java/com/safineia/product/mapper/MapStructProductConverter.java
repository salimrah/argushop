package com.safineia.product.mapper;

import com.safineia.product.dto.ProductDetailsResponse;
import com.safineia.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MapStructProductConverter extends ProductConverter{
      MapStructProductConverter MAPPER = Mappers.getMapper(MapStructProductConverter.class);

      @Override
      Product convert(ProductDetailsResponse productFullDetailsResponse);

      @Override
      ProductDetailsResponse convert(Product product);

}
