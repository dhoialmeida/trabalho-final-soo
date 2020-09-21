package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.ConteudoFilme;
import br.unesp.grupo5.trabalhofinal.repository.ConteudoFilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConteudoFilmeService {

    @Autowired
    private ConteudoFilmeRepository repository;

    public ConteudoFilmeService() {
    }

    public ConteudoFilme getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<ConteudoFilme> findByTituloLike(String titulo) {
        return repository.findByTituloLike(titulo);
    }

    public List<ConteudoFilme> findAll() {
        return repository.findAll();
    }

    public <S extends ConteudoFilme> S save(S s) {
        return repository.save(s);
    }

    public <S extends ConteudoFilme> S update(S s) {
        return repository.save(s);
    }

    public void delete(ConteudoFilme t) {
        repository.delete(t);
    }

}
