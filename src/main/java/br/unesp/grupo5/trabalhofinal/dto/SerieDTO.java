package br.unesp.grupo5.trabalhofinal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SerieDTO {
    private String titulo;
    private String descricao;
    private int numeroTemporadas;
    private Long idGenero;
    private int duracaoMediaEpisodios;
}
