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
public class DeleteProjectAction implements Action, MenuComponent {
    private String name;
    private Config config;
    private CompanyUser user;

    public DeleteProjectAction(String name, Config config, CompanyUser user) {
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
            Long elementId = Long.valueOf(((ProjectConsoleView) projectView).getElementId().toString());
            projectRepository.findProjectById(elementId)
                    .ifPresentOrElse(
                            project -> {
                                Long projectId = project.getId();
                                if (isUserAuthorizedToPerformAction(user, projectId, config, getAllowedRoles())) {
                                    projectRepository.delete(project);
                                    log.info("Project deleted");
                                } else {
                                    log.info("You are not authorized to delete this project.");
                                }
                            },
                            () -> log.info("You can't delete project with ID " + elementId + "because is not found.")
                    );
        }

    }
    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ADMINISTRATOR, Role.MANAGER);
    }


}
