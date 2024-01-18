package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

// Declaração da classe do controlador para a entidade "Paciente"
@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    // Injeção de dependência do repositório de pacientes
    @Autowired
    private PacienteRepository repository;

    // Endpoint para cadastrar um novo paciente
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
        // Criação de uma nova instância de Paciente com base nos dados fornecidos e salvamento no repositório
        repository.save(new Paciente(dados));
    }

    // Endpoint para listar pacientes paginados
    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        // Recuperação da página de pacientes ativos do repositório e mapeamento para dados de listagem
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    // Endpoint para atualizar informações de um paciente
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        // Obtenção de uma referência ao paciente pelo ID sem carregar todos os detalhes
        var paciente = repository.getReferenceById(dados.id());
        // Atualização das informações do paciente com base nos dados fornecidos
        paciente.atualizarInformacoes(dados);
    }

    // Endpoint para excluir um paciente pelo ID
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        // Obtenção de uma referência ao paciente pelo ID sem carregar todos os detalhes
        var paciente = repository.getReferenceById(id);
        // Exclusão do paciente (marcação como inativo, por exemplo)
        paciente.excluir();
    }
}