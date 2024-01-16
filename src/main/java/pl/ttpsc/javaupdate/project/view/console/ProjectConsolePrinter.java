package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.User;

import java.util.Collection;
import java.util.Scanner;

public class ProjectConsolePrinter extends ConsolePrinter {

        private Scanner sc = new Scanner(System.in);
        public Project getProjectData() {
            printLine("Name");
            String firstName = sc.nextLine();
            printLine("id");
            Integer id = sc.nextInt();
            return new Project(id,firstName);
        }
        public void printUsers(Collection<User> users) {
            users.stream()
                    .map(User::toString)
                    .forEach(this::printLine);
        }
    }
