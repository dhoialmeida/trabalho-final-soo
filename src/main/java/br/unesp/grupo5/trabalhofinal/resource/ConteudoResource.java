package br.unesp.grupo5.trabalhofinal.resource;

import br.unesp.grupo5.trabalhofinal.dto.ConteudoDTO;
import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.ConteudoEpisodico;
import br.unesp.grupo5.trabalhofinal.entity.ConteudoFilme;
import br.unesp.grupo5.trabalhofinal.entity.Genero;
import br.unesp.grupo5.trabalhofinal.entity.Serie;
import br.unesp.grupo5.trabalhofinal.service.ConteudoService;
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
@RequestMapping("/api/conteudo")
public class ConteudoResource {

    @Autowired
    private ConteudoService conteudoService;

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
    public List<Conteudo> getAll() {
        return conteudoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conteudo> getOne(@PathVariable(value = "id") Long id) {
        Conteudo conteudo = conteudoService.getOne(id);
        if (conteudo != null) {
            return ResponseEntity.ok(conteudo);
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Conteudo conteudo = conteudoService.getOne(id);
        if (conteudo != null) {
            conteudoService.delete(conteudo);
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(404).body(false);
    }

    @PostMapping("/")
    public ResponseEntity<Conteudo> save(@RequestBody ConteudoDTO conteudoDTO) {
        return doSave(conteudoDTO, null);
    }

    private ResponseEntity<Conteudo> doSave(ConteudoDTO conteudoDTO, Conteudo conteudo) {
        if (conteudoDTO.isEpisodico()) {
            if (conteudo == null) {
                conteudo = new ConteudoEpisodico();
            }
            Serie serie = serieService.getOne(conteudoDTO.getIdSerie());
            if (serie == null) {
                return ResponseEntity.badRequest().body(null);
            }
            ((ConteudoEpisodico) conteudo).setSerie(serie);
        } else {
            if (conteudo == null) {
                conteudo = new ConteudoFilme();
            }
        }
        mapper.map(conteudoDTO, conteudo);
        Genero genero = generoService.getOne(conteudoDTO.getIdGenero());
        if (genero == null) {
            return ResponseEntity.badRequest().body(null);
        }
        conteudo.setGenero(genero);
        conteudo = conteudoService.save(conteudo);
        return ResponseEntity.ok(conteudo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Conteudo> update(@PathVariable(value = "id") Long id, @RequestBody ConteudoDTO conteudoDTO) {
        Conteudo conteudo = conteudoService.getOne(id);
        if (conteudo != null) {
            return doSave(conteudoDTO, conteudo);
        }
        return ResponseEntity.status(404).body(null);
    }
}
