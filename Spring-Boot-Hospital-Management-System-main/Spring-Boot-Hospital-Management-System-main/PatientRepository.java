package com.HospitalManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalManagement.Entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	
}
