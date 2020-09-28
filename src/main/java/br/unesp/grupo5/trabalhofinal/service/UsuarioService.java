package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import br.unesp.grupo5.trabalhofinal.repository.UsuarioRepository;
import br.unesp.grupo5.trabalhofinal.security.AuthUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    public UsuarioService() {
    }

    public Usuario findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }
    
    @Override
    public UserDetails loadUserByUsername(String name) {
        Usuario usuario = findByEmail(name);
        if (usuario != null) {
            ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
            if (usuario.isFuncionario()) {
                roles.add(new SimpleGrantedAuthority("funcionario"));
            } else {
                roles.add(new SimpleGrantedAuthority("usuario"));
            }
            return new AuthUser(name, usuario.getSenha(), roles, usuario.getIdUsuario());
        }

        throw new UsernameNotFoundException(name);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public <S extends Usuario> S save(S s) {
        return repository.save(s);
    }

    public <S extends Usuario> S update(S s) {
        return repository.save(s);
    }

    @Transactional
    public void delete(Usuario t) {
        comentarioService.deleteByUsuario(t);
        avaliacaoService.deleteByUsuario(t);
        repository.delete(t);
    }

}
