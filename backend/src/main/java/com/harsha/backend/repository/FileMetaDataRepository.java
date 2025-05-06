package com.harsha.backend.repository;

import com.harsha.backend.entity.FileMetaData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileMetaDataRepository extends MongoRepository<FileMetaData, String> {
  Optional<FileMetaData> findByOriginalName(String originalName);
}
