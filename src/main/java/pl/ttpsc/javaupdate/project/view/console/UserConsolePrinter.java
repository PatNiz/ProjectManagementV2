package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.User;

import java.util.Collection;
import java.util.Scanner;

public class UserConsolePrinter extends ConsolePrinter{
    private Scanner sc = new Scanner(System.in);
    public User getUserData() {
        printLine("Name");
        String firstName = sc.nextLine();
        return new User(firstName);
    }
    public void printUsers(Collection<User> users) {
        users.stream()
                .map(User::toString)
                .forEach(this::printLine);
    }
}
