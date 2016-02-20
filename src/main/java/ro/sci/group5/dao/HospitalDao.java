package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.Hospital;

/**
 * HospitalDao interface extends BaseDAO methods, with Hospital object types instead of T.
 *
 */
public interface HospitalDao extends BaseDAO<Hospital> {
	/**
	 * Abstract method for searching Hospital based on one string parameter.
	 * @param query
	 * @return all Hospitals that contain string "query"
	 */
	Collection<Hospital> searchByName(String query);
}

