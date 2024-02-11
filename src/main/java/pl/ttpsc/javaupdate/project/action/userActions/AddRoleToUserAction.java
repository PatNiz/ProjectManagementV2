package pl.ttpsc.javaupdate.project.action.userActions;

import lombok.extern.log4j.Log4j2;
import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.ProjectRolesRepository;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.UserView;
import pl.ttpsc.javaupdate.project.view.console.UserConsoleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAdministrator;
import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAuthorizedToPerformAction;

@Log4j2
public class AddRoleToUserAction implements Action, MenuComponent {
    private String name;
    private Config config;
    private CompanyUser user;

    public AddRoleToUserAction(String name, Config config, CompanyUser user) {
        this.name = name;
        this.config = config;
        this.user = user;

    }
    public String getName() {
        return name;
    }

    public void execute()  {
        ProjectRolesRepository projectRolesRepository = new ProjectRolesRepository();
        UserView userView = config.getUserView();
        if(userView instanceof UserConsoleView){
            Long projectId = ((UserConsoleView) userView).getProjectId();
            if(isUserAuthorizedToPerformAction(user, projectId, config, getAllowedRoles()) || isUserAdministrator(user.getId())) {
                Long userId = ((UserConsoleView) userView).getUserId();
                List<Role> roles = new ArrayList<>();
                roles.add(((UserConsoleView) userView).getUserRole());
                projectRolesRepository.give(projectId,userId,roles);
            }else{
                log.info("You can't grand role to user in project id:"+projectId);
            }

        }

    }
    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ADMINISTRATOR, Role.MANAGER);
    }


}
