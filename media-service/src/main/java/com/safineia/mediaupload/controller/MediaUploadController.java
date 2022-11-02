package com.safineia.mediaupload.controller;

import com.safineia.mediaupload.service.MediaUploadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/media")
public class MediaUploadController {

      private final MediaUploadService mediaUploadService ;

      public MediaUploadController(MediaUploadService mediaUploadService) {
            this.mediaUploadService = mediaUploadService;
      }

      @PostMapping(value = "upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_OCTET_STREAM_VALUE})
      public ResponseEntity<?> uploadMediaFiles(@RequestPart("files") MultipartFile[] files) throws IOException, HttpMediaTypeNotSupportedException {
            mediaUploadService.uploadAndPersistBatchFiles(files);
            return ResponseEntity.noContent().build();
      }

}
