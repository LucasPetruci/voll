package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

	@Autowired
	private ConsultaRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var primeiroHorario = dados.data().withHour(7);
		var ultimoHorario = dados.data().withHour(18);
		var pacientePossuiOutraConsultaNoDia = repository.existByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
		
		if(pacientePossuiOutraConsultaNoDia) {
			throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
		}
	}

}
