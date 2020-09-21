package br.unesp.grupo5.trabalhofinal.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Serie")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Serie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idSerie;

    private String titulo;
    private String descricao;

    private int numeroTemporadas;

    @ManyToOne()
    @JoinColumn(name = "genero_idGenero")
    private Genero genero;

    private int duracaoMediaEpisodios;

    public Serie() {
    }
}
