package com.sergio.bdas2.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BinaryContentService {

//    @Autowired
//    private BinaryContentRepository binaryContentRepository;
//
//    public void saveImage(Long userId, MultipartFile picture) throws IOException {
//        BinaryContent binaryContent = new BinaryContent();
//        binaryContent.setUserId(userId);
//        binaryContent.setFilename(picture.getOriginalFilename());
//        binaryContent.setFiletype(picture.getContentType());
//        binaryContent.setFileextension(FilenameUtils.getExtension(picture.getOriginalFilename()));
//        binaryContent.setUploadedBy(/* get the current user or username */);
//        binaryContent.setContent(picture.getBytes());
//
//        // Set other metadata (upload_date, modification_date) as needed
//
//        binaryContentRepository.save(binaryContent);
//    }
}
