package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadoresCancelamento;
import med.voll.api.domain.entity.Consulta;
import med.voll.api.domain.entity.Medico;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;

@Service
public class AgendaDeConsultas {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private List<ValidadorAgendamentoDeConsulta> validadores;
	
	@Autowired
	private List<ValidadoresCancelamento> validadoresCancelamento;
	
	public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dados) {
		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Id do paciente não existe");
		}
		if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Id do médico não existe");
		}
		
		validadores.forEach(v -> v.validar(dados));
		
		var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		var medico = escolherMedico(dados);
		if(medico == null) {
			throw new ValidacaoException("Não existe médico diposnivel nessa data");
		}
		var consulta = new Consulta(null, medico, paciente, dados.data(), null);
		
		consultaRepository.save(consulta);
		
		return new DadosDetalhamentoConsulta(consulta);
	}
	
	
	public void cancelar(DadosCancelamentoConsulta dados) {

		if(!consultaRepository.existsById(dados.idConsulta())) {
			throw new ValidacaoException("ID da consulta informadao não existe!");
		}
		
		validadoresCancelamento.forEach(v -> v.validar(dados));
		
		var consulta = consultaRepository.getReferenceById(dados.idConsulta());
		consulta.cancelar(dados.motivo());		
	}

	
	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
		if(dados.idMedico() != null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		if(dados.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido");
		}
		
		return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
	}

	
}
