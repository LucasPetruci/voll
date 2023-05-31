package med.voll.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.entity.Paciente;

public interface PacienteRpository extends JpaRepository<Paciente, Long> {

}
