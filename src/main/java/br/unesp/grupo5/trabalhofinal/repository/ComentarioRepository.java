package br.unesp.grupo5.trabalhofinal.repository;

import br.unesp.grupo5.trabalhofinal.entity.Comentario;
import br.unesp.grupo5.trabalhofinal.entity.Conteudo;
import br.unesp.grupo5.trabalhofinal.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByUsuario(Usuario usuario);
    List<Comentario> findByConteudo(Conteudo conteudo);
    
    long deleteByUsuario(Usuario usuario);
    long deleteByConteudo(Conteudo conteudo);
}
