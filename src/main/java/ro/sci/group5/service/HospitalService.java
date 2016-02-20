package ro.sci.group5.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sci.group5.dao.HospitalDao;
import ro.sci.group5.domain.Hospital;

/**
 * This class describes the actions that can be done on Hospital objects like
 * save, edit, delete
 *
 */

@Service
public class HospitalService {
	@Autowired
	private HospitalDao hospitalDao;

	/**
	 * Method used for saving and editing a hospital
	 * 
	 * @param hospital
	 * @return Hospital
	 */
	public Hospital save(Hospital hospital) {
		return hospitalDao.update(hospital);
	}

	/**
	 * Method used for getting all the hospitals in a Collection
	 * 
	 * @return Collection<Hospital>
	 */
	public Collection<Hospital> listAll() {
		return hospitalDao.getAll();
	}

	/**
	 * Method used for finding a hospital by its id
	 * 
	 * @param id
	 * @return Hospital
	 */

	public Hospital findById(long id) {
		Hospital hospital = hospitalDao.findById(id);
		return hospital;
	}

	/**
	 * Method used for deleting a hospital
	 * 
	 * @param id
	 * @return boolean
	 */

	public boolean delete(long id) {
		Hospital hospital = hospitalDao.findById(id);
		if (hospital == null) {
			return false;
		} else {
			return hospitalDao.delete(hospital);
		}
	}

}