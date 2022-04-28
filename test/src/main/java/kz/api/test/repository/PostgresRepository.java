package kz.api.test.repository;

import com.sun.istack.NotNull;
import kz.api.test.TestApplication;
import kz.api.test.model.ProjectRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PostgresRepository implements CrudRepository<ProjectRecord, Long> {

    TestApplication testApplication = new TestApplication();
    String url = testApplication.getUrl();
    String user = testApplication.getUser();
    String password = testApplication.getPassword();
    String tableName = testApplication.getTableName();
    Connection connection;


    public PostgresRepository() {
    }

    private final Map<Long, ProjectRecord> localCache = new HashMap<>();


    public void createTable(String name){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String updateSQL = "CREATE TABLE " + name +
                    "\t(id bigint,\n" +
                    "\t name varchar(255),\n" +
                    "\t description varchar(255),\n" +
                    "\t projectName varchar(255),\n" +
                    "\t projectStart varchar(255),\n" +
                    "\t completionDate varchar(255),\n" +
                    "\t currentStatus varchar(255),\n" +
                    "\t priority int,\n" +
                    "\t taskName varchar(255),\n" +
                    "\t taskStatus varchar(255),\n" +
                    "\t taskDescription varchar(255),\n" +
                    "\t taskPriority int\n" +
                    "\t);";

            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("creating tab  " + name );
    }
    @Override
    public <S extends ProjectRecord> S save(S entity) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String insertSQL = "INSERT INTO "  +tableName +
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setString(4,entity.getProjectInformation().getProjectName());
            preparedStatement.setString(5, entity.getProjectInformation().getProjectStart());
            preparedStatement.setString(6,entity.getProjectInformation().getCompletionDate());
            preparedStatement.setString(7, entity.getProjectInformation().getCurrentStatus().getCode());
            preparedStatement.setInt(8, entity.getProjectInformation().getPriority());
            preparedStatement.setString(9, entity.getTaskInformation().getTaskName());
            preparedStatement.setString(10, entity.getTaskInformation().getTaskStatus().getCode());
            preparedStatement.setString(11, entity.getTaskInformation().getTaskDescription());
            preparedStatement.setInt(12, entity.getTaskInformation().getTaskPriority());
            preparedStatement.executeUpdate();
            localCache.put(entity.getId(), entity);
            return entity;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
//        System.out.println(" complete save ");
//        return entity;
    }

    @Override
    public <S extends ProjectRecord> Iterable<S> saveAll(Iterable<S> entities) {
        System.out.println("attempt to add to DB the project records: ");

        Map<Long, S> mapForAdd = new HashMap<>();
        entities.forEach(entity -> mapForAdd.put(entity.getId(), entity));
        localCache.putAll(mapForAdd);
        return null;
    }

    @Override
    public Optional<ProjectRecord> findById(Long aLong) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String deleteSQL = "SELECT * FROM "+ tableName +
                    " WHERE id=(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setLong(1, aLong);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                String projectName = resultSet.getString(4);
                String projectStart = resultSet.getString(5);
                String completionDate = resultSet.getString(6);
                String currentStatus = resultSet.getString(7);
                int priority =  resultSet.getInt(8);
                String taskName = resultSet.getString(9);
                String taskStatus = resultSet.getString(10);
                String taskDescription = resultSet.getString(11);
                int taskPriority = resultSet.getInt(12);
                System.out.println(" id= " + id + " name= " + name
                        + " description= " + description
                + " projectName= " + projectName
                + " projectStart= " + projectStart
                + " completionDate= " + completionDate
                + " currentStatus= " + currentStatus
                + " priority= " + priority
                + " taskName= " + taskName
                + " taskStatus= " + taskStatus
                + " taskDescription= " + taskDescription
                + " taskPriority= " + taskPriority);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("attempt to find at DB the project record with id: " + aLong);
        return Optional.of(localCache.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        System.out.println("attempt to check DB for the project record presence with id: " + aLong);
        return false;
    }

    @Override
    public Iterable<ProjectRecord> findAll() {
        System.out.println("attempt to select from DB all project records");
        return localCache.values();
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
            String deleteSQL = "DELETE FROM "+ tableName +
                    " WHERE id=(?)";
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
