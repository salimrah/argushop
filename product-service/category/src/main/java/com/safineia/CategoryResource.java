package com.safineia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.safineia.dto.AddCategoryRequest;
import com.safineia.dto.CategoryDetailsResponse;
import com.safineia.dto.PageableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "api/v1/categories")
public class CategoryResource {

      private final static Logger LOG = LoggerFactory.getLogger(CategoryResource.class);

      private final CategoryService categoryService;

      public CategoryResource(CategoryService categoryService) {
            this.categoryService = categoryService;
      }

      @PostMapping
      public ResponseEntity<Void> addCategory(@RequestBody AddCategoryRequest addCategoryRequest) {
            LOG.info("adding new category {}", addCategoryRequest);
            Long categoryId = categoryService.addCategory(addCategoryRequest);
            //return new ResponseEntity<>(HttpStatus.CREATED);
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoryId).toUri()).build();
      }

      @GetMapping
      public PageableResponse<CategoryDetailsResponse> listCategories(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
            LOG.info("categories of page {} and size {} ", page, size);
            return categoryService.findAllCategoriesWithPagination(page, size);
      }

      @GetMapping("{categoryId}")
      public CategoryDetailsResponse getCategoryDetails(@PathVariable("categoryId") Long categoryId) {
            LOG.info("finding category id : {}", categoryId);
            return categoryService.findCategoryById(categoryId);
      }

      @PatchMapping("{categoryId}")
      public void editProductPartially(@PathVariable("categoryId") Long categoryId , @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
            LOG.info("updating category with id : {}", categoryId);
            // TODO : add method on category service to patch only changed attribute
      }

      @DeleteMapping("{categoryId}")
      public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long categoryId){
            LOG.info("deleting category with id : {}", categoryId);
            categoryService.deleteCategoryById(categoryId);
            return ResponseEntity.noContent().build();
      }

}
