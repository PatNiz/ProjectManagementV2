/**
 * Created By: Patryk Niziołek
 * Created in: 2022
 * Updated in: 2024
 */
package pl.ttpsc.javaupdate.project.persistence.sql;


import lombok.extern.log4j.Log4j2;
import pl.ttpsc.javaupdate.project.exception.IdGenerationException;
import pl.ttpsc.javaupdate.project.model.CompanyUser;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

@Log4j2
public class SqlService {
    private static final String CONFIG_FILE = "database.properties";
    protected static void insert(Persistable persistableObject) {
        try (Connection conn = getConnection()) {
            String tableName = persistableObject.getClass().getSimpleName().toLowerCase();
            StringBuilder columnNames = new StringBuilder();
            StringBuilder placeholders = new StringBuilder();
            List<Object> values = new ArrayList<>();
            for (Field field : persistableObject.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                columnNames.append(field.getName()).append(", ");
                placeholders.append("?, ");
                values.add(field.get(persistableObject));

            }
            columnNames.setLength(columnNames.length() - 2);
            placeholders.setLength(placeholders.length() - 2);

            String sql = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + placeholders + ")";
            log.info(sql);

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                for (int i = 0; i < values.size(); i++) {
                    if (values.get(i) instanceof Object[]) {
                        statement.setArray(i + 1, conn.createArrayOf("VARCHAR", (Object[]) values.get(i)));
                    } else {
                        statement.setObject(i + 1, values.get(i));
                    }
                }
                statement.executeUpdate();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    // Helper method to generate ID

    public static <T extends Persistable> List<T> find(QuerySpec querySpec) {
        List<T> entities = new ArrayList<>();
        Class<T> entityType = querySpec.getEntityType();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String selectQuery = querySpec.createSQLSelectQuery();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                T entity = entityType.getDeclaredConstructor().newInstance();
                Field[] fields = entityType.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object fieldValue = resultSet.getObject(fieldName);
                    field.set(entity, fieldValue);
                }

                entities.add(entity);
            }
        } catch (SQLException | ReflectiveOperationException e) {
            e.printStackTrace();

        }

        return entities;
    }
    public static void update(Persistable persistable) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            Class<?> clazz = persistable.getClass();
            String tableName = clazz.getSimpleName();
            Field[] fields = clazz.getDeclaredFields();
            StringBuilder updateQuery = new StringBuilder("UPDATE " + tableName + " SET ");
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue;
                try {
                    fieldValue = field.get(persistable);
                    updateQuery.append(fieldName).append(" = '").append(fieldValue).append("', ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            updateQuery.deleteCharAt(updateQuery.length() - 2);
            updateQuery.append(" WHERE id = ").append(persistable.getId());
            statement.executeUpdate(updateQuery.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
    protected static void delete(Persistable persistable) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            Class<?> clazz =persistable.getClass();
            String tableName = clazz.getSimpleName();
            String deleteQuery = "DELETE FROM " + tableName + " WHERE id = " + persistable.getId();
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public static Long generateId(Persistable persistable) {
        Class<?> clazz = persistable.getClass();
        String tableName = clazz.getSimpleName();
        String sql = "SELECT COALESCE(MAX(id), 0) + 1 FROM " + tableName;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                try {
                    Field idField = clazz.getDeclaredField("id");
                    idField.setAccessible(true);
                    Long generatedId = resultSet.getLong(1);
                    idField.set(persistable, generatedId);
                    return generatedId;
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new IdGenerationException("Failed to generate ID for table: " + tableName, e);
                }
            } else {
                throw new RuntimeException("Failed to generate ID for table: " + tableName);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new IdGenerationException("Error while generating ID", e);
        }
    }
    public static List<Role> getUserRolesOnProject(CompanyUser user, Long projectId) {
        List<Role> roles = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT role FROM user_roles WHERE user_id = ? AND project_id = ?")) {

            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, projectId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String roleName = resultSet.getString("role");
                    roles.add(Role.valueOf(roleName));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        return roles.isEmpty() ? Collections.EMPTY_LIST : roles;
    }
    public static void saveProjectRoles(Long projectId, Long userId, List<Role> roles)  {
        String insertRolesQuery = "INSERT INTO user_roles (project_id, user_id, role) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRolesQuery)) {
            for (Role role : roles) {
                preparedStatement.setLong(1, projectId);
                preparedStatement.setLong(2, userId);
                preparedStatement.setString(3, role.name());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
    public static void giveAdministratorRole(Long userId) {
        String insertAdministratorQuery = "INSERT INTO administrator (user_id) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertAdministratorQuery)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            log.info("Administrator role saved successfully for user with ID: " + userId);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error saving administrator role.");
        }
    }

    public static List<Map<String, Object>> getProjectDetails(long projectId) {
        List<Map<String, Object>> projectDetails = new ArrayList<>();

        try (Connection connection = getConnection()) {
            // Zapytanie SQL
            String sql = "SELECT pr.id AS project_id, pr.name AS project_name, ur.user_id, cu.username, ur.role " +
                    "FROM user_roles ur " +
                    "JOIN project pr ON ur.project_id = pr.id " +
                    "JOIN companyuser cu ON ur.user_id = cu.id " +
                    "WHERE ur.project_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, projectId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Przetwarzanie wyników
                    while (resultSet.next()) {
                        Map<String, Object> detail = new HashMap<>();
                        detail.put("project_id", resultSet.getLong("project_id"));
                        detail.put("project_name", resultSet.getString("project_name"));
                        detail.put("user_id", resultSet.getLong("user_id"));
                        detail.put("username", resultSet.getString("username"));
                        detail.put("role", resultSet.getString("role"));

                        projectDetails.add(detail);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectDetails;
    }
    public static Connection getConnection() throws SQLException {

        Properties connectionProperties = new Properties();

        try (InputStream inputStream = SqlService.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            connectionProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String host = connectionProperties.getProperty("db.host");
        String port = connectionProperties.getProperty("db.port");
        String database = connectionProperties.getProperty("db.database");
        String username = connectionProperties.getProperty("db.username");
        String password = connectionProperties.getProperty("db.password");

        String connectionUrl = "jdbc:postgresql://" + host + ":" + port + "/" + database + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        return DriverManager.getConnection(connectionUrl, username, password);
    }
}