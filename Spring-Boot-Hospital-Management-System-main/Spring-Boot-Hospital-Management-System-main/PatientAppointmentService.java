package com.HospitalManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalManagement.Entity.PatientAppointment;
import com.HospitalManagement.Repository.PatientAppointmentRepository;

@Service
public class PatientAppointmentService {

	@Autowired
	private PatientAppointmentRepository patientRe;
	
    public List<PatientAppointment> listAll() {
        return patientRe.findAll();
    }
}
