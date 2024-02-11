/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.repository;

import lombok.extern.log4j.Log4j2;
import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlService;

import java.util.List;


public class ProjectRolesRepository {
    public ProjectRolesRepository() {
    }
    public void give(Long projectId, Long userId, List<Role> roles)  {
        SqlService.saveProjectRoles(projectId,userId,roles);
    }
    public List<Role> find(CompanyUser user, Long projectId){
        return SqlService.getUserRolesOnProject(user, projectId);
    }
}
