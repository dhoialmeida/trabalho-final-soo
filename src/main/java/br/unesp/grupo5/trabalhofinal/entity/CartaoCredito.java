package br.unesp.grupo5.trabalhofinal.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "CartaoCredito")
@Table(
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "numero", name = "numero_unico"),}
)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CartaoCredito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCartao;

    @Column(unique = true)
    private String numero;

    private String titular;

    private String expiracao;

    private String CVV;

    private String bandeira;
}
