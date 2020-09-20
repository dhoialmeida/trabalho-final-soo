package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Serie;
import br.unesp.grupo5.trabalhofinal.repository.SerieRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public SerieService() {
    }

    public List<Serie> findByTituloLike(String titulo) {
        return repository.findByTituloLike(titulo);
    }

    public List<Serie> findAll() {
        return repository.findAll();
    }

    public Serie getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public <S extends Serie> S save(S s) {
        return repository.save(s);
    }

    public <S extends Serie> S update(S s) {
        return repository.save(s);
    }

    public void delete(Serie t) {
        repository.delete(t);
    }

}
