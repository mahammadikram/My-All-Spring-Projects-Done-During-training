package in.shakthi.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shakthi.entity.Specialization;
import in.shakthi.exception.SpecializationNotFoundException;
import in.shakthi.repo.SpecializationRepository;
import in.shakthi.services.ISpecializationService;
import in.shakthi.util.MyCollectionsUtil;

@Service
public class SpecializationServiceImpl implements ISpecializationService {
	
	@Autowired
	private SpecializationRepository repo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		//repo.deleteById(id);
		repo.delete(getOneSpecialization(id));
	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		Optional<Specialization>  optional = repo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			//return null;
			
			throw new SpecializationNotFoundException(id+ " Not Found");
		}
		
		/*return repo.findById(id).orElseThrow(
		()-> new SpecializationNotFoundException(id+ " Not Found")
		);*/
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		repo.save(spec);
	}

	/**
	 *
	 */
	@Override
	public boolean isSpecCodeExist(String specCode) {
		/*Integer count = repo.getSpecCodeCount(specCode);
		boolean exist = count>0 ? true : false;
		return exist;*/
		return repo.getSpecCodeCount(specCode)>0;
	}
 
	@Override
	public boolean isSpecNameExist(String specName) {
		// TODO Auto-generated method stub
		return repo.getSpecNameCount(specName)>0;
	}

	@Override
	public boolean isSpecCodeExistForEdit(String specCode, Long id) {
		
		return repo.getSpecCodeCountForEdit(specCode,id)>0;
	}

	@Override
	public boolean isSpecNameExistForEdit(String specName, Long id) {
		
		return repo.getSpecNameCountForEdit(specName,id)>0;
	}

	@Override
	public Map<Long, String> getSpecIdAndName() {
		
		List<Object[]> list=repo.getSpecIdAndName();
		Map<Long,String> map=MyCollectionsUtil.convertToMap(list);
				
		return map;
	}

}