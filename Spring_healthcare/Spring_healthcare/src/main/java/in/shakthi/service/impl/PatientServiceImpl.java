package in.shakthi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.shakthi.constants.UserRoles;
import in.shakthi.entity.Patient;
import in.shakthi.entity.User;
import in.shakthi.repo.PatientRepositor;
import in.shakthi.services.IPatientService;
import in.shakthi.services.IUserService;
import in.shakthi.util.MyMailUtil;
import in.shakthi.util.UserUtil;
//import in.nareshit.karthik.repo.PatientRepository;
@Service
public class PatientServiceImpl  implements IPatientService{
    
	@Autowired
	private PatientRepositor repo;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil util;
	
	@Autowired
	private MyMailUtil mailUtil ;
	
	@Override
	@Transactional
	public Long savePatient(Patient patient) {
		Long id = repo.save(patient).getId();
		if(id!=null) {
			String pwd = util.genPwd();
			
			System.out.println("PASSWORD---- ="+pwd);
			User user = new User();
			user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
			user.setUsername(patient.getEmail());
			//user.setPassword(util.genPwd());
			user.setPassword(pwd);
			user.setRole(UserRoles.PATIENT.name());
			//userService.saveUser(user);
			// TODO : Email part is pending
			Long genId  = userService.saveUser(user);
			
			String text = "Your name is " + patient.getEmail() +", password is "+ pwd;
//			if(genId!=null)
//				new Thread(new Runnable() {
//					public void run() {
//						String text = "Your name is " + patient.getEmail() +", password is "+ pwd;
//						mailUtil.send(patient.getEmail(), "PATIENT ADDED", text);
//					}
//				}).start();
		}
		return id;
	}

	@Override
	@Transactional
	public void updatePatient(Patient patient) {
		repo.save(patient);
		
	}

	@Override
	@Transactional
	public void deletePatient(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public Patient getOnePatient(Long id) {
		return repo.findById(id).get();
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public List<Patient> getAllPatients() {
		return repo.findAll();
	}

	@Override
	public Patient getOneByEmail(String email) {
		return repo.findByEmail(email).get();
	}

}
