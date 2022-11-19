package com.bibliotecarest.api.dto;

import com.bibliotecarest.api.entity.Usuario;
import com.bibliotecarest.api.entity.Livro;
import com.bibliotecarest.api.entity.StatusLivro;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClassificacaoDTO {

    @NotNull
    private StatusLivro statusLivro;
    @NotNull
    @Valid
    private Livro livro;
    @NotNull
    @Valid
    private Usuario usuario;

}
