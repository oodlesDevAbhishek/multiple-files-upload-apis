package io.oodles.multiplefilesuploadapi.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.oodles.multiplefilesuploadapi.modle.FileInfo;
import io.oodles.multiplefilesuploadapi.repo.FileRepository;

@Service
public class FileService {
	
	    @Autowired
	    private FileRepository filerepository;

	 public void uploadFile(MultipartFile file) throws IOException{
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(file.getOriginalFilename());
		fileInfo.setFileType(file.getContentType());
		fileInfo.setFileSize(file.getSize());
		fileInfo.setDocument(
		          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
		  filerepository.insert(fileInfo);
	}
	 
	 public FileInfo downloadFile(String id) {
		 return filerepository.findById(id).get();
	 }
	
}
