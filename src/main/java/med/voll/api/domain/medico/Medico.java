package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

// Anotações para mapeamento da entidade no banco de dados
@Table(name = "medicos")
@Entity(name = "Medico")
// Anotação Lombok para gerar automaticamente métodos getters, construtores e equals/hashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    // Anotações para mapeamento de colunas no banco de dados
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    // Anotação para mapeamento de enum como string no banco de dados
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    // Anotação para mapeamento de um objeto embutido no banco de dados
    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    // Construtor que recebe dados de cadastro e inicializa a entidade
    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    // Método para atualizar informações do médico com base em dados de atualização
    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
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

    // Método para marcar o médico como inativo (exclusão lógica)
    public void excluir() {
        this.ativo = false;
    }
}