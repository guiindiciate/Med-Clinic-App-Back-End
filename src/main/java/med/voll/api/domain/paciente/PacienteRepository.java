package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que estende JpaRepository para operações de persistência relacionadas à entidade Paciente
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Método personalizado para buscar todos os pacientes ativos paginados
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}