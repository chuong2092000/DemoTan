package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileStorageService {

    public void save(MultipartFile file,UUID uuid);
}
