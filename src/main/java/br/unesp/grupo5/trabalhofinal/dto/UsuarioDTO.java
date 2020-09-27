package br.unesp.grupo5.trabalhofinal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private String novaSenha;
    private boolean alterarSenha;
    private String cpf;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate nascimento;

    private String enderecoRua;
    private String enderecoNumero;
    private String enderecoBairro;
    private String enderecoCep;
    private String enderecoCidade;
    private String enderecoEstado;

    private String cartaoCreditoNumero;
    private String cartaoCreditoTitular;
    private String cartaoCreditoExpiracao;
    private String cartaoCreditoCVV;
    private String cartaoCreditoBandeira;
}
