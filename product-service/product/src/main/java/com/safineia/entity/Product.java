package com.safineia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PRODUCT")
public class Product {


      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEQ_PRODUCT_GEN")
      @SequenceGenerator(name = "SEQ_PRODUCT_GEN", sequenceName = "SEQ_PRODUCT",initialValue = 100)
      private Long id ;

      private String label ;

      private String description;

      private Double price ;

      /*
      private String address ;

      private Double latitude ;

      private Double longitude ;

      private boolean archived ;

      private String phoneNumber ;

      private String city  ;

      private Long publishedBy ;

      private LocalDateTime publishedDate;

      private LocalDateTime lastModifiedDate ;

      private Long lastModifiedBy ;
      */

}
