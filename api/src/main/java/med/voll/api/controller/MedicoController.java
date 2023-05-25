package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.entity.Medico;
import med.voll.api.medico.DadosAtualizcaoMedicos;
import med.voll.api.medico.DadosCadastroMedicos;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedicos dados) {
		repository.save(new Medico(dados));
		
	}
	
	
	@GetMapping
	public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
		return repository.findAll(paginacao).map(DadosListagemMedico::new);
	}
	
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizcaoMedicos dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);		
	}
}
