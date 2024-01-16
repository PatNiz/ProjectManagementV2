package pl.ttpsc.javaupdate.project.view.console.commands.userCommands;

import pl.ttpsc.javaupdate.project.utility.MenuComponent;

public class DeleteUserCommand implements MenuComponent {
    String name;


    public DeleteUserCommand(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void execute() {
        System.out.println("User deleted");
    }

    public void getNameWithSeparator() {
        System.out.print("  " + getName());
        System.out.println("");
    }

}



