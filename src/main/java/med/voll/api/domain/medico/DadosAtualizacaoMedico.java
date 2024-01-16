package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        // NotNull: Anotação indicando que o campo não pode ser nulo
        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
