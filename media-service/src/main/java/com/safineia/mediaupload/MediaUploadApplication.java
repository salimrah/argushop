package com.safineia.mediaupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MediaUploadApplication {
      public static void main(String[] args) {
            SpringApplication.run(MediaUploadApplication.class,args);
      }
}
