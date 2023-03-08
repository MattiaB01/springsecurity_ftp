package com.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageImpl implements StorageService {

	private final Path rootLocation;

	//prende le informazioni Path dalla classe StorageProperty
	@Autowired
	public StorageImpl  (StorageProperties properties) {
		
		
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(Authentication auth,  MultipartFile file) {
		   try {
			
			 //controllo per evitare upload file vuoto
		/*	if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}*/
			String loc = this.rootLocation+"/"+auth.getName();
			Path dest = Paths.get(loc);
			
			Path destinationFile = dest.resolve(
					Paths.get(file.getOriginalFilename()))
					.normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(dest.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			//throw new StorageException("Failed to store file.", e);
			throw new StorageException( e.toString());
			
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	
	@Override
	public Path load(Authentication auth,String filename) {
		Path newLocation = Paths.get(rootLocation+"/"+auth.getName());
		//return rootLocation.resolve(filename);
		return newLocation.resolve(filename);
	}

	
	@Override
	public Resource loadAsResource(Authentication auth,String filename) {
		try {
			Path file = load(auth,filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	
	@Override
	public void delete(Authentication auth,String filename) throws IOException
	{
	    Path loc = Paths.get(rootLocation+"/"+auth.getName());
		//Path fileToDeletePath = rootLocation.resolve(filename);
	    Path fileToDeletePath = loc.resolve(filename);
    	Files.delete(fileToDeletePath);
	}
	

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}