package com.storage;

import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	//void store(MultipartFile file);

	Stream<Path> loadAll();

	Path load(Authentication auth,String filename);

	Resource loadAsResource( Authentication auth,String filename);

	void deleteAll();

	public void delete(Authentication auth, String filename) throws IOException;

	void store(Authentication auth, MultipartFile file);
}