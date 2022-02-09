package com.indoxxii.indoxxii.presist.usecases;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import com.indoxxii.indoxxii.global.FileStorageConfiguration;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
public class PosterUsecase {

    private final Path posterFileLocation;
   
    @Autowired
    public PosterUsecase(FileStorageConfiguration fileStorageConfiguration) throws Exception {
        this.posterFileLocation = Paths.get(
                fileStorageConfiguration.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.posterFileLocation);
        } catch (Exception ex) {
            throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
    public String storeFile(MultipartFile file) throws Exception {
        String fileName = this.genereatePosterName(file);

        try {
            if (fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.posterFileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            Path filePath = this.posterFileLocation.resolve(fileName).normalize();
            Resource resource = (Resource) new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (Exception ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }

    private String genereatePosterName(MultipartFile file){
        String result="poster_"+(new Date()).getTime()+"_"+StringUtils.cleanPath(file.getOriginalFilename());
        return result;
    }
}
