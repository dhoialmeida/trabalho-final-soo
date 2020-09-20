package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Genero;
import br.unesp.grupo5.trabalhofinal.repository.GeneroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneroService {

    @Autowired
    private GeneroRepository repository;

    public GeneroService() {
    }

    public List<Genero> findAll() {
        return repository.findAll();
    }

    public Genero getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public <S extends Genero> S save(S s) {
        return repository.save(s);
    }

    public <S extends Genero> S update(S s) {
        return repository.save(s);
    }

    public void delete(Genero t) {
        repository.delete(t);
    }
}
