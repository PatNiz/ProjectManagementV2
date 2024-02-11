package pl.ttpsc.javaupdate.project.action.userActions;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlService;
import pl.ttpsc.javaupdate.project.repository.UserRepository;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.UserView;
import pl.ttpsc.javaupdate.project.view.console.UserConsoleView;

import java.util.Arrays;
import java.util.List;

import static pl.ttpsc.javaupdate.project.security.Authorization.isUserAdministrator;

@Log4j2
public class CreateUserAction implements Action, MenuComponent {

    private String name;
    private Config config;
    private CompanyUser creator;

    public CreateUserAction(String name, Config config, CompanyUser creator) {
        this.name = name;
        this.config = config;
        this.creator = creator;

    }

    public String getName() {
        return name;
    }
    public void execute() {
        UserRepository userRepository = config.getUserRepository();
        UserView userView = config.getUserView();
        if (userView instanceof UserConsoleView) {
            if(isUserAdministrator(creator.getId())) {
                CompanyUser userData = ((UserConsoleView) userView).getUserData();
                userData.setId(SqlService.generateId(userData));
                userRepository.save(userData);
                log.info("User added");
            }else{
                log.info("You dont have permission to add user");
            }
        }
    }
    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ENGINEER, Role.MANAGER);
    }



}



