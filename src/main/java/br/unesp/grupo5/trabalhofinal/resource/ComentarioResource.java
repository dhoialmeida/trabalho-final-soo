package br.unesp.grupo5.trabalhofinal.resource;

import br.unesp.grupo5.trabalhofinal.dto.ComentarioDTO;
import br.unesp.grupo5.trabalhofinal.entity.Comentario;
import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import br.unesp.grupo5.trabalhofinal.service.ComentarioService;
import br.unesp.grupo5.trabalhofinal.service.ConteudoService;
import br.unesp.grupo5.trabalhofinal.service.UsuarioService;
import java.time.LocalDateTime;
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
@RequestMapping("/api/comentario")
public class ComentarioResource {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ConteudoService conteudoService;

    ModelMapper mapper = new ModelMapper();
    
    @PostConstruct
    public void init() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getOne(@PathVariable(value = "id") Long id) {
        Comentario comentario = comentarioService.getOne(id);
        if (comentario != null) {
            return ResponseEntity.ok(comentario);
        }
        return ResponseEntity.status(404).body(null);
    }
    
    @GetMapping("/on-{id}")
    public ResponseEntity<List<Comentario>> findByConteudo(@PathVariable(value = "id") Long id) {
        Conteudo conteudo = conteudoService.getOne(id);
        if (conteudo != null) {
            List<Comentario> comentario = comentarioService.findByConteudo(conteudo);
            if (comentario != null) {
                return ResponseEntity.ok(comentario);
            }
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Comentario comentario = comentarioService.getOne(id);
        if (comentario != null) {
            comentarioService.delete(comentario);
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(404).body(false);
    }

    @PostMapping("/")
    public ResponseEntity<Comentario> save(@RequestBody ComentarioDTO comentarioDTO) {
        Usuario usuario = usuarioService.getOne(comentarioDTO.getIdUsuario());
        if (usuario == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Conteudo conteudo = conteudoService.getOne(comentarioDTO.getIdConteudo());
        if (conteudo == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Comentario comentario = new Comentario();
        mapper.map(comentarioDTO, comentario);
        comentario.setConteudo(conteudo);
        comentario.setUsuario(usuario);
        comentario.setData(LocalDateTime.now());
        comentario = comentarioService.save(comentario);
        return ResponseEntity.ok(comentario);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comentario> update(@PathVariable(value = "id") Long id, @RequestBody ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioService.getOne(id);
        if (comentario != null) {
            mapper.map(comentarioDTO, comentario);
            comentario = comentarioService.update(comentario);
            return ResponseEntity.ok(comentario);
        }
        return ResponseEntity.status(404).body(null);
    }
}
