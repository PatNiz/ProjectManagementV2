package pl.ttpsc.javaupdate.project.view.console.commands.userCommands;

import pl.ttpsc.javaupdate.project.model.User;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.console.UserConsolePrinter;

public class CreateUserCommand implements MenuComponent {
    String name;
    UserConsolePrinter printer = new UserConsolePrinter();
    SqlPersistenceManager sqlPersistenceService = new SqlPersistenceManager();
    public CreateUserCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void execute() {
        User userData =  printer.getUserData();
        sqlPersistenceService.insert((Persistable) userData);
        System.out.println("User added");
    }

    public void getNameWithSeparator() {
        System.out.print("  " + getName());
        System.out.println("");
    }

}



