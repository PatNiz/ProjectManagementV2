/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.action.projectActions;

import lombok.extern.log4j.Log4j2;
import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlService;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.ProjectView;
import pl.ttpsc.javaupdate.project.view.console.ProjectConsoleView;
import java.util.Arrays;
import java.util.List;


import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAdministrator;
import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAuthorizedToPerformAction;

@Log4j2
public class CreateProjectAction implements Action, MenuComponent {
    private String name;
    private CompanyUser projectCreator;
    private Config config;
    public CreateProjectAction(String name, Config config, CompanyUser projectCreator) {
        this.name = name;
        this.config = config;
        this.projectCreator = projectCreator;
    }

    public String getName() {
        return name;
    }


    public void execute() {
        ProjectView projectView = config.getProjectView();
        ProjectRepository projectRepository = config.getProjectRepository();
        if (isUserAdministrator(projectCreator.getId())) {
            if (projectView instanceof ProjectConsoleView) {
                Project project= ((ProjectConsoleView) projectView).getProjectData();
                project.setCreatorId(projectCreator.getId());
                project.setId(SqlService.generateId(project));
                projectRepository.save(project);
                log.info("Project created");
            }
        }else {
            log.info("User with ID {} is not an administrator and cannot create projects.", projectCreator.getId());
        }
    }
    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ADMINISTRATOR);
    }



}



