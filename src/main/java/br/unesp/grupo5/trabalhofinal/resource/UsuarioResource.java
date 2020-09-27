package br.unesp.grupo5.trabalhofinal.resource;

import br.unesp.grupo5.trabalhofinal.dto.CredenciaisDTO;
import br.unesp.grupo5.trabalhofinal.dto.UsuarioDTO;
import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import br.unesp.grupo5.trabalhofinal.service.UsuarioService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    ModelMapper mapper = new ModelMapper();
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @PostConstruct
    public void init() {
        // mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping("/")
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> getByCpf(@PathVariable(value = "cpf") String cpf) {
        Usuario usuario = usuarioService.findByCpf(cpf);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "cpf") String cpf) {
        Usuario usuario = usuarioService.findByCpf(cpf);
        if (usuario != null) {
            usuarioService.delete(usuario);
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.status(404).body(false);
    }

    @PostMapping("/")
    public Usuario save(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        usuario = usuarioService.save(usuario);
        return usuario;
    }
    
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody CredenciaisDTO credenciaisDTO) {
        Usuario usuario = usuarioService.findByEmail(credenciaisDTO.getEmail());
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PatchMapping("/{cpf}")
    public ResponseEntity<Usuario> update(@PathVariable(value = "cpf") String cpf, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.findByCpf(cpf);
        if (usuario != null) {
            mapper.map(usuarioDTO, usuario);
            usuario = usuarioService.update(usuario);
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(404).body(null);
    }
}
