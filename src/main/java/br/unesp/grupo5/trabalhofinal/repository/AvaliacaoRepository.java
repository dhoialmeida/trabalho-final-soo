package br.unesp.grupo5.trabalhofinal.repository;

import br.unesp.grupo5.trabalhofinal.entity.Avaliacao;
import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findByUsuario(Usuario usuario);
    Avaliacao findByUsuarioAndConteudo(Usuario usuario, Conteudo conteudo);
    List<Avaliacao> findByConteudo(Conteudo conteudo);
    
    long deleteByUsuario(Usuario usuario);
    long deleteByConteudo(Conteudo conteudo);
    
    @Query("SELECT DISTINCT a.conteudo from Avaliacao a WHERE a.nota = 5 and a.usuario IN (SELECT b.usuario from Avaliacao b where b.nota=?1 and b.conteudo = ?2) and a.conteudo != ?2")
    List<Conteudo> getRecommendations(int nota, Conteudo conteudo);
}
