package io.oodles.multiplefilesuploadapi.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.oodles.multiplefilesuploadapi.modle.FileInfo;

public interface FileRepository extends MongoRepository<FileInfo ,String> {

}
