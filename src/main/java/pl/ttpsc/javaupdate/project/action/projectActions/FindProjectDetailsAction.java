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
import pl.ttpsc.javaupdate.project.persistence.sql.SqlService;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.ProjectView;
import pl.ttpsc.javaupdate.project.view.console.ProjectConsoleView;

import java.util.Arrays;
import java.util.List;

import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAuthorizedToPerformAction;
@Log4j2
public class FindProjectDetailsAction implements Action, MenuComponent {
    private String name;
    private Config config;
    private CompanyUser user;

    public FindProjectDetailsAction(String name, Config config, CompanyUser user) {
        this.name = name;
        this.config = config;
        this.user = user;

    }
    public String getName() {
        return name;
    }
    public void execute() {
        ProjectView projectView = config.getProjectView();
        if (projectView instanceof ProjectConsoleView) {
            Long elementId = ((ProjectConsoleView)projectView).getProjectId();
            if(isUserAuthorizedToPerformAction(user, elementId, config, getAllowedRoles())) {
                ((ProjectConsoleView) projectView).printProjectDetails(SqlService.getProjectDetails(elementId));
            }else {
                log.info("You are not authorized to find this project with ID {} details", elementId);
            }
        }
    }
    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ENGINEER, Role.MANAGER,Role.ADMINISTRATOR, Role.HR);
    }

}
