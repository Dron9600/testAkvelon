package kz.api.test.service;

import kz.api.test.model.ProjectRecord;
import kz.api.test.repository.MockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private final MockRepository projectRepository; // For real postgres db connection use PostgresRepository class

    public ProjectService(MockRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectRecord> getAllProjects() {
        List<ProjectRecord>projectRecords = new ArrayList<>();
        projectRepository.findAll().forEach(projectRecords::add);
        return projectRecords;
    }

    public void addProject(ProjectRecord projectRecord) {
        projectRepository.save( projectRecord);
    }


    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public ProjectRecord getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void saveOrUpdate(ProjectRecord projectRecord) {
        projectRepository.save(projectRecord);
    }

}