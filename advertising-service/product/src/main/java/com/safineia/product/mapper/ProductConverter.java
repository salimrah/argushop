package com.safineia.product.mapper;

import com.safineia.product.dto.AddProductRequest;
import com.safineia.product.dto.ProductDetailsResponse;
import com.safineia.product.dto.UpdateProductRequest;
import com.safineia.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductConverter {

      Product convert(ProductDetailsResponse productFullDetailsResponse);

      ProductDetailsResponse convert(Product product);

      Product convert(UpdateProductRequest updateProductRequest);

      Product convert(AddProductRequest addProductRequest);

      AddProductRequest toAddProductRequest(Product product);

      Product toProduct(AddProductRequest addProductRequest);

}
