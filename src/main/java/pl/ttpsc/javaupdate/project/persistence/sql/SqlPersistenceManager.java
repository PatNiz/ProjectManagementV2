package pl.ttpsc.javaupdate.project.persistence.sql;

import pl.ttpsc.javaupdate.project.persistence.Criteria;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;

import java.io.IOException;
import java.sql.*;

import java.util.List;



public class SqlPersistenceManager implements PersistenceManager {


        public void insert(Persistable persistableObject) {
            SqlService.insert(persistableObject);
        }

        @Override
        public List<Persistable> read(Criteria criteria) throws SQLException, IOException {
            List<Persistable> persistableObjects = SqlService.read(criteria);
            return persistableObjects;
        }

    @Override
    public void update(Persistable persistable) {

    }
    @Override
        public void update(Integer id, Persistable persistableObject) throws SQLException {
            SqlService.update(id, persistableObject);
        }

        @Override
        public void delete(Persistable persistable) {

        }

        @Override
        public List<Persistable> find(QuerySpec queySpec) {
            return null;
        }
    }