/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.config;

import lombok.Getter;
import lombok.Setter;
import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.repository.DocumentRepository;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.repository.ProjectRolesRepository;
import pl.ttpsc.javaupdate.project.repository.UserRepository;
import pl.ttpsc.javaupdate.project.view.DocumentView;
import pl.ttpsc.javaupdate.project.view.ProjectView;
import pl.ttpsc.javaupdate.project.view.UserView;
import pl.ttpsc.javaupdate.project.view.console.DocumentConsoleView;
import pl.ttpsc.javaupdate.project.view.console.ProjectConsoleView;
import pl.ttpsc.javaupdate.project.view.console.UserConsoleView;


import java.util.List;

@Setter
@Getter
public class Config {
    private PersistenceManager persistenceManager;
    private DocumentView documentView;
    private ProjectView projectView;
    private UserView userView;
    private UserRepository userRepository;
    private ProjectRepository projectRepository;
    private DocumentRepository documentRepository;
    private ProjectRolesRepository projectRolesRepository;

    private List<Action> actions;
    public DocumentView getDocumentView() {
        return documentView;
    }
    public Config withSqlPersistence() {
        setPersistenceManager(new SqlPersistenceManager());
        setDocumentRepository(new DocumentRepository(persistenceManager));
        setProjectRepository(new ProjectRepository(persistenceManager));
        setUserRepository(new UserRepository(persistenceManager));
        setProjectRolesRepository(new ProjectRolesRepository());
        return this;
    }
    public Config withConsoleView() {
        setDocumentView(new DocumentConsoleView());
        setProjectView(new ProjectConsoleView());
        setUserView(new UserConsoleView());
        return this;
    }
}
