/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.view;

import pl.ttpsc.javaupdate.project.model.CompanyUser;

import java.util.List;

public interface UserView {
    void display(List<CompanyUser> users);
    void display(CompanyUser user);
}
