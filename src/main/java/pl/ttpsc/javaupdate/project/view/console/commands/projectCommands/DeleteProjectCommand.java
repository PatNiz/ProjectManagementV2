package pl.ttpsc.javaupdate.project.view.console.commands.projectCommands;


import pl.ttpsc.javaupdate.project.utility.MenuComponent;

public class DeleteProjectCommand implements MenuComponent {
    String name;

    public DeleteProjectCommand(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void execute() {
        System.out.println("Project deleted");
    }

    public void getNameWithSeparator() {
        System.out.print("  " + getName());
        System.out.println("");
    }

}
