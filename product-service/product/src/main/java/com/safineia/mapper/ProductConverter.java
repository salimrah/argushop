package com.safineia.mapper;

import com.safineia.dto.AddProductRequest;
import com.safineia.dto.ProductFullDetailsResponse;
import com.safineia.dto.UpdateProductRequest;
import com.safineia.entity.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductConverter {

      Product convert(ProductFullDetailsResponse productFullDetailsResponse);

      ProductFullDetailsResponse convert(Product product);

      Product convert(UpdateProductRequest updateProductRequest);

      Product convert(AddProductRequest addProductRequest);

      AddProductRequest toAddProductRequest(Product product);

      Product toProduct(AddProductRequest addProductRequest);

}
