package kz.api.test.service;

import kz.api.test.model.ProjectRecord;
import kz.api.test.repository.PostgresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private final CrudRepository<ProjectRecord, Long> postgresRepository; // For real postgres db connection use PostgresRepository class

    public ProjectService(PostgresRepository projectRepository) {  ///
        this.postgresRepository = projectRepository;
    }

    public List<ProjectRecord> getAllProjects() {
        List<ProjectRecord>projectRecords = new ArrayList<>();
        postgresRepository.findAll().forEach(projectRecords::add);
        return projectRecords;
    }

    public void addProject(ProjectRecord projectRecord) {
        postgresRepository.save(projectRecord);
    }

    public void deleteProject(Long id) {
        postgresRepository.deleteById(id);
    }

    public ProjectRecord getProjectById(Long id) {
        return postgresRepository.findById(id).orElse(null);
    }

    public void saveOrUpdate(ProjectRecord projectRecord) {
        postgresRepository.save(projectRecord);
    }
    
    public void update(ProjectRecord projectRecord){
        postgresRepository.save(projectRecord);
    }

}