package ro.sci.group5.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.group5.dao.NeighbourhoodDao;
import ro.sci.group5.domain.Neighbourhood;

/**
 * This class describes the actions that can be done on Doctor objects like
 * save, edit, delete
 *
 */

@Service
public class NeighbourhoodService {
	@Autowired

	private NeighbourhoodDao dao;

	/**
	 * Method used for saving and editing a doctor
	 * 
	 * @param doctor
	 * @return Doctor
	 */
	public Neighbourhood save(Neighbourhood neighbourhood) {
		if (neighbourhood.getId() <= 0 && !dao.searchByName(neighbourhood.getNeighbourhoodName()).isEmpty()) {
			// throw new IllegalArgumentException("Duplicated Doctor");
		}
		return dao.update(neighbourhood);
	}

	/**
	 * Method used for getting all the doctors in a Collection
	 * 
	 * @return Collection<Doctor>
	 */

	public Collection<Neighbourhood> listAll() {
		return dao.getAll();
	}

	/**
	 * Method used for deleting a doctor
	 * 
	 * @param id
	 * @return boolean
	 */

	public boolean delete(long id) {
		Neighbourhood neighbourhood = dao.findById(id);
		if (neighbourhood == null) {
			return false;
		} else {
			return dao.delete(neighbourhood);
		}
	}

	/**
	 * Method used for finding a doctor by its id
	 * 
	 * @param id
	 * @return Doctor
	 */

	/*public Neighbourhood findById(long id) {
		Neighbourhood doctor = dao.findById(id);
		return doctor;
	}
	public Collection<Neighbourhood> findByHospitalId(long id) {
		Collection<Neighbourhood> doctorList = dao.findByNeighbourhoodID(id);
		return doctorList;
	}
*/
	/**
	 * Method for finding doctors by its First Name and/or Last Name
	 * 
	 * @param query
	 * @return Collection<Doctor>
	 */

	public Collection<Neighbourhood> findByName(String query) {
		return dao.searchByName(query);
	}

}
