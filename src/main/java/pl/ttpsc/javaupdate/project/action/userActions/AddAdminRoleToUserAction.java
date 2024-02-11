package pl.ttpsc.javaupdate.project.action.userActions;

import lombok.extern.log4j.Log4j2;
import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlService;
import pl.ttpsc.javaupdate.project.repository.ProjectRolesRepository;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.UserView;
import pl.ttpsc.javaupdate.project.view.console.UserConsoleView;

import java.util.Arrays;
import java.util.List;

import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAdministrator;

@Log4j2
public class AddAdminRoleToUserAction implements Action, MenuComponent {
    private String name;
    private Config config;
    private CompanyUser user;

    public AddAdminRoleToUserAction(String name, Config config, CompanyUser user) {
        this.name = name;
        this.config = config;
        this.user = user;

    }
    public String getName() {
        return name;
    }

    public void execute() {
        ProjectRolesRepository projectRolesRepository = new ProjectRolesRepository();
        UserView userView = config.getUserView();
        if(userView instanceof UserConsoleView){
            if(isUserAdministrator(user.getId())) {
                Long userId = ((UserConsoleView) userView).getUserId();
                SqlService.giveAdministratorRole(userId);
            }
        }

    }
    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ADMINISTRATOR, Role.MANAGER);
    }


}
