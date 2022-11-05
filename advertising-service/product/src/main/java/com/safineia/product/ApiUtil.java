package com.safineia.product;

import com.safineia.category.dto.PageableResponse;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Page;


public class ApiUtil {


      public static <T> PageableResponse<T> pageableResponse(@NotNull  Page<T> pageableModel){
            return PageableResponse.<T>builder()
            .content(pageableModel.getContent())
            .size(pageableModel.getSize())
            .totalElements(pageableModel.getTotalElements())
            .totalPages(pageableModel.getTotalPages())
            .offset(pageableModel.getPageable().getOffset())
            .pageNumber(pageableModel.getPageable().getPageNumber())
            .hasPrevious(pageableModel.getPageable().hasPrevious()).build();

      }
      
}
