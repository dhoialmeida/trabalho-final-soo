package br.unesp.grupo5.trabalhofinal.resource;

import br.unesp.grupo5.trabalhofinal.service.UploadService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploaded")
public class DownloadResource {

    @Autowired
    private UploadService uploadService;

    @GetMapping("/serie/thumb/{name}")
    public ResponseEntity<FileSystemResource> getSerieThumb(@PathVariable(value = "name") String name) {
        FileSystemResource resource = uploadService.getSerieThumb(name);
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaTypeFactory.getMediaType(resource.getFilename()).orElse(MediaType.IMAGE_PNG))
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/conteudo/thumb/{name}")
    public ResponseEntity<FileSystemResource> getConteudoThumb(@PathVariable(value = "name") String name) {
        FileSystemResource resource = uploadService.getConteudoThumb(name);
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaTypeFactory.getMediaType(resource.getFilename()).orElse(MediaType.IMAGE_PNG))
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/conteudo/video/{name}")
    public ResponseEntity<FileSystemResource> getConteudoVideo(@PathVariable(value = "name") String name) {
        FileSystemResource resource = uploadService.getConteudoVideo(name);
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaTypeFactory.getMediaType(resource.getFilename()).orElse(MediaType.IMAGE_PNG))
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
