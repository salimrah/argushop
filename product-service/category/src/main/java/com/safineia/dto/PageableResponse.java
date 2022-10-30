package com.safineia.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageableResponse<T> {

      private Long totalElements ;

      private Integer totalPages ;

      private List<T> content ;

      private Integer size ;

      private Integer pageNumber ;

      private Boolean hasPrevious ;

      private Long offset ;

}
