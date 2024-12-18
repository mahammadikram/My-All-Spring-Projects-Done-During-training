package com.HospitalManagement.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalManagement.Entity.PathologyAdd;
import com.HospitalManagement.Repository.PathologyAddRepository;

@Service
public class PathologyAddService {

	@Autowired
	private PathologyAddRepository pathRe;

	public List<PathologyAdd> listAll() {
		return pathRe.findAll();
	}

	public void save(PathologyAdd pathologyadd) {
		pathRe.save(pathologyadd);
	}

	public PathologyAdd get(int id) {
		return pathRe.findById(id).get();
	}

	public void delete(int id) {
		pathRe.deleteById(id);
	}

}
