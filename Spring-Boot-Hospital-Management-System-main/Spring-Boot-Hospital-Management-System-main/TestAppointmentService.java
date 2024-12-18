package com.HospitalManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalManagement.Entity.TestAppointment;
import com.HospitalManagement.Repository.TestAppointmentRepository;

@Service
public class TestAppointmentService {

    @Autowired
	private TestAppointmentRepository testRe;
	
    public List<TestAppointment> listSave() {
        return testRe.findAll();
    }
}
