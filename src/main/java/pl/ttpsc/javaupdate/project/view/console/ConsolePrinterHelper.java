/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.Role;

import java.util.*;


public class ConsolePrinterHelper {

    private Scanner sc = new Scanner(System.in);
    public Long getElementId() {
        Long id = null;

        do {
            printLine("ID:");
            try {
                id = sc.nextLong();
            } catch (InputMismatchException e) {
                sc.nextLine();
                printLine("Invalid input. Please enter a valid ID.");
            }
        } while (id == null);
        return id;
    }

    public Long getProjectId() {
        System.out.print("Project ");
        return getElementId();
    }
    public Long getDocumentId() {
        System.out.print("Document ");
        return getElementId();
    }
    public void printProjectDetails(List<Map<String, Object>> projectDetails){
        for (Map<String, Object> detail : projectDetails) {
            System.out.println(detail);
        }
    }


    public String getUsername() {
        String username = null;
        do {
            printLine("Username:");
            username = sc.nextLine();
        } while (username == null || username.isEmpty());

        return username;
    }

    public Long getUserId() {
        Long userId = null;

        do {
            printLine("User id:");

            try {
                userId = sc.nextLong();
            } catch (InputMismatchException e) {
                sc.nextLine();
                printLine("Invalid input. Please enter a valid Long.");
            }
        } while (userId == null);

        return userId;
    }


    public Role getUserRole() {
        Role role = null;

        while (role == null) {
            printLine("Which role:");
            System.out.println("(1 - Manager, 2 - HR, 3 - Administrator, 4 - Engineer):");
            try {
                Integer choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        role = Role.MANAGER;
                        break;
                    case 2:
                        role = Role.HR;
                        break;
                    case 3:
                        role = Role.ADMINISTRATOR;
                        break;
                    case 4:
                        role = Role.ENGINEER;
                        break;
                    default:
                        printLine("Nieprawidłowy wybór roli. Spróbuj ponownie.");
                }
            }catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("Input mismatch");
            }
        }
        return role;
    }


    public String promptForInput(String message) {
        System.out.print(message + ": ");
        return sc.nextLine();
    }

    static void printLine(String text) {
        System.out.println(text);
    }
}
