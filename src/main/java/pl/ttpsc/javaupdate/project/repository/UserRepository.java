/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.repository;

import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;

import java.util.*;

public class UserRepository {
    private final PersistenceManager manager;
    public UserRepository(PersistenceManager manager){
        this.manager = manager;
    }

    public void save(CompanyUser user){
        manager.insert(user);
    }
    public void delete(CompanyUser companyUser){
        manager.delete(companyUser);
    }
    public List<CompanyUser> findAllUsers() {
        return manager.find(new QuerySpec<>(CompanyUser.class));
    }
    public Optional<CompanyUser> findUserByUsername(String username){
        QuerySpec querySpec = new QuerySpec(CompanyUser.class);
        querySpec.addCondition("username",username);
        List<Persistable> persistables = manager.find(querySpec);
        return persistables.isEmpty() ? Optional.empty() : Optional.of((CompanyUser) persistables.get(0));
    }

}
