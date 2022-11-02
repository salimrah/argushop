package com.safineia.mediaupload.service;

import com.safineia.mediaupload.exception.MediaUploadTypeNotSupportedException;
import com.safineia.mediaupload.model.entity.MediaUpload;
import com.safineia.mediaupload.repository.MediaUploadRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class MediaUploadService {


      // @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
      // private int batchSize;

      private static final int BATCH_SIZE = 10;
      private final MediaUploadRepository mediaUploadRepository;

      private final MediaUploadStorage mediaUploadStorage;

      public MediaUploadService(MediaUploadRepository mediaUploadRepository, MediaUploadStorage mediaUploadStorage) {
            this.mediaUploadRepository = mediaUploadRepository;
            this.mediaUploadStorage = mediaUploadStorage;
      }

      private MediaUpload storeMediaUpload(MultipartFile multipartFile) {
            MediaUpload mediaUpload = new MediaUpload();
            mediaUpload.setOriginalFileName(multipartFile.getOriginalFilename());
            mediaUpload.setCompressed(false);
            mediaUpload.setMimeType(multipartFile.getContentType());
            mediaUpload.setOriginalFileExtension(FilenameUtils.getExtension(mediaUpload.getOriginalFileName()));
            mediaUpload.setOriginalFilePath(mediaUploadStorage.storeFile(multipartFile));
            return mediaUpload;
      }

      public void uploadAndPersistFiles(MultipartFile[] multipartFiles) {
            Predicate<MultipartFile> multipartFilePredicate =
                    multipartFile -> multipartFile.getContentType() != null && (
                            multipartFile.getContentType().startsWith("image")
                                    || multipartFile.getContentType().startsWith("video")
                    );
            List<MediaUpload> mediaUploads = Arrays.stream(multipartFiles)
                    .filter(multipartFilePredicate)
                    .map(this::storeMediaUpload)
                    .toList();
            mediaUploadRepository.saveAll(mediaUploads);
      }
      public void uploadAndPersistBatchFiles(MultipartFile[] multipartFiles) throws HttpMediaTypeNotSupportedException {

            List<MediaUpload> mediaUploads = new ArrayList<>();
            for (int i = 0; i < multipartFiles.length; i++) {
                  if ( !multipartFiles[i].getContentType().startsWith("image")
                          && !multipartFiles[i].getContentType().startsWith("video") ){
                        throw new MediaUploadTypeNotSupportedException("media supported : images , videos");
                  }
                  mediaUploads.add(this.storeMediaUpload(multipartFiles[i]));
                  if (i % BATCH_SIZE == 0 && i > 0) {
                        mediaUploadRepository.saveAll(mediaUploads);
                        mediaUploads.clear();
                  }
            }

            if (mediaUploads.size() > 0) {
                  mediaUploadRepository.saveAll(mediaUploads);
                  mediaUploads.clear();
            }

      }

}
