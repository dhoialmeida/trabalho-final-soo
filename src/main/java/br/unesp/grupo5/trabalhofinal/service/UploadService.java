package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.Serie;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;

@Component
@Getter
public class UploadService {

    Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads");
    Path seriesThumb = Paths.get(uploadPath.toString(), "serie-thumb");
    Path conteudoThumb = Paths.get(uploadPath.toString(), "conteudo-thumb");
    Path conteudoVideo = Paths.get(uploadPath.toString(), "conteudo-video");

    @Autowired
    private ConteudoService conteudoService;

    @Autowired
    private SerieService serieService;

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(uploadPath);
            Files.createDirectories(seriesThumb);
            Files.createDirectories(conteudoThumb);
            Files.createDirectories(conteudoVideo);
        } catch (IOException ex) {

        }
    }

    public void saveFile(MultipartFile file, String dir, String name) {
        try {
            Path copyLocation = Paths.get(dir, name);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public FileSystemResource readFile(String dir, String name) {
        Path location = Paths.get(dir, name);
        return new FileSystemResource(location);
    }

    public String getNewFilename(long id, String oldname) {
        return String.valueOf(id) + "." + FilenameUtils.getExtension(oldname);
    }

    public void saveThumb(Serie serie, MultipartFile thumb) {
        String name = getNewFilename(serie.getIdSerie(), thumb.getOriginalFilename());
        saveFile(thumb, seriesThumb.toString(), name);
        serie.setThumbnailFile(name);
    }

    public void saveThumb(Conteudo conteudo, MultipartFile thumb) {
        String name = getNewFilename(conteudo.getIdConteudo(), thumb.getOriginalFilename());
        saveFile(thumb, conteudoThumb.toString(), name);
        conteudo.setThumbnailFile(name);
    }

    public void saveVideo(Conteudo conteudo, MultipartFile thumb) {
        String name = getNewFilename(conteudo.getIdConteudo(), thumb.getOriginalFilename());
        saveFile(thumb, conteudoVideo.toString(), name);
        conteudo.setVideoFile(name);
    }

    public FileSystemResource getSerieThumb(String name) {
        return readFile(seriesThumb.toString(), name);
    }

    public FileSystemResource getConteudoThumb(String name) {
        return readFile(conteudoThumb.toString(), name);
    }

    public FileSystemResource getConteudoVideo(String name) {
        return readFile(conteudoVideo.toString(), name);
    }
    
    public void deleteFile(String dir, String name) {
        Path location = Paths.get(dir, name);
        try {
            Files.delete(location);
        } catch (IOException ex) {
            System.out.println("Arquivo nao encontrado: " + location.toString());
        }
    }
    
    public void deleteSerieThumb(String name) {
        deleteFile(seriesThumb.toString(), name);
    }

    public void deleteConteudoThumb(String name) {
        deleteFile(conteudoThumb.toString(), name);
    }

    public void deleteConteudoVideo(String name) {
        deleteFile(conteudoVideo.toString(), name);
    }
}
