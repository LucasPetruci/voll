package med.voll.api.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosCadastroMedicos;
import med.voll.api.paciente.DadosCadastroPacientes;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String nome;
	    private String email;

	    private String telefone;

	    private String cpf;

	    @Embedded
	    private Endereco endereco;

	    private Boolean ativo;
	    
	    public Paciente(DadosCadastroPacientes dados) {
	    	this.ativo = true;
	    	this.nome = dados.nome();
	    	this.email = dados.email();
	    	this.telefone = dados.telefone();
	    	this.cpf = cpf;
	    	this.endereco = new Endereco(dados.endereco());
	    	
	    }
}