package in.shakthi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.shakthi.constants.SlotStatus;
import in.shakthi.entity.SlotRequest;
import in.shakthi.repo.SlotRequestRepository;
import in.shakthi.services.ISlotRequestService;

@Service
public class SlotRequestServiceImpl implements ISlotRequestService {

	
	@Autowired
	private SlotRequestRepository repo;
	
	@Override
	public Long saveSlotRequest(SlotRequest sr) {
		return repo.save(sr).getId();
	}

	@Override
	public List<SlotRequest> getAllSlotRequests() {
		return repo.findAll();
	}
	@Override
	@Transactional
	public void updateSlotRequestStatus(Long id, String status) {
		repo.updateSlotRequestStatus(id, status);
		
	}

	@Override
	public List<SlotRequest> viewSlotsByPatientMail(String patientMail) {
		return repo.getAllPatientSlots(patientMail);
	}

	@Override
	public SlotRequest getOneSlotRequest(Long id) {
		Optional<SlotRequest> opt = repo.findById(id);
		if(opt!=null) {
			return opt.get();
		}
		return null;
	}

	@Override
	public List<SlotRequest> viewSlotsByDoctorMail(String doctorMail) {
		return repo.getAllDoctorSlots(doctorMail,SlotStatus.ACCEPTED.name());
	}

	
}
