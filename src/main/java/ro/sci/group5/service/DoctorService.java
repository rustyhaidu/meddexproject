package ro.sci.group5.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.group5.dao.DoctorDao;
import ro.sci.group5.domain.Doctor;

@Service
public class DoctorService {
	@Autowired
	private DoctorDao dao;

	public Doctor save(Doctor doctor) {
		if (doctor.getId() <= 0 && !dao.searchByName(doctor.getLastName()).isEmpty()) {
			// throw new IllegalArgumentException("Duplicated Doctor");
		}
		return dao.update(doctor);
	}

	public Collection<Doctor> listAll() {
		return dao.getAll();
	}

	public boolean delete(long id) {
		Doctor doctor = dao.findById(id);
		if (doctor == null) {
			return false;
		} else {
			return dao.delete(doctor);
		}
	}

	public Doctor findById(long id) {
		Doctor student = dao.findById(id);
		return student;
	}

	public Collection<Doctor> findByName(String query) {
		return dao.searchByName(query);
	}

}
