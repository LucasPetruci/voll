package med.voll.api.domain.consulta.validacoes.cancelamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadoresCancelamento{

	@Autowired
	private ConsultaRepository repository;
	
	
	@Override
	public void validar(DadosCancelamentoConsulta dados) {
		var consulta = repository.getReferenceById(dados.idConsulta());
		var agora = LocalDateTime.now();
		var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();
		
		if (diferencaEmHoras < 24) {
			throw new ValidacaoException("Consulta somente pode ser cancelada com 24 horas de antecedência");
		}
		
	}

}
