/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.action;

import lombok.extern.log4j.Log4j2;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.UserRepository;
import pl.ttpsc.javaupdate.project.security.Authorization;
import pl.ttpsc.javaupdate.project.utility.Menu;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.UserView;
import pl.ttpsc.javaupdate.project.view.console.MainMenu;
import pl.ttpsc.javaupdate.project.view.console.UserConsoleView;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Log4j2
public class LoginAction implements Action, MenuComponent {
    private String name;
    private Config config;

    public LoginAction(String name, Config config) {
        this.name = name;
        this.config = config;
    }
    public String getName() {
        return name;
    }
    public void execute() {
        String username = null;
        String password = null;
        UserView userView = config.getUserView();
        UserRepository userRepository = config.getUserRepository();
        if(userView instanceof UserConsoleView){
            username = ((UserConsoleView) userView).promptForInput("Enter username");
            password = ((UserConsoleView) userView).promptForInput("Enter password");
        }
        if (Authorization.isValidLogin(username, password)) {
            Optional<CompanyUser> userByUsername = userRepository.findUserByUsername(username);
            log.info("Logged in as: " + userByUsername.get().getUsername());
            Menu menu = MainMenu.getMainMenu(userByUsername.get(), config);
            menu.chooseOption(new Scanner(System.in));
        } else {
            log.info("Invalid login or password. Try again.");
        }
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ENGINEER, Role.MANAGER, Role.HR, Role.ADMINISTRATOR);
    }

}
