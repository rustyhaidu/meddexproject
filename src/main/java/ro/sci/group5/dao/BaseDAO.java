package ro.sci.group5.dao;

import java.util.Collection;

import ro.sci.group5.domain.AbstractModel;
import ro.sci.group5.domain.Doctor;
/**
 * Generic class for DAO operations
 *
 * @param <T>
 */
public interface BaseDAO<T extends AbstractModel> {
    /**
     * Generic method for returning all objects of type T
     */
	Collection<T> getAll();
	/**
	 * Generic method for finding an object based on its id
	 * @param Long
	 * @return object of type T
	 */
	T findById(Long id);
	/**
	 * Generic update method, which acts as save or edit
	 * @param T
	 * @return object of type T
	 */
	T update(T model);
	/**
	 * Generic delete method
	 * @param T
	 * @return boolean
	 */
	boolean delete(T model);
	
	
}
