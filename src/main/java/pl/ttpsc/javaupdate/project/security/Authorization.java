/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.security;

import lombok.extern.log4j.Log4j2;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.ProjectRolesRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static pl.ttpsc.javaupdate.project.persistence.sql.SqlService.getConnection;
@Log4j2
public class Authorization {

    public static boolean isValidLogin(String username, String password) {
        try (Connection connection = getConnection()) {
            String query = "SELECT COUNT(*) FROM companyuser WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            e.printStackTrace();

        }
        return false;
    }
    public static boolean isUserAuthorizedToPerformAction(CompanyUser user, Long projectId, Config config, List<Role> roles) {
        ProjectRolesRepository projectRolesRepository = config.getProjectRolesRepository();
        List<Role> userRolesOnProject = projectRolesRepository.find(user, projectId);
        List<Role> commonRoles = new ArrayList<>(userRolesOnProject);
        commonRoles.retainAll(roles);
        return !commonRoles.isEmpty();
    }
    public static boolean isUserAdministrator(Long userId) {
        String query = "SELECT COUNT(*) FROM administrator WHERE user_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return false;
    }



}
