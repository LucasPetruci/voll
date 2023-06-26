package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.PacienteRepository;

public class ValidadorPacienteAtivo {

	private PacienteRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
		
		if(!pacienteEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendadada com paciente excluido");
		}
	}
}
