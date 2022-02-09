package com.indoxxii.indoxxii.global;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "file")
@Getter
@Setter
public class FileStorageConfiguration {
    private String uploadDir;

    
}
