package br.unesp.grupo5.trabalhofinal.resource;

import br.unesp.grupo5.trabalhofinal.dto.GeneroDTO;
import br.unesp.grupo5.trabalhofinal.entity.Genero;
import br.unesp.grupo5.trabalhofinal.service.GeneroService;
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
@RequestMapping("/api/genero")
public class GeneroResource {

    @Autowired
    private GeneroService generoService;

    ModelMapper mapper = new ModelMapper();
    
    @PostConstruct
    public void init() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping("/")
    public List<Genero> getAll() {
        return generoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> getOne(@PathVariable(value = "id") Long id) {
        Genero genero = generoService.getOne(id);
        if (genero != null) {
            return ResponseEntity.ok(genero);
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Genero genero = generoService.getOne(id);
        if (genero != null) {
            generoService.delete(genero);
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(404).body(false);
    }

    @PostMapping("/")
    public Genero save(@RequestBody GeneroDTO generoDTO) {
        Genero genero = generoService.save(mapper.map(generoDTO, Genero.class));
        return genero;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Genero> update(@PathVariable(value = "id") Long id, @RequestBody GeneroDTO generoDTO) {
        Genero genero = generoService.getOne(id);
        if (genero != null) {
            mapper.map(generoDTO, genero);
            genero = generoService.update(genero);
            return ResponseEntity.ok(genero);
        }
        return ResponseEntity.status(404).body(null);
    }
}
