package com.safineia.category.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CATEGORY")
public class Category {

      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEQ_CATEGORY_GEN")
      @SequenceGenerator(name = "SEQ_CATEGORY_GEN", sequenceName = "SEQ_CATEGORY",initialValue = 100)
      private Long id ;

      private String name ;

      private String description;


}
