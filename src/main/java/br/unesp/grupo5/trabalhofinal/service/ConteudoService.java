package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Avaliacao;
import br.unesp.grupo5.trabalhofinal.entity.Comentario;
import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.repository.ConteudoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ConteudoService {

    @Autowired
    private ConteudoRepository repository;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private AvaliacaoService avaliacaoService;
    
    @Autowired
    private UploadService uploadService;

    public ConteudoService() {
    }

    public Conteudo getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Conteudo> findByTituloLike(String titulo) {
        return repository.findByTituloLike(titulo);
    }

    public List<Conteudo> findAll() {
        return repository.findAll();
    }

    public <S extends Conteudo> S save(S s) {
        return repository.save(s);
    }

    public <S extends Conteudo> S update(S s) {
        return repository.save(s);
    }

    @Transactional
    public void delete(Conteudo t) {
        comentarioService.deleteByConteudo(t);
        avaliacaoService.deleteByConteudo(t);
        
        uploadService.deleteConteudoThumb(t.getThumbnailFile());
        uploadService.deleteConteudoVideo(t.getVideoFile());
        repository.delete(t);
    }

}
