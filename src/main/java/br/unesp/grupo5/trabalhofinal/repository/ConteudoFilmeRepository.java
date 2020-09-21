package br.unesp.grupo5.trabalhofinal.repository;

import br.unesp.grupo5.trabalhofinal.entity.ConteudoFilme;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConteudoFilmeRepository extends JpaRepository<ConteudoFilme, Long> {
    List<ConteudoFilme> findByTituloLike(String titulo);
}
