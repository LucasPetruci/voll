package med.voll.api.domain.consulta.validacoes;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import java.time.Duration;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta  {

	public void validar(DadosAgendamentoConsulta dados) {
		
		var dataConsulta = dados.data();
		var agora = LocalDateTime.now();
		var diferencaEmMninutos = Duration.between(agora, dataConsulta).toMinutes();
		
		if(diferencaEmMninutos < 30) {
			throw new ValidacaoException("Consulta deve ser agendada com 30 minutos de antecedência");
		}
		
	}
		
}
