package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.Doctor;

/**
 * DoctorDao interface extends BaseDAO methods, with Doctor object types instead of T.
 *
 */
public interface DoctorDao extends BaseDAO<Doctor> {
    
	/**
	 * Abstract method for searching Doctors based on one string parameter.
	 * @param query
	 * @return all Doctors that contain string "query"
	 */
	Collection<Doctor> searchByName(String query);
	Collection<Doctor> findByHospitalID(Long hospitalID);
}
