package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.Hospital;

public interface HospitalDao extends BaseDAO<Hospital> {

	Collection<Hospital> searchByName(String query);
}

