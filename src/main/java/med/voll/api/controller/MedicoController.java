package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

// Declaração da classe do controlador para a entidade "Medico"
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    // Injeção de dependência do repositório de médicos
    @Autowired
    private MedicoRepository repository;

    // Endpoint para cadastrar um novo médico
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        // Criação de uma nova instância de Medico com base nos dados fornecidos
        var medico = new Medico(dados);
        // Salvamento do médico no repositório
        repository.save(medico);

        // Construção da URI para o novo recurso criado
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        // Retorno da resposta com o status 201 Created e detalhes do médico criado
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    // Endpoint para listar médicos paginados
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        // Recuperação da página de médicos ativos do repositório e mapeamento para dados de listagem
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        // Retorno da resposta com a página de médicos
        return ResponseEntity.ok(page);
    }

    // Endpoint para atualizar informações de um médico
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        // Obtenção de uma referência ao médico pelo ID sem carregar todos os detalhes
        var medico = repository.getReferenceById(dados.id());
        // Atualização das informações do médico com base nos dados fornecidos
        medico.atualizarInformacoes(dados);
        // Retorno da resposta com o status 200 OK e detalhes atualizados do médico
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    // Endpoint para excluir um médico pelo ID
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        // Obtenção de uma referência ao médico pelo ID sem carregar todos os detalhes
        var medico = repository.getReferenceById(id);
        // Exclusão do médico (marcação como inativo, por exemplo)
        medico.excluir();
        // Retorno da resposta com o status 204 No Content
        return ResponseEntity.noContent().build();
    }

    // Endpoint para detalhar informações de um médico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        // Obtenção de uma referência ao médico pelo ID sem carregar todos os detalhes
        var medico = repository.getReferenceById(id);
        // Retorno da resposta com os detalhes do médico
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
