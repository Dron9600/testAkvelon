package kz.api.test.repository;

import kz.api.test.model.ProjectRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MockRepository implements CrudRepository<ProjectRecord, Long> {

    public void update(ProjectRecord projectRecord) {
        System.out.println("attempt to update in DB the proje§ct record: " + projectRecord.toString());
    }


    @Override
    public <S extends ProjectRecord> S save(S entity) {
        System.out.println("attempt to add to DB the project record: " + entity.toString());
        return null;
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
    public void deleteById(Long aLong) {
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
