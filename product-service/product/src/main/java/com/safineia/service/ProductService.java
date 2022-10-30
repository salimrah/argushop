package com.safineia.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.safineia.*;
import com.safineia.dto.AddProductRequest;
import com.safineia.dto.PageableResponse;
import com.safineia.entity.ProductRepository;
import com.safineia.mapper.ProductConverter;
import com.safineia.dto.ProductDetailsResponse;
import com.safineia.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {

      private final ProductRepository productRepository;

      private final ProductConverter productConverter ;

      private final ObjectMapper objectMapper;

      public ProductService(ProductRepository productRepository,
                            ProductConverter productConverter,
                            ObjectMapper objectMapper){
            this.productRepository = productRepository ;
            this.productConverter = productConverter ;
            this.objectMapper = objectMapper ;
      }

      public Long addProduct(AddProductRequest addProductRequest) {
            Product product = productConverter.convert(addProductRequest) ;
            productRepository.saveAndFlush(product) ;
            return product.getId();
      }


      public PageableResponse<ProductDetailsResponse> findAllProductsWithPagination(Integer page, Integer size) {
            Page<ProductDetailsResponse> products =
                    productRepository.findAll(PageRequest.of(page, size)).map(productConverter::convert) ;
            return ApiUtil.pageableResponse(products);
      }

      public ProductDetailsResponse findProductById(Long productId) {
            return productRepository.findById(productId).map(productConverter::convert)
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));
      }


      public void deleteProductById(Long productId){
            productRepository.findById(productId).ifPresent(productRepository::delete);
      }


      private Product applyPatchToProduct(JsonPatch patch,Product product)
            throws JsonPatchException, JsonProcessingException {
                  JsonNode patched = patch.apply(objectMapper.convertValue(product, JsonNode.class));
                  return objectMapper.treeToValue(patched, Product.class);
      }

      public void updateProductPartially(Long productId, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
            Product product   = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
            Product productPatched = applyPatchToProduct(patch, product);
            productRepository.save(productPatched);
      }
}
