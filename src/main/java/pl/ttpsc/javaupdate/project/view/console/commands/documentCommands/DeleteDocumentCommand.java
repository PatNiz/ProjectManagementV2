package pl.ttpsc.javaupdate.project.view.console.commands.documentCommands;


import pl.ttpsc.javaupdate.project.utility.MenuComponent;

public class DeleteDocumentCommand implements MenuComponent {
    String name;

    public DeleteDocumentCommand(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void execute() {
        System.out.println("Document deleted");
    }

    public void getNameWithSeparator() {
        System.out.print("  " + getName());
        System.out.println("");
    }

}




