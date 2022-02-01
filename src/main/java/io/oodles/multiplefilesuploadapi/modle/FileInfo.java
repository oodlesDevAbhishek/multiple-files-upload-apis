package io.oodles.multiplefilesuploadapi.modle;



import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "oodlesDocuments")
public class FileInfo {
	
	@Id
	private String id;
	private String fileName;
	private Long fileSize;
	private Binary document;
	private String fileType;
	
}