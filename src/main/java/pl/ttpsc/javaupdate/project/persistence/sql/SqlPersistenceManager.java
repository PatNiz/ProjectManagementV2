/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.persistence.sql;


import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
import java.util.List;



public class SqlPersistenceManager implements PersistenceManager {
        @Override
        public void insert(Persistable persistable) {
            SqlService.insert(persistable);
        }

        @Override
        public void update(Persistable persistable)  {
            SqlService.update(persistable);
        }
        @Override
        public void delete(Persistable persistable) {
            SqlService.delete(persistable);
        }
        @Override
        public <T extends Persistable> List<T> find(QuerySpec<T> querySpec) {
            return SqlService.find(querySpec);
        }
    }