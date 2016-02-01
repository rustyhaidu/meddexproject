package ro.sci.group5.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.group5.dao.HospitalDao;
import ro.sci.group5.domain.Hospital;
import ro.sci.group5.domain.Review;

@Service
public class HospitalService {
	@Autowired
	private HospitalDao hospitalDao;

	public Hospital save(Hospital hospital) {
		return hospitalDao.update(hospital);
	}

	public Collection<Hospital> listAll() {
		return hospitalDao.getAll();
	}
	
	public Hospital findById(long id) {
		Hospital hospital = hospitalDao.findById(id);
		return hospital;
	}

}