package com.HospitalManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.HospitalManagement.Entity.TestAppointment;
import com.HospitalManagement.Repository.TestAppointmentRepository;

@Controller
public class TestAppointmentController {

	@Autowired
	private TestAppointmentRepository testRe;
	
	@GetMapping("/pathologyTest")
	public String test(Model model){
		model.addAttribute("testappointment", new TestAppointment());
		return "pathologyTest";
	}
	
	@PostMapping("/test")
	public String testSave(TestAppointment testappointment){
		testRe.save(testappointment);
		return "patienthome";
	}
}
