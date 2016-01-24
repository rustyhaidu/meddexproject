package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.AbstractModel;

public interface BaseDAO<T extends AbstractModel> {

	Collection<T> getAll();
	
	T findById(Long id);
	
	T update(T model);
	
	boolean delete(T model);
}
