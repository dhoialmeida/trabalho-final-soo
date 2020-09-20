package br.unesp.grupo5.trabalhofinal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.Serializable;
import java.time.LocalDate;
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

@Entity(name = "Usuario")
@Table(
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "cpf", name = "cpf_unico"),
            @UniqueConstraint(columnNames = "email", name = "email_unico"),}
)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPessoa;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @Column(unique = true)
    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate nascimento;

    private boolean eFuncionario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_idEndereco")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assinatura_idAssinatura")
    private Assinatura assinatura;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartaoCredito_idCartaoCredito")
    private CartaoCredito cartaoCredito;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String cpf, LocalDate nascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.eFuncionario = false;
        this.endereco = null;
        this.assinatura = new Assinatura();
        this.cartaoCredito = null;
    }
}
