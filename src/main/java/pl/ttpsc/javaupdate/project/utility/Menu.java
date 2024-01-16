package pl.ttpsc.javaupdate.project.utility;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Menu implements MenuComponent {
    List<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
    String name;

    public Menu(String name) {
        this.name = name;

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
                System.out.println("Invalid choice. Please enter a valid number.");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
    }
}