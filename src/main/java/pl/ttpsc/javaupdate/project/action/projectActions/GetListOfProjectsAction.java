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
import java.util.Arrays;
import java.util.List;
import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAdministrator;
@Log4j2
public class GetListOfProjectsAction implements Action, MenuComponent {
    private String name;
    private Config config;
    private CompanyUser user;

    public GetListOfProjectsAction(String name, Config config, CompanyUser user) {
        this.name = name;
        this.config = config;
        this.user = user;

    }
    public String getName() {
        return name;
    }
    public void execute() {
        ProjectRepository projectRepository = config.getProjectRepository();
        ProjectView projectView = config.getProjectView();
        if (isUserAdministrator(user.getId())) {
            projectView.display(projectRepository.findAllProjects());
        } else {
            log.info("You are not authorized to find projects");
        }

    }
    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ADMINISTRATOR);
    }

}
