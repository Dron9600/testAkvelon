package kz.api.test.repository;

import kz.api.test.model.ProjectRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MockRepository implements CrudRepository<ProjectRecord, Long> {

    private final Map<Long, ProjectRecord> localCache = new HashMap<>();

    public void update(ProjectRecord projectRecord) {
        System.out.println("attempt to update in DB the proje§ct record: " + projectRecord.toString());
        localCache.put(projectRecord.getId(), projectRecord);
    }


    @Override
    public <S extends ProjectRecord> S save(S entity) {
        System.out.println("attempt to add to DB the project record: " + entity.toString());
        localCache.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends ProjectRecord> Iterable<S> saveAll(Iterable<S> entities) {
        System.out.println("attempt to add to DB the project records: " + entities.toString());
        Map<Long, S> mapForAdd = new HashMap<>();
        entities.forEach(entity -> mapForAdd.put(entity.getId(), entity));
        localCache.putAll(mapForAdd);
        return entities;
    }

    @Override
    public Optional<ProjectRecord> findById(Long aLong) {
        System.out.println("attempt to find at DB the project record with id: " + aLong);
        return Optional.of(localCache.get(aLong));
    }

    @Override
    public boolean existsById(Long aLong) {
        System.out.println("attempt to check DB for the project record presence with id: " + aLong);
        return localCache.containsKey(aLong);
    }

    @Override
    public Iterable<ProjectRecord> findAll() {
        System.out.println("attempt to select from DB all project records");
        return localCache.values();
    }

    @Override
    public Iterable<ProjectRecord> findAllById(Iterable<Long> longs) {
        System.out.println("attempt to select from DB all project records with id: " + longs.toString());
        // TODO implement me
        return null;
    }

    @Override
    public long count() {
        System.out.println("attempt to cont all records number");
        return localCache.size();
    }

    @Override
    public void deleteById(Long aLong) {
        System.out.println("attempt to remove from DB the project record with id: " + aLong);
        localCache.remove(aLong);
    }

    @Override
    public void delete(ProjectRecord entity) {
        System.out.println("attempt to from DB project record: " + entity.toString());
        localCache.remove(entity.getId(), entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        System.out.println("attempt to remove from DB all project records with ids: " + longs.toString());
        longs.forEach(localCache::remove);
    }

    @Override
    public void deleteAll(Iterable<? extends ProjectRecord> entities) {
        System.out.println("attempt to remove from DB all project records: " + entities.toString());
        entities.forEach(entity -> localCache.remove(entity.getId(), entity));
    }

    @Override
    public void deleteAll() {
        System.out.println("attempt to remove from DB all project records");
        localCache.clear();
    }
}
