package in.shakthi.services;

import java.util.List;
import java.util.Map;

import in.shakthi.entity.Doctor;
 
public interface IDoctorService {
	public Long saveDoctor(Doctor doc);
	public List<Doctor> getAllDoctors();
	public void removeDoctor(Long id);
	public Doctor getOneDoctor(Long id);
	public void updateDoctor(Doctor dec);
	
	public Map<Long,String> getDoctorIdAndNames();
	public List<Doctor> findDoctorBySpecName(Long specId);

}
