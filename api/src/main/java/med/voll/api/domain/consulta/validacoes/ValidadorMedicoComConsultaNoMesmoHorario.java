package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.repository.ConsultaRepository;

@Component
public class ValidadorMedicoComConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var medicoPossuiConsultaNoMesmoHoraio = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
		if(medicoPossuiConsultaNoMesmoHoraio) {
			throw new ValidationException("Médico já possui outra consulta agendada nesse mesmo horario");
		}
	}

}
