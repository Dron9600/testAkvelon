package kz.api.test.database;

import kz.api.test.model.ProjectRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreDb {
    String url = "jdbc:postgresql://localhost:5432/akvelonTest";
    String user = "postgres";
    String password = "postgres";
    Connection connection;

    public void addToDb(ProjectRecord projectRecord) {
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, user, password);
//            String insertSQL = "INSERT INTO projects(id, name, description) VALUES(?,?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
//
//            preparedStatement.setString(1, projectRecord.getId());
//            preparedStatement.setString(2, projectRecord.getName());
//            preparedStatement.setString(3, projectRecord.getDescription());
//
//            preparedStatement.executeUpdate();
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }

    }

    public void deleteFromDb(String id) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String deleteSQL = "DELETE FROM projects WHERE id=(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);

            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ProjectRecord projectRecord) {
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, user, password);
//            String updateSQL = "UPDATE projects SET name=(?), description=(?) WHERE id=(?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
//
//            preparedStatement.setString(1, projectRecord.getName());
//            preparedStatement.setString(2, projectRecord.getDescription());
//            preparedStatement.setString(3, projectRecord.getId());
//            preparedStatement.executeUpdate();
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
    }
}
