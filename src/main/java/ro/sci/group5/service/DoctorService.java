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

	public Doctor save(Doctor student) {
		if (student.getId() <= 0 && !dao.searchByName(student.getLastName()).isEmpty()) {
			//throw new IllegalArgumentException("Duplicated Doctor");
		}
		return dao.update(student);
	}

	public Collection<Doctor> listAll() {
		return dao.getAll();
	}

	public boolean delete(long id) {
		Doctor student = dao.findById(id);
		if (student == null) {
			return false;
		} else {
			return dao.delete(student);
		}
	}

	public Doctor findById(long id) {
		Doctor student = dao.findById(id);
		return student;
	}

}
