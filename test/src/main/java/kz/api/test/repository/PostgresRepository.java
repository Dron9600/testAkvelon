package kz.api.test.repository;

import com.sun.istack.NotNull;
import kz.api.test.model.ProjectRecord;
import org.springframework.data.repository.CrudRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class PostgresRepository implements CrudRepository<ProjectRecord, Long> {

    String url = "jdbc:postgresql://localhost:5432/akvelonTest";
    String user = "postgres";
    String password = "postgres";
    Connection connection;

    public void update(ProjectRecord projectRecord) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String updateSQL = "UPDATE projects SET name=(?), description=(?) WHERE id=(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, projectRecord.getName());
            preparedStatement.setLong(3, projectRecord.getId());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <S extends ProjectRecord> S save(S entity) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String insertSQL = "INSERT INTO projects(id, name, description) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.executeUpdate();
            return entity;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <S extends ProjectRecord> Iterable<S> saveAll(Iterable<S> entities) {
        System.out.println("attempt to add to DB the project records: " + entities.toString());
        return null;
    }

    @Override
    public Optional<ProjectRecord> findById(Long aLong) {
        System.out.println("attempt to find at DB the project record with id: " + aLong);
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        System.out.println("attempt to check DB for the project record presence with id: " + aLong);
        return false;
    }

    @Override
    public Iterable<ProjectRecord> findAll() {
        System.out.println("attempt to select from DB all project records");
        return null;
    }

    @Override
    public Iterable<ProjectRecord> findAllById(Iterable<Long> longs) {
        System.out.println("attempt to select from DB all project records with id: " + longs.toString());
        return null;
    }

    @Override
    public long count() {
        System.out.println("attempt to cont all records number");
        return 0;
    }

    @Override
    public void deleteById(@NotNull Long aLong) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String deleteSQL = "DELETE FROM projects WHERE id=(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("attempt to remove from DB the project record with id: " + aLong);
    }

    @Override
    public void delete(ProjectRecord entity) {
        System.out.println("attempt to from DB project record: " + entity.toString());
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        System.out.println("attempt to remove from DB all project records with ids: " + longs.toString());
    }

    @Override
    public void deleteAll(Iterable<? extends ProjectRecord> entities) {
        System.out.println("attempt to remove from DB all project records: " + entities.toString());
    }

    @Override
    public void deleteAll() {
        System.out.println("attempt to remove from DB all project records");
    }

}
