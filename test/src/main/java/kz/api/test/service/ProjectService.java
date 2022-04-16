package kz.api.test.service;

import kz.api.test.model.ProjectRecord;
import kz.api.test.repository.MockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private final CrudRepository<ProjectRecord, Long> mockRepository; // For real postgres db connection use PostgresRepository class

    public ProjectService(MockRepository projectRepository) {
        this.mockRepository = projectRepository;
    }

    public List<ProjectRecord> getAllProjects() {
        List<ProjectRecord>projectRecords = new ArrayList<>();
        mockRepository.findAll().forEach(projectRecords::add);
        return projectRecords;
    }

    public void addProject(ProjectRecord projectRecord) {
        mockRepository.save( projectRecord);
    }

    public void deleteProject(Long id) {
        mockRepository.deleteById(id);
    }

    public ProjectRecord getProjectById(Long id) {
        return mockRepository.findById(id).orElse(null);
    }

    public void saveOrUpdate(ProjectRecord projectRecord) {
        mockRepository.save(projectRecord);
    }

}