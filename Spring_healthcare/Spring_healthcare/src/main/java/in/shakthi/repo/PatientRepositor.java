package in.shakthi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shakthi.entity.Patient;

public interface PatientRepositor extends JpaRepository<Patient, Long> 
{
	Optional<Patient> findByEmail(String email);
}
