package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import br.unesp.grupo5.trabalhofinal.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioService() {
    }

    public Usuario findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
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

    public void delete(Usuario t) {
        repository.delete(t);
    }

}
