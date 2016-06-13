package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.Neighbourhood;

public interface NeighbourhoodDao extends BaseDAO<Neighbourhood> {
	/**
	 * Abstract method for searching Hospital based on one string parameter.
	 * @param query
	 * @return all Hospitals that contain string "query"
	 */
	Collection<Neighbourhood> searchByName(String query);
}

