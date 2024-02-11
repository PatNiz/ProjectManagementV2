/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.repository;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;

import java.util.*;

public class ProjectRepository {
    private final PersistenceManager manager;
    public ProjectRepository(PersistenceManager manager){
        this.manager = manager;
    }

    public void save(Project project){ manager.insert(project);
    }
    public void delete(Project project){
        manager.delete(project);
    }
    public List<Project> findAllProjects() {
        return manager.find(new QuerySpec<>(Project.class));
    }
    public Optional<Project> findProjectByName(String name){
        QuerySpec querySpec = new QuerySpec(Project.class);
        querySpec.addCondition("name",name);
        List<Persistable> persistables = manager.find(querySpec);
        return persistables.isEmpty() ? Optional.empty() : Optional.of((Project) persistables.get(0));
    }
    public Optional<Project> findProjectById(Long id){
        QuerySpec querySpec = new QuerySpec(Project.class);
        querySpec.addCondition("id",id);
        List<Persistable> persistables = manager.find(querySpec);
        return persistables.isEmpty() ? Optional.empty() : Optional.of((Project) persistables.get(0));
    }



}
