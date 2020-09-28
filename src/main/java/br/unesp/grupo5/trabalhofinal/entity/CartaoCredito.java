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

@Entity(name = "CartaoCredito")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CartaoCredito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCartao;

    private String numero;

    private String titular;

    private String expiracao;

    private String CVV;

    private String bandeira;
}
