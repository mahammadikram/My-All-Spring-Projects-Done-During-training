package in.shakthi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.shakthi.entity.Appointment;
import in.shakthi.repo.AppointmentRepository;
import in.shakthi.services.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService{

	@Autowired
	private AppointmentRepository repo;
	@Override
	@Transactional
	public Long saveAppointment(Appointment appointment) {
		return repo.save(appointment).getId();
	}

	@Override
	@Transactional
	public void updateAppointment(Appointment appointment) {
		repo.save(appointment);
		
	}

	@Override
	@Transactional
	public void deleteAppointment(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public Appointment getOneAppointment(Long id) {
		return repo.findById(id).get();
	}

	@Override
	@Transactional(
			readOnly = true
			)
	public List<Appointment> getAllAppointments() {
		return repo.findAll();
	}

	@Override
	public List<Object[]> getAppoinmentsByDoctor(Long docId) {
		return repo.getAppoinmentsByDoctor(docId);
	}

	@Override
	public List<Object[]> getAppoinmentsByDoctorEmail(String userName) {
		return repo.getAppoinmentsByDoctorEmail(userName);
	}

	@Override
	@Transactional
	public void updateSlotCountForAppoinment(Long id, int count) {
		repo.updateSlotCountForAppoinment(id, count);
		
	}

}
