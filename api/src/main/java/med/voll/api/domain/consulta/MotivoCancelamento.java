package med.voll.api.domain.consulta;

import lombok.Getter;

@Getter
public enum MotivoCancelamento {

	PACIENTE_DESISTIU,
	MEDICO_CANCELOU,
	OUTROS;
	
	
}
