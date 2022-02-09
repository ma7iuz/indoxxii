package com.indoxxii.indoxxii;

import com.indoxxii.indoxxii.global.FileStorageConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageConfiguration.class
})
public class IndoxxiiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndoxxiiApplication.class, args);
	}

}
