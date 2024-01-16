package pl.ttpsc.javaupdate.project.view.console.commands.projectCommands;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.utility.MenuComponent;
import pl.ttpsc.javaupdate.project.view.console.ProjectConsolePrinter;
import pl.ttpsc.javaupdate.project.view.console.UserConsolePrinter;

public class CreateProjectCommand implements MenuComponent {
    String name;
    SqlPersistenceManager sqlPersistenceService = new SqlPersistenceManager();
    ProjectConsolePrinter printer = new ProjectConsolePrinter();

    public CreateProjectCommand(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void execute() {

        Project projectData = printer.getProjectData();
        sqlPersistenceService.insert(projectData);
        System.out.println("Project added");
    }

    public void getNameWithSeparator() {
        System.out.print("  " + getName());
        System.out.println("");
    }

}



