package br.unesp.grupo5.trabalhofinal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComentarioDTO {
    private long idUsuario;
    private long idConteudo;
    private String comentario;
}
