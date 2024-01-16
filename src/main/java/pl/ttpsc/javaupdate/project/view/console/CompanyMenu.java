package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.utility.Menu;
import pl.ttpsc.javaupdate.project.utility.MenuItem;
import pl.ttpsc.javaupdate.project.view.console.commands.documentCommands.CreateDocumentCommand;
import pl.ttpsc.javaupdate.project.view.console.commands.documentCommands.DeleteDocumentCommand;
import pl.ttpsc.javaupdate.project.view.console.commands.documentCommands.GetListOfDocumentsCommand;
import pl.ttpsc.javaupdate.project.view.console.commands.projectCommands.CreateProjectCommand;
import pl.ttpsc.javaupdate.project.view.console.commands.projectCommands.DeleteProjectCommand;
import pl.ttpsc.javaupdate.project.view.console.commands.projectCommands.GetListOfProjectsCommand;
import pl.ttpsc.javaupdate.project.view.console.commands.userCommands.CreateUserCommand;
import pl.ttpsc.javaupdate.project.view.console.commands.userCommands.DeleteUserCommand;
import pl.ttpsc.javaupdate.project.view.console.commands.userCommands.GetListOfUsersCommand;

public class CompanyMenu {
    public static Menu getProjectMenu() {
        Menu projectMenu = new Menu("Project Menu");
        projectMenu.add(new CreateProjectCommand("Add project"));
        projectMenu.add(new DeleteProjectCommand("Delete project"));
        projectMenu.add(new GetListOfProjectsCommand("List of projects"));
        projectMenu.add(new MenuItem("Back to main menu"));
        return projectMenu;
    }

    public static Menu getDocumentMenu() {
        Menu documentMenu = new Menu("Document Menu");
        documentMenu.add(new CreateDocumentCommand("Add document"));
        documentMenu.add(new DeleteDocumentCommand("Delete document"));
        documentMenu.add(new GetListOfDocumentsCommand("List of document"));
        documentMenu.add(new MenuItem("Back to main menu"));
        return documentMenu;
    }

    public static Menu getUserMenu() {
        Menu userMenu = new Menu("User Menu");
        userMenu.add(new CreateUserCommand("Add user"));
        userMenu.add(new DeleteUserCommand("Delete user"));
        userMenu.add(new GetListOfUsersCommand("List of users"));
        userMenu.add(new MenuItem("Back to main menu"));
        return userMenu;
    }

    public static Menu getMainMenu() {
        Menu mainMenu = new Menu("Main menu");
        mainMenu.add(getDocumentMenu());
        mainMenu.add(getProjectMenu());
        mainMenu.add(getUserMenu());
        return mainMenu;
    }
}
