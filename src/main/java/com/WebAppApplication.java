package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.storage.StorageProperties;



@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class WebAppApplication extends SpringBootServletInitializer {
	 @Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(WebAppApplication.class);
	   }
	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
	}

}
