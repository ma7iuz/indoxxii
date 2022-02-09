package com.indoxxii.indoxxii.applications.controllers.v1;

import javax.servlet.http.HttpServletRequest;

import com.indoxxii.indoxxii.applications.response.GlobalResponse;
import com.indoxxii.indoxxii.applications.response.Response;
import com.indoxxii.indoxxii.global.Routes;
import com.indoxxii.indoxxii.presist.usecases.PosterUsecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PosterController {
    @Autowired
    private PosterUsecase useCase;

    private Response response = new Response();

    @PostMapping(Routes.API_V1+Routes.POSTER)
    public ResponseEntity<GlobalResponse>upload(@RequestParam("file") MultipartFile file) throws Exception{
        String fileName=useCase.storeFile(file);
        return response.buildV1("Success upload file "+fileName);
    }

    @GetMapping(Routes.API_V1+Routes.POSTER+"/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {
        Resource resource = useCase.loadFileAsResource(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception ex) {
            throw new Exception("Could not determine file type");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
