package com.safineia.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ModelPageable<E> {

      List<E> content();

      Integer size();

      Long totalElements();

      Integer totalPages();

      Long offset();

      Integer pageNumber();

      Boolean hasPrevious();

}
