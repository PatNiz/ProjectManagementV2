package pl.ttpsc.javaupdate.project.view.console.commands.projectCommands;


import pl.ttpsc.javaupdate.project.utility.MenuComponent;

public class GetListOfProjectsCommand implements MenuComponent {
    String name;

    public GetListOfProjectsCommand(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void execute() {
        System.out.println("List all projects");
    }

    public void getNameWithSeparator() {
        System.out.print("  " + getName());
        System.out.println("");
    }

}
