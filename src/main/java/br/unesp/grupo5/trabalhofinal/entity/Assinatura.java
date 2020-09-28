package br.unesp.grupo5.trabalhofinal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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

    private boolean ativa;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime validaAte;

    public Assinatura(boolean ativa, LocalDateTime validaAte) {
        this.ativa = ativa;
        this.validaAte = validaAte;
    }

    public Assinatura() {
    }
}
