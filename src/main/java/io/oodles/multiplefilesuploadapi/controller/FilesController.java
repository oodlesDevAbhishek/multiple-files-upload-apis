package io.oodles.multiplefilesuploadapi.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.oodles.multiplefilesuploadapi.modle.FileInfo;
import io.oodles.multiplefilesuploadapi.service.FileService;

@RestController
public class FilesController {

	@Autowired
	FileService fileService;

	@PostMapping("/files/upload")
	public ResponseEntity<String> uploadFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
		String message = "";
		try {
			List<String> fileNames = new ArrayList<>();
			Arrays.asList(files).stream().forEach(file -> {
				try {
					fileService.uploadFile(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				fileNames.add(file.getOriginalFilename());
			});
			message = "Uploaded the files successfully: " + fileNames;
			return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			message = "Fail to upload files!";
			e.printStackTrace();
			return new ResponseEntity<>(message, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/files/{id}")
	public ResponseEntity<?> getPhoto(@PathVariable String id) {
		FileInfo fileInfo = fileService.downloadFile(id);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(fileInfo.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileInfo.getFileName() + "\"")
				.body(new ByteArrayResource(fileInfo.getDocument().getData()));
		// return new ResponseEntity<>( , HttpStatus.OK);
	}

}
