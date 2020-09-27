package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Avaliacao;
import br.unesp.grupo5.trabalhofinal.entity.Comentario;
import br.unesp.grupo5.trabalhofinal.entity.ConteudoEpisodico;
import br.unesp.grupo5.trabalhofinal.entity.Serie;
import br.unesp.grupo5.trabalhofinal.repository.ConteudoEpisodicoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConteudoEpisodicoService {

    @Autowired
    private ConteudoEpisodicoRepository repository;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    public ConteudoEpisodicoService() {
    }

    public ConteudoEpisodico getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<ConteudoEpisodico> findBySerie(Serie serie) {
        return repository.findBySerieOrderByTemporadaAscEpisodioAsc(serie);
    }

    public List<ConteudoEpisodico> findAll() {
        return repository.findAll();
    }

    public <S extends ConteudoEpisodico> S save(S s) {
        return repository.save(s);
    }

    public <S extends ConteudoEpisodico> S update(S s) {
        return repository.save(s);
    }

    public void delete(ConteudoEpisodico t) {
        List<Comentario> comentarios = comentarioService.findByConteudo(t);
        List<Avaliacao> avaliacoes = avaliacaoService.findByConteudo(t);

        for (Comentario c : comentarios) {
            comentarioService.delete(c);
        }

        for (Avaliacao a : avaliacoes) {
            avaliacaoService.delete(a);
        }
        
        repository.delete(t);
    }
}
