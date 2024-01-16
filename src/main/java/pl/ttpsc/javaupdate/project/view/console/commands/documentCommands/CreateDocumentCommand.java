package pl.ttpsc.javaupdate.project.view.console.commands.documentCommands;

import pl.ttpsc.javaupdate.project.utility.MenuComponent;

public class CreateDocumentCommand implements MenuComponent {
    String name;

    public CreateDocumentCommand(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void execute() {

        System.out.println("Document added");
    }

    public void getNameWithSeparator() {
        System.out.print("  " + getName());
        System.out.println("");
    }

}



