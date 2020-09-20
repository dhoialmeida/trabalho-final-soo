package br.unesp.grupo5.trabalhofinal.service;

import br.unesp.grupo5.trabalhofinal.entity.Avaliacao;
import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import br.unesp.grupo5.trabalhofinal.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository repository;

    public AvaliacaoService() {
    }

    public Avaliacao findByUsuario(Usuario usuario) {
        return repository.findByUsuario(usuario);
    }

    public Avaliacao findByUsuarioAndConteudo(Usuario usuario, Conteudo conteudo) {
        return repository.findByUsuarioAndConteudo(usuario, conteudo);
    }

    public Avaliacao getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public <S extends Avaliacao> S save(S s) {
        return repository.save(s);
    }

    public <S extends Avaliacao> S update(S s) {
        return repository.save(s);
    }

    public void delete(Avaliacao t) {
        repository.delete(t);
    }

}
