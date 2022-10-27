package com.safineia.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponseList<T> {

      private Long totalElements ;

      private Integer totalPages ;

      private List<T> content ;

      private Integer size ;

      private Integer pageNumber ;

      private Boolean hasPrevious ;

      private Long offset ;

}
