package br.unesp.grupo5.trabalhofinal.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Assinatura")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Assinatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idAssinatura;

    private boolean assinaturaAtiva = true;

    private LocalDateTime validaAte;

}
