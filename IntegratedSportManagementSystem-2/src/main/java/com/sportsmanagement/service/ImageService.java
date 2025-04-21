package com.sportsmanagement.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	public String saveImage(MultipartFile file) throws IOException;
	public byte[] getImage(String filePath) throws IOException;

}
