package br.unesp.grupo5.trabalhofinal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Comentario")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idComentario;

    private String comentario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime data;

    @ManyToOne()
    @JoinColumn(name = "usuario_idUsuario")
    private Usuario usuario;

    @ManyToOne()
    @JoinColumn(name = "conteudo_idConteudo")
    private Conteudo conteudo;
}
