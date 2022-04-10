package kz.api.test.service;

import kz.api.test.model.ProjectRecord;
import kz.api.test.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProjectService
{


    @Autowired
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public List<ProjectRecord> getAllProjects()
    {
        List<ProjectRecord>projectRecords = new ArrayList<>();
        projectRepository.findAll().forEach(projectRecords::add);
        return projectRecords;
    }
//    public List<ProjectRecord> getAllProjects()
//    {
//        List<ProjectRecord>projectRecords = new ArrayList<>();
//        projectRepository.findAll().forEach(projectRecords::add);
//        return projectRecords;
//    }

//    public void addProject(List<ProjectRecord> projectRecord){
//        projectRepository.save(projectRecord);
//    }


    public void addProject(List<ProjectRecord> projectRecord){
        projectRepository.saveAll( projectRecord);
    }


    public void deleteProject(String id) {
        projectRepository.deleteById(id);
    }

    public ProjectRecord getProjectById(String id){
        return projectRepository.findById(id).get();
    }

    public void saveOrUpdate(ProjectRecord projectRecord){
        projectRepository.save(projectRecord);
    }
}