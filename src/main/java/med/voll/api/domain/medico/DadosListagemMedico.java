package med.voll.api.domain.medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    // Construtor adicional que recebe uma instância de Medico e extrai os dados
    public DadosListagemMedico(Medico medico) {
        // Chamada para o construtor principal com os dados extraídos do objeto Medico
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
