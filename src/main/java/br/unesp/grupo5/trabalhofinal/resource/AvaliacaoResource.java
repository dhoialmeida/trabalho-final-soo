package br.unesp.grupo5.trabalhofinal.resource;

import br.unesp.grupo5.trabalhofinal.dto.AvaliacaoDTO;
import br.unesp.grupo5.trabalhofinal.entity.Avaliacao;
import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import br.unesp.grupo5.trabalhofinal.service.AvaliacaoService;
import br.unesp.grupo5.trabalhofinal.service.ConteudoService;
import br.unesp.grupo5.trabalhofinal.service.UsuarioService;
import java.time.LocalDateTime;
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
@RequestMapping("/api/avaliacao")
public class AvaliacaoResource {

    @Autowired
    private AvaliacaoService avaliacaoService;

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
    public ResponseEntity<Avaliacao> getOne(@PathVariable(value = "id") Long id) {
        Avaliacao avaliacao = avaliacaoService.getOne(id);
        if (avaliacao != null) {
            return ResponseEntity.ok(avaliacao);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/{user}/{content}")
    public ResponseEntity<Avaliacao> findByUsuarioAndConteudo(@PathVariable(value = "user") Long user, @PathVariable(value = "content") Long content) {
        Usuario usuario = usuarioService.getOne(user);
        Conteudo conteudo = conteudoService.getOne(content);
        if (usuario != null && conteudo != null) {
            Avaliacao avaliacao = avaliacaoService.findByUsuarioAndConteudo(usuario, conteudo);
            if (avaliacao != null) {
                return ResponseEntity.ok(avaliacao);
            }
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Avaliacao avaliacao = avaliacaoService.getOne(id);
        if (avaliacao != null) {
            avaliacaoService.delete(avaliacao);
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(404).body(false);
    }

    @PostMapping("/")
    public ResponseEntity<Avaliacao> save(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        Usuario usuario = usuarioService.getOne(avaliacaoDTO.getIdUsuario());
        if (usuario == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Conteudo conteudo = conteudoService.getOne(avaliacaoDTO.getIdConteudo());
        if (conteudo == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Avaliacao avaliacao = new Avaliacao();
        mapper.map(avaliacaoDTO, avaliacao);
        avaliacao.setConteudo(conteudo);
        avaliacao.setUsuario(usuario);
        avaliacao.setData(LocalDateTime.now());
        avaliacao = avaliacaoService.save(avaliacao);
        return ResponseEntity.ok(avaliacao);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Avaliacao> update(@PathVariable(value = "id") Long id, @RequestBody AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacao = avaliacaoService.getOne(id);
        if (avaliacao != null) {
            mapper.map(avaliacaoDTO, avaliacao);
            avaliacao = avaliacaoService.update(avaliacao);
            return ResponseEntity.ok(avaliacao);
        }
        return ResponseEntity.status(404).body(null);
    }
}
