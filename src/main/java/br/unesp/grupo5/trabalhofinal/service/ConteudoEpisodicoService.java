package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.ConteudoEpisodico;
import br.unesp.grupo5.trabalhofinal.entity.Serie;
import br.unesp.grupo5.trabalhofinal.repository.ConteudoEpisodicoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ConteudoEpisodicoService {

    @Autowired
    private ConteudoEpisodicoRepository repository;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private UploadService uploadService;

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

    @Transactional
    public void delete(ConteudoEpisodico t) {
        comentarioService.deleteByConteudo(t);
        avaliacaoService.deleteByConteudo(t);

        uploadService.deleteConteudoThumb(t.getThumbnailFile());
        uploadService.deleteConteudoVideo(t.getVideoFile());

        repository.delete(t);
    }
}
