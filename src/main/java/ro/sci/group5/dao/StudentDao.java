package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.Student;

public interface StudentDao extends BaseDAO<Student> {

	Collection<Student> searchByName(String query);
}
