/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.action;


import pl.ttpsc.javaupdate.project.model.Role;

import java.util.List;

public interface Action {
    String getName();
    void execute();
    List<Role> getAllowedRoles();
}
