package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Anotação indicando que esta classe é um Advice responsável por tratar exceções em controladores
@RestControllerAdvice
public class TratadorDeErros {

    // Método que trata a exceção EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        // Retorna uma resposta HTTP 404 Not Found
        return ResponseEntity.notFound().build();
    }

    // Método que trata a exceção MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        // Obtém os erros de validação da exceção
        var erros = ex.getFieldErrors();
        // Retorna uma resposta HTTP 400 Bad Request com detalhes dos erros de validação
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    // Record (Java 16+) para representar dados de erro de validação
    private record DadosErroValidacao(String campo, String mensagem) {

        // Construtor que recebe um objeto FieldError e extrai os dados relevantes
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}