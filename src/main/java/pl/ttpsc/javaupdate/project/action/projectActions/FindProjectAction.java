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
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.ProjectView;
import pl.ttpsc.javaupdate.project.view.console.ProjectConsoleView;
import java.util.Arrays;
import java.util.List;
import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAuthorizedToPerformAction;

@Log4j2
public class FindProjectAction implements Action, MenuComponent {
    private String name;
    private Config config;
    private CompanyUser user;

    public FindProjectAction(String name, Config config, CompanyUser user) {
        this.name = name;
        this.config = config;
        this.user = user;

    }
    public String getName() {
        return name;
    }
    public void execute() {
        ProjectView projectView = config.getProjectView();
        ProjectRepository projectRepository = config.getProjectRepository();

        if (projectView instanceof ProjectConsoleView) {
            Long elementId = ((ProjectConsoleView)projectView).getElementId();
            if(isUserAuthorizedToPerformAction(user, elementId, config, getAllowedRoles())) {
                projectRepository.findProjectById(elementId)
                        .ifPresentOrElse(
                                project -> projectView.display(project),
                                () -> log.info("Project with ID " + elementId + " not found.")
                        );
            }else {
                log.info("You are not authorized to find this project"+ elementId);
            }
        }
    }
    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ENGINEER, Role.MANAGER,Role.ADMINISTRATOR, Role.HR);
    }

}
