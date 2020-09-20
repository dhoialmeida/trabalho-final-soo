package br.unesp.grupo5.trabalhofinal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AvaliacaoDTO {
    private long idUsuario;
    private long idConteudo;
    private int nota;
    private String comentario;
}
