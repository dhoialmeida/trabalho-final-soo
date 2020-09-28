package br.unesp.grupo5.trabalhofinal.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class ConteudoEpisodico extends Conteudo {

    private int temporada;

    private int episodio;

    @ManyToOne()
    @JoinColumn(name = "serie_idSerie")
    private Serie serie;

    public ConteudoEpisodico() {
    }

}
