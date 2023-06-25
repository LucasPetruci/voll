package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(
		
		Long idMedico,
		
		@NotNull
		Long idPaciente,
		
		@NotNull
		@Future
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
		LocalDateTime data
		) {

}
