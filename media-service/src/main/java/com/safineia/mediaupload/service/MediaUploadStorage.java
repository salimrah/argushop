package com.safineia.mediaupload.service;

import org.springframework.web.multipart.MultipartFile;

public interface MediaUploadStorage {

      String storeFile(MultipartFile file);
}
