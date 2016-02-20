package ro.sci.group5.dao.inmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import ro.sci.group5.dao.BaseDAO;
import ro.sci.group5.domain.AbstractModel;

/**
 * This is the generic class used for storing In Memory information.
 *
 * @param <T>
 */
public class IMBaseDAO<T extends AbstractModel> implements BaseDAO<T> {
	private Map<Long, T> models = new HashMap<Long, T>();

	private static AtomicLong ID = new AtomicLong(System.currentTimeMillis());

	/**
	 * Generic method for getting all objects of type T.
	 */
	@Override
	public Collection<T> getAll() {

		return models.values();
	}

	/**
	 * Generic method for finding objects by id.
	 * 
	 * @param id
	 * @return T
	 */
	@Override
	public T findById(Long id) {

		return models.get(id);
	}

	/**
	 * Generic method for saving/editing.
	 * 
	 * @param model
	 * @return T
	 */
	@Override
	public T update(T model) {
		if (model.getId() <= 0) {
			model.setId(ID.getAndIncrement());
		}
		models.put(model.getId(), model);
		return model;
	}

	/**
	 * Generic method for delete.
	 * 
	 * @param model
	 * @return boolean
	 */
	@Override
	public boolean delete(T model) {
		boolean result = models.containsKey(model.getId());

		if (result)
			models.remove(model.getId());
		return result;
	}

}
