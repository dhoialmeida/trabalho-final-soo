package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Comentario;
import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import br.unesp.grupo5.trabalhofinal.repository.ComentarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    public ComentarioService() {
    }

    public List<Comentario> findByUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    public List<Comentario> findByConteudo(Conteudo conteudo) {
        return repository.findByConteudo(conteudo);
    }

    public List<Comentario> findAll() {
        return repository.findAll();
    }

    public Comentario getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public <S extends Comentario> S save(S s) {
        return repository.save(s);
    }

    public <S extends Comentario> S update(S s) {
        return repository.save(s);
    }

    public void delete(Comentario t) {
        repository.delete(t);
    }

    @Transactional
    public long deleteByUsuario(Usuario usuario) {
        return repository.deleteByUsuario(usuario);
    }

    @Transactional
    public long deleteByConteudo(Conteudo conteudo) {
        return repository.deleteByConteudo(conteudo);
    }
}
