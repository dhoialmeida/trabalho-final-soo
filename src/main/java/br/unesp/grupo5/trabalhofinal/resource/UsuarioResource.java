package br.unesp.grupo5.trabalhofinal.resource;

import br.unesp.grupo5.trabalhofinal.dto.UsuarioDTO;
import br.unesp.grupo5.trabalhofinal.entity.Assinatura;
import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import br.unesp.grupo5.trabalhofinal.service.UsuarioService;
import java.time.LocalDateTime;
import java.util.List;
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

    @GetMapping("/")
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Usuario> getByCpf(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioService.getOne(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioService.getOne(id);
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
        LocalDateTime now = LocalDateTime.now();
        usuario.setAssinatura(new Assinatura(true, now.plusDays(31)));
        usuario = usuarioService.save(usuario);
        return usuario;
    }
    
    @PatchMapping("/ativar/{id}")
    public ResponseEntity<Usuario> ativarAssinatura(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioService.getOne(id);
        if (usuario != null) {
            usuario.getAssinatura().setAtiva(true);
            usuarioService.save(usuario);
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(404).body(null);
    }
    
    @PatchMapping("/desativar/{id}")
    public ResponseEntity<Usuario> desativarAssinatura(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioService.getOne(id);
        if (usuario != null) {
            usuario.getAssinatura().setAtiva(false);
            usuarioService.save(usuario);
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable(value = "id") Long id, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.getOne(id);
        if (usuario != null) {
            String old = usuario.getSenha();
            mapper.map(usuarioDTO, usuario);
            if (usuarioDTO.getSenha().isBlank()) {
                usuario.setSenha(old);
            } else {
                usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
            }

            usuario = usuarioService.update(usuario);
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(404).body(null);
    }
}
