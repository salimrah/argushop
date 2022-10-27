package com.safineia;

import com.safineia.dto.ApiResponseList;
import com.safineia.dto.ModelPageable;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Page;


public class ApiUtility {



      public static <T> ApiResponseList<T> toApiResponseList (@NotNull  Page<T> entityPageable){
            ApiResponseList<T> apiResponseList = new ApiResponseList<>();
            apiResponseList.setContent(entityPageable.getContent());
            apiResponseList.setSize(entityPageable.getSize());
            apiResponseList.setTotalElements(entityPageable.getTotalElements());
            apiResponseList.setTotalPages(entityPageable.getTotalPages());
            apiResponseList.setOffset(entityPageable.getPageable().getOffset());
            apiResponseList.setPageNumber(entityPageable.getPageable().getPageNumber());
            apiResponseList.setHasPrevious(entityPageable.getPageable().hasPrevious());
            return apiResponseList ;
      }

      public static <T> ApiResponseList<T> toApiResponseList(@NotNull ModelPageable<T> modelPageable){
            ApiResponseList<T> apiResponseList = new ApiResponseList<>();
            apiResponseList.setContent(modelPageable.content());
            apiResponseList.setSize(modelPageable.size());
            apiResponseList.setTotalElements(modelPageable.totalElements());
            apiResponseList.setTotalPages(modelPageable.totalPages());
            apiResponseList.setOffset(modelPageable.offset());
            apiResponseList.setPageNumber(modelPageable.pageNumber());
            apiResponseList.setHasPrevious(modelPageable.hasPrevious());
            return apiResponseList ;
      }

}
