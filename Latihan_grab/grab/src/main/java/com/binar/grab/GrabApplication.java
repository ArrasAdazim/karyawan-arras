package com.binar.grab;

import com.binar.grab.controller.fileupload.FileStorageProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})

@OpenAPIDefinition
public class GrabApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrabApplication.class, args);
	}

}
