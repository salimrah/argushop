package com.safineia.mediaupload.service;

import com.safineia.mediaupload.exception.MediaUploadTypeNotSupportedException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.system.SystemProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Component
@Primary
public class LocalStorage implements MediaUploadStorage{

      private String getHomeDirectory(){
            return SystemProperties.get("user.home");
      }

      @Override
      public String storeFile(MultipartFile file) {
            String dirPath = getHomeDirectory() + File.separator +
                    "media_data" + File.separator +
                    "videos" + File.separator +
                    LocalDate.now();

            String fileName = String.format(
                    "%s.%s",
                    UUID.randomUUID().toString(),
                    FilenameUtils.getExtension(file.getOriginalFilename())
            );

            File dir = new File(dirPath);
            if (!dir.exists()) {
                  dir.mkdirs();
            }

            String location = dirPath + File.separator + fileName;
            Path path = Paths.get(location);
            try {
                  Files.write(path, file.getBytes());
            } catch (IOException e) {
                  throw new MediaUploadTypeNotSupportedException("Media type supported : images , videos");
            }
            return location;
      }
}
