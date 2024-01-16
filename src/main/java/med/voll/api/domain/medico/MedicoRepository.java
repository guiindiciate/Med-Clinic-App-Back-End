package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que estende JpaRepository para operações de persistência relacionadas à entidade Medico
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Método personalizado para buscar todos os médicos ativos paginados
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
