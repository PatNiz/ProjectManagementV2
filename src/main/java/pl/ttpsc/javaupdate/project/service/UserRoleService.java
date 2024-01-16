package pl.ttpsc.javaupdate.project.service;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.model.User;

import java.lang.reflect.Method;

public class UserRoleService {

   /* public void assignRoleToUser(User user, Project project, Role role) {
        if (user.getRoles().contains(Role.ADMINISTRATOR) && role != Role.ADMINISTRATOR) {
            System.out.println("Error: Administrator cannot have any other role assigned.");
        } else {
            user.addRole(role);
            project.addRole(role);
        }
    }
    // Metoda do wykonania akcji w zależności od roli użytkownika
    public void performAction(User user, String action) {
        try {
            Class<?> systemClass = Class.forName("System");
            Method method = systemClass.getMethod(action, User.class);

            // Sprawdzenie, czy użytkownik ma odpowiednią rolę do wykonania akcji
            if (isRoleAuthorized(user, action)) {
                method.invoke(this, user);
            } else {
                System.out.println("Error: Unauthorized access for action '" + action + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    //// Przykładowe użycie
    //        system.performAction(admin, "createUser");
    private boolean isRoleAuthorized(User user, String action) {
        // zakładam, że Administrator ma dostęp do wszystkich akcji
        return user.getRoles().contains(Role.ADMINISTRATOR);
    } */

}
