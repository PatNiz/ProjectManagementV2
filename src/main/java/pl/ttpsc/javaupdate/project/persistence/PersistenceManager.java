package pl.ttpsc.javaupdate.project.persistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PersistenceManager {

	List<Persistable> read(Criteria criteria) throws SQLException, IOException;

	
	void update(Persistable persistable);

	void update(Integer id, Persistable persistableObject) throws SQLException;

	void delete(Persistable persistable);

	List<Persistable> find(QuerySpec queySpec);
      
}
