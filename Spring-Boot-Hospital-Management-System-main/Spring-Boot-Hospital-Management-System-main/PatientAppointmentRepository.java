package com.HospitalManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HospitalManagement.Entity.PatientAppointment;

public interface PatientAppointmentRepository extends JpaRepository<PatientAppointment, Integer>{



}
