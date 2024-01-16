package med.voll.api.domain.paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String cpf) {

    // Construtor adicional que recebe uma instância de Paciente e extrai os dados
    public DadosListagemPaciente(Paciente paciente) {
        // Chamada para o construtor principal com os dados extraídos do objeto Paciente
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}