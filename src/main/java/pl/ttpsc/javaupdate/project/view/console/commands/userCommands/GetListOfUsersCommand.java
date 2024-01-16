package pl.ttpsc.javaupdate.project.view.console.commands.userCommands;


import pl.ttpsc.javaupdate.project.utility.MenuComponent;

public class GetListOfUsersCommand implements MenuComponent {
    String name;

    public GetListOfUsersCommand(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void execute() {
        System.out.println("List of users");
    }

    public void getNameWithSeparator() {
        System.out.print("  " + getName());
        System.out.println("");
    }

}



