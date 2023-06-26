package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoDeConsulta {

	public void validar(DadosAgendamentoConsulta dados) {
		var dataConsulta = dados.data();
		
		var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var antesDaAbertura = dataConsulta.getHour() < 7;
		var depoisDoEncerramento = dataConsulta.getHour() > 18;
		if(domingo || antesDaAbertura || depoisDoEncerramento) {
			throw new ValidacaoException("Consulta fora do horario de funcionamnetoda clinica");
		}
	}
}
