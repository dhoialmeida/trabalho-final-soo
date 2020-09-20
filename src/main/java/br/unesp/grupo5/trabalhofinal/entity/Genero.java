package br.unesp.grupo5.trabalhofinal.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Genero")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idGenero;

    private String nome;
}
