package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    // Construtor adicional que recebe uma instância de Medico e extrai os dados
    public DadosDetalhamentoMedico(Medico medico) {
        // Chamada para o construtor principal com os dados extraídos do objeto Medico
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
