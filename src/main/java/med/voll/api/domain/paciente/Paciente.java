package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

// Anotações para mapeamento da entidade no banco de dados
@Table(name = "pacientes")
@Entity(name = "Paciente")
// Anotação Lombok para gerar automaticamente métodos getters, construtores e equals/hashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    // Anotações para mapeamento de colunas no banco de dados
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    // Anotação para mapeamento de um objeto embutido no banco de dados
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    // Construtor que recebe dados de cadastro e inicializa a entidade
    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    // Método para atualizar informações do paciente com base em dados de atualização
    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    // Método para marcar o paciente como inativo (exclusão lógica)
    public void excluir() {
        this.ativo = false;
    }
}