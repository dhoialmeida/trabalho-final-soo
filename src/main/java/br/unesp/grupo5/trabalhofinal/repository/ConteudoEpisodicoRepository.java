package br.unesp.grupo5.trabalhofinal.repository;

import br.unesp.grupo5.trabalhofinal.entity.ConteudoEpisodico;
import br.unesp.grupo5.trabalhofinal.entity.Serie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConteudoEpisodicoRepository extends JpaRepository<ConteudoEpisodico, Long> {
    List<ConteudoEpisodico> findBySerieOrderByTemporadaAscEpisodioAsc(Serie serie);
}
