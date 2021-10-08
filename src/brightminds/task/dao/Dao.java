package brightminds.task.dao;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Dana Barghouthi
 *
 * @param <T>
 */
public interface Dao<T> {

	Optional<T> get(int id);

	List<T> getAll();

	void save(T t);

	void update(T t, String[] params);

	void delete(T t);
}