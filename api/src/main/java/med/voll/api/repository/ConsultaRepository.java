package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.domain.entity.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

	boolean existByMedicoIdAndData(Long idMedico, LocalDateTime data);

	boolean existByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

}
