package com.HospitalManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.HospitalManagement.Entity.Patient;
import com.HospitalManagement.Repository.PatientRepository;

@Controller
public class PatientController {

	@Autowired
	private PatientRepository paRe;

	@GetMapping("/") //Mapped for HTML page..
	public String home() {
		return "home";
	}

	@GetMapping("/patientregistration")
	public String patient1(Model model) {
		model.addAttribute("patient", new Patient());
		return "patientregistration";
	}

	@PostMapping("/patientlogin")
	public String patientRegister(Patient patient) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(patient.getPassword());
		patient.setPassword(encodedPassword);
		paRe.save(patient);
		return "patientlogin";
	}

	@GetMapping("/patientlogin")
	public String patient2() {
		return "patientlogin";
	}

	@GetMapping("/patienthome")
	public String patientHome() {
		return "patienthome";
	}
	
}
