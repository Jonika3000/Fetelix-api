package com.example.fetelix.services;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileSystemStorageService implements ImageService {

    private final Path rootLocation;

    private FileSystemStorageService(StorageProperties storageProperties) {
        rootLocation = Paths.get(storageProperties.getFolder());
    }
    @Override
    public void init() {
        try {
            if(!Files.exists(rootLocation))
                Files.createDirectory(rootLocation);
        }catch(IOException e) {
            throw new StorageException("Помилка створення папки!");
        }
    }

    @Override
    public void removeFile(String removeFile) {
//        try {
//
//        } catch(MalformedURLException e) {
//            throw new StorageException("Файл не знаєте: "+ ашду)
//        }

    }

    @Override
    public Path load(String fileName) {
        return null;
    }

    @Override
    public String saveImage(MultipartFile file) {
        try {
            String extension = "webp";
            String randomFilename = UUID.randomUUID().toString() + "." + extension;
            int [] imageSize = {32, 150, 300, 600, 1200};
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            for(int size : imageSize) {
                String fileOutputSize = rootLocation.toString()+"/"+size+"_"+randomFilename;
                Thumbnails.of(bufferedImage)
                        .size(size, size)
                        .outputFormat(extension)
                        .toFile(fileOutputSize);
            }
            return randomFilename;
        } catch(IOException e) {
            throw new StorageException("Проблема перетвоення файлу!", e);
        }
    }
}