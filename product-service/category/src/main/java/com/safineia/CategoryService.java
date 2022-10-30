package com.safineia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safineia.dto.AddCategoryRequest;
import com.safineia.dto.ApiUtil;
import com.safineia.dto.CategoryDetailsResponse;
import com.safineia.dto.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryService {

      private final CategoryRepository categoryRepository ;

      private final ModelMapper modelMapper ;

      public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
            this.categoryRepository = categoryRepository;
            this.modelMapper = modelMapper;
      }

      public Long addCategory(AddCategoryRequest addCategoryRequest) {
            Category category = modelMapper.map(addCategoryRequest,Category.class) ;
            categoryRepository.saveAndFlush(category) ;
            return category.getId();
      }


      public PageableResponse<CategoryDetailsResponse> findAllCategoriesWithPagination(Integer page, Integer size) {
            Page<CategoryDetailsResponse> categories =
                    categoryRepository.findAll(PageRequest.of(page, size))
                            .map(category -> modelMapper.map(category,CategoryDetailsResponse.class));
            return ApiUtil.pageableResponse(categories);
      }

      public CategoryDetailsResponse findCategoryById(Long categoryId) {
            return categoryRepository.findById(categoryId)
                    .map(category -> modelMapper.map(category, CategoryDetailsResponse.class))
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
      }

      public void deleteCategoryById(Long categoryId){
            categoryRepository.findById(categoryId).ifPresent(categoryRepository::delete);
      }


      public String execCategory(){
            System.out.println("Hello from category module , it works :p");
            return "Hello from category service :p" ;
      }



}
