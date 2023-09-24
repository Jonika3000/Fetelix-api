package com.example.fetelix.services;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;

public interface ImageService {
    void init();
    void removeFile(String removeFile);
    Path load(String fileName);
    String saveImage(MultipartFile file);
}