package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.repository.ConsultaRepository;

public class ValidadorMedicoComConsultaNoMesmoHorario {

	private ConsultaRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var medicoPossuiConsultaNoMesmoHoraio = repository.existByMedicoIdAndData(dados.idMedico(), dados.data());
		if(medicoPossuiConsultaNoMesmoHoraio) {
			throw new ValidationException("Médico já possui outra consulta agendada nesse mesmo horario");
		}
	}

}
