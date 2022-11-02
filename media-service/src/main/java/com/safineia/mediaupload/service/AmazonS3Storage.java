package com.safineia.mediaupload.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AmazonS3Storage implements MediaUploadStorage {
      @Override
      public String storeFile(MultipartFile file) {
            return null;
      }
}
