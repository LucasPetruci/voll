package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizcaoMedicos(
		@NotNull
		Long id, 
		
		@NotBlank(message = "Nome obrigatório")
		String nome, 
		
		@NotBlank(message = "Telefone obrigatório")
		String telefone, 
		
	    @NotNull(message = "Dados do endereço são obrigatórios")
		@Valid DadosEndereco endereco ) {

}
