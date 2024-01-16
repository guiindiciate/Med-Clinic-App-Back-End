package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Embeddable: Anotação indicando que esta classe pode ser incorporada em outra entidade
@Embeddable
// Todas as 3 abaixo: Anotação Lombok para gerar automaticamente construtores com e sem argumentos, e métodos getters
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    // Atributos representando informações de endereço
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    // Construtor que recebe dados de endereço e inicializa os atributos
    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    // Método para atualizar informações do endereço com base em novos dados
    public void atualizarInformacoes(DadosEndereco dados) {
        // Verifica se os novos dados são diferentes de null e atualiza os atributos correspondentes
        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
    }
}