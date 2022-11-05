package com.safineia.product.mapper;

import com.safineia.product.dto.AddProductRequest;
import com.safineia.product.dto.ProductDetailsResponse;
import com.safineia.product.dto.UpdateProductRequest;
import com.safineia.product.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ModelMapperProductConverter implements ProductConverter{

      private final ModelMapper modelMapper;

      public ModelMapperProductConverter() {
            modelMapper = new ModelMapper();
      }

      @Override
      public Product convert(ProductDetailsResponse productFullDetailsResponse) {
            return modelMapper.map(productFullDetailsResponse, Product.class);
      }

      @Override
      public ProductDetailsResponse convert(Product product) {
            return modelMapper.map(product, ProductDetailsResponse.class);
      }

      @Override
      public Product convert(UpdateProductRequest updateProductRequest){
            return modelMapper.map(updateProductRequest, Product.class);
      }

      @Override
      public Product convert(AddProductRequest addProductRequest) {
            return modelMapper.map(addProductRequest,Product.class);
      }

      @Override
      public AddProductRequest toAddProductRequest(Product product) {
            return modelMapper.map(product,AddProductRequest.class);
      }

      @Override
      public Product toProduct(AddProductRequest addProductRequest) {
            return modelMapper.map(addProductRequest,Product.class);
      }


}
