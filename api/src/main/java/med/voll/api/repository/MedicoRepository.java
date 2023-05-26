package med.voll.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.entity.Medico;
import med.voll.api.medico.DadosListagemMedico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);

}
