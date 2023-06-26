package med.voll.api.domain.consulta.validacoes;

import java.time.LocalDateTime;
import java.time.Duration;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

public class ValidadorHorarioAntecedencia {

	public void validar(DadosAgendamentoConsulta dados) {
		
		var dataConsulta = dados.data();
		var agora = LocalDateTime.now();
		var diferencaEmMninutos = Duration.between(agora, dataConsulta).toMinutes();
		
		if(diferencaEmMninutos < 30) {
			throw new ValidacaoException("Consulta deve ser agendada com 30 minutos de antecedência");
		}
		
	}
		
}
