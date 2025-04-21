package com.sportsmanagement.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sportsmanagement.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{
	
	private static final String UPLOAD_DIR = "uploads/";

	@Override
	public String saveImage(MultipartFile file) throws IOException{
		if (file.isEmpty()) {
            return null;
        }

        // Create the uploads directory if it doesn't exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Generate a unique file name
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        // Save the file
        Files.write(filePath, file.getBytes());

        // Return the saved file path (relative path)
        return UPLOAD_DIR + fileName;
	}

	@Override
	public byte[] getImage(String filePath) throws IOException{
		Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
	}

}
