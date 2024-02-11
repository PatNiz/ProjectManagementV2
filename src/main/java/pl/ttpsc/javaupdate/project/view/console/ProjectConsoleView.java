/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.view.ProjectView;
import java.util.List;
import java.util.Scanner;

public class ProjectConsoleView extends ConsolePrinterHelper implements ProjectView {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void display(List<Project> projects) {
        if (projects.isEmpty()) {
            printLine("List is empty");
        } else {
            projects.forEach(document -> printLine(document.toString()));
        }
    }

    @Override
    public void display(Project project) {
    printLine(project.toString());
    }
    public Project getProjectData() {
        printLine("NAME:");
        String projectName = sc.nextLine();
        return new Project(projectName);
    }
}
