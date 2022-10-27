package com.safineia.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.safineia.dto.*;
import com.safineia.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(value = "api/v1/products")
public class ProductResource {

      private final static Logger LOG = LoggerFactory.getLogger(ProductResource.class);

      private final ProductService productService;

      public ProductResource(ProductService productService) {
            this.productService = productService;
      }

      @PostMapping
      public ResponseEntity<Void> publishProduct(@RequestBody AddProductRequest addProductRequest) {
            LOG.info("publishing new product {}", addProductRequest);
            Long productId = productService.addProduct(addProductRequest);
            //return new ResponseEntity<>(HttpStatus.CREATED);
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productId).toUri()).build();
      }

      @GetMapping
      public ApiResponseList<ProductFullDetailsResponse> listingProducts(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
            LOG.info("products list of page {} and size {} ", page, size);
            return productService.findAllProductsWithPagination(page, size);
      }

      @GetMapping("{productId}")
      public ProductFullDetailsResponse getProductDetails(@PathVariable("productId") Long productId) {
            LOG.info("finding product id : {}", productId);
            return productService.findProductById(productId);
      }

      @PatchMapping("{productId}")
      public void editProductPartially(@PathVariable("productId") Long productId , @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
            LOG.info("updating product with id : {}", productId);
            productService.updateProductPartially(productId,patch);
      }

      @DeleteMapping("{productId}")
      public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId){
            LOG.info("deleting product with id : {}", productId);
            productService.deleteProductById(productId);
            return ResponseEntity.noContent().build();
      }


}
