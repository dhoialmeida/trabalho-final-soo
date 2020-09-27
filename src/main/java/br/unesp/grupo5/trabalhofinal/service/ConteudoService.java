package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Avaliacao;
import br.unesp.grupo5.trabalhofinal.entity.Comentario;
import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.repository.ConteudoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConteudoService {

    @Autowired
    private ConteudoRepository repository;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private AvaliacaoService avaliacaoService;

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

    public void delete(Conteudo t) {
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
