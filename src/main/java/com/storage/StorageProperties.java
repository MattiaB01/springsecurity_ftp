package com.storage;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties("storage")
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	
	//private String location = "src/main/resources/static/files";
	
//private String location = "files"; 
	/*private String location = "/files"; */
	
	
	//File homeBase = new File( System.getProperty( "user.dir" ) ).getAbsoluteFile();
	File homeBase = new File( System.getProperty( "user.home" ) ).getAbsoluteFile();
	File propertyFile = new File( homeBase, "files/" );

	//private String location = propertyFile.toString();
	private String location ="/home/files";
	
	//InputStream inputStream = new FileInputStream( propertyFile );
	
	//private String location="";
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}