package br.unesp.grupo5.trabalhofinal.resource;

import br.unesp.grupo5.trabalhofinal.entity.ConteudoFilme;
import br.unesp.grupo5.trabalhofinal.service.ConteudoFilmeService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conteudo/filme")
public class ConteudoFilmeResource {

    @Autowired
    private ConteudoFilmeService conteudoService;

    ModelMapper mapper = new ModelMapper();
    
    @PostConstruct
    public void init() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping("/")
    public List<ConteudoFilme> getAll() {
        return conteudoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteudoFilme> getOne(@PathVariable(value = "id") Long id) {
        ConteudoFilme conteudo = conteudoService.getOne(id);
        if (conteudo != null) {
            return ResponseEntity.ok(conteudo);
        }
        return ResponseEntity.status(404).body(null);
    }
}
