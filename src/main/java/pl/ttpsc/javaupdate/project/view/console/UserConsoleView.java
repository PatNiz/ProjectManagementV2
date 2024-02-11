/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.view.UserView;
import java.util.List;
import java.util.Scanner;


public class UserConsoleView extends ConsolePrinterHelper implements UserView {

    private Scanner sc = new Scanner(System.in);
    @Override
    public void display(List<CompanyUser> users) {
        if (users.isEmpty()) {
            printLine("List is empty");
        } else {
            users.forEach(document -> printLine(document.toString()));
        }
    }
    @Override
    public void display(CompanyUser companyUser) {
        printLine(companyUser.toString());
    }

    public CompanyUser getUserData() {
        printLine("Name");
        String firstName = sc.nextLine();
        printLine("Password");
        String password = sc.nextLine();
        return new CompanyUser(firstName,password);
    }

}
