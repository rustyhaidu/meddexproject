package ro.sci.group5.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.group5.dao.DoctorDao;
import ro.sci.group5.domain.Doctor;

/**
 * This class describes the actions that can be done on Doctor objects like
 * save, edit, delete
 *
 */

@Service
public class DoctorService {
	@Autowired

	private DoctorDao dao;

	/**
	 * Method used for saving and editing a doctor
	 * 
	 * @param doctor
	 * @return Doctor
	 */
	public Doctor save(Doctor doctor) {
		if (doctor.getId() <= 0 && !dao.searchByName(doctor.getLastName()).isEmpty()) {
			// throw new IllegalArgumentException("Duplicated Doctor");
		}
		return dao.update(doctor);
	}

	/**
	 * Method used for getting all the doctors in a Collection
	 * 
	 * @return Collection<Doctor>
	 */

	public Collection<Doctor> listAll() {
		return dao.getAll();
	}

	/**
	 * Method used for deleting a doctor
	 * 
	 * @param id
	 * @return boolean
	 */

	public boolean delete(long id) {
		Doctor doctor = dao.findById(id);
		if (doctor == null) {
			return false;
		} else {
			return dao.delete(doctor);
		}
	}

	/**
	 * Method used for finding a doctor by its id
	 * 
	 * @param id
	 * @return Doctor
	 */

	public Doctor findById(long id) {
		Doctor doctor = dao.findById(id);
		return doctor;
	}
	public Collection<Doctor> findByHospitalId(long id) {
		Collection<Doctor> doctorList = dao.findByHospitalID(id);
		return doctorList;
	}

	/**
	 * Method for finding doctors by its First Name and/or Last Name
	 * 
	 * @param query
	 * @return Collection<Doctor>
	 */

	public Collection<Doctor> findByName(String query) {
		return dao.searchByName(query);
	}

}
