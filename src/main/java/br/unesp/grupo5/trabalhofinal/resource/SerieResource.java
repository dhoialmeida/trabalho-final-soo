package br.unesp.grupo5.trabalhofinal.resource;

import br.unesp.grupo5.trabalhofinal.dto.SerieDTO;
import br.unesp.grupo5.trabalhofinal.entity.Genero;
import br.unesp.grupo5.trabalhofinal.entity.Serie;
import br.unesp.grupo5.trabalhofinal.service.GeneroService;
import br.unesp.grupo5.trabalhofinal.service.SerieService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/serie")
public class SerieResource {

    @Autowired
    private SerieService serieService;

    @Autowired
    private GeneroService generoService;

    ModelMapper mapper = new ModelMapper();

    @PostConstruct
    public void init() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping("/")
    public List<Serie> getAll() {
        return serieService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Serie> getOne(@PathVariable(value = "id") Long id) {
        Serie serie = serieService.getOne(id);
        if (serie != null) {
            return ResponseEntity.ok(serie);
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Serie serie = serieService.getOne(id);
        if (serie != null) {
            serieService.delete(serie);
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(404).body(false);
    }

    @PostMapping("/")
    public ResponseEntity<Serie> save(@RequestBody SerieDTO serieDTO) {
        Serie serie = mapper.map(serieDTO, Serie.class);

        Genero genero = generoService.getOne(serieDTO.getIdGenero());
        if (genero == null) {
            return ResponseEntity.badRequest().body(null);
        }
        serie.setGenero(genero);

        serie = serieService.save(serie);
        return ResponseEntity.ok(serie);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Serie> update(@PathVariable(value = "id") Long id, @RequestBody SerieDTO serieDTO) {
        Serie serie = serieService.getOne(id);
        if (serie != null) {
            mapper.map(serieDTO, serie);
            Genero genero = generoService.getOne(serieDTO.getIdGenero());
            if (genero == null) {
                return ResponseEntity.badRequest().body(null);
            }
            serie.setGenero(genero);
            serie = serieService.update(serie);
            return ResponseEntity.ok(serie);
        }
        return ResponseEntity.status(404).body(null);
    }
}
