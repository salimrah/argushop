package com.safineia.mediaupload.repository;

import com.safineia.mediaupload.model.entity.MediaUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaUploadRepository extends JpaRepository<MediaUpload,Long> {


}
