package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(

        // NotBlank: Anotação indicando que o campo não pode ser em branco
        // Pattern: Anotação indicando que deve seguir o padrão especificado
        // Valid: Anotação indicando que o campo deve ser validado
        // NotNull: Anotação indicando que o campo não pode ser nulo
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,

        @NotNull @Valid DadosEndereco endereco) {
}