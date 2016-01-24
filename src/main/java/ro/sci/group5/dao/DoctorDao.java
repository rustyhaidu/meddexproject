package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.Doctor;

public interface DoctorDao extends BaseDAO<Doctor> {

	Collection<Doctor> searchByName(String query);
}
