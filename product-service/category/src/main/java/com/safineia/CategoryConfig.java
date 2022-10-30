package com.safineia;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {

      @Bean
      public ModelMapper modelMapper(){
            return new ModelMapper();
      }


}
