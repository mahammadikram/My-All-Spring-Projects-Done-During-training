package in.shakthi.services;

import java.util.List;

import in.shakthi.entity.Patient;

public interface IPatientService 
{
	Long savePatient(Patient patient);

	void updatePatient(Patient patient);

	void deletePatient(Long id);

	Patient getOnePatient(Long id);

	List<Patient> getAllPatients();
	
	Patient getOneByEmail(String email);

}
