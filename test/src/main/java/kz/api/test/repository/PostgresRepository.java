package kz.api.test.repository;

import com.sun.istack.NotNull;
import kz.api.test.TestApplication;
import kz.api.test.model.ProjectRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PostgresRepository implements CrudRepository<ProjectRecord, Long> {

    TestApplication testApplication = new TestApplication();
    String url = testApplication.getUrl();
    String user = testApplication.getUser();
    String password = testApplication.getPassword();
    Connection connection;


    public PostgresRepository() {
    }

    private final Map<Long, ProjectRecord> localCache = new HashMap<>();

    public void update(ProjectRecord projectRecord) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            String updateSQL = "UPDATE projects SET name=(?), description=(?) WHERE id=(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, projectRecord.getName());
//            preparedStatement.setString(2, projectRecord.getInformation().getStatus());
            preparedStatement.setLong(3, projectRecord.getId());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String name){
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
//            String updateSQL = "CREATE TABLE (?)" +
//                    "(personId int," +
//                    "lastN varchar(255)" +
//                    ");";
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
//            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println("successful test " + name );
    }
    
    @Override
    public <S extends ProjectRecord> S save(S entity) {
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, user, password);
//            String insertSQL = "INSERT INTO projects(id, name, description) VALUES(?,?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
//            preparedStatement.setLong(1, entity.getId());
//            preparedStatement.setString(2, entity.getName());
//            preparedStatement.setString(3, entity.getDescription().getStatus());
//            preparedStatement.executeUpdate();
//            return entity;
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        System.out.println(" complete save ");
        localCache.put(entity.getId(), entity);
        System.out.println(" it is data of the sooodffdf " + entity.getProjectInformation().getCompletionDate());
        System.out.println(" value of the enum " + entity.getTaskInformation().getTaskStatus().getCode());
        System.out.println(" value of the second enum " + entity.getProjectInformation().getCurrentStatus().getCode());
        System.out.println(" url " + url + " " + password + " " + user);
        return entity;
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
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, user, password);
//            String deleteSQL = "DELETE FROM projects WHERE id=(?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
//            preparedStatement.setLong(1, aLong);
//            preparedStatement.executeUpdate();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
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
