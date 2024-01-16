package pl.ttpsc.javaupdate.project.view.console.commands.documentCommands;


import pl.ttpsc.javaupdate.project.utility.MenuComponent;

public class GetListOfDocumentsCommand implements MenuComponent {
    String name;

    public GetListOfDocumentsCommand(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void execute() {
        System.out.println("List of documents");
    }

    public void getNameWithSeparator() {
        System.out.print("  " + getName());
        System.out.println("");
    }

}



