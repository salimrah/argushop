package com.safineia.mediaupload.model.entity;


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
@Table(name = "media_upload")
public class MediaUpload {


      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "seq_media_upload_gen")
      @SequenceGenerator(name = "seq_media_upload_gen", sequenceName = "seq_media_upload",initialValue = 100)
      private Long id ;

      private String originalFilePath;

      private String originalFileName;

      private String originalFileExtension;

      private String compressedFilePath;

      private boolean compressed;

      private String thumbnailPath;

      private String mimeType;

      private boolean thumbnail;

      @Version
      private Short version;


}

