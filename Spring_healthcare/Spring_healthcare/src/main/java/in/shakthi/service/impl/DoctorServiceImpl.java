package in.shakthi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shakthi.constants.UserRoles;
import in.shakthi.entity.Doctor;
import in.shakthi.entity.User;
import in.shakthi.exception.DoctorNotFoundException;
import in.shakthi.repo.DoctorRepository;
import in.shakthi.services.IDoctorService;
import in.shakthi.services.IUserService;
import in.shakthi.util.MyCollectionsUtil;
import in.shakthi.util.MyMailUtil;
import in.shakthi.util.UserUtil;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repo;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil util;

	@Autowired
	private MyMailUtil mailUtil ;
	@Override
	public List<Doctor> getAllDoctors() {
		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		repo.delete(getOneDoctor(id));
		
	}

	@Override
	public Doctor getOneDoctor(Long id) {
		return repo.findById(id).orElseThrow(
				()->new DoctorNotFoundException(id+", not exist")
				);
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(repo.existsById(doc.getId()))
			repo.save(doc);
		else 
			throw new DoctorNotFoundException(doc.getId()+", not exist"); 
		
	}

	@Override
	public Long saveDoctor(Doctor doc) {
		Long id = repo.save(doc).getId();
		if(id!=null) {
			String pwd = util.genPwd();
			User user = new User();
			user.setDisplayName(doc.getFirstName()+" "+doc.getLastName());
			user.setUsername(doc.getEmail());
			//user.setPassword(util.genPwd());
			user.setPassword(pwd);
			user.setRole(UserRoles.DOCTOR.name());
			//userService.saveUser(user); //we have to comment for email part use next code
			// TODO : Email part is pending
			
			Long genId  = userService.saveUser(user);
			if(genId!=null)
				new Thread(new Runnable() {
					public void run() {
						String text = "Your uname is " + doc.getEmail() +", password is "+ pwd;
						mailUtil.send(doc.getEmail(), "DOCTOR ADDED", text);
					}
				}).start();
		
			
			
		}
		return id;
	}

	@Override
	public Map<Long, String> getDoctorIdAndNames() {
		
		List<Object[]> list = repo.getDoctorIdAndNames();
		return MyCollectionsUtil.convertToMapIndex(list);
	}

	@Override
	public List<Doctor> findDoctorBySpecName(Long specId) {
		return repo.findDoctorBySpecName(specId);
	}

	
	

}
