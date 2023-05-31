package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.entity.Paciente;
import med.voll.api.paciente.DadosCadastroPacientes;
import med.voll.api.paciente.DadosDetalhamentoPaciente;
import med.voll.api.repository.PacienteRpository;

@RestController
@RequestMapping("pacientes")
public class PacientesController {
	
	@Autowired
	private PacienteRpository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPacientes dados, UriComponentsBuilder uriBuilder) {
		
		var paciente = new Paciente(dados);
		repository.save(paciente);
		
		var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
	}

}
