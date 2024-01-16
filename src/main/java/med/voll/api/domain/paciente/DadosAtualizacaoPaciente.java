package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        // NotNull: Anotação indicando que o campo não pode ser nulo
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}