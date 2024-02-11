/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.utility;

import lombok.extern.log4j.Log4j2;
import pl.ttpsc.javaupdate.project.model.CompanyUser;

import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Log4j2
public class Menu implements MenuComponent {
    List<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
    String name;
    CompanyUser user;

    public Menu(String name) {
        this.name = name;
        this.user = user;

    }

    public CompanyUser getUser() {
        return user;
    }

    public void setUser(CompanyUser user) {
        this.user = user;
    }

    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }

    public String getName() {
        return name;
    }

    public void getNameWithSeparator() {
        System.out.println("\n" + getName());
        System.out.println("---------------------");
    }

    private void printMenuComponents() {
        for (MenuComponent menuComponent : menuComponents) {
            System.out.println((menuComponents.indexOf(menuComponent) + 1) + "." + menuComponent.getName());
        }
        System.out.println("\nEnter your choice: ");
    }

    public void chooseOption(Scanner scanner) {
        Menu mainMenu = this;
        while (true) {
            mainMenu.getNameWithSeparator();
            mainMenu.printMenuComponents();
            try {
                int choice = scanner.nextInt();

                MenuComponent selectedOption = mainMenu.getChild(choice - 1);
                if (selectedOption instanceof Menu) {
                    mainMenu = (Menu) selectedOption;
                } else if (selectedOption.getName().equals("Back to main menu")) {
                    mainMenu = this;
                } else {
                    selectedOption.execute();
                }
            } catch (InputMismatchException e) {
                log.info("Invalid choice. Please enter a valid number.");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                log.info("Invalid choice. Please enter a valid number.");
            }
        }
    }
}