package enzo.tabuchi.api.domain.paciente;

import enzo.tabuchi.api.domain.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPaciente (
        @NotBlank
        boolean ativo,

        @NotBlank
        String nome,

        @Email
        @NotBlank
        String email,

        @NotNull
        String telefone,

        @NotBlank
        String cpf,

        @NotNull
        @Valid
        Endereco endereco
    ) {
}
