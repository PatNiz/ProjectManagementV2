/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.persistence;

import java.util.List;


public interface PersistenceManager {


	public void insert(Persistable persistable);


	public void update(Persistable persistable);

	public void delete(Persistable persistable);

	<T extends Persistable> List<T> find(QuerySpec<T> querySpec);
}

