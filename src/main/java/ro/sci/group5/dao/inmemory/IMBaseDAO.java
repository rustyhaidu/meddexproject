package ro.sci.group5.dao.inmemory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import ro.sci.group5.dao.BaseDAO;
import ro.sci.group5.domain.AbstractModel;

public class IMBaseDAO<T extends AbstractModel> implements BaseDAO<T> {
	private Map<Long, T> models = new HashMap<Long, T>();

	private static AtomicLong ID = new AtomicLong(System.currentTimeMillis());

	@Override
	public Collection<T> getAll() {

		return models.values();
	}

	@Override
	public T findById(Long id) {

		return models.get(id);
	}

	@Override
	public T update(T model) {
		if (model.getId() <= 0) {
			model.setId(ID.getAndIncrement());
		}
		System.out.println("In IMBASEDAO update"+" "+model.getId());
		models.put(model.getId(), model);
		return model;
	}

	@Override
	public boolean delete(T model) {
		boolean result = models.containsKey(model.getId());

		if (result)
			models.remove(model.getId());
		return result;
	}

}
