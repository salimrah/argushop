package com.safineia.mapper;

import com.safineia.dto.ProductDetailsResponse;
import com.safineia.entity.Product;
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
