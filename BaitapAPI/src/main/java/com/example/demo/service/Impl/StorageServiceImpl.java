package com.example.demo.service.Impl;

import org.springframework.stereotype.Service;

import com.example.demo.service.IStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageServiceImpl implements IStorageService {

    private final Path rootLocation = Paths.get("upload-dir");


    @Override
	public String storeFile(String fileName, byte[] content) {
        try {
            Files.write(rootLocation.resolve(fileName), content);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

  
    @Override
	public byte[] loadFile(String fileName) {
        try {
            return Files.readAllBytes(rootLocation.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load file", e);
        }
    }
}