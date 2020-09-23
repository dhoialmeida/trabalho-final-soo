package br.unesp.grupo5.trabalhofinal.resource;

import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.Serie;
import br.unesp.grupo5.trabalhofinal.service.ConteudoService;
import br.unesp.grupo5.trabalhofinal.service.SerieService;
import br.unesp.grupo5.trabalhofinal.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadResource {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private SerieService serieService;

    @Autowired
    private ConteudoService conteudoService;

    @PostMapping("/serie/thumb/{id}")
    public ResponseEntity<Serie> saveSerieThumb(@PathVariable(value = "id") Long id, @RequestParam("file") MultipartFile thumb) {
        Serie serie = serieService.getOne(id);
        if (serie != null) {
            uploadService.saveThumb(serie, thumb);
            serie = serieService.update(serie);
            return ResponseEntity.ok(serie);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/conteudo/thumb/{id}")
    public ResponseEntity<Conteudo> saveConteudoThumb(@PathVariable(value = "id") Long id, @RequestParam("file") MultipartFile thumb) {
        Conteudo conteudo = conteudoService.getOne(id);
        if (conteudo != null) {
            uploadService.saveThumb(conteudo, thumb);
            conteudo = conteudoService.update(conteudo);
            return ResponseEntity.ok(conteudo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/conteudo/video/{id}")
    public ResponseEntity<Conteudo> saveVideo(@PathVariable(value = "id") Long id, @RequestParam("file") MultipartFile thumb) {
        Conteudo conteudo = conteudoService.getOne(id);
        if (conteudo != null) {
            uploadService.saveVideo(conteudo, thumb);
            conteudo = conteudoService.update(conteudo);
            return ResponseEntity.ok(conteudo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
