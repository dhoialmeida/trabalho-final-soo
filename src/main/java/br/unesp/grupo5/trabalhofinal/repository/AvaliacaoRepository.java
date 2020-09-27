package br.unesp.grupo5.trabalhofinal.repository;

import br.unesp.grupo5.trabalhofinal.entity.Avaliacao;
import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByUsuario(Usuario usuario);
    Avaliacao findByUsuarioAndConteudo(Usuario usuario, Conteudo conteudo);
    List<Avaliacao> findByConteudo(Conteudo conteudo);
}
