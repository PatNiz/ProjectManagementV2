/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.action.documentActions.*;
import pl.ttpsc.javaupdate.project.action.projectActions.*;
import pl.ttpsc.javaupdate.project.action.userActions.*;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.utility.Menu;
import pl.ttpsc.javaupdate.project.utility.MenuItem;

public class MainMenu {
    public static Menu getProjectMenu(CompanyUser user, Config config) {
        Menu projectMenu = new Menu("Project Menu");
        projectMenu.add(new CreateProjectAction("Add project", config, user));
        projectMenu.add(new GetListOfProjectsAction("List of projects", config, user));
        projectMenu.add(new DeleteProjectAction("Delete project", config, user));
        projectMenu.add(new FindProjectAction("Find project", config, user));
        projectMenu.add(new FindProjectDetailsAction("Find project details",config,user));
        projectMenu.add(new MenuItem("Back to main menu"));

        return projectMenu;
    }

    public static Menu getDocumentMenu(CompanyUser user, Config config) {
        Menu documentMenu = new Menu("Document Menu");
        documentMenu.add(new CreateDocumentAction("Add document", config, user));
        documentMenu.add(new DeleteDocumentAction("Delete document", config, user));
        documentMenu.add(new GetListOfDocumentsAction("List of document", config, user));
        documentMenu.add(new FindDocumentAction("Find document", config, user));
        documentMenu.add(new UpdateDocumentAction("Update document", config, user));
        documentMenu.add(new MenuItem("Back to main menu"));
        return documentMenu;
    }

    public static Menu getUserMenu(CompanyUser user, Config config) {
        Menu userMenu = new Menu("User Menu");
        userMenu.add(new CreateUserAction("Add user", config, user));
        userMenu.add(new AddAdminRoleToUserAction("Grand golobal admin role", config, user));
        userMenu.add(new AddRoleToUserAction("Grant role to user", config, user));
        userMenu.add(new MenuItem("Back to main menu"));
        return userMenu;
    }

    public static Menu getMainMenu(CompanyUser user, Config config) {
        Menu mainMenu = new Menu("Main menu");
        mainMenu.add(getDocumentMenu(user, config));
        mainMenu.add(getProjectMenu(user, config));
        mainMenu.add(getUserMenu(user, config));
        return mainMenu;
    }
}
