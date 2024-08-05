package com.example.demo.service.impl;

import com.example.demo.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${root.path.upload}")
    private String root;

    @Override
    public void save(MultipartFile file,UUID uuid) {
        Path rootPath = Paths.get(root);
        try {
            if (!Files.exists(rootPath)) {
                Files.createDirectories(rootPath);
            }
            Files.copy(file.getInputStream(), rootPath.resolve(
                    uuid + (file.getOriginalFilename() != null ? file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')) : "")
            ));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
}
